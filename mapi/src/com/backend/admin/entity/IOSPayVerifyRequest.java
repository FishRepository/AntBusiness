package com.backend.admin.entity;

import java.io.Serializable;

public class IOSPayVerifyRequest implements Serializable {
    private static final long serialVersionUID = -1044757904518081535L;
    //苹果内购交易ID
    String transaction_id;
    //payload 校验体
    String payload;
    //订单金额,单位分
    private Integer order_money;
    //用户id
    private Integer account_id;
    //订单类型：1年费;2连续包月;3月付费
    private Integer order_type;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Integer getOrder_money() {
        return order_money;
    }

    public void setOrder_money(Integer order_money) {
        this.order_money = order_money;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public Integer getOrder_type() {
        return order_type;
    }

    public void setOrder_type(Integer order_type) {
        this.order_type = order_type;
    }
}
