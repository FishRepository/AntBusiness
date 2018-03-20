package com.api.order.entity;

import java.util.List;

import com.api.common.entity.Result;

public class OrderMonthListResult extends Result{

	private List<OrderMonth> list;
	private String year; //当前年份
	private int totalpage;		//总页数
	private int totalresult;	//总记录数

	public List<OrderMonth> getList() {
		return list;
	}
	public void setList(List<OrderMonth> list) {
		this.list = list;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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