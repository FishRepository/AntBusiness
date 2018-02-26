package com.api.sync.entity;

public class GoodsTotal {
	private Integer goods_id;
	private String goods_name;
	private Integer brand_id;
	private String brand_name;
	private Integer total = 0;
	private Float salestotal = 0f;
	
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
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Float getSalestotal() {
		return salestotal;
	}
	public void setSalestotal(Float salestotal) {
		this.salestotal = salestotal;
	}
}