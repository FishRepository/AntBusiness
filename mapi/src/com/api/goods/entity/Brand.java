package com.api.goods.entity;

import java.util.Date;

public class Brand{

	private Integer brand_id;
	private String brand_name;
	private Integer goods_count;
	private Integer images_count;
	private Integer brand_from;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;

	private Integer stock_remind;//库存提醒数量
	private Integer is_hot;//是否推荐品牌
	private String brand_info;//品牌简介
	private String logo_url;//品牌logo
	private String title;//品牌副标题

	private String phone;
	private String wechat;
	
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
	public Integer getBrand_from() {
		return brand_from;
	}
	public void setBrand_from(Integer brand_from) {
		this.brand_from = brand_from;
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

	public Integer getStock_remind() {
		return stock_remind;
	}

	public void setStock_remind(Integer stock_remind) {
		this.stock_remind = stock_remind;
	}

	public Integer getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}

	public String getBrand_info() {
		return brand_info;
	}

	public void setBrand_info(String brand_info) {
		this.brand_info = brand_info;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
}