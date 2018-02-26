package com.api.order.entity;

import java.util.List;

import com.api.common.entity.Result;

public class OrderListResult extends Result{

	private List<OrderList> list;
	private int totalpage;		//总页数
	private int totalresult;	//总记录数

	public List<OrderList> getList() {
		return list;
	}
	public void setList(List<OrderList> list) {
		this.list = list;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getTotalresult() {
		return totalresult;
	}
	public void setTotalresult(int totalresult) {
		this.totalresult = totalresult;
	}
}