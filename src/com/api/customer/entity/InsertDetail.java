package com.api.customer.entity;

import com.api.common.entity.Result;

public class InsertDetail extends Result{

	private Integer customer_id;
	private Integer account_id;
	private String phonelist;
	private String addresslist;
	
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public String getPhonelist() {
		return phonelist;
	}
	public void setPhonelist(String phonelist) {
		this.phonelist = phonelist;
	}
	public String getAddresslist() {
		return addresslist;
	}
	public void setAddresslist(String addresslist) {
		this.addresslist = addresslist;
	}
}