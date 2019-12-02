package com.api.goods.entity;

import com.api.common.entity.Result;

public class InsertBrand extends Result{

	private String brand_name;
	private Integer account_id;
	private String agentlevel_names;
	private String brand_info;//品牌简介
	private String logo_url;//品牌logo
	private String title;//品牌副标题
	
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

	public String getBrand_info() {
		return brand_info;
	}

	public void setBrand_info(String brand_info) {
		this.brand_info = brand_info;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}