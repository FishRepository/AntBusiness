package com.api.goods.entity;

import com.api.common.entity.Result;

public class ShareCreateResult extends Result{
	private String share_url;

	public String getShare_url() {
		return share_url;
	}

	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}
}