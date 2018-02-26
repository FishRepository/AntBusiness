package com.api.sync.entity;

import java.util.List;

import com.api.common.entity.Result;

public class StatisticsResult extends Result{
	private ProfitTotal profittotal;
	private List<CustomerTotal> customerlist;
	private List<GoodsTotal> goodslist;
	private List<Notice> noticelist;
	private int issale; //0今日未开单1今日已开单
	
	public ProfitTotal getProfittotal() {
		return profittotal;
	}
	public void setProfittotal(ProfitTotal profittotal) {
		this.profittotal = profittotal;
	}
	public List<CustomerTotal> getCustomerlist() {
		return customerlist;
	}
	public void setCustomerlist(List<CustomerTotal> customerlist) {
		this.customerlist = customerlist;
	}
	public List<GoodsTotal> getGoodslist() {
		return goodslist;
	}
	public void setGoodslist(List<GoodsTotal> goodslist) {
		this.goodslist = goodslist;
	}
	public List<Notice> getNoticelist() {
		return noticelist;
	}
	public void setNoticelist(List<Notice> noticelist) {
		this.noticelist = noticelist;
	}
	public int getIssale() {
		return issale;
	}
	public void setIssale(int issale) {
		this.issale = issale;
	}
}