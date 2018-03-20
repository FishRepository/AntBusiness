package com.api.goods.entity;

import com.api.common.entity.Result;

public class InsertBrand extends Result{

	private String brand_name;
	private Integer account_id;
	private String agentlevel_names;
	
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public String getAgentlevel_names() {
		return agentlevel_names;
	}
	public void setAgentlevel_names(String agentlevel_names) {
		this.agentlevel_names = agentlevel_names;
	}
}