package com.api.goods.entity;

import com.api.common.utils.PropertiesUtil;
import com.api.common.utils.StringUtil;

public class BrandImagesResult{

	private Integer brandimages_id;
	private String brandimages_url;
	
	public Integer getBrandimages_id() {
		return brandimages_id;
	}
	public void setBrandimages_id(Integer brandimages_id) {
		this.brandimages_id = brandimages_id;
	}
	public String getBrandimages_url() {
		if(StringUtil.isValid(brandimages_url)){
			if(brandimages_url.startsWith("http")){
				return brandimages_url;
			}else{
				return PropertiesUtil.getKeyValue("img.showpath")+brandimages_url;
			}
		}else{
			return brandimages_url;
		}
	}
	public void setBrandimages_url(String brandimages_url) {
		this.brandimages_url = brandimages_url;
	}
}