package com.api.sync.entity;

import java.util.Date;

public class Brand{

	private Integer brand_id;
	private String brand_name;
	private Integer goods_count;
	private Integer images_count;
	private Integer threshold_count;
	private Integer brand_from;
	private Integer brand_index;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;
	
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
	public Integer getImages_count() {
		return images_count;
	}
	public void setImages_count(Integer images_count) {
		this.images_count = images_count;
	}
	public Integer getThreshold_count() {
		return threshold_count;
	}
	public void setThreshold_count(Integer threshold_count) {
		this.threshold_count = threshold_count;
	}
	public Integer getBrand_from() {
		return brand_from;
	}
	public void setBrand_from(Integer brand_from) {
		this.brand_from = brand_from;
	}
	public Integer getBrand_index() {
		return brand_index;
	}
	public void setBrand_index(Integer brand_index) {
		this.brand_index = brand_index;
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