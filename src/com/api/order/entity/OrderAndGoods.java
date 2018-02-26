package com.api.order.entity;

import java.util.List;

public class OrderAndGoods{
	private Order order;
	private List<OrderGoods> ordergoodslist;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderGoods> getOrdergoodslist() {
		return ordergoodslist;
	}
	public void setOrdergoodslist(List<OrderGoods> ordergoodslist) {
		this.ordergoodslist = ordergoodslist;
	}
}