package com.powerlong.sharera.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class pointForCouponVo {
    private String id;
    private String createTime;
    private String uid;
    private String projectId;
    private Integer type;
    private Integer payPoint;
    private Integer exchangeMoney;
    private Integer exchangeNum;
}
