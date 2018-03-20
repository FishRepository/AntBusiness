package com.api.sync.entity;

import com.api.common.entity.Result;
import com.api.common.utils.PropertiesUtil;
import com.api.common.utils.StringUtil;

public class AppVersion extends Result{

	private Integer app_version;
	private String app_url;
	
	public Integer getApp_version() {
		return app_version;
	}
	public void setApp_version(Integer app_version) {
		this.app_version = app_version;
	}
	public String getApp_url() {
		if(StringUtil.isValid(app_url)){
			if(app_url.startsWith("http")){
				return app_url;
			}else{
				return PropertiesUtil.getKeyValue("img.showpath")+app_url;
			}
		}else{
			return app_url;
		}
	}
	public void setApp_url(String app_url) {
		this.app_url = app_url;
	}
}