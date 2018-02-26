package com.api.goods.entity;

public class GoodsAgentLevelPrice{

	private Integer goods_id;
	private String goods_name;
	private Integer goodsprice_id;
	private Float goods_price;
	private Float goods_costprice;
	
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Integer getGoodsprice_id() {
		return goodsprice_id;
	}
	public void setGoodsprice_id(Integer goodsprice_id) {
		this.goodsprice_id = goodsprice_id;
	}
	public Float getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Float goods_price) {
		this.goods_price = goods_price;
	}
	public Float getGoods_costprice() {
		return goods_costprice;
	}
	public void setGoods_costprice(Float goods_costprice) {
		this.goods_costprice = goods_costprice;
	}
}