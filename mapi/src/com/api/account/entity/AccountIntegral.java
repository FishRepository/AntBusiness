package com.api.account.entity;

import com.api.common.entity.Result;

public class AccountIntegral extends Result{

	private Integer account_integral = 0;//账号总积分

	public Integer getAccount_integral() {
		return account_integral;
	}

	public void setAccount_integral(Integer account_integral) {
		this.account_integral = account_integral;
	}
}