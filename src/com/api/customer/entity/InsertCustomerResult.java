package com.api.customer.entity;

import java.util.List;

import com.api.common.entity.Result;

public class InsertCustomerResult extends Result{

	private Integer customer_id;
	
	private List<DetailResult> phoneidlist;
	
	private List<DetailResult> addressidlist;

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public List<DetailResult> getPhoneidlist() {
		return phoneidlist;
	}

	public void setPhoneidlist(List<DetailResult> phoneidlist) {
		this.phoneidlist = phoneidlist;
	}

	public List<DetailResult> getAddressidlist() {
		return addressidlist;
	}

	public void setAddressidlist(List<DetailResult> addressidlist) {
		this.addressidlist = addressidlist;
	}
}