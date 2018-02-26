package com.api.account.entity;

import java.util.Date;

import com.api.common.utils.PropertiesUtil;
import com.api.common.utils.StringUtil;

public class AdviseResult{

	private Integer advise_id;
	private String advise_content;
	private Integer account_id;
	private String account_username;
	private String account_icon;
	private Date create_time;
	
	public Integer getAdvise_id() {
		return advise_id;
	}
	public void setAdvise_id(Integer advise_id) {
		this.advise_id = advise_id;
	}
	public String getAdvise_content() {
		return advise_content;
	}
	public void setAdvise_content(String advise_content) {
		this.advise_content = advise_content;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public String getAccount_username() {
		return account_username;
	}
	public void setAccount_username(String account_username) {
		this.account_username = account_username;
	}
	public String getAccount_icon() {
		if(StringUtil.isValid(account_icon)){
			if(account_icon.startsWith("http")){
				return account_icon;
			}else{
				return PropertiesUtil.getKeyValue("img.showpath")+account_icon;
			}
		}else{
			return account_icon;
		}
	}
	public void setAccount_icon(String account_icon) {
		this.account_icon = account_icon;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}