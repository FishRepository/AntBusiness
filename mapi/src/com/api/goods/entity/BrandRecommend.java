package com.api.goods.entity;


public class BrandRecommend{

	private Integer brand_id;
	private String brand_name;
	private Integer goods_count;
	private Float brand_price;
	private int isdownload;//是否已经下载  0未下载1已下载

	private Integer stock_remind;//库存提醒数量
	private Integer is_hot;
	private Integer brand_info;
	private String logo_url;
	private String title;

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

	public Integer getBrand_info() {
		return brand_info;
	}

	public void setBrand_info(Integer brand_info) {
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
}