package com.api.customer.entity;

import java.util.List;

import com.api.common.entity.Result;

public class CustomerTopResult extends Result{

	private List<?> list;

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
}