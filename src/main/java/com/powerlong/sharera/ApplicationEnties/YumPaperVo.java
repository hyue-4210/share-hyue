package com.powerlong.sharera.ApplicationEnties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "yzmsg")
@ToString
@Data
public class YumPaperVo {
    private Map<String, String> mapmsg;
    private String url;
    private String secret_key;
    private String basicurl;
    private String pandaRuleUrl;
    private String lmbRuleUrl;
    private String lmbgetproid;
    private String lmbtoken;
    private String lmburl1;
    private String lmburl2;

    private String ldappId;
    private String ldSecretKey;
    private String ldurl;
}
