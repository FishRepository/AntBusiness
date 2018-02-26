package com.api.goods.entity;

import java.util.Date;

public class Goods{

	private Integer goods_id;
	private String goods_name;
	private Float goods_price;
	private Integer brand_id;
	private Integer goods_stock;
	private Integer goods_threshold;
	private Integer goods_scale;
	private Integer goods_index;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;
	
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
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public Integer getGoods_stock() {
		return goods_stock;
	}
	public void setGoods_stock(Integer goods_stock) {
		this.goods_stock = goods_stock;
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
	public Integer getGoods_index() {
		return goods_index;
	}
	public void setGoods_index(Integer goods_index) {
		this.goods_index = goods_index;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}