package com.backend.admin.entity;

import com.api.common.entity.Result;

import java.util.List;

public class OrderListResult extends Result {

    private Double totalSals;

    private Double totalprofit;

    private List<Ordergoods> sellsGoodsList;

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

    public List<Ordergoods> getSellsGoodsList() {
        return sellsGoodsList;
    }

    public void setSellsGoodsList(List<Ordergoods> sellsGoodsList) {
        this.sellsGoodsList = sellsGoodsList;
    }
}
