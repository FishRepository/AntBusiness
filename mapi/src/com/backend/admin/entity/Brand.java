package com.backend.admin.entity;

import java.io.Serializable;

import com.api.goods.entity.AgentLevel;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2018-05-05
 */
@TableName("brand")
public class Brand extends Model<Brand> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="brand_id", type= IdType.AUTO)
	private Integer brandId;
    /**
     * 品牌名称
     */
	@TableField("brand_name")
	private String brandName;
    /**
     * 品牌下商品的数量
     */
	@TableField("goods_count")
	private Integer goodsCount;
    /**
     * 品牌下价目图片的数量
     */
	@TableField("images_count")
	private Integer imagesCount;
    /**
     * 商品缺货数量
     */
	@TableField("threshold_count")
	private Integer thresholdCount;
    /**
     * 品牌来源0：自行创建 >0推荐下载品牌编号 <0好友分享品牌编号
     */
	@TableField("brand_from")
	private Integer brandFrom;
    /**
     * 品牌顺序
     */
	@TableField("brand_index")
	private Integer brandIndex;
    /**
     * 品牌搜索名称
     */
	@TableField("brand_shortname")
	private String brandShortname;
    /**
     * 推荐品牌下载兑换码
     */
	@TableField("brand_downloadcode")
	private String brandDownloadcode;
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

	private List<AgentLevel> agents;

	private Double brandPrice;

	public Double getBrandPrice() {
		return brandPrice;
	}

	public void setBrandPrice(Double brandPrice) {
		this.brandPrice = brandPrice;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Integer getImagesCount() {
		return imagesCount;
	}

	public void setImagesCount(Integer imagesCount) {
		this.imagesCount = imagesCount;
	}

	public Integer getThresholdCount() {
		return thresholdCount;
	}

	public void setThresholdCount(Integer thresholdCount) {
		this.thresholdCount = thresholdCount;
	}

	public Integer getBrandFrom() {
		return brandFrom;
	}

	public void setBrandFrom(Integer brandFrom) {
		this.brandFrom = brandFrom;
	}

	public Integer getBrandIndex() {
		return brandIndex;
	}

	public void setBrandIndex(Integer brandIndex) {
		this.brandIndex = brandIndex;
	}

	public String getBrandShortname() {
		return brandShortname;
	}

	public void setBrandShortname(String brandShortname) {
		this.brandShortname = brandShortname;
	}

	public String getBrandDownloadcode() {
		return brandDownloadcode;
	}

	public void setBrandDownloadcode(String brandDownloadcode) {
		this.brandDownloadcode = brandDownloadcode;
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

	public List<AgentLevel> getAgents() {
		return agents;
	}

	public void setAgents(List<AgentLevel> agents) {
		this.agents = agents;
	}

	@Override
	protected Serializable pkVal() {
		return this.brandId;
	}

	@Override
	public String toString() {
		return "Brand{" +
			"brandId=" + brandId +
			", brandName=" + brandName +
			", goodsCount=" + goodsCount +
			", imagesCount=" + imagesCount +
			", thresholdCount=" + thresholdCount +
			", brandFrom=" + brandFrom +
			", brandIndex=" + brandIndex +
			", brandShortname=" + brandShortname +
			", brandDownloadcode=" + brandDownloadcode +
			", accountId=" + accountId +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", state=" + state +
			"}";
	}
}
