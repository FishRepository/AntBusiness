package com.api.order.entity;

import java.util.Date;

public class OrderGoods{

	private Integer ordergoods_id;
	private Integer order_id;
	private String brand_name;
	private Integer brand_id;
	private String agentlevel_name;
	private Integer agentlevel_id;
	private Integer goods_id;
	private String goods_name;
	private Integer goodsprice_id;
	private Float goods_price;
	private Float goods_costprice;
	private Integer goods_num;
	private Float ordergoods_sales;
	private Float ordergoods_cost;
	private Float ordergoods_profit;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;
	private Integer goods_cnum;
	
	public Integer getOrdergoods_id() {
		return ordergoods_id;
	}
	public void setOrdergoods_id(Integer ordergoods_id) {
		this.ordergoods_id = ordergoods_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public String getAgentlevel_name() {
		return agentlevel_name;
	}
	public void setAgentlevel_name(String agentlevel_name) {
		this.agentlevel_name = agentlevel_name;
	}
	public Integer getAgentlevel_id() {
		return agentlevel_id;
	}
	public void setAgentlevel_id(Integer agentlevel_id) {
		this.agentlevel_id = agentlevel_id;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Integer getGoodsprice_id() {
		return goodsprice_id;
	}
	public void setGoodsprice_id(Integer goodsprice_id) {
		this.goodsprice_id = goodsprice_id;
	}
	public Float getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Float goods_price) {
		this.goods_price = goods_price;
	}
	public Float getGoods_costprice() {
		return goods_costprice;
	}
	public void setGoods_costprice(Float goods_costprice) {
		this.goods_costprice = goods_costprice;
	}
	public Integer getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
	}
	public Float getOrdergoods_sales() {
		return ordergoods_sales;
	}
	public void setOrdergoods_sales(Float ordergoods_sales) {
		this.ordergoods_sales = ordergoods_sales;
	}
	public Float getOrdergoods_cost() {
		return ordergoods_cost;
	}
	public void setOrdergoods_cost(Float ordergoods_cost) {
		this.ordergoods_cost = ordergoods_cost;
	}
	public Float getOrdergoods_profit() {
		return ordergoods_profit;
	}
	public void setOrdergoods_profit(Float ordergoods_profit) {
		this.ordergoods_profit = ordergoods_profit;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getGoods_cnum() {
		return goods_cnum;
	}
	public void setGoods_cnum(Integer goods_cnum) {
		this.goods_cnum = goods_cnum;
	}
}