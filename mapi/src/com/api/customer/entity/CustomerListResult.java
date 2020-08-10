package com.api.customer.entity;

import com.api.common.entity.Result;

import java.util.List;

public class CustomerListResult extends Result{

	private List<CustomerDetailResult> favorList;

	private List<CustomerDetailResult> list;

	public List<CustomerDetailResult> getList() {
		return list;
	}

	public void setList(List<CustomerDetailResult> list) {
		this.list = list;
	}

	public List<CustomerDetailResult> getFavorList() {
		return favorList;
	}

	public void setFavorList(List<CustomerDetailResult> favorList) {
		this.favorList = favorList;
	}
}