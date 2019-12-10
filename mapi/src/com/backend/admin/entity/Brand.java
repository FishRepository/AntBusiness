package com.backend.admin.entity;

import com.api.goods.entity.AgentLevel;
import com.api.goods.entity.BrandImages;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
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
	/**
	 * 否推荐品牌 1是0不是
	 */
	@TableField("is_hot")
	private Integer isHot;
	@TableField("brand_info")
	private String brandInfo;
	@TableField("logo_url")
	private String logoUrl;
	@TableField("title")
	private String title;
	@TableField("phone")
	private String phone;
	@TableField("wechat")
	private String wechat;

	private String images;

	private List<AgentLevel> agents;

	private List<BrandImages> imageList;

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

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public String getBrandInfo() {
		return brandInfo;
	}

	public void setBrandInfo(String brandInfo) {
		this.brandInfo = brandInfo;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

    public List<BrandImages> getImageList() {
        return imageList;
    }

    public void setImageList(List<BrandImages> imageList) {
        this.imageList = imageList;
    }

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
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
