package com.api.customer.entity;

import java.util.Date;

public class Customer{
	
	private Integer customer_id;
	private String customer_username;
	private String customer_icon;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;
	private String birthday;//生日
	private String constellation;//星座
	private String period;//经期
	private Integer period_notified;//客户生理期间是否通知了用户 0,未通知 1,已通知
	private Date period_notify_time;
	private Integer is_star;//星标用户 0不是 1是
	private Integer period_state;//生理期状态 0无状态 1即将到达生理期 2在生理期中
	
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
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Integer getPeriod_notified() {
		return period_notified;
	}
	public void setPeriod_notified(Integer period_notified) {
		this.period_notified = period_notified;
	}

	public Date getPeriod_notify_time() {
		return period_notify_time;
	}

	public void setPeriod_notify_time(Date period_notify_time) {
		this.period_notify_time = period_notify_time;
	}

	public Integer getIs_star() {
		return is_star;
	}

	public void setIs_star(Integer is_star) {
		this.is_star = is_star;
	}

	public Integer getPeriod_state() {
		return period_state;
	}

	public void setPeriod_state(Integer period_state) {
		this.period_state = period_state;
	}
}