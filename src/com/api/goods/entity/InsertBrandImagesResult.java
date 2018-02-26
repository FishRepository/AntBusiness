package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;

public class InsertBrandImagesResult extends Result{

	private List<BrandImagesId> brandimages_ids;

	public List<BrandImagesId> getBrandimages_ids() {
		return brandimages_ids;
	}

	public void setBrandimages_ids(List<BrandImagesId> brandimages_ids) {
		this.brandimages_ids = brandimages_ids;
	}
}