package com.api.account.entity;

import java.util.Date;

public class Integral{

	private Integer integral_id;
	private Integer ext_type;
	private Integer ext_id;
	private Integer integral_value;
	private Integer old_value;
	private Integer new_value;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;
	
	public Integer getIntegral_id() {
		return integral_id;
	}
	public void setIntegral_id(Integer integral_id) {
		this.integral_id = integral_id;
	}
	public Integer getExt_type() {
		return ext_type;
	}
	public void setExt_type(Integer ext_type) {
		this.ext_type = ext_type;
	}
	public Integer getExt_id() {
		return ext_id;
	}
	public void setExt_id(Integer ext_id) {
		this.ext_id = ext_id;
	}
	public Integer getIntegral_value() {
		return integral_value;
	}
	public void setIntegral_value(Integer integral_value) {
		this.integral_value = integral_value;
	}
	public Integer getOld_value() {
		return old_value;
	}
	public void setOld_value(Integer old_value) {
		this.old_value = old_value;
	}
	public Integer getNew_value() {
		return new_value;
	}
	public void setNew_value(Integer new_value) {
		this.new_value = new_value;
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