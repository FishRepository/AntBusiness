package com.api.goods.controller;

import com.api.common.entity.Result;
import com.api.goods.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.goods.service.GoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value = "/insertBrand")
    @ResponseBody
	public Object insertBrand(InsertBrand insertbrand){
		return goodsService.insertBrand(insertbrand);
	}
	
	@RequestMapping(value = "/updateBrand")
    @ResponseBody
	public Object updateBrand(Brand brand){
		return goodsService.updateBrand(brand);
	}
	
	@RequestMapping(value = "/deleteBrand")
    @ResponseBody
	public Object deleteBrand(Brand brand){
		return goodsService.deleteBrand(brand);
	}
	
	@RequestMapping(value = "/queryBrand")
    @ResponseBody
	public Object queryBrand(Integer account_id,Integer type){
		return goodsService.queryBrand(account_id,type);
	}
	
	@RequestMapping(value = "/queryBrandByName")
    @ResponseBody
	public Object queryBrandByName(Integer account_id,String brand_name){
		return goodsService.queryBrandByName(account_id,brand_name);
	}
	
	@RequestMapping(value = "/listPageRecommendBrand")
    @ResponseBody
	public Object listPageRecommendBrand(ListPageRecommendBrand recommendBrand){
		return goodsService.listPageRecommendBrand(recommendBrand);
	}
	
	@RequestMapping(value = "/queryRecommendBrand")
    @ResponseBody
	public Object queryRecommendBrand(Integer account_id,Integer brand_id){
		return goodsService.queryRecommendBrand(account_id,brand_id);
	}
	
	@RequestMapping(value = "/insertBrandImages")
    @ResponseBody
	public Object insertBrandImages(BrandImages brandimages){
		return goodsService.insertBrandImages(brandimages);
	}
	
	@RequestMapping(value = "/deleteBrandImages")
    @ResponseBody
	public Object deleteBrandImages(BrandImages brandimages){
		return goodsService.deleteBrandImages(brandimages);
	}
	
	@RequestMapping(value = "/queryBrandImages")
    @ResponseBody
	public Object queryBrandImages(Integer account_id){
		return goodsService.queryBrandImages(account_id);
	}
	
	@RequestMapping(value = "/insertAgentLevel")
    @ResponseBody
	public Object insertAgentLevel(AgentLevel agentlevel){
		return goodsService.insertAgentLevel(agentlevel);
	}
	
	@RequestMapping(value = "/updateAgentLevel")
    @ResponseBody
	public Object updateAgentLevel(AgentLevel agentlevel){
		return goodsService.updateAgentLevel(agentlevel);
	}
	
	@RequestMapping(value = "/deleteAgentLevel")
    @ResponseBody
	public Object deleteAgentLevel(AgentLevel agentlevel){
		return goodsService.deleteAgentLevel(agentlevel);
	}
	
	@RequestMapping(value = "/queryAgentLevel")
    @ResponseBody
	public Object queryAgentLevel(Brand brand,Integer type){
		return goodsService.queryAgentLevel(brand,type);
	}
	
	@RequestMapping(value = "/insertGoods")
    @ResponseBody
	public Object insertGoods(InsertGoods insertgoods){
		return goodsService.insertGoods(insertgoods);
	}
	
	@RequestMapping(value = "/updateGoods")
    @ResponseBody
	public Object updateGoods(UpdateGoods updategoods){
		return goodsService.updateGoods(updategoods);
	}
	
	@RequestMapping(value = "/deleteGoods")
    @ResponseBody
	public Object deleteGoods(Goods goods){
		return goodsService.deleteGoods(goods);
	}
	
	@RequestMapping(value = "/queryGoods")
    @ResponseBody
	public Object queryGoods(Integer account_id,Integer brand_id,Integer type){
		return goodsService.queryGoods(account_id,brand_id,type);
	}
	
	@RequestMapping(value = "/queryScaleGoods")
    @ResponseBody
	public Object queryScaleGoods(Integer account_id,Integer brand_id){
		return goodsService.queryScaleGoods(account_id,brand_id);
	}
	
	@RequestMapping(value = "/queryOneGoods")
    @ResponseBody
	public Object queryOneGoods(Goods goods){
		return goodsService.queryOneGoods(goods);
	}
	
	@RequestMapping(value = "/updateGoodsStock")
    @ResponseBody
	public Object updateGoodsStock(Goods goods,Integer type){
		return goodsService.updateGoodsStock(goods,type);
	}
	
	@RequestMapping(value = "/updateGoodsThreshold")
    @ResponseBody
	public Object updateGoodsThreshold(Goods goods){
		return goodsService.updateGoodsThreshold(goods);
	}
	
	@RequestMapping(value = "/updateMoreGoodsStock")
    @ResponseBody
	public Object updateMoreGoodsStock(Integer account_id,String goodsstock,Integer type){
		return goodsService.updateMoreGoodsStock(account_id,goodsstock,type);
	}
	
	@RequestMapping(value = "/updateMoreGoodsThreshold")
    @ResponseBody
	public Object updateMoreGoodsThreshold(Integer account_id,String goodsthreshold){
		return goodsService.updateMoreGoodsThreshold(account_id,goodsthreshold);
	}
	
	@RequestMapping(value = "/updateMoreGoodsScale")
    @ResponseBody
	public Object updateMoreGoodsScale(Integer account_id,String goodsscale){
		return goodsService.updateMoreGoodsScale(account_id,goodsscale);
	}
	
	@RequestMapping(value = "/updateGoodsPrice")
    @ResponseBody
	public Object updateGoodsPrice(GoodsPrice goodsprice){
		return goodsService.updateGoodsPrice(goodsprice);
	}
	
	@RequestMapping(value = "/queryGoodsPrice")
    @ResponseBody
	public Object queryGoodsPrice(GoodsPriceQuery goodsPriceQuery){
		return goodsService.queryGoodsPrice(goodsPriceQuery);
	}
	
	@RequestMapping(value = "/changeBrandIndex")
    @ResponseBody
	public Object changeBrandIndex(@RequestBody IndexBrandList list){
		return goodsService.changeBrandIndex(list);
	}
	
	@RequestMapping(value = "/changeAgentLevelIndex")
    @ResponseBody
	public Object changeAgentLevelIndex(@RequestBody IndexAgentLevelList list){
		return goodsService.changeAgentLevelIndex(list);
	}
	
	@RequestMapping(value = "/changeGoodsIndex")
    @ResponseBody
	public Object changeGoodsIndex(@RequestBody IndexGoodsList list){
		return goodsService.changeGoodsIndex(list);
	}
	
	@RequestMapping(value = "/sendPush")
    @ResponseBody
	public Object sendPush(String message,Integer aid,Integer bid,Integer gid,String bname,String gname){
		return goodsService.sendPush(message, aid, bid, gid, bname, gname);
	}
	
	@RequestMapping(value = "/createShareBrand")
    @ResponseBody
	public Object createShareBrand(@RequestBody ShareCreate sharecreate){
		return goodsService.createShareBrand(sharecreate);
	}
	
	@RequestMapping(value = "/queryShare")
    @ResponseBody
	public Object queryShare(Integer account_id){
		return goodsService.queryShare(account_id);
	}
	
	@RequestMapping(value = "/deleteAllShareUse")
    @ResponseBody
	public Object deleteAllShareUse(Integer account_id){
		return goodsService.deleteAllShareUse(account_id);
	}
	
	@RequestMapping(value = "/downloadShareUse")
    @ResponseBody
	public Object downloadShareUse(@RequestBody ShareDownload sharedownload){
		return goodsService.downloadShareUse(sharedownload);
	}
	
	@RequestMapping(value = "/queryNoSetStockGoods")
    @ResponseBody
	public Object queryNoSetStockGoods(Integer account_id){
		return goodsService.queryNoSetStockGoods(account_id);
	}
	
	@RequestMapping(value = "/queryNoStockGoods")
    @ResponseBody
	public Object queryNoStockGoods(Integer account_id,Integer brand_id){
		return goodsService.queryNoStockGoods(account_id,brand_id);
	}
	
	@RequestMapping(value = "/queryHasStockGoods")
    @ResponseBody
	public Object queryHasStockGoods(Integer account_id,Integer brand_id){
		return goodsService.queryHasStockGoods(account_id,brand_id);
	}
	
	@RequestMapping(value = "/queryStockTotal")
    @ResponseBody
	public Object queryStockTotal(Integer account_id){
		return goodsService.queryStockTotal(account_id);
	}
	
	@RequestMapping(value = "/queryStockGoodsList")
    @ResponseBody
	public Object queryStockGoodsList(Integer account_id,Integer type,String time){
		return goodsService.queryStockGoodsList(account_id,type,time);
	}
	
	@RequestMapping(value = "/updateAllGoodsThreshold")
    @ResponseBody
	public Object updateAllGoodsThreshold(Integer account_id,Integer goodsthreshold){
		return goodsService.updateAllGoodsThreshold(account_id,goodsthreshold);
	}
	
	@RequestMapping(value = "/queryAllStockGoods")
    @ResponseBody
	public Object queryAllStockGoods(Integer account_id,Integer brand_id){
		return goodsService.queryAllStockGoods(account_id,brand_id);
	}
	
	@RequestMapping(value = "/queryAllHotBrand")
    @ResponseBody
	public Object queryAllHotBrand(Integer account_id){
		return goodsService.queryAllHotBrand(account_id);
	}

	@ResponseBody
	@RequestMapping(value = "/setBrandStockRemain")
	public Object setBrandStockRemain(Brand brand){
		return goodsService.setBrandStockRemain(brand);
	}

	/**
	 * 查询推荐品牌  bran   is_hot在后台设置
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectHotBrand")
	public Object selectHotBrand(String brand_name){
		return goodsService.selectHotBrand(brand_name);
	}

	/**
	 * 查询品牌详情
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getBrandInfo")
	public Result getBrandInfo(Integer brand_id){
		return goodsService.getBrandInfo(brand_id);
	}
}