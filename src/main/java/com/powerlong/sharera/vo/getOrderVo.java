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
public class getOrderVo {
    @NotNull(message = "missing parameters: orderNum")
    private String orderNum;
    @NotNull(message = "missing parameters: serviceType")
    private String serviceType;

}
