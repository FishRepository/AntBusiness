package com.api.customer.entity;

public class DetailResult{

	private Integer detail_id;
	private String detail_name;
	private Integer detail_default;//客户详情是否默认(0：不是默认 1：默认)
	
	public Integer getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(Integer detail_id) {
		this.detail_id = detail_id;
	}
	public String getDetail_name() {
		return detail_name;
	}
	public void setDetail_name(String detail_name) {
		this.detail_name = detail_name;
	}
	public Integer getDetail_default() {
		return detail_default;
	}
	public void setDetail_default(Integer detail_default) {
		this.detail_default = detail_default;
	}
}