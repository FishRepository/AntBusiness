package com.api.goods.entity;

import com.api.common.entity.Result;

public class GoodsStockTotalResult extends Result{

	private Float total_money;
	private Integer total_goods;
	private Integer total_nostock;
	private Integer total_setstock;
	
	public Float getTotal_money() {
		return total_money;
	}
	public void setTotal_money(Float total_money) {
		this.total_money = total_money;
	}
	public Integer getTotal_goods() {
		return total_goods;
	}
	public void setTotal_goods(Integer total_goods) {
		this.total_goods = total_goods;
	}
	public Integer getTotal_nostock() {
		return total_nostock;
	}
	public void setTotal_nostock(Integer total_nostock) {
		this.total_nostock = total_nostock;
	}
	public Integer getTotal_setstock() {
		return total_setstock;
	}
	public void setTotal_setstock(Integer total_setstock) {
		this.total_setstock = total_setstock;
	}
}