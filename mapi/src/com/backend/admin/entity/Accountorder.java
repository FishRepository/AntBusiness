package com.backend.admin.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2018-04-20
 */
@TableName("accountorder")
public class Accountorder extends Model<Accountorder> {

    private static final long serialVersionUID = 1L;

	@TableId(value="order_id", type= IdType.AUTO)
	private Integer orderId;
    /**
     * 订单类型（1：出货 2：进货）
     */
	@TableField("order_type")
	private Integer orderType;
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
     * 订单商品数量
     */
	@TableField("goods_count")
	private Integer goodsCount;
    /**
     * 订单总销售
     */
	@TableField("order_sales")
	private Float orderSales;
    /**
     * 订单总成本
     */
	@TableField("order_cost")
	private Float orderCost;
    /**
     * 订单总利润
     */
	@TableField("order_profit")
	private Float orderProfit;
    /**
     * 订单额外费用  正数算销售增利润  负数算成本减利润
     */
	@TableField("order_premium")
	private Float orderPremium;
    /**
     * 订单额外费用编号
     */
	@TableField("premium_id")
	private Integer premiumId;
    /**
     * 订单额外费用名称
     */
	@TableField("premium_name")
	private String premiumName;
    /**
     * 订单商品总销售
     */
	@TableField("order_goodssales")
	private Float orderGoodssales;
    /**
     * 订单商品总成本
     */
	@TableField("order_goodscost")
	private Float orderGoodscost;
    /**
     * 订单商品总利润
     */
	@TableField("order_goodsprofit")
	private Float orderGoodsprofit;
    /**
     * 客户编号
     */
	@TableField("customer_id")
	private Integer customerId;
    /**
     * 客户姓名
     */
	@TableField("customer_username")
	private String customerUsername;
    /**
     * 客户头像
     */
	@TableField("customer_icon")
	private String customerIcon;
    /**
     * 客户手机号码
     */
	@TableField("customer_phone")
	private String customerPhone;
    /**
     * 客户手机号码编号
     */
	@TableField("customer_phoneid")
	private Integer customerPhoneid;
    /**
     * 客户地址
     */
	@TableField("customer_address")
	private String customerAddress;
    /**
     * 客户地址编号
     */
	@TableField("customer_addressid")
	private Integer customerAddressid;
    /**
     * 订单备注
     */
	@TableField("order_remark")
	private String orderRemark;
    /**
     * 订单品牌概述
     */
	@TableField("brand_names")
	private String brandNames;
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
     * 状态0未处理1已处理
     */
	private Integer state;


	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
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

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Float getOrderSales() {
		return orderSales;
	}

	public void setOrderSales(Float orderSales) {
		this.orderSales = orderSales;
	}

	public Float getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(Float orderCost) {
		this.orderCost = orderCost;
	}

	public Float getOrderProfit() {
		return orderProfit;
	}

	public void setOrderProfit(Float orderProfit) {
		this.orderProfit = orderProfit;
	}

	public Float getOrderPremium() {
		return orderPremium;
	}

	public void setOrderPremium(Float orderPremium) {
		this.orderPremium = orderPremium;
	}

	public Integer getPremiumId() {
		return premiumId;
	}

	public void setPremiumId(Integer premiumId) {
		this.premiumId = premiumId;
	}

	public String getPremiumName() {
		return premiumName;
	}

	public void setPremiumName(String premiumName) {
		this.premiumName = premiumName;
	}

	public Float getOrderGoodssales() {
		return orderGoodssales;
	}

	public void setOrderGoodssales(Float orderGoodssales) {
		this.orderGoodssales = orderGoodssales;
	}

	public Float getOrderGoodscost() {
		return orderGoodscost;
	}

	public void setOrderGoodscost(Float orderGoodscost) {
		this.orderGoodscost = orderGoodscost;
	}

	public Float getOrderGoodsprofit() {
		return orderGoodsprofit;
	}

	public void setOrderGoodsprofit(Float orderGoodsprofit) {
		this.orderGoodsprofit = orderGoodsprofit;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public String getCustomerIcon() {
		return customerIcon;
	}

	public void setCustomerIcon(String customerIcon) {
		this.customerIcon = customerIcon;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Integer getCustomerPhoneid() {
		return customerPhoneid;
	}

	public void setCustomerPhoneid(Integer customerPhoneid) {
		this.customerPhoneid = customerPhoneid;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Integer getCustomerAddressid() {
		return customerAddressid;
	}

	public void setCustomerAddressid(Integer customerAddressid) {
		this.customerAddressid = customerAddressid;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public String getBrandNames() {
		return brandNames;
	}

	public void setBrandNames(String brandNames) {
		this.brandNames = brandNames;
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
		return this.orderId;
	}

	@Override
	public String toString() {
		return "Accountorder{" +
			"orderId=" + orderId +
			", orderType=" + orderType +
			", brandName=" + brandName +
			", brandId=" + brandId +
			", agentlevelName=" + agentlevelName +
			", agentlevelId=" + agentlevelId +
			", goodsCount=" + goodsCount +
			", orderSales=" + orderSales +
			", orderCost=" + orderCost +
			", orderProfit=" + orderProfit +
			", orderPremium=" + orderPremium +
			", premiumId=" + premiumId +
			", premiumName=" + premiumName +
			", orderGoodssales=" + orderGoodssales +
			", orderGoodscost=" + orderGoodscost +
			", orderGoodsprofit=" + orderGoodsprofit +
			", customerId=" + customerId +
			", customerUsername=" + customerUsername +
			", customerIcon=" + customerIcon +
			", customerPhone=" + customerPhone +
			", customerPhoneid=" + customerPhoneid +
			", customerAddress=" + customerAddress +
			", customerAddressid=" + customerAddressid +
			", orderRemark=" + orderRemark +
			", brandNames=" + brandNames +
			", accountId=" + accountId +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", state=" + state +
			"}";
	}
}
