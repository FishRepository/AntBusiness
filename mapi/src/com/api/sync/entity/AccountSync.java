package com.api.sync.entity;

import java.util.List;

import com.api.common.entity.Result;

public class AccountSync extends Result{

	private List<Brand> brandlist;
	private List<BrandImages> brandimageslist;
	private List<AgentLevel> agentlevellist;
	private List<Goods> goodslist;
	private List<GoodsPrice> goodspricelist;
	private List<Customer> customerlist;
	private List<Detail> detaillist;
	private List<Order> orderlist;
	private List<OrderGoods> ordergoodslist;
	
	public List<Brand> getBrandlist() {
		return brandlist;
	}
	public void setBrandlist(List<Brand> brandlist) {
		this.brandlist = brandlist;
	}
	public List<BrandImages> getBrandimageslist() {
		return brandimageslist;
	}
	public void setBrandimageslist(List<BrandImages> brandimageslist) {
		this.brandimageslist = brandimageslist;
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
	public List<Customer> getCustomerlist() {
		return customerlist;
	}
	public void setCustomerlist(List<Customer> customerlist) {
		this.customerlist = customerlist;
	}
	public List<Detail> getDetaillist() {
		return detaillist;
	}
	public void setDetaillist(List<Detail> detaillist) {
		this.detaillist = detaillist;
	}
	public List<Order> getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(List<Order> orderlist) {
		this.orderlist = orderlist;
	}
	public List<OrderGoods> getOrdergoodslist() {
		return ordergoodslist;
	}
	public void setOrdergoodslist(List<OrderGoods> ordergoodslist) {
		this.ordergoodslist = ordergoodslist;
	}
}