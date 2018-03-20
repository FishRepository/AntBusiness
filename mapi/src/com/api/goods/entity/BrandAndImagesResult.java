package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;

public class BrandAndImagesResult extends Result{

	private List<BrandAndImages> list;

	public List<BrandAndImages> getList() {
		return list;
	}

	public void setList(List<BrandAndImages> list) {
		this.list = list;
	}
}