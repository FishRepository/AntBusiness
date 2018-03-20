package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;

public class BrandHotListResult extends Result{

	private List<String> list;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
}