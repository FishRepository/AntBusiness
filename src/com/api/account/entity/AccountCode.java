package com.api.account.entity;

import java.util.Date;

public class AccountCode{

	private Integer accountcode_id;// 主键
	private String account_userphone;// 管理员手机号码
	private String account_code;// 验证码
	private Date create_time;// 创建时间
	private Date update_time;// 更新时间
	private Integer state;// 状态0无效1有效
	
	public Integer getAccountcode_id() {
		return accountcode_id;
	}
	public void setAccountcode_id(Integer accountcode_id) {
		this.accountcode_id = accountcode_id;
	}
	public String getAccount_userphone() {
		return account_userphone;
	}
	public void setAccount_userphone(String account_userphone) {
		this.account_userphone = account_userphone;
	}
	public String getAccount_code() {
		return account_code;
	}
	public void setAccount_code(String account_code) {
		this.account_code = account_code;
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