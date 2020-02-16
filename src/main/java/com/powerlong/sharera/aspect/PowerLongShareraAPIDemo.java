package com.powerlong.sharera.aspect;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TreeMap;

public class PowerLongShareraAPIDemo {

    private  final static Charset UTF8 = StandardCharsets.UTF_8;
    private final static String SECRET_ID = "J5yKBZrbPx3EXspn7QAKIDz8k4WFkmLAMPLE";
    private final static String SECRET_KEY = "Npq86cxGAR8joQYd9Gu5t9CN3EXAMPLE";
    private  final static String CT_JSON = "application/json; charset=utf-8";

    public static byte[] hmac256(byte[] key, String msg) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
        mac.init(secretKeySpec);         return mac.doFinal(msg.getBytes(UTF8));
    }
    public static String sha256Hex(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes(UTF8));
        return DatatypeConverter.printHexBinary(d).toLowerCase();
    }
    public static void main(String[] args) throws Exception {
        //service为共享平台提供,秘钥类型
        String version = "v1.0";
        String algorithm = "TC3-HMAC-SHA256";
        //时间戳动态变化
        String timestamp = "1574222508";
        //        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 注意时区，否则容易易出错
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = sdf.format(new Date(Long.valueOf(timestamp + "000")));

        // ************* 步骤 1：拼接规范请求串串 *************
        //httpRequestMethod 根据实时方式动态变化
        String httpRequestMethod = "GET";
        //canonicalUri 根据实时接口名称动态变化
        String canonicalUri = "/memberLevel";
        String canonicalQueryString = "mobile=13819108992&projectID=6D7E1C7AFAFB43E986670A81CF444233";
        String canonicalHeaders = "content-type:application/x-www-form-urlencoded; charset=utf-8\n";
        String signedHeaders = "content-type";
        /**
         * GET请求时 payload="";
         * POST请求时为正文内容
         *   payload 根据实时请求参数动态变化
         */


        String payload = "";
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
        String signature = DatatypeConverter.printHexBinary(hmac256(secretSigning, stringToSign)).toLowerCase();
        System.out.println(signature);
        // ************* 步骤 4：拼接 Authorization *************
        String authorization = algorithm + " " + "Credential=" + SECRET_ID + ", "
                + "SignedHeaders=" + signedHeaders + ", " + "Signature=" + signature;
        System.out.println(authorization);
        System.out.println("\n");
        TreeMap<String, String> headers = new TreeMap<String, String>();
        headers.put("Authorization", authorization);
        headers.put("Content-Type", CT_JSON);
        headers.put("X-PLS-Timestamp", timestamp);
        headers.put("X-PLS-Version", version);
        StringBuilder sb = new StringBuilder();
        sb.append("curl -X POST https://")
                .append(" -H \"Authorization: ").append(authorization).append("\"")
                .append(" -H \"Content-Type: application/json; charset=utf-8\"")
                .append(" -H \"Host: ").append("\"")
                .append(" -H \"X-PLS-Timestamp: ").append(timestamp).append("\"")
                .append(" -H \"X-PLS-Version: ").append(version).append("\"")
                .append(" -d '").append(payload).append("'");
        System.out.println(sb.toString());

    }

}