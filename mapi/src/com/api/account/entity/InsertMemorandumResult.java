package com.api.account.entity;

import com.api.common.entity.Result;

public class InsertMemorandumResult extends Result{

	private Integer memorandum_id;

	public Integer getMemorandum_id() {
		return memorandum_id;
	}

	public void setMemorandum_id(Integer memorandum_id) {
		this.memorandum_id = memorandum_id;
	}
}