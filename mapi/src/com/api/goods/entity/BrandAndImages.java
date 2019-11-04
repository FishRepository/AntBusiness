package com.api.goods.entity;

import java.util.List;

public class BrandAndImages{

	private BrandAndAgentLevel brand;
	private List<BrandImagesResult> imageslist;
	private BrandGoodsInfo brandGoodsInfo;
	
	public BrandAndAgentLevel getBrand() {
		return brand;
	}
	public void setBrand(BrandAndAgentLevel brand) {
		this.brand = brand;
	}
	public List<BrandImagesResult> getImageslist() {
		return imageslist;
	}
	public void setImageslist(List<BrandImagesResult> imageslist) {
		this.imageslist = imageslist;
	}

	public BrandGoodsInfo getBrandGoodsInfo() {
		return brandGoodsInfo;
	}

	public void setBrandGoodsInfo(BrandGoodsInfo brandGoodsInfo) {
		this.brandGoodsInfo = brandGoodsInfo;
	}
}