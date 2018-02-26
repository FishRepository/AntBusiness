package com.api.goods.entity;


public class InsertGoods{

	private String goods_name;
	private Float goods_price;
	private Integer brand_id;
	private Integer account_id;
	private String agent_prices;//多个|分隔，再英文逗号分隔出代理层级编号及代理价格
	
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Float getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Float goods_price) {
		this.goods_price = goods_price;
	}
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public String getAgent_prices() {
		return agent_prices;
	}
	public void setAgent_prices(String agent_prices) {
		this.agent_prices = agent_prices;
	}
}