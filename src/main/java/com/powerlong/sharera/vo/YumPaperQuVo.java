package com.powerlong.sharera.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class YumPaperQuVo {
    private String openId;
    private String uid;
    private String sn;
    private String projectId;
    private String type;
    private String level;
    private String levelName;
    private String mobile;
}
