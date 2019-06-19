package com.api.order.entity;

import java.util.Date;

import com.api.common.utils.PropertiesUtil;
import com.api.common.utils.StringUtil;

public class OrderList{

	private Integer order_id;
	private Integer order_type;//订单类型（1：出货 2：进货）
	private Integer goods_count;
	private Float order_sales;
	private Integer customer_id;
	private String customer_username;
	private String customer_icon;
	private String brand_names;
	private Date create_time;
	private Integer state;//状态0未处理1已处理
	private String tag_name;
	private String tag_color;
	
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
		if(StringUtil.isValid(customer_icon)){
			if(customer_icon.startsWith("http")){
				return customer_icon;
			}else{
				return PropertiesUtil.getKeyValue("img.showpath")+customer_icon;
			}
		}else{
			return customer_icon;
		}
	}
	public void setCustomer_icon(String customer_icon) {
		this.customer_icon = customer_icon;
	}
	public String getBrand_names() {
		return brand_names;
	}
	public void setBrand_names(String brand_names) {
		this.brand_names = brand_names;
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
}