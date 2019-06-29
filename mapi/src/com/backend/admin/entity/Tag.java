package com.backend.admin.entity;

import java.io.Serializable;

/**
 * (Tag)实体类
 *
 * @author makejava
 * @since 2019-06-25 21:29:39
 */
public class Tag implements Serializable {
    private static final long serialVersionUID = 578622894185552186L;
    //主键
    private Integer tag_id;
    //标签名称
    private String tag_name;
    //标签颜色
    private String tag_color;
    //用户id
    private Integer account_id;
    //标签类型1,默认2,自定义
    private Integer tag_type;
    //订单类型1,进货2,出货
    private Integer order_type;

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getTag_color() {
        return tag_color;
    }

    public void setTag_color(String tag_color) {
        this.tag_color = tag_color;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public Integer getTag_type() {
        return tag_type;
    }

    public void setTag_type(Integer tag_type) {
        this.tag_type = tag_type;
    }

    public Integer getOrder_type() {
        return order_type;
    }

    public void setOrder_type(Integer order_type) {
        this.order_type = order_type;
    }
}