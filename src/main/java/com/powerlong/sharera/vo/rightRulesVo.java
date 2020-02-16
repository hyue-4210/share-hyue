package com.powerlong.sharera.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class  rightRulesVo {
    private String id;//    id
    private String ids;//   分 id
    private String projectId;//   商场id
    private Date createTime;//    创建时间
    private String createPeople;//    创建人
    private String userId;//    创建人
    private Date updateTime;//    更改时间
    private String updatePeople;//    更改人
    private Date effectiveStartTime;//    生效开始时间
    private Date effectiveEndTime;//    生效结束时间
    private Integer topPoint;//    积分上线
    private String memberType;//    会员类型 0 全部
    private String memberTypeName; //会员名称
    private Integer postPoint;//    消费积分
    private Integer getMoney;//    获得金额
    private Integer postMoney;//    消费金额
    private Integer getPoint;//    获得积分
    private Integer submitType;//	1提交 2暂存
    private String status;	// 1待审核 2审核通过 3已驳回 4已发布 5已下架
    private List<String> statusList;
    private String remarks;//    备注 审核人;
    private String backReason;//  驳回理由
    private Integer type;  // 1云纸 2乐摩吧 3来电 4熊猫溜娃 5摩伞 6摩伞
    private Integer isCommon; //是否通用 1是 2不是
    private String nextAutor; // 待审核人
    private Integer isAddPoint; // 是否增加积分 1是 2否

    private Integer beginRow;

    private Integer limitRow;

    public String getMemberTypeName() {
        return memberTypeName;
    }

    public void setMemberTypeName(String memberTypeName) {
        this.memberTypeName = memberTypeName;
    }

    public String getNextAutor() {
        return nextAutor;
    }

    public void setNextAutor(String nextAutor) {
        this.nextAutor = nextAutor;
    }

    public Integer getIsAddPoint() {
        return isAddPoint;
    }

    public void setIsAddPoint(Integer isAddPoint) {
        this.isAddPoint = isAddPoint;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsCommon() {
        return isCommon;
    }

    public void setIsCommon(Integer isCommon) {
        this.isCommon = isCommon;
    }

    public String getBackReason() {
        return backReason;
    }

    public void setBackReason(String backReason) {
        this.backReason = backReason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePeople() {
        return createPeople;
    }

    public void setCreatePeople(String createPeople) {
        this.createPeople = createPeople;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdatePeople() {
        return updatePeople;
    }

    public void setUpdatePeople(String updatePeople) {
        this.updatePeople = updatePeople;
    }

    public Date getEffectiveStartTime() {
        return effectiveStartTime;
    }

    public void setEffectiveStartTime(Date effectiveStartTime) {
        this.effectiveStartTime = effectiveStartTime;
    }

    public Date getEffectiveEndTime() {
        return effectiveEndTime;
    }

    public void setEffectiveEndTime(Date effectiveEndTime) {
        this.effectiveEndTime = effectiveEndTime;
    }

    public Integer getTopPoint() {
        return topPoint;
    }

    public void setTopPoint(Integer topPoint) {
        this.topPoint = topPoint;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public Integer getPostPoint() {
        return postPoint;
    }

    public void setPostPoint(Integer postPoint) {
        this.postPoint = postPoint;
    }

    public Integer getGetMoney() {
        return getMoney;
    }

    public void setGetMoney(Integer getMoney) {
        this.getMoney = getMoney;
    }

    public Integer getPostMoney() {
        return postMoney;
    }

    public void setPostMoney(Integer postMoney) {
        this.postMoney = postMoney;
    }

    public Integer getGetPoint() {
        return getPoint;
    }

    public void setGetPoint(Integer getPoint) {
        this.getPoint = getPoint;
    }

    public Integer getSubmitType() {
        return submitType;
    }

    public void setSubmitType(Integer submitType) {
        this.submitType = submitType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
