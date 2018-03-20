package com.api.account.entity;

import java.util.Date;

public class Memorandum{

	private Integer memorandum_id;
	private String memorandum_content;
	private Integer memorandum_type;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;
	
	public Integer getMemorandum_id() {
		return memorandum_id;
	}
	public void setMemorandum_id(Integer memorandum_id) {
		this.memorandum_id = memorandum_id;
	}
	public String getMemorandum_content() {
		return memorandum_content;
	}
	public void setMemorandum_content(String memorandum_content) {
		this.memorandum_content = memorandum_content;
	}
	public Integer getMemorandum_type() {
		return memorandum_type;
	}
	public void setMemorandum_type(Integer memorandum_type) {
		this.memorandum_type = memorandum_type;
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