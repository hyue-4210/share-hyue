package com.powerlong.sharera.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class goThirdUrlVo {
    private String id;
    private String thirdUrl;
    private String appId;
    private String mobile;
    private String memberlevel;
    private String memberleveName;
    private String validPoints;
    private String baolongId;
    private String projectId;
    private Integer type;
    private String openId;
    private String device_id;
    private String uid;
}
