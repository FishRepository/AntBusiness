package com.api.sync.entity;

import java.util.List;

import com.api.common.entity.Result;

public class ProfitList extends Result{
	private List<Profit> profitlist;
	private int isnow;//0：不是本年或本月  1：是本年或本月
	private String nowtime;
	
	public List<Profit> getProfitlist() {
		return profitlist;
	}
	public void setProfitlist(List<Profit> profitlist) {
		this.profitlist = profitlist;
	}
	public int getIsnow() {
		return isnow;
	}
	public void setIsnow(int isnow) {
		this.isnow = isnow;
	}
	public String getNowtime() {
		return nowtime;
	}
	public void setNowtime(String nowtime) {
		this.nowtime = nowtime;
	}
}