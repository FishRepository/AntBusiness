package com.api.account.entity;

import java.util.Date;

public class ShareAccount {
	private Integer shareaccount_id;
	private Integer account_id;
	private String account_userphone;
	private Date create_time;
	private Date update_time;
	private Integer state;
	
	public Integer getShareaccount_id() {
		return shareaccount_id;
	}
	public void setShareaccount_id(Integer shareaccount_id) {
		this.shareaccount_id = shareaccount_id;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public String getAccount_userphone() {
		return account_userphone;
	}
	public void setAccount_userphone(String account_userphone) {
		this.account_userphone = account_userphone;
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