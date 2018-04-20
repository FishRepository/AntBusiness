package com.backend.admin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2018-04-20
 */
@TableName("ordergoods")
public class Ordergoods extends Model<Ordergoods> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="ordergoods_id", type= IdType.AUTO)
	private Integer ordergoodsId;
    /**
     * 订单编号
     */
	@TableField("order_id")
	private Integer orderId;
    /**
     * 品牌名称
     */
	@TableField("brand_name")
	private String brandName;
    /**
     * 品牌编号
     */
	@TableField("brand_id")
	private Integer brandId;
    /**
     * 代理商级别
     */
	@TableField("agentlevel_name")
	private String agentlevelName;
    /**
     * 代理商级别编号
     */
	@TableField("agentlevel_id")
	private Integer agentlevelId;
    /**
     * 商品编号
     */
	@TableField("goods_id")
	private Integer goodsId;
    /**
     * 商品名称
     */
	@TableField("goods_name")
	private String goodsName;
    /**
     * 商品代理商价格 为0表示成本价
     */
	@TableField("goodsprice_id")
	private Integer goodspriceId;
    /**
     * 商品价格
     */
	@TableField("goods_price")
	private Float goodsPrice;
    /**
     * 商品成本价格
     */
	@TableField("goods_costprice")
	private Float goodsCostprice;
    /**
     * 商品数量
     */
	@TableField("goods_num")
	private Integer goodsNum;
    /**
     * 商品总销售
     */
	@TableField("ordergoods_sales")
	private Float ordergoodsSales;
    /**
     * 商品总成本
     */
	@TableField("ordergoods_cost")
	private Float ordergoodsCost;
    /**
     * 商品总利润
     */
	@TableField("ordergoods_profit")
	private Float ordergoodsProfit;
    /**
     * 所属账号
     */
	@TableField("account_id")
	private Integer accountId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private Date updateTime;
    /**
     * 状态0无效1有效
     */
	private Integer state;


	public Integer getOrdergoodsId() {
		return ordergoodsId;
	}

	public void setOrdergoodsId(Integer ordergoodsId) {
		this.ordergoodsId = ordergoodsId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getAgentlevelName() {
		return agentlevelName;
	}

	public void setAgentlevelName(String agentlevelName) {
		this.agentlevelName = agentlevelName;
	}

	public Integer getAgentlevelId() {
		return agentlevelId;
	}

	public void setAgentlevelId(Integer agentlevelId) {
		this.agentlevelId = agentlevelId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodspriceId() {
		return goodspriceId;
	}

	public void setGoodspriceId(Integer goodspriceId) {
		this.goodspriceId = goodspriceId;
	}

	public Float getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Float getGoodsCostprice() {
		return goodsCostprice;
	}

	public void setGoodsCostprice(Float goodsCostprice) {
		this.goodsCostprice = goodsCostprice;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Float getOrdergoodsSales() {
		return ordergoodsSales;
	}

	public void setOrdergoodsSales(Float ordergoodsSales) {
		this.ordergoodsSales = ordergoodsSales;
	}

	public Float getOrdergoodsCost() {
		return ordergoodsCost;
	}

	public void setOrdergoodsCost(Float ordergoodsCost) {
		this.ordergoodsCost = ordergoodsCost;
	}

	public Float getOrdergoodsProfit() {
		return ordergoodsProfit;
	}

	public void setOrdergoodsProfit(Float ordergoodsProfit) {
		this.ordergoodsProfit = ordergoodsProfit;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	protected Serializable pkVal() {
		return this.ordergoodsId;
	}

	@Override
	public String toString() {
		return "Ordergoods{" +
			"ordergoodsId=" + ordergoodsId +
			", orderId=" + orderId +
			", brandName=" + brandName +
			", brandId=" + brandId +
			", agentlevelName=" + agentlevelName +
			", agentlevelId=" + agentlevelId +
			", goodsId=" + goodsId +
			", goodsName=" + goodsName +
			", goodspriceId=" + goodspriceId +
			", goodsPrice=" + goodsPrice +
			", goodsCostprice=" + goodsCostprice +
			", goodsNum=" + goodsNum +
			", ordergoodsSales=" + ordergoodsSales +
			", ordergoodsCost=" + ordergoodsCost +
			", ordergoodsProfit=" + ordergoodsProfit +
			", accountId=" + accountId +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", state=" + state +
			"}";
	}
}
