package com.backend.admin.entity;

import java.util.List;

public class OrderListVo extends Accountorder{

    private List<Ordergoods> ordergoodsList;

    public List<Ordergoods> getOrdergoodsList() {
        return ordergoodsList;
    }

    public void setOrdergoodsList(List<Ordergoods> ordergoodsList) {
        this.ordergoodsList = ordergoodsList;
    }
}
