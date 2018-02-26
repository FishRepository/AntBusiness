package com.api.goods.entity;

import java.util.List;

public class ShareDownloadShare {
	private Integer share_id;
	private Integer account_id;
	private List<ShareDownloadBrand> brandlist;
	
	public Integer getShare_id() {
		return share_id;
	}
	public void setShare_id(Integer share_id) {
		this.share_id = share_id;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public List<ShareDownloadBrand> getBrandlist() {
		return brandlist;
	}
	public void setBrandlist(List<ShareDownloadBrand> brandlist) {
		this.brandlist = brandlist;
	}
}