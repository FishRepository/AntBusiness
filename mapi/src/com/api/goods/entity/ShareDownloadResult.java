package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;

public class ShareDownloadResult extends Result{
	private List<Integer> failbrandlist;
	private List<Integer> successbrandlist;

	public List<Integer> getFailbrandlist() {
		return failbrandlist;
	}

	public void setFailbrandlist(List<Integer> failbrandlist) {
		this.failbrandlist = failbrandlist;
	}

	public List<Integer> getSuccessbrandlist() {
		return successbrandlist;
	}

	public void setSuccessbrandlist(List<Integer> successbrandlist) {
		this.successbrandlist = successbrandlist;
	}
}