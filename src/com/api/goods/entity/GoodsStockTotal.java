package com.api.goods.entity;

import java.util.Date;

public class GoodsStockTotal{

	private Integer total_id;
	private Integer total_type;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;
	
	public Integer getTotal_id() {
		return total_id;
	}
	public void setTotal_id(Integer total_id) {
		this.total_id = total_id;
	}
	public Integer getTotal_type() {
		return total_type;
	}
	public void setTotal_type(Integer total_type) {
		this.total_type = total_type;
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