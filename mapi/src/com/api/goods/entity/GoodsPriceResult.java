package com.api.goods.entity;


public class GoodsPriceResult{

	private Integer goodsprice_id;
	private Float goods_price;
	private Integer agentlevel_id;
	private String agentlevel_name;
	private Integer agentlevel_default;
	private Integer agentlevel_index;
	
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
	public Integer getAgentlevel_id() {
		return agentlevel_id;
	}
	public void setAgentlevel_id(Integer agentlevel_id) {
		this.agentlevel_id = agentlevel_id;
	}
	public String getAgentlevel_name() {
		return agentlevel_name;
	}
	public void setAgentlevel_name(String agentlevel_name) {
		this.agentlevel_name = agentlevel_name;
	}
	public Integer getAgentlevel_default() {
		return agentlevel_default;
	}
	public void setAgentlevel_default(Integer agentlevel_default) {
		this.agentlevel_default = agentlevel_default;
	}
	public Integer getAgentlevel_index() {
		return agentlevel_index;
	}
	public void setAgentlevel_index(Integer agentlevel_index) {
		this.agentlevel_index = agentlevel_index;
	}
}