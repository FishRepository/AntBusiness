package com.api.goods.entity;

import java.util.List;

public class BrandAndAgentLevel{

	private Integer brand_id;
	private String brand_name;
	private Integer goods_count;
	private Integer images_count;
	private Integer threshold_count;
	private Integer brand_from;//品牌来源0：自行创建 >0推荐下载品牌编号 <0好友分享品牌编号
	private Integer brand_index;
	private Integer account_id;//所属账号0表示推荐品牌
	private List<AgentLevel> agentlevellist;
	private BrandGoodsInfo brandGoodsInfo;
	private String brandimages_url;
	private Integer stockState;//1、低于预警库存 2、缺货 3、尚未设置库存 4、尚未设置库存提醒 5、盘点提醒 6、累计销售及订单统计结果
	private String logo_url;
	private String brand_info;
	private String title;

	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public Integer getGoods_count() {
		return goods_count;
	}
	public void setGoods_count(Integer goods_count) {
		this.goods_count = goods_count;
	}
	public Integer getImages_count() {
		return images_count;
	}
	public void setImages_count(Integer images_count) {
		this.images_count = images_count;
	}
	public Integer getThreshold_count() {
		return threshold_count;
	}
	public void setThreshold_count(Integer threshold_count) {
		this.threshold_count = threshold_count;
	}
	public Integer getBrand_from() {
		return brand_from;
	}
	public void setBrand_from(Integer brand_from) {
		this.brand_from = brand_from;
	}
	public Integer getBrand_index() {
		return brand_index;
	}
	public void setBrand_index(Integer brand_index) {
		this.brand_index = brand_index;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public List<AgentLevel> getAgentlevellist() {
		return agentlevellist;
	}
	public void setAgentlevellist(List<AgentLevel> agentlevellist) {
		this.agentlevellist = agentlevellist;
	}

	public String getBrandimages_url() {
		return brandimages_url;
	}

	public void setBrandimages_url(String brandimages_url) {
		this.brandimages_url = brandimages_url;
	}

	public BrandGoodsInfo getBrandGoodsInfo() {
		return brandGoodsInfo;
	}

	public void setBrandGoodsInfo(BrandGoodsInfo brandGoodsInfo) {
		this.brandGoodsInfo = brandGoodsInfo;
	}

	public Integer getStockState() {
		return stockState;
	}

	public void setStockState(Integer stockState) {
		this.stockState = stockState;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public String getBrand_info() {
		return brand_info;
	}

	public void setBrand_info(String brand_info) {
		this.brand_info = brand_info;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}