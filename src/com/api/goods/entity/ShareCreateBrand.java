package com.api.goods.entity;

import java.util.List;

public class ShareCreateBrand {
	private Integer brand_id;
	private List<Integer> agentlevel_idlist;
	
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public List<Integer> getAgentlevel_idlist() {
		return agentlevel_idlist;
	}
	public void setAgentlevel_idlist(List<Integer> agentlevel_idlist) {
		this.agentlevel_idlist = agentlevel_idlist;
	}
}