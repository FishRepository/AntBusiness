package com.api.customer.entity;

import java.util.List;

import com.api.common.entity.Result;

public class CustomerDetailDefaultResult extends Result{

	private CustomerResult customer;
	private DetailResult customerphone;
	private DetailResult customeraddress;
    private CustomerProfit customerprofit;
	private List<GoodsNumTotal> goodslist;
    
	public CustomerResult getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerResult customer) {
		this.customer = customer;
	}
	public DetailResult getCustomerphone() {
		return customerphone;
	}
	public void setCustomerphone(DetailResult customerphone) {
		this.customerphone = customerphone;
	}
	public DetailResult getCustomeraddress() {
		return customeraddress;
	}
	public void setCustomeraddress(DetailResult customeraddress) {
		this.customeraddress = customeraddress;
	}
	public CustomerProfit getCustomerprofit() {
		return customerprofit;
	}
	public void setCustomerprofit(CustomerProfit customerprofit) {
		this.customerprofit = customerprofit;
	}
	public List<GoodsNumTotal> getGoodslist() {
		return goodslist;
	}
	public void setGoodslist(List<GoodsNumTotal> goodslist) {
		this.goodslist = goodslist;
	}
}