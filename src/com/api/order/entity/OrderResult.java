package com.api.order.entity;

import java.util.List;

import com.api.common.entity.Result;

public class OrderResult extends Result{

	private Order order;
	private List<OrderGoods> ordergoodslist;
	private List<Integer> faillist;
	
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
	public List<Integer> getFaillist() {
		return faillist;
	}
	public void setFaillist(List<Integer> faillist) {
		this.faillist = faillist;
	}
}