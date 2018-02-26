package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;

public class GoodsScaleResult extends Result{

	private List<Goods> list;
	private Integer isSetting;//0未设置1已设置

	public List<Goods> getList() {
		return list;
	}

	public void setList(List<Goods> list) {
		this.list = list;
	}

	public Integer getIsSetting() {
		return isSetting;
	}

	public void setIsSetting(Integer isSetting) {
		this.isSetting = isSetting;
	}
}