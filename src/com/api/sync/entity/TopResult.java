package com.api.sync.entity;

import java.util.List;

import com.api.common.entity.Result;

public class TopResult extends Result{

	private List<?> list;

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
}