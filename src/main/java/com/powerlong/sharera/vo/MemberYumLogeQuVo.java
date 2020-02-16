package com.powerlong.sharera.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.experimental.Accessors;

import java.util.Date;
@Accessors(chain = true)
public class MemberYumLogeQuVo{
    private Integer beginRow;
    private Integer limitRow;
    private String searchKey;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date orderStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date orderEndTime;
    private String orderName;
    private Integer userStartTime;
    private Integer userEndTime;
    private Integer userStartTimes;
    private Integer userEndTimes;
    private String earnPoint1;
    private String earnPoint2;
    private String memberLevel;
    private Integer usedPoint1;
    private Integer usedPoint2;
    private Integer payMoney1;
    private Integer payMoney2;
    private String equipmentNo;
    private String mobile;
    private String projectId;
    private String orderType;
    private String type;

    public Integer getUserStartTimes() {
        return userStartTimes;
    }

    public void setUserStartTimes(Integer userStartTimes) {
        this.userStartTimes = userStartTimes;
    }

    public Integer getUserEndTimes() {
        return userEndTimes;
    }

    public void setUserEndTimes(Integer userEndTimes) {
        this.userEndTimes = userEndTimes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

    public Date getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(Date orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Integer getUserStartTime() {
        return userStartTime;
    }

    public void setUserStartTime(Integer userStartTime) {
        this.userStartTime = userStartTime;
    }

    public Integer getUserEndTime() {
        return userEndTime;
    }

    public void setUserEndTime(Integer userEndTime) {
        this.userEndTime = userEndTime;
    }

    public String getEarnPoint1() {
        return earnPoint1;
    }

    public void setEarnPoint1(String earnPoint1) {
        this.earnPoint1 = earnPoint1;
    }

    public String getEarnPoint2() {
        return earnPoint2;
    }

    public void setEarnPoint2(String earnPoint2) {
        this.earnPoint2 = earnPoint2;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Integer getUsedPoint1() {
        return usedPoint1;
    }

    public void setUsedPoint1(Integer usedPoint1) {
        this.usedPoint1 = usedPoint1;
    }

    public Integer getUsedPoint2() {
        return usedPoint2;
    }

    public void setUsedPoint2(Integer usedPoint2) {
        this.usedPoint2 = usedPoint2;
    }

    public Integer getPayMoney1() {
        return payMoney1;
    }

    public void setPayMoney1(Integer payMoney1) {
        this.payMoney1 = payMoney1;
    }

    public Integer getPayMoney2() {
        return payMoney2;
    }

    public void setPayMoney2(Integer payMoney2) {
        this.payMoney2 = payMoney2;
    }

    public Integer getBeginRow() {
        return beginRow;
    }

    public void setBeginRow(Integer beginRow) {
        this.beginRow = beginRow;
    }

    public Integer getLimitRow() {
        return limitRow;
    }

    public void setLimitRow(Integer limitRow) {
        this.limitRow = limitRow;
    }
}
