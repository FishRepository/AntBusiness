package com.api.sync.entity;

public class Profit {
	private String total_time;
	private Float total_sales = 0f;
	private Float total_profit = 0f;
	
	public String getTotal_time() {
		return total_time;
	}
	public void setTotal_time(String total_time) {
		this.total_time = total_time;
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
}