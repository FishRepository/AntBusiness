package com.backend.admin.entity;

import java.util.Date;

public class Introduction {

    private Integer id;

    private Integer type;

    private String title;

    private String functions;

    private String operates;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Boolean isDeleted;

    private IntroductionType introductionType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public String getOperates() {
        return operates;
    }

    public void setOperates(String operates) {
        this.operates = operates;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public IntroductionType getIntroductionType() {
        return introductionType;
    }

    public void setIntroductionType(IntroductionType introductionType) {
        this.introductionType = introductionType;
    }

    @Override
    public String toString() {
        return "Introduction{" +
                "id=" + id +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", functions='" + functions + '\'' +
                ", operates='" + operates + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                ", introductionType=" + introductionType +
                '}';
    }
}
