package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;


public class GoodsStockListResult extends Result{

	private List<GoodsStockList> list;

	public List<GoodsStockList> getList() {
		return list;
	}

	public void setList(List<GoodsStockList> list) {
		this.list = list;
	}
}