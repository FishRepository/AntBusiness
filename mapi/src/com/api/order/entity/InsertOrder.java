package com.api.order.entity;

public class InsertOrder{
	
	private Integer order_type;//订单类型（1：出货 2：进货）
	private Integer brand_id;
	private Integer agentlevel_id;
	private Float order_premium;
	private Integer customer_id;
	private Integer customer_phoneid;
	private Integer customer_addressid;
	private Integer account_id;
	private String goodslist;//goods_id,goods_num|goods_id,goods_num
	
	public Integer getOrder_type() {
		return order_type;
	}
	public void setOrder_type(Integer order_type) {
		this.order_type = order_type;
	}
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public Integer getAgentlevel_id() {
		return agentlevel_id;
	}
	public void setAgentlevel_id(Integer agentlevel_id) {
		this.agentlevel_id = agentlevel_id;
	}
	public Float getOrder_premium() {
		return order_premium;
	}
	public void setOrder_premium(Float order_premium) {
		this.order_premium = order_premium;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getCustomer_phoneid() {
		return customer_phoneid;
	}
	public void setCustomer_phoneid(Integer customer_phoneid) {
		this.customer_phoneid = customer_phoneid;
	}
	public Integer getCustomer_addressid() {
		return customer_addressid;
	}
	public void setCustomer_addressid(Integer customer_addressid) {
		this.customer_addressid = customer_addressid;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public String getGoodslist() {
		return goodslist;
	}
	public void setGoodslist(String goodslist) {
		this.goodslist = goodslist;
	}
}