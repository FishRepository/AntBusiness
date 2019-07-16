package com.api.order.entity;

import java.util.Date;

public class OrderDetail{

	private Integer order_id;
	private Integer order_type;//订单类型（1：出货 2：进货）
	private Integer goods_count;
	private Float order_sales;
	private Float order_profit;
	private Float order_premium;
	private Integer premium_id;
	private String premium_name;
	private Integer customer_id;
	private String customer_username;
	private String customer_icon;
	private String customer_phone;
	private Integer customer_phoneid;
	private String customer_address;
	private Integer customer_addressid;
	private String brand_names;
	private String order_remark;
	private Date create_time;
	private Integer state;//状态0未处理1已处理
	private String tag_name;
	private String tag_color;
	private Integer tag_id;
	
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getOrder_type() {
		return order_type;
	}
	public void setOrder_type(Integer order_type) {
		this.order_type = order_type;
	}
	public Integer getGoods_count() {
		return goods_count;
	}
	public void setGoods_count(Integer goods_count) {
		this.goods_count = goods_count;
	}
	public Float getOrder_sales() {
		return order_sales;
	}
	public void setOrder_sales(Float order_sales) {
		this.order_sales = order_sales;
	}
	public Float getOrder_profit() {
		return order_profit;
	}
	public void setOrder_profit(Float order_profit) {
		this.order_profit = order_profit;
	}
	public Float getOrder_premium() {
		return order_premium;
	}
	public void setOrder_premium(Float order_premium) {
		this.order_premium = order_premium;
	}
	public Integer getPremium_id() {
		return premium_id;
	}
	public void setPremium_id(Integer premium_id) {
		this.premium_id = premium_id;
	}
	public String getPremium_name() {
		return premium_name;
	}
	public void setPremium_name(String premium_name) {
		this.premium_name = premium_name;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_username() {
		return customer_username;
	}
	public void setCustomer_username(String customer_username) {
		this.customer_username = customer_username;
	}
	public String getCustomer_icon() {
		return customer_icon;
	}
	public void setCustomer_icon(String customer_icon) {
		this.customer_icon = customer_icon;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public Integer getCustomer_phoneid() {
		return customer_phoneid;
	}
	public void setCustomer_phoneid(Integer customer_phoneid) {
		this.customer_phoneid = customer_phoneid;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public Integer getCustomer_addressid() {
		return customer_addressid;
	}
	public void setCustomer_addressid(Integer customer_addressid) {
		this.customer_addressid = customer_addressid;
	}
	public String getBrand_names() {
		return brand_names;
	}
	public void setBrand_names(String brand_names) {
		this.brand_names = brand_names;
	}
	public String getOrder_remark() {
		return order_remark;
	}
	public void setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	public String getTag_color() {
		return tag_color;
	}

	public void setTag_color(String tag_color) {
		this.tag_color = tag_color;
	}

	public Integer getTag_id() {
		return tag_id;
	}

	public void setTag_id(Integer tag_id) {
		this.tag_id = tag_id;
	}
}