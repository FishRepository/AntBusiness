package com.backend.admin.entity;

import com.backend.admin.controller.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户支付订单信息(PayOrder)实体类
 *
 */
public class PayOrder implements Serializable {
    private static final long serialVersionUID = -41015129354486958L;
    //订单编号UUID
    private String order_no;
    //消费类型1年费;2连续包月;3月付费;4兑换下载
    private Integer order_type;
    //交易时间yyyyMMddHHmmss
    private Date create_time;
    //消费金额，单位分。19999即为199.99元
    private Integer order_amount;
    //充值Vip天数
    private Integer vip_time;
    //会员到期时间yyyyMMddHHmmss
    private Date remain_time;
    //0等待支付;1交易成功;2交易取消
    private Integer state;
    //支付方式 1 支付宝 2微信  3IOS
    private Integer pay_type;
    //会员id
    private Integer account_id;
    //会员联系方式
    private String user_phone;


    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Integer getOrder_type() {
        return order_type;
    }

    public void setOrder_type(Integer order_type) {
        this.order_type = order_type;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(Integer order_amount) {
        this.order_amount = order_amount;
    }

    public Integer getVip_time() {
        return vip_time;
    }

    public void setVip_time(Integer vip_time) {
        this.vip_time = vip_time;
    }

    public Date getRemain_time() {
        return remain_time;
    }

    public void setRemain_time(Date remain_time) {
        this.remain_time = remain_time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }
}