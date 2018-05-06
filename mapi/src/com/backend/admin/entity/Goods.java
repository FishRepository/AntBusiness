package com.backend.admin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2018-05-05
 */
@TableName("goods")
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="goods_id", type= IdType.AUTO)
	private Integer goodsId;
    /**
     * 商品名称
     */
	@TableField("goods_name")
	private String goodsName;
    /**
     * 商品进货价
     */
	@TableField("goods_price")
	private Float goodsPrice;
    /**
     * 品牌编号
     */
	@TableField("brand_id")
	private Integer brandId;
	private String brandName;
    /**
     * 商品库存
     */
	@TableField("goods_stock")
	private Integer goodsStock;
    /**
     * 是否设置过库存 0未设置 1已设置
     */
	@TableField("goods_setstock")
	private Integer goodsSetstock;
    /**
     * 商品库存阈值
     */
	@TableField("goods_threshold")
	private Integer goodsThreshold;
    /**
     * 箱库存与个库存的比例
     */
	@TableField("goods_scale")
	private Integer goodsScale;
    /**
     * 商品顺序
     */
	@TableField("goods_index")
	private Integer goodsIndex;
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

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
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

	public Float getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(Integer goodsStock) {
		this.goodsStock = goodsStock;
	}

	public Integer getGoodsSetstock() {
		return goodsSetstock;
	}

	public void setGoodsSetstock(Integer goodsSetstock) {
		this.goodsSetstock = goodsSetstock;
	}

	public Integer getGoodsThreshold() {
		return goodsThreshold;
	}

	public void setGoodsThreshold(Integer goodsThreshold) {
		this.goodsThreshold = goodsThreshold;
	}

	public Integer getGoodsScale() {
		return goodsScale;
	}

	public void setGoodsScale(Integer goodsScale) {
		this.goodsScale = goodsScale;
	}

	public Integer getGoodsIndex() {
		return goodsIndex;
	}

	public void setGoodsIndex(Integer goodsIndex) {
		this.goodsIndex = goodsIndex;
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
		return this.goodsId;
	}

	@Override
	public String toString() {
		return "Goods{" +
			"goodsId=" + goodsId +
			", goodsName=" + goodsName +
			", goodsPrice=" + goodsPrice +
			", brandId=" + brandId +
			", goodsStock=" + goodsStock +
			", goodsSetstock=" + goodsSetstock +
			", goodsThreshold=" + goodsThreshold +
			", goodsScale=" + goodsScale +
			", goodsIndex=" + goodsIndex +
			", accountId=" + accountId +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", state=" + state +
			"}";
	}
}
