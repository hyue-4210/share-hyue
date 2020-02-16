package com.powerlong.sharera.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.powerlong.sharera.ApplicationEnties.YumPaperVo;
import com.powerlong.sharera.result.CheckTokenException;
import com.powerlong.sharera.result.ResponseInfoEnum;
import com.powerlong.sharera.util.HttpUtils;
import com.powerlong.sharera.util.PowerLongBaolong;
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

import javax.servlet.http.HttpServletRequest;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: ABC
 * Date: 2018/6/13
 * Time: 2:32 PM
 */
@Aspect
@Order(-99)
@Component
public class CheckUserTokenAspect {
    private static final Logger logger = LoggerFactory.getLogger(CheckUserTokenAspect.class);

    @Autowired
    YumPaperVo yunPapers;

    @Pointcut("@annotation(com.powerlong.sharera.aspect.CheckUserToken)")
    public void checkTokenAnnotation() {

    }

    @Before("checkTokenAnnotation()")
    public void doBeforeGet(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uid = request.getHeader("uid");
        String token = request.getHeader("token");

        if (StringUtils.isBlank(uid) || StringUtils.isBlank(token)) {
            throw new CheckTokenException(ResponseInfoEnum.CODE_ERR_MISSING_HEADER);
        }

        //调取宝龙api验证小程序token是否合法
        try {
            TreeMap<String, String> headers = PowerLongBaolong.getheaders();
            headers.put("token", token);
            headers.put("uid", uid);
            logger.debug("token----------:"+token);
            logger.debug("uid----------:"+uid);
            JSONObject jsonObject = HttpUtils.postJsonAndHeader(yunPapers.getBasicurl() + "/authentication/smallroutine","", headers);
            logger.debug("url----------:"+yunPapers.getBasicurl());
            logger.debug("jsonObject----------:"+jsonObject.toJSONString());
            if(!"200".equals(jsonObject.getString("status"))){
                throw new CheckTokenException(ResponseInfoEnum.TOKEN_INCORRECT_OR_HAS_EXPIRED);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new CheckTokenException(ResponseInfoEnum.CODE_ERR_SERVER);
        }




    }

}
