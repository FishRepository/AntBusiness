package com.api.account.entity;

import java.util.List;

import com.api.common.entity.Result;

public class AccountQueryResult extends Result{

	private Integer totalreg;
	
	private Integer totaltimereg;
	
	private List<String> phonelist;

	public Integer getTotalreg() {
		return totalreg;
	}

	public void setTotalreg(Integer totalreg) {
		this.totalreg = totalreg;
	}

	public Integer getTotaltimereg() {
		return totaltimereg;
	}

	public void setTotaltimereg(Integer totaltimereg) {
		this.totaltimereg = totaltimereg;
	}

	public List<String> getPhonelist() {
		return phonelist;
	}

	public void setPhonelist(List<String> phonelist) {
		this.phonelist = phonelist;
	}
}