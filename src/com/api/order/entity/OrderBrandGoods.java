package com.api.order.entity;

import java.util.List;

public class OrderBrandGoods{

	private String name;
	private List<OrderGoods> goodslist;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<OrderGoods> getGoodslist() {
		return goodslist;
	}
	public void setGoodslist(List<OrderGoods> goodslist) {
		this.goodslist = goodslist;
	}
}