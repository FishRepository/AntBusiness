package com.api.goods.entity;

/**
 *  品牌订单记录
 */
public class BrandSales {
    //订单金额
    private double sales;
    //订单id
    private Integer orderId;

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
