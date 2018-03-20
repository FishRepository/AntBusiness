package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;

public class ShareQueryResult extends Result{
	private List<ShareQuery> sharelist;

	public List<ShareQuery> getSharelist() {
		return sharelist;
	}

	public void setSharelist(List<ShareQuery> sharelist) {
		this.sharelist = sharelist;
	}
}