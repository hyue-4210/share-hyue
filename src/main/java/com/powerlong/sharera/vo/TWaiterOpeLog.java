package com.powerlong.sharera.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TWaiterOpeLog implements Serializable{

	private static final long serialVersionUID = 7325309446276933108L;
	
	private Integer id;
	private String waiterUid;
	private String uid;
	private Integer type;
	private Date createTime;
	private String productId;
	private String orderId;
	private String productName;
	private String merchantId;
	private String merchantName;
	private Integer baseValue;
	private String imgUrl;
	private Integer status; //审核状态 1、待审核，2、审核通过、3、拒绝
	private String remark;//备注
	private String rebackReason;//驳回原因
	private String cardId;
	private String realname;
	private String mobile;
	private int mold;//抵扣金额
	private int delPoint;
	private String projectId;
    private String sysUserId;
    private Integer orderBaseId;
    private String operaName;
    private String nickName;
    private String cutReason;
    private String relateId;
    private Integer payType;
    private Date payTime;
	private Boolean isReturnedPoints;//是否为返回的积分，true： 是 false：否 默认false


	

}
