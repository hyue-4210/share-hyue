package com.powerlong.sharera.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class AdminPageVo {

    private Integer beginRow;

    private Integer limitRow;

	private String searchKey;


    /**
     * 优惠券idv2.1新加
     */
    private String couponId;

    /**
     * 会员昵称v2.1新加
     */
    private String nickname;

    /**
     * 会员手机号v2.1新加
     */
    private String mobile;

    /**
     * 会员卡号v2.1新加
     */
    private String cardId;

    /**
     * 核销商铺v2.1新加
     */
    private String merchantId;

    /**
     * 优惠券状态v2.1新加
     */
    private String memberstatus;

	/**
	 * 领取开始时间v2.1新加
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date getBeginTime;

	/**
	 * 领取结束时间v2.1新加
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date getEndTime;

    /**
     * 核销开始时间v2.1新加
     */
    private Date usedBeginTime;

	/**
	 * 核销结束时间v2.1新加
	 */
	private Date usedEndTime;

	//项目区域ID
	private String regionId;
	//项目类型ID
	private String typeId;
	//商场是否禁用1开放2关闭

	private Integer openStatus;
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMemberstatus() {
		return memberstatus;
	}

	public void setMemberstatus(String memberstatus) {
		this.memberstatus = memberstatus;
	}

	public Date getGetBeginTime() {
		return getBeginTime;
	}

	public void setGetBeginTime(Date getBeginTime) {
		this.getBeginTime = getBeginTime;
	}

	public Date getGetEndTime() {
		return getEndTime;
	}

	public void setGetEndTime(Date getEndTime) {
		this.getEndTime = getEndTime;
	}

	public Date getUsedBeginTime() {
		return usedBeginTime;
	}

	public void setUsedBeginTime(Date usedBeginTime) {
		this.usedBeginTime = usedBeginTime;
	}

	public Date getUsedEndTime() {
		return usedEndTime;
	}

	public void setUsedEndTime(Date usedEndTime) {
		this.usedEndTime = usedEndTime;
	}

	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
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

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public Integer getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}

}
