package com.powerlong.sharera.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.powerlong.sharera.dao.KeyPairMapper;
import com.powerlong.sharera.result.CheckTokenException;
import com.powerlong.sharera.result.ResponseInfoEnum;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.apache.commons.io.IOUtils;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: ABC
 * Date: 2018/6/13
 * Time: 2:32 PM
 */
@Aspect
@Order(-99)
@Component
public class CheckThirdPartyAspect {
    private static final Logger logger = LoggerFactory.getLogger(CheckThirdPartyAspect.class);
    private final static Charset UTF8 = StandardCharsets.UTF_8;

    @Autowired
    private KeyPairMapper keyPairMapper;

    @Pointcut("@annotation(com.powerlong.sharera.aspect.CheckThirdParty)")
    public void checkTokenAnnotation() {

    }


    @Before("checkTokenAnnotation()")
    public  void doBeforeGet(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 记录下请求内容
        logger.info("canonicalUri : " + request.getRequestURI().toString());
        logger.info("httpRequestMethod : " + request.getMethod());
        String body="";
        InputStream io = null;
        io=request.getInputStream();
        body= IOUtils.toString(io);
        logger.info("payload：" + body);

        logger.info("canonicalHeaders：" + request.getContentType());

        String algorithm = "TC3-HMAC-SHA256";
        logger.info("algorithm:"+algorithm);
        String timestamp = request.getHeader("X-PLS-Timestamp");
        logger.info("timestamp:"+timestamp);
        String authorization = request.getHeader("Authorization");
        logger.info("authorization:"+authorization);

        if (StringUtils.isBlank(algorithm)|| StringUtils.isBlank(timestamp)|| StringUtils.isBlank(authorization)) {
            throw new CheckTokenException(ResponseInfoEnum.CODE_ERR_MISSING_HEADER);
        }
        String serverid=null;
        try{
             serverid=authorization.substring(authorization.indexOf("Credential=")+11, authorization.indexOf(","));
        }catch (Exception e){
            throw new CheckTokenException(ResponseInfoEnum.CODE_ERR_INVALID_SECRETID);
        }

        if(serverid==null){
            System.out.println("密钥不存在");
            throw new CheckTokenException(ResponseInfoEnum.CODE_ERR_SECRETID_NOT_FOUND);
        }

        String[] substrings =null;
        String signature = null;
        try{
            substrings =authorization.split("Signature=");
        }catch (Exception e){
            throw new CheckTokenException(ResponseInfoEnum.CODE_ERR_INVALID_SECRETID);
        }

        if(substrings.length>1){
            signature=substrings[1];
        }else{
            System.out.println("签名不合法");
            throw new CheckTokenException(ResponseInfoEnum.CODE_ERR_INVALID_SECRETID);
        }

        //判断签名是否过期，5分钟


        String SECRET_KEY=keyPairMapper.queryServerKey(serverid);
        if(SECRET_KEY==null){
            System.out.println("密钥不存在");
            throw new CheckTokenException(ResponseInfoEnum.CODE_ERR_SECRETID_NOT_FOUND);
        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 注意时区，否则容易出错
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = sdf.format(new Date(Long.valueOf(timestamp + "000")));

        // ************* 步骤 1：拼接规范请求串串 *************
        String httpRequestMethod = request.getMethod();
        String canonicalUri = request.getRequestURI().toString();
        String canonicalQueryString = "";
        if("GET".equals(httpRequestMethod)){
            if(request.getQueryString()!=null){
                try {
                    String cs= URLDecoder.decode(request.getQueryString(),"utf-8");//将中文转码
                    canonicalQueryString=cs;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        String canonicalHeaders="";
        if( "POST".equals(httpRequestMethod)){
            canonicalHeaders = "content-type:application/json; charset=utf-8\n";
        }else{
            canonicalHeaders = "content-type:application/x-www-form-urlencoded; charset=utf-8\n";
        }

        String signedHeaders = "content-type";
        String payload = body;
        System.out.println("payload:"+payload);
        String hashedRequestPayload = sha256Hex(payload);
        String canonicalRequest = httpRequestMethod + "\n" + canonicalUri + "\n" + canonicalQueryString + "\n"
                + canonicalHeaders + "\n" + signedHeaders + "\n" + hashedRequestPayload;
        System.out.println(canonicalRequest);
        System.out.println("\n");

        // ************* 步骤 2：拼接待签名字符串串 *************
        String hashedCanonicalRequest = sha256Hex(canonicalRequest);
        String stringToSign = algorithm + "\n" + timestamp + "\n" + hashedCanonicalRequest;
        System.out.println(stringToSign);

        // ************* 步骤 3：计算签名 *************
        byte[] secretDate = hmac256(("PLS1" + SECRET_KEY).getBytes(UTF8), date);
        byte[] secretSigning = hmac256(secretDate, "pls1_request");
        System.out.println(signature);
        //签名
        String signature1 = DatatypeConverter.printHexBinary(hmac256(secretSigning, stringToSign)).toLowerCase();
        System.out.println(signature1);
        if(!signature.equals(signature1)){
            System.out.println("签名失败");
            throw new CheckTokenException(ResponseInfoEnum.CODE_ERR_SIGNATURE_FAILURE);
        }


    }

    public static byte[] hmac256(byte[] key, String msg) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
        mac.init(secretKeySpec);
        return mac.doFinal(msg.getBytes(UTF8));
    }

    public static String sha256Hex(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes(UTF8));
        return DatatypeConverter.printHexBinary(d).toLowerCase();
    }

}
