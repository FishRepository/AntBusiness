package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;

public class InsertBrandResult extends Result{

	private Integer brand_id;
	private List<AgentLevel> list;
	
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public List<AgentLevel> getList() {
		return list;
	}
	public void setList(List<AgentLevel> list) {
		this.list = list;
	}
}