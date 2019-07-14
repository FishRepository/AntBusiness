package com.backend.admin.entity;

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
    //消费类型1,会员充值2,商品下载
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
}