package com.api.sync.entity;

import java.util.Date;

public class GoodsPrice{

	private Integer goodsprice_id;
	private Integer goods_id;
	private Integer agentlevel_id;
	private Float goods_price;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;
	
	public Integer getGoodsprice_id() {
		return goodsprice_id;
	}
	public void setGoodsprice_id(Integer goodsprice_id) {
		this.goodsprice_id = goodsprice_id;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public Integer getAgentlevel_id() {
		return agentlevel_id;
	}
	public void setAgentlevel_id(Integer agentlevel_id) {
		this.agentlevel_id = agentlevel_id;
	}
	public Float getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Float goods_price) {
		this.goods_price = goods_price;
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
}