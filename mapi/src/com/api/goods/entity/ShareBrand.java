package com.api.goods.entity;

import java.util.Date;

public class ShareBrand {
	private Integer sharebrand_id;
	private Integer share_id;
	private Integer brand_id;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;
	
	public Integer getSharebrand_id() {
		return sharebrand_id;
	}
	public void setSharebrand_id(Integer sharebrand_id) {
		this.sharebrand_id = sharebrand_id;
	}
	public Integer getShare_id() {
		return share_id;
	}
	public void setShare_id(Integer share_id) {
		this.share_id = share_id;
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