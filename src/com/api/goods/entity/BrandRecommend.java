package com.api.goods.entity;


public class BrandRecommend{

	private Integer brand_id;
	private String brand_name;
	private Integer goods_count;
	private Float brand_price;
	private int isdownload;//是否已经下载  0未下载1已下载
	
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
	public Integer getGoods_count() {
		return goods_count;
	}
	public void setGoods_count(Integer goods_count) {
		this.goods_count = goods_count;
	}
	public Float getBrand_price() {
		return brand_price;
	}
	public void setBrand_price(Float brand_price) {
		this.brand_price = brand_price;
	}
	public int getIsdownload() {
		return isdownload;
	}
	public void setIsdownload(int isdownload) {
		this.isdownload = isdownload;
	}
}