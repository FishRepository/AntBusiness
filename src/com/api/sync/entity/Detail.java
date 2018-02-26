package com.api.sync.entity;

import java.util.Date;

public class Detail{

	private Integer detail_id;
	private String detail_name;
	private Integer detail_type;//客户详情类型(1：手机号码 2：地址)
	private Integer detail_default;//客户详情是否默认(0：不是默认 1：默认)
	private Integer customer_id;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;
	
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
	public Integer getDetail_type() {
		return detail_type;
	}
	public void setDetail_type(Integer detail_type) {
		this.detail_type = detail_type;
	}
	public Integer getDetail_default() {
		return detail_default;
	}
	public void setDetail_default(Integer detail_default) {
		this.detail_default = detail_default;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
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