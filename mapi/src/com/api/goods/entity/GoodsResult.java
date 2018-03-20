package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;

public class GoodsResult extends Result{

	private List<GoodsAndGoodsPrice> list;

	public List<GoodsAndGoodsPrice> getList() {
		return list;
	}

	public void setList(List<GoodsAndGoodsPrice> list) {
		this.list = list;
	}
}