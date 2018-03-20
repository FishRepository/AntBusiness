package com.api.sync.entity;

import java.util.Date;

public class BrandImages{

	private Integer brandimages_id;
	private String brandimages_url;
	private Integer brand_id;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;
	
	public Integer getBrandimages_id() {
		return brandimages_id;
	}
	public void setBrandimages_id(Integer brandimages_id) {
		this.brandimages_id = brandimages_id;
	}
	public String getBrandimages_url() {
		return brandimages_url;
	}
	public void setBrandimages_url(String brandimages_url) {
		this.brandimages_url = brandimages_url;
	}
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
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