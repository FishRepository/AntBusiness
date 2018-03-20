package com.api.order.entity;

public class UpdateMoreOrder{
	
	private Integer order_id;
	private Integer order_type;//订单类型（1：出货 2：进货）
	private Float order_premium;
	private Integer premium_id;
	private String premium_name;
	private Integer customer_id;
	private Integer customer_phoneid;
	private Integer customer_addressid;
	private Integer account_id;
	private String oldgoodslist;//ordergoods_id,goods_num|ordergoods_id,goods_num
	private String newgoodslist;//brand_id,agentlevel_id,goods_id,goods_num|brand_id,agentlevel_id,goods_id,goods_num
	
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getOrder_type() {
		return order_type;
	}
	public void setOrder_type(Integer order_type) {
		this.order_type = order_type;
	}
	public Float getOrder_premium() {
		return order_premium;
	}
	public void setOrder_premium(Float order_premium) {
		this.order_premium = order_premium;
	}
	public Integer getPremium_id() {
		return premium_id;
	}
	public void setPremium_id(Integer premium_id) {
		this.premium_id = premium_id;
	}
	public String getPremium_name() {
		return premium_name;
	}
	public void setPremium_name(String premium_name) {
		this.premium_name = premium_name;
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
	public String getOldgoodslist() {
		return oldgoodslist;
	}
	public void setOldgoodslist(String oldgoodslist) {
		this.oldgoodslist = oldgoodslist;
	}
	public String getNewgoodslist() {
		return newgoodslist;
	}
	public void setNewgoodslist(String newgoodslist) {
		this.newgoodslist = newgoodslist;
	}
}