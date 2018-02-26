package com.api.goods.entity;

import java.util.List;

public class IndexBrandList{
	private Integer account_id;
	private List<IndexId> list;
	
	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public List<IndexId> getList() {
		return list;
	}

	public void setList(List<IndexId> list) {
		this.list = list;
	}
}