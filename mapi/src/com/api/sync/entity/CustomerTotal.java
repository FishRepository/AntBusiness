package com.api.sync.entity;

import com.api.common.utils.PropertiesUtil;
import com.api.common.utils.StringUtil;

public class CustomerTotal {
	private Integer customer_id;
	private String customer_username;
	private String customer_icon;
	private Float total = 0f;
	private Float profittotal = 0f;
	
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_username() {
		return customer_username;
	}
	public void setCustomer_username(String customer_username) {
		this.customer_username = customer_username;
	}
	public String getCustomer_icon() {
		if(StringUtil.isValid(customer_icon)){
			if(customer_icon.startsWith("http")){
				return customer_icon;
			}else{
				return PropertiesUtil.getKeyValue("img.showpath")+customer_icon;
			}
		}else{
			return customer_icon;
		}
	}
	public void setCustomer_icon(String customer_icon) {
		this.customer_icon = customer_icon;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public Float getProfittotal() {
		return profittotal;
	}
	public void setProfittotal(Float profittotal) {
		this.profittotal = profittotal;
	}
}