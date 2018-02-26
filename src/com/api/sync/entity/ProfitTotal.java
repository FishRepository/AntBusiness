package com.api.sync.entity;

import java.util.List;

import com.api.common.entity.Result;

public class ProfitTotal extends Result{
	private Float today_sales = 0f;
	private Float today_profit = 0f;
	private Float total_sales = 0f;
	private Float total_profit = 0f;
	private SevenDay sevenday;
	private List<Profit> profitlist;
	
	public Float getToday_sales() {
		return today_sales;
	}
	public void setToday_sales(Float today_sales) {
		this.today_sales = today_sales;
	}
	public Float getToday_profit() {
		return today_profit;
	}
	public void setToday_profit(Float today_profit) {
		this.today_profit = today_profit;
	}
	public Float getTotal_sales() {
		return total_sales;
	}
	public void setTotal_sales(Float total_sales) {
		this.total_sales = total_sales;
	}
	public Float getTotal_profit() {
		return total_profit;
	}
	public void setTotal_profit(Float total_profit) {
		this.total_profit = total_profit;
	}
	public SevenDay getSevenday() {
		return sevenday;
	}
	public void setSevenday(SevenDay sevenday) {
		this.sevenday = sevenday;
	}
	public List<Profit> getProfitlist() {
		return profitlist;
	}
	public void setProfitlist(List<Profit> profitlist) {
		this.profitlist = profitlist;
	}
}