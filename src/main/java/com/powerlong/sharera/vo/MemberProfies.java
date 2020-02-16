package com.powerlong.sharera.vo;


import javax.validation.constraints.NotNull;
import java.util.List;

public class MemberProfies {
    private String memberLevel;
    private String originlevel;
    @NotNull(message = "missing parameters: mobile")
    private String mobile;
    private String memberLevelName;
    private String uid;
    private String projectId;
    @NotNull(message = "missing parameters: projectID")
    private String projectID;
    private Integer validPoints;
    private String openId;
    private String projectName;
    private List<MemberProfies> listlevel;

    public String getOriginlevel() {
        return originlevel;
    }

    public void setOriginlevel(String originlevel) {
        this.originlevel = originlevel;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMemberLevelName() {
        return memberLevelName;
    }

    public void setMemberLevelName(String memberLevelName) {
        this.memberLevelName = memberLevelName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public Integer getValidPoints() {
        return validPoints;
    }

    public void setValidPoints(Integer validPoints) {
        this.validPoints = validPoints;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public List<MemberProfies> getListlevel() {
        return listlevel;
    }

    public void setListlevel(List<MemberProfies> listlevel) {
        this.listlevel = listlevel;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "MemberProfies{" +
                "memberLevel='" + memberLevel + '\'' +
                ", mobile='" + mobile + '\'' +
                ", memberLevelName='" + memberLevelName + '\'' +
                ", uid='" + uid + '\'' +
                ", projectId='" + projectId + '\'' +
                ", projectID='" + projectID + '\'' +
                ", validPoints=" + validPoints +
                ", openId='" + openId + '\'' +
                ", listlevel=" + listlevel +
                '}';
    }
}
