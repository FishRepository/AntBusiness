package com.api.customer.entity;

import com.api.common.utils.PropertiesUtil;
import com.api.common.utils.StringUtil;

import java.util.Date;

public class CustomerResult{
	
	private Integer customer_id;
	private String customer_username;
	private String customer_icon;
	private Date birthday;//生日
	private String constellation;//星座
	private Date period;//经期
	
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public Date getPeriod() {
		return period;
	}

	public void setPeriod(Date period) {
		this.period = period;
	}
}