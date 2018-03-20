package com.api.goods.entity;

import java.util.List;

public class ShareDownload {
	private Integer download_id;
	private List<ShareDownloadShare> sharelist;
	
	public Integer getDownload_id() {
		return download_id;
	}
	public void setDownload_id(Integer download_id) {
		this.download_id = download_id;
	}
	public List<ShareDownloadShare> getSharelist() {
		return sharelist;
	}
	public void setSharelist(List<ShareDownloadShare> sharelist) {
		this.sharelist = sharelist;
	}
}