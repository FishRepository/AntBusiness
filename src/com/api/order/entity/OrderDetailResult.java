package com.api.order.entity;

import java.util.List;

import com.api.common.entity.Result;

public class OrderDetailResult extends Result{

	private OrderDetail order;
	private List<OrderGoods> list;

	public OrderDetail getOrder() {
		return order;
	}
	public void setOrder(OrderDetail order) {
		this.order = order;
	}
	public List<OrderGoods> getList() {
		return list;
	}
	public void setList(List<OrderGoods> list) {
		this.list = list;
	}
}