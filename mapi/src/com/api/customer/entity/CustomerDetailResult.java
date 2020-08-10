package com.api.customer.entity;

import com.api.common.utils.PropertiesUtil;
import com.api.common.utils.StringUtil;

public class CustomerDetailResult{
	
	private Integer customer_id;
	private String customer_username;
	private String customer_icon;
	private String birthday;
	private String constellation;
	private String period;
	private Integer is_star;
	private Integer period_state;
	private DetailResult customerphone;
	private DetailResult customeraddress;
	
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Integer getIs_star() {
		return is_star;
	}

	public void setIs_star(Integer is_star) {
		this.is_star = is_star;
	}

	public Integer getPeriod_state() {
		return period_state;
	}

	public void setPeriod_state(Integer period_state) {
		this.period_state = period_state;
	}
}