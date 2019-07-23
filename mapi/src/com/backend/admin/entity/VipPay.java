package com.backend.admin.entity;

import java.io.Serializable;

/**
 * vip付费信息(VipPay)实体类
 *
 * @author makejava
 * @since 2019-07-23 10:46:35
 */
public class VipPay implements Serializable {
    private static final long serialVersionUID = -21860797062508733L;
    //主键
    private Integer id;
    //1年费;2连续包月;3月付费
    private Integer type;
    //原价,单位分。19999即为199.99元
    private Integer oriPrice;
    //优惠价,单位分。19999即为199.99元
    private Integer disPrice;


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

    public Integer getOriPrice() {
        return oriPrice;
    }

    public void setOriPrice(Integer oriPrice) {
        this.oriPrice = oriPrice;
    }

    public Integer getDisPrice() {
        return disPrice;
    }

    public void setDisPrice(Integer disPrice) {
        this.disPrice = disPrice;
    }

}