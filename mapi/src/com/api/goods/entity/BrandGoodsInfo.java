package com.api.goods.entity;

public class BrandGoodsInfo {

    private Integer goodsCates;//品牌下商品种类

    private double stockPrice;//总库存金额

    private Integer goodsNum;//库存商品件数

    private Integer goodsThreshold;//库存提醒数量

    private Integer totalOrders;//该品牌出货订单总订单数

    private Double totalSales;//该品牌出货订单总销售额

    public Integer getGoodsCates() {
        return goodsCates;
    }

    public void setGoodsCates(Integer goodsCates) {
        this.goodsCates = goodsCates;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getGoodsThreshold() {
        return goodsThreshold;
    }

    public void setGoodsThreshold(Integer goodsThreshold) {
        this.goodsThreshold = goodsThreshold;
    }

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }
}
