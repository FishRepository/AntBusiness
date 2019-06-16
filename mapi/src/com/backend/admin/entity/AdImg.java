package com.backend.admin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 广告图片表(AdImg)实体类
 *
 * @author makejava
 * @since 2019-06-06 10:42:59
 */
public class AdImg implements Serializable {
    private static final long serialVersionUID = -92260432452493568L;
    //主键
    private Integer id;
    //广告图片地址
    private String imgUrl;
    //广告跳转链接
    private String linkUrl;
    //创建时间
    private Date createTime;
    //描述
    private String description;
    //状态0,无效1,有效
    private Integer status;
    //排序按数值倒序
    private Integer sort;
    //是否删除0,未删除1,已删除
    private Integer del;
    //广告轮播时间
    private Integer cyclic;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getCyclic() {
        return cyclic;
    }

    public void setCyclic(Integer cyclic) {
        this.cyclic = cyclic;
    }

}