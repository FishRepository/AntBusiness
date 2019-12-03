package com.api.goods.entity;

import java.util.List;

import com.api.common.entity.Result;

public class BrandRecommendResult extends Result{

	private BrandRecommend brand;
	private List<AgentLevel> agentlevellist;
	private List<Goods> goodslist;
	private List<AgentLevel> codeAgentLevelList;
	
	public BrandRecommend getBrand() {
		return brand;
	}
	public void setBrand(BrandRecommend brand) {
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

	public List<AgentLevel> getCodeAgentLevelList() {
		return codeAgentLevelList;
	}

	public void setCodeAgentLevelList(List<AgentLevel> codeAgentLevelList) {
		this.codeAgentLevelList = codeAgentLevelList;
	}
}