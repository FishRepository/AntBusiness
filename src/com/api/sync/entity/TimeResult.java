package com.api.sync.entity;

import com.api.common.entity.Result;

public class TimeResult extends Result{

	private String nowtime;
	private String createtime;
	
	public String getNowtime() {
		return nowtime;
	}
	public void setNowtime(String nowtime) {
		this.nowtime = nowtime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}