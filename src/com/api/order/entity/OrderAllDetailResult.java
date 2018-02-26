package com.api.order.entity;

import java.util.List;

import com.api.common.entity.Result;

public class OrderAllDetailResult extends Result{

	private OrderDetail order;
	private List<OrderBrandGoods> list;
	
	public OrderDetail getOrder() {
		return order;
	}
	public void setOrder(OrderDetail order) {
		this.order = order;
	}
	public List<OrderBrandGoods> getList() {
		return list;
	}
	public void setList(List<OrderBrandGoods> list) {
		this.list = list;
	}
}