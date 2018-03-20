package com.api.customer.entity;

import java.util.List;

import com.api.common.entity.Result;

public class CustomerListResult extends Result{

	private List<CustomerDetailResult> list;

	public List<CustomerDetailResult> getList() {
		return list;
	}

	public void setList(List<CustomerDetailResult> list) {
		this.list = list;
	}
}