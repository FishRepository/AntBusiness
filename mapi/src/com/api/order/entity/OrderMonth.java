package com.api.order.entity;

import java.util.List;

public class OrderMonth{

	private String month;
	private List<OrderList> orderlist;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public List<OrderList> getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(List<OrderList> orderlist) {
		this.orderlist = orderlist;
	}
}