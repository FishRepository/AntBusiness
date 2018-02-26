package com.api.sync.entity;

public class AccountDay{

	private Integer account_id;
	private String account_userphone;
	private String time;
	
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}