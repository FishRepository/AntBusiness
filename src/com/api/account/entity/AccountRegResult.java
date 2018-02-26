package com.api.account.entity;

import com.api.common.entity.Result;

public class AccountRegResult extends Result{

	private Integer account_id;// 管理员编号
	
	private Integer ischange = 0;//imei是否已经改变  0未改变 1已改变
	
	private Integer reg_integral;//注册积分

	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public Integer getIschange() {
		return ischange;
	}

	public void setIschange(Integer ischange) {
		this.ischange = ischange;
	}

	public Integer getReg_integral() {
		return reg_integral;
	}

	public void setReg_integral(Integer reg_integral) {
		this.reg_integral = reg_integral;
	}
}