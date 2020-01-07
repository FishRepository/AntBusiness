package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;

public class GoodsStockResult extends Result{

	private List<GoodsAndGoodsStock> list;

	private BrandGoodsInfo brandGoodsInfo;

	public List<GoodsAndGoodsStock> getList() {
		return list;
	}

	public void setList(List<GoodsAndGoodsStock> list) {
		this.list = list;
	}

	public BrandGoodsInfo getBrandGoodsInfo() {
		return brandGoodsInfo;
	}

	public void setBrandGoodsInfo(BrandGoodsInfo brandGoodsInfo) {
		this.brandGoodsInfo = brandGoodsInfo;
	}
}