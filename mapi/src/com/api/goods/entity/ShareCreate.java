package com.api.goods.entity;

import java.util.List;

public class ShareCreate {
	private Integer account_id;
	private List<ShareCreateBrand> brand_idlist;
	
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public List<ShareCreateBrand> getBrand_idlist() {
		return brand_idlist;
	}
	public void setBrand_idlist(List<ShareCreateBrand> brand_idlist) {
		this.brand_idlist = brand_idlist;
	}
}