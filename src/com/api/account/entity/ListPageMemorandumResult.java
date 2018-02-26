package com.api.account.entity;

import java.util.List;

import com.api.common.entity.Result;

public class ListPageMemorandumResult extends Result{

	private List<?> list;
	private int totalpage;		//总页数
	private int totalresult;	//总记录数
	
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
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