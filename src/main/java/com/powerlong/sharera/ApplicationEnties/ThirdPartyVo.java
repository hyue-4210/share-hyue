package com.powerlong.sharera.ApplicationEnties;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: sharera
 * @description:
 * @author: liu.j
 * @create: 2019-12-11 13:55
 **/

@Component
@Data
public class ThirdPartyVo {

    @Value("${ThirdParty.bl.SECRET_ID}")
    private String SECRET_ID;
    @Value("${ThirdParty.bl.SECRET_KEY}")
    private String SECRET_KEY;

    private static String SECRETID;

    private static String SECRETKEY;

    @PostConstruct
    public void setSECRET(){
        SECRETID=this.SECRET_ID;
        SECRETKEY=this.SECRET_KEY;
    }

    public static String getSECRETID(){
        return SECRETID;
    }

    public static String getSECRETKEY(){
        return SECRETKEY;
    }

}

