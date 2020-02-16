package com.powerlong.sharera.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class decutOrderVo {
    private String id;
    @NotNull(message = "missing parameters: mobile")
    private String mobile;
    @NotNull(message = "missing parameters: projectID")
    private String projectID;
    @NotNull(message = "missing parameters: deductPoint")
    private Integer deductPoint;
    @NotNull(message = "missing parameters: orderNum")
    private String orderNum;
    @NotNull(message = "missing parameters: deductMoney")
    private Integer deductMoney;
    @NotNull(message = "missing parameters: serviceType")
    private String serviceType;
    @NotNull(message = "missing parameters: deviceID")
    private String deviceID;
    @NotNull(message = "missing parameters: isReturnedPoints")
    private Boolean isReturnedPoints;//是否为返回的积分，true： 是 false：否 默认false

}
