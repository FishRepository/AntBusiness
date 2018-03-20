package com.api.goods.entity;

import java.util.List;

public class IndexGoodsList{
	private Integer account_id;
	private Integer brand_id;
	private List<IndexId> list;
	
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public List<IndexId> getList() {
		return list;
	}
	public void setList(List<IndexId> list) {
		this.list = list;
	}
}