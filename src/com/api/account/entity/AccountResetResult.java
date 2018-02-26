package com.api.account.entity;

import com.api.common.entity.Result;

public class AccountResetResult extends Result{

	private Integer account_id;// 管理员编号

	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
}