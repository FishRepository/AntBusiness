package com.api.sync.entity;

import java.util.Date;

public class AgentLevel{

	private Integer agentlevel_id;
	private String agentlevel_name;
	private Integer agentlevel_default;
	private Integer agentlevel_index;
	private Integer brand_id;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;
	
	public Integer getAgentlevel_id() {
		return agentlevel_id;
	}
	public void setAgentlevel_id(Integer agentlevel_id) {
		this.agentlevel_id = agentlevel_id;
	}
	public String getAgentlevel_name() {
		return agentlevel_name;
	}
	public void setAgentlevel_name(String agentlevel_name) {
		this.agentlevel_name = agentlevel_name;
	}
	public Integer getAgentlevel_default() {
		return agentlevel_default;
	}
	public void setAgentlevel_default(Integer agentlevel_default) {
		this.agentlevel_default = agentlevel_default;
	}
	public Integer getAgentlevel_index() {
		return agentlevel_index;
	}
	public void setAgentlevel_index(Integer agentlevel_index) {
		this.agentlevel_index = agentlevel_index;
	}
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
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