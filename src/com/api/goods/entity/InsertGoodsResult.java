package com.api.goods.entity;

import com.api.common.entity.Result;

public class InsertGoodsResult extends Result{

	private Integer goods_id;
	
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
}