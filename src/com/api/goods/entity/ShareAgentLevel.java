package com.api.goods.entity;

import java.util.Date;

public class ShareAgentLevel {
	private Integer shareagentlevel_id;
	private Integer sharebrand_id;
	private Integer agentlevel_id;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer state;
	
	public Integer getShareagentlevel_id() {
		return shareagentlevel_id;
	}
	public void setShareagentlevel_id(Integer shareagentlevel_id) {
		this.shareagentlevel_id = shareagentlevel_id;
	}
	public Integer getSharebrand_id() {
		return sharebrand_id;
	}
	public void setSharebrand_id(Integer sharebrand_id) {
		this.sharebrand_id = sharebrand_id;
	}
	public Integer getAgentlevel_id() {
		return agentlevel_id;
	}
	public void setAgentlevel_id(Integer agentlevel_id) {
		this.agentlevel_id = agentlevel_id;
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