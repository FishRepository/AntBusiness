package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;

public class GoodsStockResult extends Result{

	private List<GoodsAndGoodsStock> list;

	public List<GoodsAndGoodsStock> getList() {
		return list;
	}

	public void setList(List<GoodsAndGoodsStock> list) {
		this.list = list;
	}
}