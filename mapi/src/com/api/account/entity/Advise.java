package com.api.account.entity;

import java.util.Date;

public class Advise{

	private Integer advise_id;
	private String advise_content;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;
	
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
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}