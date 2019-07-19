package com.backend.admin.entity;

import java.io.Serializable;

/**
 * 支付请求
 */
public class PayRequest implements Serializable {

    private static final long serialVersionUID = -4364637175111297952L;
    //支付方式(1 支付宝、2微信)
    private Integer pay_type;
    //订单金额,单位分
    private Integer order_money;
    //用户id
    private Integer account_id;
    //订单类型：1年费;2连续包月;3月付费
    private Integer order_type;
    //客户端ip
    private String ip;

    private String orderNo;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public Integer getPay_type() {
        return pay_type;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }

    public Integer getOrder_type() {
        return order_type;
    }

    public void setOrder_type(Integer order_type) {
        this.order_type = order_type;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
