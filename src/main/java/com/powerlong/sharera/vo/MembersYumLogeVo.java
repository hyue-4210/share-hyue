package com.powerlong.sharera.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MembersYumLogeVo{
    private String id;
    @NotNull(message = "missing parameters: projectID")
    private String projectID; //String 是 商场id
    @NotNull(message = "missing parameters: orderNum")
    private String orderNum; //String 是 第三⽅方订单号
    @NotNull(message = "missing parameters: serviceType")
    private Integer serviceType; //Integer 是 共享服务类型。1：云纸；2：乐摩吧；3：来电；4：熊猫遛娃；5：摩伞；6：⻩黄⼩小⿅鹿
    @NotNull(message = "missing parameters: orderStartTime")
    private Date orderStartTime; //Integer 是 订单开始时间，时间戳
    @NotNull(message = "missing parameters: orderEndTime")
    private Date orderEndTime; //Integer 是 订单结束时间，时间戳
    @NotNull(message = "missing parameters: equipmentNo")
    private String equipmentNo; //String 是 设备编号
    private String branchPlace; //String 是 ⽹网点名称
    private String mobile; //String 是 会员⼿手机号
    private String memberLevel; //String 是 会员等级
    private Integer consumeTotalTime; //Integer 否 结算总时⻓长，单位（秒）
    private Integer consumeTotalTimes; //Integer 否 结算总次数
    private Integer orderMoney; //Integer 是 订单总⾦金金额，单位（元）
    private Integer consumePoint; //Integer 是 消耗积分
    private Integer exchangeMoney; //Integer 是 消耗积分所兑换的⾦金金额
    private Integer giftPoint; //Integer 否 获得积分
    private Integer consumeMoney; //Integer 否 ⽀支付⾦金金额，单位（元）
    private String levelName;
    private String rule;

    private Integer beginRow;

    private Integer limitRow;

    private String searchKey;
    private String orderType;


}
