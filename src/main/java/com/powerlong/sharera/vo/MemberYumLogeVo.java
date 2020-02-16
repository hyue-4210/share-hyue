package com.powerlong.sharera.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MemberYumLogeVo {
    private String id;
    private String orderNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private String equipmentNo;
    private String placeName;
    private String mobile;
    private String memberLevel;
    private Integer usedTimes;
    private Integer orderMoney;
    private Integer consumePoints;
    private Integer earnPoints;
    private Integer payMoney;
    private String projectId;
    private String levelName;
    private Integer type;
    private String orderType;
    private String rule;
    private Integer exchangeMoney;
    private Integer usedTime;//使用时长
}
