package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;

public class GoodsInfoResult extends Result{
    private Brand brand;
	private Goods goods;
	private List<GoodsPriceResult> list;
	
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public List<GoodsPriceResult> getList() {
		return list;
	}
	public void setList(List<GoodsPriceResult> list) {
		this.list = list;
	}
}