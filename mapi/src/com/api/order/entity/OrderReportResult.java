package com.api.order.entity;

import java.util.List;

import com.api.common.entity.Result;

public class OrderReportResult extends Result{

	private Integer order_id;
	private List<OrderGoodsResult> list;
	
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public List<OrderGoodsResult> getList() {
		return list;
	}
	public void setList(List<OrderGoodsResult> list) {
		this.list = list;
	}
}