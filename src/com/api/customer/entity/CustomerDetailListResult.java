package com.api.customer.entity;

import java.util.List;

import com.api.common.entity.Result;

public class CustomerDetailListResult extends Result{

	private CustomerResult customer;
	private List<DetailResult> phonelist;
	private List<DetailResult> addresslist;
	
	public CustomerResult getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerResult customer) {
		this.customer = customer;
	}
	public List<DetailResult> getPhonelist() {
		return phonelist;
	}
	public void setPhonelist(List<DetailResult> phonelist) {
		this.phonelist = phonelist;
	}
	public List<DetailResult> getAddresslist() {
		return addresslist;
	}
	public void setAddresslist(List<DetailResult> addresslist) {
		this.addresslist = addresslist;
	}
}