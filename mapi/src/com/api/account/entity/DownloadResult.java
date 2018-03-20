package com.api.account.entity;

import java.util.List;

import com.api.common.entity.Result;
import com.api.goods.entity.AgentLevel;
import com.api.goods.entity.Brand;
import com.api.goods.entity.Goods;
import com.api.goods.entity.GoodsPrice;

public class DownloadResult extends Result{

	private Integer integral_value;
	private Integer old_value;
	private Integer new_value;
	private Brand brand;
	private List<AgentLevel> agentlevellist;
	private List<Goods> goodslist;
	private List<GoodsPrice> goodspricelist;
	
	public Integer getIntegral_value() {
		return integral_value;
	}
	public void setIntegral_value(Integer integral_value) {
		this.integral_value = integral_value;
	}
	public Integer getOld_value() {
		return old_value;
	}
	public void setOld_value(Integer old_value) {
		this.old_value = old_value;
	}
	public Integer getNew_value() {
		return new_value;
	}
	public void setNew_value(Integer new_value) {
		this.new_value = new_value;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public List<AgentLevel> getAgentlevellist() {
		return agentlevellist;
	}
	public void setAgentlevellist(List<AgentLevel> agentlevellist) {
		this.agentlevellist = agentlevellist;
	}
	public List<Goods> getGoodslist() {
		return goodslist;
	}
	public void setGoodslist(List<Goods> goodslist) {
		this.goodslist = goodslist;
	}
	public List<GoodsPrice> getGoodspricelist() {
		return goodspricelist;
	}
	public void setGoodspricelist(List<GoodsPrice> goodspricelist) {
		this.goodspricelist = goodspricelist;
	}
}