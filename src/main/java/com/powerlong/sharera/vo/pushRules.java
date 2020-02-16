package com.powerlong.sharera.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class pushRules {
    private String id;
    private String srcID="powerlong";
    private String projectId;
    private String projectID;
    private Integer maxPoint;
//    private Integer ifGiftPoint;
    private Long validTime;
    private String timestamp;
    private String signature;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    private memberRight[] memberRight;

    public String getSrcID() {
        return srcID;
    }

    public void setSrcID(String srcID) {
        this.srcID = srcID;
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

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public Integer getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(Integer maxPoint) {
        this.maxPoint = maxPoint;
    }

    public Long getValidTime() {
        return validTime;
    }

    public void setValidTime(Long validTime) {
        this.validTime = validTime;
    }

    public com.powerlong.sharera.vo.memberRight[] getMemberRight() {
        return memberRight;
    }

    public void setMemberRight(com.powerlong.sharera.vo.memberRight[] memberRight) {
        this.memberRight = memberRight;
    }
}
