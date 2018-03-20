package com.api.order.entity;

import com.api.common.page.Page;

public class ListPageOrder extends Page{

	private Integer account_id;
	private Integer customer_id;
	private Integer type;//0:任何订单  1:出货待完成订单  2:出货已完成订单 3:出货全部订单 4：进货全部订单
	
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}