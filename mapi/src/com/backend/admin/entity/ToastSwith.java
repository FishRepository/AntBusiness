package com.backend.admin.entity;

import java.io.Serializable;

/**
 * (ToastSwith)实体类
 *
 * @author makejava
 * @since 2019-07-23 10:58:43
 */
public class ToastSwith implements Serializable {
    private static final long serialVersionUID = -23287079808199883L;
    //首页弹窗ID
    private Integer id;
    //是否弹窗0不弹1弹
    private Integer isShow;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

}