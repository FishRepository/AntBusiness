package com.api.goods.entity;

import java.util.Date;


public class GoodsStockList{

	private Integer goods_id;
	private String goods_name;
	private Float goods_price;
	private Integer goods_threshold;
	private Integer goods_scale;
	private Integer brand_id;
	private String brand_name;
	private Integer goods_stock;
	private Integer total_id;
	private Date create_time;
	
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
	public Float getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Float goods_price) {
		this.goods_price = goods_price;
	}
	public Integer getGoods_threshold() {
		return goods_threshold;
	}
	public void setGoods_threshold(Integer goods_threshold) {
		this.goods_threshold = goods_threshold;
	}
	public Integer getGoods_scale() {
		return goods_scale;
	}
	public void setGoods_scale(Integer goods_scale) {
		this.goods_scale = goods_scale;
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
	public Integer getGoods_stock() {
		return goods_stock;
	}
	public void setGoods_stock(Integer goods_stock) {
		this.goods_stock = goods_stock;
	}
	public Integer getTotal_id() {
		return total_id;
	}
	public void setTotal_id(Integer total_id) {
		this.total_id = total_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}