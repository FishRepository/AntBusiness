package com.api.goods.entity;

import java.util.Date;

public class GoodsStock{

	private Integer goodsstock_id;
	private Integer goods_id;
	private Integer goods_stock;
	private Integer old_stock;
	private Integer new_stock;
	private Integer total_id;
	private Integer account_id;
	private Date create_time;
	private Date update_time;
	private Integer laststate;
	private Integer state;
	
	public Integer getGoodsstock_id() {
		return goodsstock_id;
	}
	public void setGoodsstock_id(Integer goodsstock_id) {
		this.goodsstock_id = goodsstock_id;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public Integer getGoods_stock() {
		return goods_stock;
	}
	public void setGoods_stock(Integer goods_stock) {
		this.goods_stock = goods_stock;
	}
	public Integer getOld_stock() {
		return old_stock;
	}
	public void setOld_stock(Integer old_stock) {
		this.old_stock = old_stock;
	}
	public Integer getNew_stock() {
		return new_stock;
	}
	public void setNew_stock(Integer new_stock) {
		this.new_stock = new_stock;
	}
	public Integer getTotal_id() {
		return total_id;
	}
	public void setTotal_id(Integer total_id) {
		this.total_id = total_id;
	}
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Integer getLaststate() {
		return laststate;
	}
	public void setLaststate(Integer laststate) {
		this.laststate = laststate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}