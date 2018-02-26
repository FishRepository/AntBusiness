package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;

public class BrandAndAgentLevelResult extends Result{

	private String brand_name;
	private List<AgentLevel> list;
	
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public List<AgentLevel> getList() {
		return list;
	}
	public void setList(List<AgentLevel> list) {
		this.list = list;
	}
}