package com.api.goods.mapper;

import java.util.List;

import com.api.goods.entity.*;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper {
	public int insertBrand(Brand brand);
	
	public int countBrand(Brand brand);
	
	public int updateBrand(Brand brand);
	
	public int updateThresholdCount(Integer brand_id);
	
	public int incBrandGoodsCount(Integer brand_id);
	
	public int incBrandImagesCount(Integer brand_id);
	
	public int decBrandGoodsCount(Integer brand_id);
	
	public int decBrandImagesCount(Integer brand_id);
	
	public String queryBrandById(Brand brand);
	
	public String queryBrandNameById(Integer account_id);
	
	public List<BrandAndAgentLevel> queryBrandName(Integer account_id);
	
	public List<BrandAndAgentLevel> queryBrandNameAndGoodsCount(Integer account_id);
	
	public List<BrandAndAgentLevel> queryBrandNameAndImagesCount(Integer account_id);
	
	public List<BrandAndAgentLevel> queryBrandByName(Brand brand);
	
	public List<BrandRecommend> listPageRecommendBrand(ListPageRecommendBrand recommendBrand);
	
	public BrandRecommend queryRecommendBrand(Brand brand);
	
	public int checkRecommendBrand(Brand brand);
	
	public int deleteBrand(Brand brand);
	
	public int insertBrandImages(BrandImages brandimages);
	
	public int deleteBrandImages(BrandImages brandimages);

	public int deleteBrandImagesByImagesUrl(BrandImages brandimages);
	
	public List<BrandImagesResult> queryBrandImages(Brand brand);
	
	public int insertAgentLevel(AgentLevel agentlevel);
	
	public int countAgentLevel(AgentLevel agentlevel);
	
	public int updateAgentLevel(AgentLevel agentlevel);
	
	public String queryAgentLevelById(AgentLevel agentlevel);
	
	public List<AgentLevel> queryAgentLevel(Brand brand);
	
	public int deleteAgentLevel(AgentLevel agentlevel);
	
	public int insertGoods(Goods goods);
	
	public int countGoods(Goods goods);
	
	public int updateGoods(Goods goods);
		
	public int updateGoodsStock(GoodsStock goodsStock);
	
	public int updateSetGoodsStock(GoodsStock goodsStock);
	
	public int updateGoodsThreshold(Goods goods);
	
	public int updateAllGoodsThreshold(@Param(value="account_id")Integer account_id,@Param(value="goodsthreshold")Integer goodsthreshold);
	
	public int updateGoodsScale(Goods goods);
	
	public Goods queryGoodsStock(GoodsStock goodsStock);
	
	public Goods queryGoodsById(Goods goods);
	
	public int checkGoodsScale(Brand brand);
	
	public int deleteGoods(Goods goods);
	
	public List<GoodsAndGoodsPrice> queryGoods(Brand brand);
	
	public List<Goods> queryGoodsList(Brand brand);
	
	public List<GoodsAndGoodsStock> queryGoodsStockList(Integer account_id);
	
	public List<GoodsAndGoodsStock> queryGoodsAllNoStockList(Integer account_id);
	
	public List<GoodsAndGoodsStock> queryGoodsNoStockList(@Param(value="account_id")Integer account_id,@Param(value="brand_id")Integer brand_id);
	
	public List<GoodsAndGoodsStock> queryGoodsAllHasStockList(Integer account_id);
	
	public List<GoodsAndGoodsStock> queryGoodsHasStockList(@Param(value="account_id")Integer account_id,@Param(value="brand_id")Integer brand_id);
	
	public List<GoodsAndGoodsStock> queryGoodsAllAllStockList(Integer account_id);
	
	public List<GoodsAndGoodsStock> queryGoodsAllStockList(@Param(value="account_id")Integer account_id,@Param(value="brand_id")Integer brand_id);
	
	public Float queryGoodsTotalMoney(Integer account_id);
	
	public int queryGoodsTotal(Integer account_id);
	
	public int queryGoodsNoStockTotal(Integer account_id);
	
	public int queryGoodsSetStockTotal(Integer account_id);
	
	public int insertGoodsStockTotal(GoodsStockTotal goodsStocktotal);
	
	public int insertGoodsStock(GoodsStock goodsStock);
	
	public int updateGoodsStockState(GoodsStock goodsStock);
	
	public int checkGoodsStock(GoodsStock goodsStock);
	
	public List<GoodsStockList> queryGoodsTypeStockList(@Param(value="account_id")Integer account_id,@Param(value="type")Integer type,@Param(value="time")String time);
	
	public int insertGoodsPrice(GoodsPrice goodsprice);
	
	public int countGoodsPrice(GoodsPrice goodsprice);
	
	public int updateGoodsPrice(GoodsPrice goodsprice);
	
	public GoodsPrice queryGoodsPriceById(GoodsPrice goodsprice);
	
	public List<GoodsPriceResult> queryGoodsPrice(Goods goods);
	
	public List<GoodsAgentLevelPrice> queryGoodsAgentLevelPrice(GoodsPriceQuery goodsPriceQuery);
	
	public List<GoodsAgentLevelPrice> queryDefaultGoodsPrice(GoodsPriceQuery goodsPriceQuery);
	
	public int deleteGoodsPrice(GoodsPrice goodsprice);
	
	public BrandPrice queryBrandPrice(Integer brand_id);
	
	public int changeBrandIndex(@Param(value="aid")Integer aid,@Param(value="id")Integer id,@Param(value="index")Integer index);
	
	public int changeAgentLevelIndex(@Param(value="aid")Integer aid,@Param(value="bid")Integer bid,@Param(value="id")Integer id,@Param(value="index")Integer index);
	
	public int changeGoodsIndex(@Param(value="aid")Integer aid,@Param(value="bid")Integer bid,@Param(value="id")Integer id,@Param(value="index")Integer index);
	
	public int insertShare(Share share);
	
	public int insertShareBrand(ShareBrand sharebrand);
	
	public int insertShareAgentLevel(ShareAgentLevel shareagentlevel);
	
	public int insertShareUse(ShareUse shareuse);
	
	public Integer queryShare(Integer share_id);
	
	public List<ShareQueryBrand> queryShareBrand(Integer share_id);
	
	public ShareQueryBrand queryOneShareBrand(@Param(value="share_id")Integer share_id,@Param(value="brand_id")Integer brand_id);
	
	public List<ShareQueryAgentLevel> queryShareAgentLevel(Integer sharebrand_id);
	
	public List<ShareQueryAgentLevel> queryShareAgentLevelByShare(@Param(value="share_id")Integer share_id,@Param(value="brand_id")Integer brand_id);
	
	public ShareQueryAgentLevel queryOneShareAgentLevelByShare(@Param(value="share_id")Integer share_id,@Param(value="brand_id")Integer brand_id,@Param(value="agentlevel_id")Integer agentlevel_id);
	
	public List<ShareQuery> queryShareUse(Integer account_id);
	
	public int checkShareUse(ShareUse shareuse);
	
	public int checkShareNoUse(ShareUse shareuse);
	
	public int deleteAllShareUse(Integer account_id);
	
	public int deleteShareUse(ShareUse shareuse);
	
	public int downloadShareUse(ShareUse shareuse);
	
	public int checkExchangeCode(@Param(value="brand_id")Integer brand_id,@Param(value="code")String code);
	
	public List<String> queryAllHotBrand();
	
	public String queryNowdate();

	public BrandGoodsInfo queryBrandGoods(Brand brand);

	public int setBrandStockRemain(Brand brand);

	public List<Brand> selectHotBrand();

	Brand getBrandInfo(Integer id);

	int setBrandHot(Integer is_hot);

	/**
	 * 返回库存低于库存预警值的商品
	 * @param brand
	 * @return
	 */
	List<Goods> selectLowStockGoods(Brand brand);

	/**
	 * 返回库存低于0的商品
	 * @param brand
	 * @return
	 */
	List<Goods> selectZeroStockGoods(Brand brand);

	/**
	 * 返回库存不为0的商品
	 * @param brand
	 * @return
	 */
	List<Goods> selectNotEmptyStockGoods(Brand brand);

	/**
	 * 批量插入商品价格
	 * @param list
	 */
    void insertBatch(List<GoodsPrice> list);

	/**
	 * 批量插入品牌代理
	 * @param list
	 */
	void insertAgentLevelBatch(List<AgentLevel> list);

	/**
	 * 查询品牌销售额 订单数
	 * @param brand
	 * @return
	 */
    BrandGoodsInfo queryBrandOrder(Brand brand);
}