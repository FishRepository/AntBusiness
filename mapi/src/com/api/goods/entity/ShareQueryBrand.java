package com.api.goods.entity;

import java.util.List;

public class ShareQueryBrand {
	private Integer sharebrand_id;
	private Integer brand_id;
	private String brand_name;
	private Integer goods_count;
	private List<ShareQueryAgentLevel> agentlevellist;
	
	public Integer getSharebrand_id() {
		return sharebrand_id;
	}
	public void setSharebrand_id(Integer sharebrand_id) {
		this.sharebrand_id = sharebrand_id;
	}
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public Integer getGoods_count() {
		return goods_count;
	}
	public void setGoods_count(Integer goods_count) {
		this.goods_count = goods_count;
	}
	public List<ShareQueryAgentLevel> getAgentlevellist() {
		return agentlevellist;
	}
	public void setAgentlevellist(List<ShareQueryAgentLevel> agentlevellist) {
		this.agentlevellist = agentlevellist;
	}
}