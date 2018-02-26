package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;

public class BrandResult extends Result{

	private List<BrandAndAgentLevel> list;

	public List<BrandAndAgentLevel> getList() {
		return list;
	}

	public void setList(List<BrandAndAgentLevel> list) {
		this.list = list;
	}
}