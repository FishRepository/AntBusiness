package com.backend.admin.entity;

import com.api.common.entity.Result;

import java.util.List;

public class OrderListResult extends Result {

    private Double totalSals;

    private Double totalprofit;

    private List<OrderListVo> stockList;

    private List<OrderListVo> sellsList;

    public Double getTotalSals() {
        return totalSals;
    }

    public void setTotalSals(Double totalSals) {
        this.totalSals = totalSals;
    }

    public Double getTotalprofit() {
        return totalprofit;
    }

    public void setTotalprofit(Double totalprofit) {
        this.totalprofit = totalprofit;
    }

    public List<OrderListVo> getStockList() {
        return stockList;
    }

    public void setStockList(List<OrderListVo> stockList) {
        this.stockList = stockList;
    }

    public List<OrderListVo> getSellsList() {
        return sellsList;
    }

    public void setSellsList(List<OrderListVo> sellsList) {
        this.sellsList = sellsList;
    }
}
