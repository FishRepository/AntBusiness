package com.api.goods.entity;

public class BrandGoodsInfo {

    private Integer goodsCates;//品牌下商品种类

    private double stockPrice;//总库存金额

    private Integer goodsNum;//库存商品件数

    private Integer stockRemind;//库存提醒数量

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

    public Integer getStockRemind() {
        return stockRemind;
    }

    public void setStockRemind(Integer stockRemind) {
        this.stockRemind = stockRemind;
    }
}
