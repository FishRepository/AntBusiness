package com.api.account.entity;

import com.api.common.page.Page;

public class ListPageMemorandum extends Page{

	private Integer account_id;
	private Integer type;

	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}