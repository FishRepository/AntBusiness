package com.api.sync.entity;

import java.util.List;

import com.api.common.entity.Result;

public class NoticeList extends Result{

	private List<Notice> list;
	private int issale; //0今日未开单1今日已开单

	public List<Notice> getList() {
		return list;
	}

	public void setList(List<Notice> list) {
		this.list = list;
	}

	public int getIssale() {
		return issale;
	}

	public void setIssale(int issale) {
		this.issale = issale;
	}
}