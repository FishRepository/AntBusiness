package com.api.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.api.goods.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.common.entity.Result;
import com.api.common.utils.JPushUtil;
import com.api.common.utils.PropertiesUtil;
import com.api.common.utils.StringUtil;
import com.api.goods.mapper.GoodsMapper;
import com.api.order.entity.OrderGoods;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
public class GoodsService {
	@Autowired
    private GoodsMapper goodsMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(GoodsService.class);
	
	public InsertBrandResult insertBrand(InsertBrand insertbrand){
		InsertBrandResult result = new InsertBrandResult();
		if(insertbrand!=null && insertbrand.getAccount_id()!=null && insertbrand.getAccount_id() > 0 && StringUtil.isValid(insertbrand.getBrand_name())){
			Brand brand = new Brand();
			brand.setAccount_id(insertbrand.getAccount_id());
			brand.setBrand_name(insertbrand.getBrand_name());
			if(StringUtil.isValid(insertbrand.getLogo_url())){
				brand.setLogo_url(insertbrand.getLogo_url());
			}
			if(StringUtil.isValid(insertbrand.getTitle())){
				brand.setTitle(insertbrand.getTitle());
			}
			if(StringUtil.isValid(insertbrand.getBrand_info())){
				brand.setBrand_info(insertbrand.getBrand_info());
			}
			brand.setBrand_from(0);
			if(goodsMapper.countBrand(brand) <= 0){
				if(goodsMapper.insertBrand(brand) > 0){
					result.setCode(0);
					result.setMsg("添加成功");
					result.setBrand_id(brand.getBrand_id());
					List<AgentLevel> list = new ArrayList<AgentLevel>();
					AgentLevel agentlevel = new AgentLevel();
					agentlevel.setAccount_id(brand.getAccount_id());
					agentlevel.setBrand_id(brand.getBrand_id());
					agentlevel.setAgentlevel_name("零售价");
					agentlevel.setAgentlevel_default(1);
					if(goodsMapper.insertAgentLevel(agentlevel) > 0){
						list.add(agentlevel);
					}
					if(StringUtil.isValid(insertbrand.getAgentlevel_names())){
						String[] agentlevels = insertbrand.getAgentlevel_names().split("\\|");
						if(agentlevels!=null){
							for(String agentlevel_name:agentlevels){
								agentlevel = new AgentLevel();
								agentlevel.setAccount_id(brand.getAccount_id());
								agentlevel.setBrand_id(brand.getBrand_id());
								agentlevel.setAgentlevel_name(agentlevel_name);
								if(goodsMapper.countAgentLevel(agentlevel) <= 0){
									agentlevel.setAgentlevel_default(0);
									if(goodsMapper.insertAgentLevel(agentlevel) > 0){
										list.add(agentlevel);
									}
								}
							}
						}
					}
					result.setList(list);
				}else{
					result.setCode(1);
					result.setMsg("添加失败");
				}
			}else{
				result.setCode(2);
				result.setMsg("品牌名称重复");
			}
		}else{
			result.setCode(1);
			result.setMsg("添加失败");
		}
		return result;
	}
	
	public Result updateBrand(Brand brand){
		Result result = new Result();
		if(brand!=null && brand.getBrand_id()!=null && brand.getBrand_id() > 0 && brand.getAccount_id()!=null && brand.getAccount_id() > 0 && StringUtil.isValid(brand.getBrand_name())){
			if(goodsMapper.countBrand(brand) <= 0){
				if(goodsMapper.updateBrand(brand) > 0){
					result.setCode(0);
					result.setMsg("修改成功");
				}else{
					result.setCode(1);
					result.setMsg("修改失败");
				}
			}else{
				result.setCode(2);
				result.setMsg("品牌名称重复");
			}
		}else{
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public Result deleteBrand(Brand brand){
		Result result = new Result();
		if(brand!=null && brand.getBrand_id()!=null && brand.getBrand_id() > 0 && brand.getAccount_id()!=null && brand.getAccount_id() > 0){
			/*Goods goods = new Goods();
			goods.setAccount_id(brand.getAccount_id());
			goods.setBrand_id(brand.getBrand_id());
			if(goodsMapper.countGoods(goods) <= 0){*/
				if(goodsMapper.deleteBrand(brand) > 0){
					result.setCode(0);
					result.setMsg("删除成功");
					AgentLevel agentlevel = new AgentLevel();
					agentlevel.setAccount_id(brand.getAccount_id());
					agentlevel.setBrand_id(brand.getBrand_id());
					goodsMapper.deleteAgentLevel(agentlevel);
					
					BrandImages brandimages = new BrandImages();
					brandimages.setAccount_id(brand.getAccount_id());
					brandimages.setBrand_id(brand.getBrand_id());
					goodsMapper.deleteBrandImages(brandimages);
					
					Goods goods =  new Goods();
					goods.setAccount_id(brand.getAccount_id());
					goods.setBrand_id(brand.getBrand_id());
					goodsMapper.deleteGoods(goods);
				}else{
					result.setCode(1);
					result.setMsg("删除失败");
				}
			/*}else{
				result.setCode(2);
				result.setMsg("该品牌下还有商品信息，删除失败");
			}*/
		}else{
			result.setCode(1);
			result.setMsg("删除失败");
		}
		return result;
	}
	
	public BrandResult queryBrand(Integer account_id,Integer type){
		BrandResult result = new BrandResult();
		if(account_id!=null && account_id > 0){
			if(type!=null){
				if(type == 1){//名称和商品数量
					result.setList(goodsMapper.queryBrandNameAndGoodsCount(account_id));
				}else if(type == 2){//名称和商品图片数量
					result.setList(goodsMapper.queryBrandNameAndImagesCount(account_id));
				}else if(type == 3){//名称和代理层级
					List<BrandAndAgentLevel> list = goodsMapper.queryBrandName(account_id);
					if(list!=null && !list.isEmpty()){
						Brand brand = new Brand();
						brand.setAccount_id(account_id);
						for(BrandAndAgentLevel b:list){
							brand.setBrand_id(b.getBrand_id());
							b.setAgentlevellist(goodsMapper.queryAgentLevel(brand));
						}
					}
					result.setList(list);
				}else if(type == 4){//名称、商品数量和代理层级
					List<BrandAndAgentLevel> list = goodsMapper.queryBrandNameAndGoodsCount(account_id);
					if(list!=null && !list.isEmpty()){
						Brand brand = new Brand();
						brand.setAccount_id(account_id);
						for(BrandAndAgentLevel b:list){
							brand.setBrand_id(b.getBrand_id());
							b.setAgentlevellist(goodsMapper.queryAgentLevel(brand));
						}
					}
					result.setList(list);
				}else if(type == 5){//名称、商品图片数量和代理层级
					List<BrandAndAgentLevel> list = goodsMapper.queryBrandNameAndImagesCount(account_id);
					if(list!=null && !list.isEmpty()){
						Brand brand = new Brand();
						brand.setAccount_id(account_id);
						for(BrandAndAgentLevel b:list){
							brand.setBrand_id(b.getBrand_id());
							b.setAgentlevellist(goodsMapper.queryAgentLevel(brand));
						}
					}
					result.setList(list);
				}else{//名称
					result.setList(goodsMapper.queryBrandName(account_id));
				}
			}else{//名称
				result.setList(goodsMapper.queryBrandName(account_id));
			}
			List<BrandAndAgentLevel> brandAndAgentLevels = result.getList();
			//遍历品牌及代理集合，插入brandimages
			if(!CollectionUtils.isEmpty(brandAndAgentLevels)){
				for (BrandAndAgentLevel brandAndAgentLevel:brandAndAgentLevels) {
					Integer brand_id = brandAndAgentLevel.getBrand_id();
					Brand brand = new Brand();
					brand.setAccount_id(account_id);
					brand.setBrand_id(brand_id);
					List<BrandImagesResult> brandImagesResults = goodsMapper.queryBrandImages(brand);
					if(!CollectionUtils.isEmpty(brandImagesResults) && brandImagesResults.get(0)!=null
							&& StringUtils.isNotBlank(brandImagesResults.get(0).getBrandimages_url())){
						brandAndAgentLevel.setBrandimages_url(brandImagesResults.get(0).getBrandimages_url());
					}
					BrandGoodsInfo brandGoodsInfo = goodsMapper.queryBrandGoods(brand);
					if(brandGoodsInfo!=null){
						brandAndAgentLevel.setBrandGoodsInfo(brandGoodsInfo);
					}
				}
			}
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public BrandResult queryBrandByName(Integer account_id,String brand_name){
		BrandResult result = new BrandResult();
		if(account_id!=null && account_id > 0 && StringUtil.isValid(brand_name)){
			Brand brand = new Brand();
			brand.setAccount_id(account_id);
			brand.setBrand_name(brand_name);
			result.setList(goodsMapper.queryBrandByName(brand));
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public BrandRecommendListResult listPageRecommendBrand(ListPageRecommendBrand recommendBrand){
		BrandRecommendListResult result = new BrandRecommendListResult();
		if(recommendBrand!=null && recommendBrand.getAccount_id()!=null && recommendBrand.getAccount_id() > 0 && recommendBrand.getCurrentPage() > 0){
			result.setList(goodsMapper.listPageRecommendBrand(recommendBrand));
			result.setTotalresult(recommendBrand.getTotalResult());
			result.setTotalpage(recommendBrand.getTotalPage());
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public BrandRecommendResult queryRecommendBrand(Integer account_id,Integer brand_id){
		BrandRecommendResult result = new BrandRecommendResult();
		if(account_id!=null && account_id > 0 && brand_id!=null && brand_id > 0){
			Brand brand = new Brand();
			brand.setAccount_id(account_id);
			brand.setBrand_id(brand_id);
			result.setBrand(goodsMapper.queryRecommendBrand(brand));
			brand.setAccount_id(0);
			result.setAgentlevellist(goodsMapper.queryAgentLevel(brand));
			result.setGoodslist(goodsMapper.queryGoodsList(brand));
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public InsertBrandImagesResult insertBrandImages(BrandImages brandimages){
		InsertBrandImagesResult result = new InsertBrandImagesResult();
		if(brandimages!=null && brandimages.getBrand_id()!=null && brandimages.getBrand_id() > 0 && brandimages.getAccount_id()!=null && brandimages.getAccount_id() > 0 && StringUtil.isValid(brandimages.getBrandimages_url())){
			List<BrandImagesId> brandimages_ids = new ArrayList<BrandImagesId>();
			BrandImagesId brandimagesid = null;
			String[] urls = brandimages.getBrandimages_url().split(",");
			if(urls!=null){
				for(String url:urls){
					brandimages.setBrandimages_url(url);
					if(goodsMapper.insertBrandImages(brandimages) > 0){
						brandimagesid = new BrandImagesId();
						brandimagesid.setBrandimages_id(brandimages.getBrandimages_id());
						brandimagesid.setBrandimages_url(url);
						brandimages_ids.add(brandimagesid);
						brandimages.setBrandimages_id(null);
						goodsMapper.incBrandImagesCount(brandimages.getBrand_id());
					}
				}
			}
			if(!brandimages_ids.isEmpty()){
				result.setCode(0);
				result.setMsg("添加成功");
				result.setBrandimages_ids(brandimages_ids);
			}else{
				result.setCode(1);
				result.setMsg("添加失败");
			}
		}else{
			result.setCode(1);
			result.setMsg("添加失败");
		}
		return result;
	}
	
	public Result deleteBrandImages(BrandImages brandimages){
		Result result = new Result();
		if(brandimages!=null && brandimages.getBrandimages_id()!=null && brandimages.getBrandimages_id() > 0 && brandimages.getBrand_id()!=null && brandimages.getBrand_id() > 0 && brandimages.getAccount_id()!=null && brandimages.getAccount_id() > 0){
			if(goodsMapper.deleteBrandImages(brandimages) > 0){
				result.setCode(0);
				result.setMsg("删除成功");
				goodsMapper.decBrandImagesCount(brandimages.getBrand_id());
			}else{
				result.setCode(1);
				result.setMsg("删除失败");
			}
		}
		return result;
	}
	
	public BrandAndImagesResult queryBrandImages(Integer account_id){
		BrandAndImagesResult result = new BrandAndImagesResult();
		if(account_id!=null && account_id > 0){
			List<BrandAndImages> list = new ArrayList<>();
			List<BrandAndAgentLevel> brandlist = goodsMapper.queryBrandNameAndImagesCount(account_id);
			if(brandlist!=null && !brandlist.isEmpty()){
				BrandAndImages brandAndImages;
				Brand brand;
				for(BrandAndAgentLevel brandandagentlevel:brandlist){
					brandAndImages = new BrandAndImages();
					brandAndImages.setBrand(brandandagentlevel);
					brand = new Brand();
					brand.setAccount_id(account_id);
					brand.setBrand_id(brandandagentlevel.getBrand_id());
					brandAndImages.setImageslist(goodsMapper.queryBrandImages(brand));
					brandAndImages.setBrandGoodsInfo(goodsMapper.queryBrandGoods(brand));
					list.add(brandAndImages);
				}
			}
			result.setList(list);
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public InsertAgentLevelResult insertAgentLevel(AgentLevel agentlevel){
		InsertAgentLevelResult result = new InsertAgentLevelResult();
		if(agentlevel!=null && agentlevel.getBrand_id()!=null && agentlevel.getBrand_id() > 0 && agentlevel.getAccount_id()!=null && agentlevel.getAccount_id() > 0 && StringUtil.isValid(agentlevel.getAgentlevel_name())){
			if(goodsMapper.countAgentLevel(agentlevel) <= 0){
				agentlevel.setAgentlevel_default(0);
				if(goodsMapper.insertAgentLevel(agentlevel) > 0){
					result.setCode(0);
					result.setMsg("添加成功");
					result.setAgentlevel_id(agentlevel.getAgentlevel_id());
				}else{
					result.setCode(1);
					result.setMsg("添加失败");
				}
			}else{
				result.setCode(2);
				result.setMsg("代理层级名称重复");
			}
		}else{
			result.setCode(1);
			result.setMsg("添加失败");
		}
		return result;
	}
	
	public Result updateAgentLevel(AgentLevel agentlevel){
		Result result = new Result();
		if(agentlevel!=null && agentlevel.getAgentlevel_id()!=null && agentlevel.getAgentlevel_id() > 0 && agentlevel.getBrand_id()!=null && agentlevel.getBrand_id() > 0 && agentlevel.getAccount_id()!=null && agentlevel.getAccount_id() > 0 && StringUtil.isValid(agentlevel.getAgentlevel_name())){
			if(goodsMapper.countAgentLevel(agentlevel) <= 0){
				if(goodsMapper.updateAgentLevel(agentlevel) > 0){
					result.setCode(0);
					result.setMsg("修改成功");
				}else{
					result.setCode(1);
					result.setMsg("修改失败");
				}
			}else{
				result.setCode(2);
				result.setMsg("代理层级名称重复");
			}
		}else{
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public Result deleteAgentLevel(AgentLevel agentlevel){
		Result result = new Result();
		if(agentlevel!=null && agentlevel.getAgentlevel_id()!=null && agentlevel.getAgentlevel_id() > 0 && agentlevel.getBrand_id()!=null && agentlevel.getBrand_id() > 0 && agentlevel.getAccount_id()!=null && agentlevel.getAccount_id() > 0){
			if(goodsMapper.deleteAgentLevel(agentlevel) > 0){
				result.setCode(0);
				result.setMsg("删除成功");
				GoodsPrice goodsprice = new GoodsPrice();
				goodsprice.setAccount_id(agentlevel.getAccount_id());
				goodsprice.setAgentlevel_id(agentlevel.getAgentlevel_id());
				goodsMapper.deleteGoodsPrice(goodsprice);
			}else{
				result.setCode(1);
				result.setMsg("删除失败");
			}
		}else{
			result.setCode(1);
			result.setMsg("删除失败");
		}
		return result;
	}
	
	public BrandAndAgentLevelResult queryAgentLevel(Brand brand,Integer type){
		BrandAndAgentLevelResult result = new BrandAndAgentLevelResult();
		if(brand!=null && brand.getBrand_id()!=null && brand.getBrand_id() > 0 && brand.getAccount_id()!=null && brand.getAccount_id() > 0){
			if(type!=null){
				if(type == 1){//名称和等级
					result.setBrand_name(goodsMapper.queryBrandById(brand));
					result.setList(goodsMapper.queryAgentLevel(brand));
				}else{//等级
					result.setList(goodsMapper.queryAgentLevel(brand));
				}
			}else{//等级
				result.setList(goodsMapper.queryAgentLevel(brand));
			}
			List<BrandImagesResult> brandImagesResults = goodsMapper.queryBrandImages(brand);
			//设置品牌图片
			if(!CollectionUtils.isEmpty(brandImagesResults)){
				result.setBrandImagesResult(brandImagesResults.get(0));
			}
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public InsertGoodsResult insertGoods(InsertGoods insertgoods){
		InsertGoodsResult result = new InsertGoodsResult();
		if(insertgoods!=null && insertgoods.getBrand_id()!=null && insertgoods.getBrand_id() > 0 && insertgoods.getAccount_id()!=null && insertgoods.getAccount_id() > 0 && insertgoods.getGoods_price()!=null && StringUtil.isValid(insertgoods.getGoods_name())){
			Goods goods = new Goods();
			goods.setAccount_id(insertgoods.getAccount_id());
			goods.setBrand_id(insertgoods.getBrand_id());
			goods.setGoods_name(insertgoods.getGoods_name());
			if(goodsMapper.countGoods(goods) <= 0){
				goods.setGoods_price(insertgoods.getGoods_price());
				goods.setGoods_scale(1);
				if(goodsMapper.insertGoods(goods) > 0){
					result.setCode(0);
					result.setMsg("添加成功");
					result.setGoods_id(goods.getGoods_id());
					goodsMapper.incBrandGoodsCount(insertgoods.getBrand_id());
					if(StringUtil.isValid(insertgoods.getAgent_prices())){
						String[] agentprices = insertgoods.getAgent_prices().split("\\|");
						if(agentprices!=null){
							String[] prices = null;
							GoodsPrice goodsprice = null;
							for(String agentprice:agentprices){
								prices = agentprice.split(",");
								if(prices!=null && prices.length == 2){
									try{
										goodsprice = new GoodsPrice();
										goodsprice.setAccount_id(insertgoods.getAccount_id());
										goodsprice.setGoods_id(goods.getGoods_id());
										goodsprice.setAgentlevel_id(Integer.parseInt(prices[0]));
										goodsprice.setGoods_price(Float.parseFloat(prices[1]));
										goodsMapper.insertGoodsPrice(goodsprice);
									}catch(Exception e){
									}
								}
							}
						}
					}
				}else{
					result.setCode(1);
					result.setMsg("添加失败");
				}
			}else{
				result.setCode(2);
				result.setMsg("商品名称重复");
			}
		}else{
			result.setCode(1);
			result.setMsg("添加失败");
		}
		return result;
	}
	
	public Result updateGoods(UpdateGoods updategoods){
		Result result = new Result();
		if(updategoods!=null && updategoods.getGoods_id()!=null && updategoods.getGoods_id() > 0 && updategoods.getBrand_id()!=null && updategoods.getBrand_id() > 0 && updategoods.getAccount_id()!=null && updategoods.getAccount_id() > 0 && updategoods.getGoods_price()!=null && StringUtil.isValid(updategoods.getGoods_name())){
			Goods goods = new Goods();
			goods.setGoods_id(updategoods.getGoods_id());
			goods.setAccount_id(updategoods.getAccount_id());
			goods.setBrand_id(updategoods.getBrand_id());
			goods.setGoods_name(updategoods.getGoods_name());
			if(goodsMapper.countGoods(goods) <= 0){
				goods.setGoods_price(updategoods.getGoods_price());
				if(goodsMapper.updateGoods(goods) > 0){
					result.setCode(0);
					result.setMsg("修改成功");
					if(StringUtil.isValid(updategoods.getAgent_prices())){
						String[] agentprices = updategoods.getAgent_prices().split("\\|");
						if(agentprices!=null){
							String[] prices = null;
							GoodsPrice goodsprice = null;
							for(String agentprice:agentprices){
								prices = agentprice.split(",");
								if(prices!=null && prices.length == 2){
									try{
										goodsprice = new GoodsPrice();
										goodsprice.setAccount_id(updategoods.getAccount_id());
										goodsprice.setGoods_id(goods.getGoods_id());
										goodsprice.setAgentlevel_id(Integer.parseInt(prices[0]));
										goodsprice.setGoods_price(Float.parseFloat(prices[1]));
										if(goodsMapper.updateGoodsPrice(goodsprice) <= 0){
											goodsMapper.insertGoodsPrice(goodsprice);
										}
									}catch(Exception e){
									}
								}
							}
						}
					}
				}else{
					result.setCode(1);
					result.setMsg("修改失败");
				}
			}else{
				result.setCode(2);
				result.setMsg("商品名称重复");
			}
		}else{
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public Result deleteGoods(Goods goods){
		Result result = new Result();
		if(goods!=null && goods.getGoods_id()!=null && goods.getGoods_id() > 0 && goods.getBrand_id()!=null && goods.getBrand_id() > 0 && goods.getAccount_id()!=null && goods.getAccount_id() > 0){
			if(goodsMapper.deleteGoods(goods) > 0){
				result.setCode(0);
				result.setMsg("删除成功");
				goodsMapper.decBrandGoodsCount(goods.getBrand_id());
				GoodsPrice goodsprice = new GoodsPrice();
				goodsprice.setAccount_id(goods.getAccount_id());
				goodsprice.setGoods_id(goods.getGoods_id());
				goodsMapper.deleteGoodsPrice(goodsprice);
			}else{
				result.setCode(1);
				result.setMsg("删除失败");
			}
		}else{
			result.setCode(1);
			result.setMsg("删除失败");
		}
		return result;
	}
	
	public GoodsResult queryGoods(Integer account_id,Integer brand_id,Integer type){
		GoodsResult result = new GoodsResult();
		if(brand_id!=null && brand_id > 0 && account_id!=null && account_id > 0){
			Brand brand = new Brand();
			brand.setAccount_id(account_id);
			brand.setBrand_id(brand_id);
			if(type!=null){
				if(type == 1){//名称和商品数量
					List<GoodsAndGoodsPrice> list = goodsMapper.queryGoods(brand);
					if(list!=null && !list.isEmpty()){
						Goods goods = new Goods();
						goods.setAccount_id(account_id);
						goods.setBrand_id(brand_id);
						for(GoodsAndGoodsPrice g:list){
							goods.setGoods_id(g.getGoods_id());
							g.setGoodspricelist(goodsMapper.queryGoodsPrice(goods));
						}
					}
					result.setList(list);
				}else{
					result.setList(goodsMapper.queryGoods(brand));
				}
			}else{
				result.setList(goodsMapper.queryGoods(brand));
			}
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public GoodsScaleResult queryScaleGoods(Integer account_id,Integer brand_id){
		GoodsScaleResult result = new GoodsScaleResult();
		if(brand_id!=null && brand_id > 0 && account_id!=null && account_id > 0){
			Brand brand = new Brand();
			brand.setAccount_id(account_id);
			brand.setBrand_id(brand_id);
			result.setIsSetting((goodsMapper.checkGoodsScale(brand) > 0)?1:0);
			result.setList(goodsMapper.queryGoodsList(brand));
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public GoodsInfoResult queryOneGoods(Goods goods){
		GoodsInfoResult result = new GoodsInfoResult();
		if(goods!=null && goods.getGoods_id()!=null && goods.getGoods_id() > 0 && goods.getBrand_id()!=null && goods.getBrand_id() > 0 && goods.getAccount_id()!=null && goods.getAccount_id() > 0){
			Goods rgoods = goodsMapper.queryGoodsById(goods);
			if(rgoods!=null){
				Brand brand = new Brand();
				brand.setBrand_id(goods.getBrand_id());
				brand.setAccount_id(goods.getAccount_id());
				brand.setBrand_name(goodsMapper.queryBrandById(brand));
				result.setBrand(brand);
				rgoods.setGoods_id(goods.getGoods_id());
				result.setGoods(rgoods);
				result.setList(goodsMapper.queryGoodsPrice(goods));
				result.setCode(0);
				result.setMsg("查询成功");
			}else{
				result.setCode(1);
				result.setMsg("查询失败");
			}
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public Result updateGoodsStock(Goods goods,Integer type){
		Result result = new Result();
		if(goods!=null && goods.getGoods_id()!=null && goods.getGoods_id() > 0 && goods.getBrand_id()!=null && goods.getBrand_id() > 0 && goods.getAccount_id()!=null && goods.getAccount_id() > 0 && goods.getGoods_stock()!=null && type!=null && type >= 0 && type <= 6){
			GoodsStockTotal total = new GoodsStockTotal();
			total.setTotal_type(type);
			total.setAccount_id(goods.getAccount_id());
			goodsMapper.insertGoodsStockTotal(total);
			if(changeGoodsStock(goods.getBrand_id(),goods.getGoods_id(),goods.getAccount_id(),goods.getGoods_stock(),total.getTotal_id(),type)){
				goodsMapper.updateThresholdCount(goods.getBrand_id());
				result.setCode(0);
				result.setMsg("修改成功");
			}else{
				result.setCode(1);
				result.setMsg("修改失败");
			}
		}else{
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public Result updateGoodsThreshold(Goods goods){
		Result result = new Result();
		if(goods!=null && goods.getGoods_id()!=null && goods.getGoods_id() > 0 && goods.getBrand_id()!=null && goods.getBrand_id() > 0 && goods.getAccount_id()!=null && goods.getAccount_id() > 0 && goods.getGoods_threshold()!=null && goods.getGoods_threshold() > 0){
			if(goodsMapper.updateGoodsThreshold(goods) > 0){
				result.setCode(0);
				result.setMsg("修改成功");
			}else{
				result.setCode(1);
				result.setMsg("修改失败");
			}
		}else{
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public Result updateMoreGoodsStock(Integer account_id,String goodsstock,Integer type){
		Result result = new Result();
		if(account_id!=null && account_id > 0 && StringUtil.isValid(goodsstock) && type!=null && type >= 0 && type <= 6){
			List<Integer> faillist = new ArrayList<Integer>();
			//brand_id,goods_id,goods_stock|brand_id,goods_id,goods_stock
			String[] goodss = goodsstock.split("\\|");
			if(goodss!=null){
				String[] goodsarray = null;
				GoodsStockTotal total = new GoodsStockTotal();
				total.setTotal_type(type);
				total.setAccount_id(account_id);
				goodsMapper.insertGoodsStockTotal(total);
				for(String goodssstr:goodss){
					goodsarray = goodssstr.split(",");
					if(goodsarray!=null && goodsarray.length == 3){
						try{
							if(!changeGoodsStock(Integer.parseInt(goodsarray[0]),Integer.parseInt(goodsarray[1]),account_id,Integer.parseInt(goodsarray[2]),total.getTotal_id(),type)){
								faillist.add(Integer.parseInt(goodsarray[1]));
							}else{
								goodsMapper.updateThresholdCount(Integer.parseInt(goodsarray[0]));
							}
						}catch(Exception e){
							try{
								faillist.add(Integer.parseInt(goodsarray[1]));
							}catch(Exception e1){
							}
						}
					}
				}
			}
			if(faillist!=null && faillist.isEmpty()){
				result.setCode(0);
				result.setMsg("修改成功");
			}else{
				result.setCode(0);
				result.setMsg("部分修改成功");
			}
		}else{
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public Result updateMoreGoodsThreshold(Integer account_id,String goodsthreshold){
		Result result = new Result();
		if(account_id!=null && account_id > 0 && StringUtil.isValid(goodsthreshold)){
			List<Integer> faillist = new ArrayList<Integer>();
			//brand_id,goods_id,goods_threshold|brand_id,goods_id,goods_threshold
			String[] goodss = goodsthreshold.split("\\|");
			if(goodss!=null){
				String[] goodsarray = null;
				Goods goods = null;
				for(String goodssstr:goodss){
					goodsarray = goodssstr.split(",");
					if(goodsarray!=null && goodsarray.length == 3){
						try{
							goods = new Goods();
							goods.setAccount_id(account_id);
							goods.setBrand_id(Integer.parseInt(goodsarray[0]));
							goods.setGoods_id(Integer.parseInt(goodsarray[1]));
							goods.setGoods_threshold(Integer.parseInt(goodsarray[2]));
							if(goodsMapper.updateGoodsThreshold(goods)<=0){
								faillist.add(Integer.parseInt(goodsarray[1]));
							}
						}catch(Exception e){
							try{
								faillist.add(Integer.parseInt(goodsarray[1]));
							}catch(Exception e1){
							}
						}
					}
				}
			}
			if(faillist!=null && faillist.isEmpty()){
				result.setCode(0);
				result.setMsg("修改成功");
			}else{
				result.setCode(0);
				result.setMsg("部分修改成功");
			}
		}else{
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public Result updateMoreGoodsScale(Integer account_id,String goodsscale){
		Result result = new Result();
		if(account_id!=null && account_id > 0 && StringUtil.isValid(goodsscale)){
			List<Integer> faillist = new ArrayList<Integer>();
			//brand_id,goods_id,goods_scale|brand_id,goods_id,goods_scale
			String[] goodss = goodsscale.split("\\|");
			if(goodss!=null){
				String[] goodsarray = null;
				Goods goods = null;
				for(String goodssstr:goodss){
					goodsarray = goodssstr.split(",");
					if(goodsarray!=null && goodsarray.length == 3){
						try{
							goods = new Goods();
							goods.setAccount_id(account_id);
							goods.setBrand_id(Integer.parseInt(goodsarray[0]));
							goods.setGoods_id(Integer.parseInt(goodsarray[1]));
							goods.setGoods_scale(Integer.parseInt(goodsarray[2]));
							if(goodsMapper.updateGoodsScale(goods)<=0){
								faillist.add(Integer.parseInt(goodsarray[1]));
							}
						}catch(Exception e){
							try{
								faillist.add(Integer.parseInt(goodsarray[1]));
							}catch(Exception e1){
							}
						}
					}
				}
			}
			if(faillist!=null && faillist.isEmpty()){
				result.setCode(0);
				result.setMsg("修改成功");
			}else{
				result.setCode(0);
				result.setMsg("部分修改成功");
			}
		}else{
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public Result updateGoodsPrice(GoodsPrice goodsprice){
		Result result = new Result();
		if(goodsprice!=null && goodsprice.getGoods_id()!=null && goodsprice.getGoods_id() > 0 && goodsprice.getAgentlevel_id()!=null && goodsprice.getAgentlevel_id() > 0 && goodsprice.getAccount_id()!=null && goodsprice.getAccount_id() > 0 && goodsprice.getGoods_price()!=null){
			if(goodsMapper.updateGoodsPrice(goodsprice) > 0){
				result.setCode(0);
				result.setMsg("修改成功");
			}else{
				if(goodsMapper.insertGoodsPrice(goodsprice) > 0){
					result.setCode(0);
					result.setMsg("修改成功");
				}else{
					result.setCode(1);
					result.setMsg("修改失败");
				}
			}
		}else{
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public GoodsAgentLevelPriceResult queryGoodsPrice(GoodsPriceQuery goodsPriceQuery){
		GoodsAgentLevelPriceResult result = new GoodsAgentLevelPriceResult();
		if(goodsPriceQuery!=null && goodsPriceQuery.getBrand_id()!=null && goodsPriceQuery.getBrand_id() > 0 && goodsPriceQuery.getAgentlevel_id()!=null && goodsPriceQuery.getAgentlevel_id() >= 0 && goodsPriceQuery.getAccount_id()!=null && goodsPriceQuery.getAccount_id() > 0){
			if(goodsPriceQuery.getAgentlevel_id() == 0){
				result.setList(goodsMapper.queryDefaultGoodsPrice(goodsPriceQuery));
			}else{
				result.setList(goodsMapper.queryGoodsAgentLevelPrice(goodsPriceQuery));
			}
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public boolean changeGoodsStock(Integer brand_id,Integer goods_id,Integer account_id,Integer goods_stock,Integer total_id,Integer total_type){
		GoodsStock goodsStock = new GoodsStock();
		goodsStock.setGoods_id(goods_id);
		goodsStock.setAccount_id(account_id);
		goodsStock.setTotal_id(total_id);
		goodsMapper.updateGoodsStockState(goodsStock);
		Goods stock = goodsMapper.queryGoodsStock(goodsStock);
		if(stock!=null){
			if(total_type == 0){
				goodsStock.setOld_stock(stock.getGoods_stock());
				goodsStock.setNew_stock(goods_stock);
				goodsStock.setGoods_stock(goods_stock - stock.getGoods_stock());
			}else if(total_type == 1){
				goodsStock.setOld_stock(stock.getGoods_stock());
				goodsStock.setNew_stock(stock.getGoods_stock() - goods_stock);
				goodsStock.setGoods_stock(-goods_stock);
			}else if(total_type == 2){
				goodsStock.setOld_stock(stock.getGoods_stock());
				goodsStock.setNew_stock(stock.getGoods_stock() + goods_stock);
				goodsStock.setGoods_stock(goods_stock);
			}else if(total_type == 3){
				goodsStock.setOld_stock(stock.getGoods_stock());
				goodsStock.setNew_stock(stock.getGoods_stock() - goods_stock);
				goodsStock.setGoods_stock(-goods_stock);
			}else if(total_type == 4){
				goodsStock.setOld_stock(stock.getGoods_stock());
				goodsStock.setNew_stock(goods_stock);
				goodsStock.setGoods_stock(goods_stock - stock.getGoods_stock());
			}else if(total_type == 5){
				goodsStock.setOld_stock(stock.getGoods_stock());
				goodsStock.setNew_stock(stock.getGoods_stock() - goods_stock);
				goodsStock.setGoods_stock(-goods_stock);
			}else if(total_type == 6){
				goodsStock.setOld_stock(stock.getGoods_stock());
				goodsStock.setNew_stock(stock.getGoods_stock() + goods_stock);
				goodsStock.setGoods_stock(goods_stock);
			}else{
				return false;
			}
			int flag = 0;
			if(total_type == 0){
				flag = goodsMapper.updateSetGoodsStock(goodsStock);
			}else{
				flag = goodsMapper.updateGoodsStock(goodsStock);
			}
			if(flag > 0){
				if(goods_stock.intValue()<0){
					goodsStock.setState(3);
					goodsMapper.insertGoodsStock(goodsStock);
					//String pushmsg = stock.getGoods_name() + "商品无货，请您查看商品库存";
				}else if(goods_stock.intValue()<stock.getGoods_threshold()){
					goodsStock.setState(2);
					goodsMapper.insertGoodsStock(goodsStock);
					//String pushmsg = stock.getGoods_name() + "商品缺货，请及时进行补货";
				}else{
					goodsStock.setState(0);
					goodsMapper.insertGoodsStock(goodsStock);
				}
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public void changeOrderGoodsStock(List<OrderGoods> ordergoodslist,Integer order_type){
		if(order_type == 1){
			GoodsStock goodsStock = null;
			Goods stock = null;
			String goods_name = null;
			Integer push_type = 0;
			Map<Integer,Integer> map = new HashMap<Integer,Integer>();
			Map<Integer,OrderGoods> namemap = new HashMap<Integer,OrderGoods>();
			if(ordergoodslist!=null && !ordergoodslist.isEmpty()){
				GoodsStockTotal total = new GoodsStockTotal();
				if(order_type == 1){
					total.setTotal_type(1);
				}else{
					total.setTotal_type(2);
				}
				int i=0;
				for(OrderGoods ordergoods:ordergoodslist){
					if(i==0){
						total.setAccount_id(ordergoods.getAccount_id());
						goodsMapper.insertGoodsStockTotal(total);
					}
					goodsStock = new GoodsStock();
					goodsStock.setGoods_id(ordergoods.getGoods_id());
					goodsStock.setAccount_id(ordergoods.getAccount_id());
					goodsStock.setTotal_id(total.getTotal_id());
					if(order_type == 2){
						goodsMapper.updateGoodsStockState(goodsStock);
					}
					stock = goodsMapper.queryGoodsStock(goodsStock);
					if(stock!=null){
						goodsStock.setOld_stock(stock.getGoods_stock());
						goodsStock.setNew_stock(stock.getGoods_stock() + ordergoods.getGoods_cnum());
						goodsStock.setGoods_stock(ordergoods.getGoods_cnum());
						if(goodsMapper.updateGoodsStock(goodsStock) > 0){
							if(goodsStock.getNew_stock().intValue()<0){
								if(order_type == 2){
									goodsStock.setState(3);
									goodsMapper.insertGoodsStock(goodsStock);
									goods_name = stock.getGoods_name();
									push_type = 2;
									if(map.get(ordergoods.getBrand_id())!=null){
										map.put(ordergoods.getBrand_id(), map.get(ordergoods.getBrand_id()) + 1);
									}else{
										map.put(ordergoods.getBrand_id(), 1);
									}
									namemap.put(ordergoods.getBrand_id(),ordergoods);
								}else{
									goodsStock.setState(3);
									if(goodsMapper.checkGoodsStock(goodsStock) <= 0){
										goodsMapper.insertGoodsStock(goodsStock);
										goods_name = stock.getGoods_name();
										push_type = 2;
										if(map.get(ordergoods.getBrand_id())!=null){
											map.put(ordergoods.getBrand_id(), map.get(ordergoods.getBrand_id()) + 1);
										}else{
											map.put(ordergoods.getBrand_id(), 1);
										}
										namemap.put(ordergoods.getBrand_id(),ordergoods);
									}else{
										goodsStock.setState(0);
										goodsMapper.insertGoodsStock(goodsStock);
									}
								}
							}else if(goodsStock.getNew_stock().intValue()<stock.getGoods_threshold()){
								if(order_type == 2){
									goodsStock.setState(2);
									goodsMapper.insertGoodsStock(goodsStock);
									goods_name = stock.getGoods_name();
									push_type = 1;
									if(map.get(ordergoods.getBrand_id())!=null){
										map.put(ordergoods.getBrand_id(), map.get(ordergoods.getBrand_id()) + 1);
									}else{
										map.put(ordergoods.getBrand_id(), 1);
									}
									namemap.put(ordergoods.getBrand_id(),ordergoods);
								}else{
									goodsStock.setState(2);
									if(goodsMapper.checkGoodsStock(goodsStock) <= 0){
										goodsMapper.insertGoodsStock(goodsStock);
										goods_name = stock.getGoods_name();
										push_type = 1;
										if(map.get(ordergoods.getBrand_id())!=null){
											map.put(ordergoods.getBrand_id(), map.get(ordergoods.getBrand_id()) + 1);
										}else{
											map.put(ordergoods.getBrand_id(), 1);
										}
										namemap.put(ordergoods.getBrand_id(),ordergoods);
									}else{
										goodsStock.setState(0);
										goodsMapper.insertGoodsStock(goodsStock);
									}
								}
							}else{
								goodsStock.setState(0);
								goodsMapper.insertGoodsStock(goodsStock);
							}
						}
					}
					i++;
				}
			}
			if(map.size() == 1){
				for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
					goodsMapper.updateThresholdCount(entry.getKey());
					OrderGoods ordergoods = namemap.get(entry.getKey());
					if(entry.getValue()!=null && entry.getValue() == 1){
						if(push_type!=null){
							String pushmsg = null;
							if(push_type == 1){
								pushmsg = goods_name + "商品缺货，请及时进行补货";
							}else if(push_type == 2){
								pushmsg = goods_name + "商品无货，请您查看商品库存";
							}
							JPushUtil.sendPush(pushmsg, ordergoods.getAccount_id(), ordergoods.getBrand_id(), ordergoods.getGoods_id(), ordergoods.getBrand_name(), goods_name);
						}
					}else{
						String pushmsg = ordergoods.getBrand_name() + "部分商品缺货，请及时进行补货";
						JPushUtil.sendPush(pushmsg, ordergoods.getAccount_id(), ordergoods.getBrand_id(), 0, ordergoods.getBrand_name(), "");
					}
				}
			}else if(map.size() > 1){
				OrderGoods ordergoods = null;
				for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
					goodsMapper.updateThresholdCount(entry.getKey());
					ordergoods = namemap.get(entry.getKey());
					String pushmsg = ordergoods.getBrand_name() + "部分商品缺货，请及时进行补货";
					JPushUtil.sendPush(pushmsg, ordergoods.getAccount_id(), ordergoods.getBrand_id(), 0, ordergoods.getBrand_name(), "");
				}
			}
		}
	}
	
	public BrandResult changeBrandIndex(IndexBrandList list){
		BrandResult result = new BrandResult();
		if(list!=null && list.getAccount_id()!=null && list.getAccount_id() > 0 && list.getList()!=null && !list.getList().isEmpty()){
			for(IndexId brand:list.getList()){
				goodsMapper.changeBrandIndex(list.getAccount_id(), brand.getId(), brand.getIndex());
			}
			result.setList(goodsMapper.queryBrandNameAndGoodsCount(list.getAccount_id()));
			result.setCode(0);
			result.setMsg("修改成功");
		}else{
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public BrandAndAgentLevelResult changeAgentLevelIndex(IndexAgentLevelList list){
		BrandAndAgentLevelResult result = new BrandAndAgentLevelResult();
		if(list!=null && list.getAccount_id()!=null && list.getAccount_id() > 0 && list.getBrand_id()!=null && list.getBrand_id() > 0 && list.getList()!=null && !list.getList().isEmpty()){
			for(IndexId brand:list.getList()){
				goodsMapper.changeAgentLevelIndex(list.getAccount_id(), list.getBrand_id(), brand.getId(), brand.getIndex());
			}
			Brand b = new Brand();
			b.setAccount_id(list.getAccount_id());
			b.setBrand_id(list.getBrand_id());
			result.setList(goodsMapper.queryAgentLevel(b));
			result.setCode(0);
			result.setMsg("修改成功");
		}else{
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public GoodsResult changeGoodsIndex(IndexGoodsList list){
		GoodsResult result = new GoodsResult();
		if(list!=null && list.getAccount_id()!=null && list.getAccount_id() > 0 && list.getBrand_id()!=null && list.getBrand_id() > 0 && list.getList()!=null && !list.getList().isEmpty()){
			for(IndexId brand:list.getList()){
				goodsMapper.changeGoodsIndex(list.getAccount_id(), list.getBrand_id(), brand.getId(), brand.getIndex());
			}
			Brand b = new Brand();
			b.setAccount_id(list.getAccount_id());
			b.setBrand_id(list.getBrand_id());
			result.setList(goodsMapper.queryGoods(b));
			result.setCode(0);
			result.setMsg("修改成功");
		}else{
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public Result sendPush(String message,Integer aid,Integer bid,Integer gid,String bname,String gname){
		Result result = new Result();
		try{
			JPushUtil.sendPush(message, aid, bid, gid, bname, gname);
			result.setCode(0);
			result.setMsg("发送成功");
		}catch(Exception e){
			e.printStackTrace();
			result.setCode(1);
			result.setMsg("发送失败");
		}
		return result;
	}
	
	public ShareCreateResult createShareBrand(ShareCreate sharecreate){
		ShareCreateResult result = new ShareCreateResult();
		try{
			if(sharecreate!=null && sharecreate.getAccount_id()!=null && sharecreate.getAccount_id() > 0){
				Share share = new Share();
				share.setAccount_id(sharecreate.getAccount_id());
				if(goodsMapper.insertShare(share) > 0){
					if(sharecreate.getBrand_idlist()!=null && !sharecreate.getBrand_idlist().isEmpty()){
						ShareBrand sharebrand = new ShareBrand();
						sharebrand.setShare_id(share.getShare_id());
						sharebrand.setAccount_id(share.getAccount_id());
						ShareAgentLevel shareagentlevel = new ShareAgentLevel();
						shareagentlevel.setAccount_id(share.getAccount_id());
						for(ShareCreateBrand scb:sharecreate.getBrand_idlist()){
							sharebrand.setBrand_id(scb.getBrand_id());
							goodsMapper.insertShareBrand(sharebrand);
							shareagentlevel.setSharebrand_id(sharebrand.getSharebrand_id());
							if(scb.getAgentlevel_idlist()!=null && !scb.getAgentlevel_idlist().isEmpty()){
								for(Integer alid:scb.getAgentlevel_idlist()){
									shareagentlevel.setAgentlevel_id(alid);
									goodsMapper.insertShareAgentLevel(shareagentlevel);
								}
							}
						}
						result.setShare_url(PropertiesUtil.getKeyValue("shareurl")+share.getShare_id());
						result.setCode(0);
						result.setMsg("创建成功");
					}else{
						result.setCode(1);
						result.setMsg("创建失败");
					}
				}else{
					result.setCode(1);
					result.setMsg("创建失败");
				}
			}else{
				result.setCode(1);
				result.setMsg("创建失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			result.setCode(1);
			result.setMsg("创建失败");
		}
		return result;
	}
	
	public ShareQueryResult queryShare(Integer account_id){
		ShareQueryResult result = new ShareQueryResult();
		try{
			if(account_id!=null && account_id > 0){
				List<ShareQuery> sharelist = goodsMapper.queryShareUse(account_id);
				if(sharelist!=null && !sharelist.isEmpty()){
					for(ShareQuery sq:sharelist){
						List<ShareQueryBrand> sharebrandlist = goodsMapper.queryShareBrand(sq.getShare_id());
						if(sharebrandlist!=null && !sharebrandlist.isEmpty()){
							for(ShareQueryBrand sqb:sharebrandlist){
								sqb.setAgentlevellist(goodsMapper.queryShareAgentLevel(sqb.getSharebrand_id()));
							}
							sq.setBrandlist(sharebrandlist);
						}
					}
					result.setSharelist(sharelist);
				}
				result.setCode(0);
				result.setMsg("查询成功");
			}else{
				result.setCode(1);
				result.setMsg("查询失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}

	public Result deleteAllShareUse(Integer account_id){
		Result result = new Result();
		try{
			if(account_id!=null && account_id > 0){
				if(goodsMapper.deleteAllShareUse(account_id) > 0){
					result.setCode(0);
					result.setMsg("取消成功");
				}else{
					result.setCode(1);
					result.setMsg("取消失败");
				}
			}else{
				result.setCode(1);
				result.setMsg("取消失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			result.setCode(1);
			result.setMsg("取消失败");
		}
		return result;
	}
	
	public ShareDownloadResult downloadShareUse(ShareDownload sharedownload){
		ShareDownloadResult result = new ShareDownloadResult();
		List<Integer> failbrandlist = new ArrayList<Integer>();
		List<Integer> successbrandlist = new ArrayList<Integer>();
		try{
			if(sharedownload!=null){
				if(sharedownload.getSharelist()!=null && !sharedownload.getSharelist().isEmpty()){
					ShareUse shareuse = null;
					Integer brand_id = null;
					for(ShareDownloadShare sds:sharedownload.getSharelist()){
						shareuse = new ShareUse();
						shareuse.setAccount_id(sharedownload.getDownload_id());
						shareuse.setShare_id(sds.getShare_id());
						if(sds.getBrandlist()!=null && !sds.getBrandlist().isEmpty()){
							if(goodsMapper.checkShareNoUse(shareuse) > 0){
								for(ShareDownloadBrand sdb:sds.getBrandlist()){
									if(sdb.getBrand_id()!=null && sdb.getBrand_id() > 0 && sdb.getAgentlevel_id()!=null && sdb.getAgentlevel_id() > 0){
										brand_id = downloadsharebrand(sharedownload.getDownload_id(),sds.getAccount_id(),sdb.getBrand_id(),sdb.getAgentlevel_id(),sdb.getRetail_agentlevel_id(),sds.getShare_id());
										if(brand_id!=null && brand_id > 0){
											successbrandlist.add(brand_id);
										}else{
											failbrandlist.add(sdb.getBrand_id());
										}
									}else{
										failbrandlist.add(sdb.getBrand_id());
									}
								}
							}else{
								for(ShareDownloadBrand sdb:sds.getBrandlist()){
									failbrandlist.add(sdb.getBrand_id());
								}
							}
						}
						goodsMapper.deleteShareUse(shareuse);
					}
				}
				result.setFailbrandlist(failbrandlist);
				result.setSuccessbrandlist(successbrandlist);
				result.setCode(0);
				result.setMsg("同步成功");
			}else{
				result.setCode(1);
				result.setMsg("同步失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			result.setCode(1);
			result.setMsg("同步失败");
		}
		return result;
	}
	
	private Integer downloadsharebrand(Integer download_id,Integer account_id,Integer brand_id,Integer agentlevel_id,Integer retail_agentlevel_id,Integer share_id){
		Integer result = 0;
		Brand brand = new Brand();
		brand.setAccount_id(download_id);
		brand.setBrand_from(-brand_id);
		if(goodsMapper.checkRecommendBrand(brand) <= 0){
			String brand_name = goodsMapper.queryBrandNameById(brand_id);
			if(StringUtil.isValid(brand_name)){
				brand.setBrand_name(brand_name);
				if(goodsMapper.insertBrand(brand) > 0){
					Integer newbrand_id = brand.getBrand_id();
					AgentLevel agentlevel = new AgentLevel();
					agentlevel.setAccount_id(download_id);
					agentlevel.setBrand_id(newbrand_id);
					agentlevel.setAgentlevel_name("零售价");
					agentlevel.setAgentlevel_default(1);
					goodsMapper.insertAgentLevel(agentlevel);
					Integer newagentlevel = agentlevel.getAgentlevel_id();
					
					Map<Integer,Integer> agentlevelidmap = new TreeMap<Integer,Integer>();
					List<ShareQueryAgentLevel> agentlevellist = goodsMapper.queryShareAgentLevelByShare(share_id, brand_id);
					if(agentlevellist!=null && !agentlevellist.isEmpty()){
						for(ShareQueryAgentLevel shareagentlevel:agentlevellist){
							if(!"零售价".equals(shareagentlevel.getAgentlevel_name())){
								agentlevel = new AgentLevel();
								agentlevel.setAccount_id(download_id);
								agentlevel.setBrand_id(newbrand_id);
								agentlevel.setAgentlevel_name(shareagentlevel.getAgentlevel_name());
								agentlevel.setAgentlevel_default(0);
								goodsMapper.insertAgentLevel(agentlevel);
								agentlevelidmap.put(shareagentlevel.getAgentlevel_id(), agentlevel.getAgentlevel_id());
							}else{
								agentlevelidmap.put(shareagentlevel.getAgentlevel_id(), newagentlevel);
							}
						}
					}
					
					brand.setBrand_id(brand_id);
					brand.setAccount_id(account_id);
					List<Goods> glist = goodsMapper.queryGoodsList(brand);									
					if(glist!=null && !glist.isEmpty()){
						GoodsPrice goodsprice = new GoodsPrice();
						goodsprice.setAccount_id(account_id);
						GoodsPrice oldgoodsprice = null;
						GoodsPrice newgoodsprice = null;
						GoodsPrice retailgoodsprice = null;
						for(Goods goods:glist){
							goodsprice.setGoods_id(goods.getGoods_id());
							goodsprice.setAgentlevel_id(agentlevel_id);
							oldgoodsprice = goodsMapper.queryGoodsPriceById(goodsprice);
							if(oldgoodsprice!=null){
								goods.setGoods_price(oldgoodsprice.getGoods_price());
							}else{
								goods.setGoods_price(0f);
							}
							goods.setAccount_id(download_id);
							goods.setBrand_id(newbrand_id);
							goodsMapper.insertGoods(goods);
							goodsMapper.incBrandGoodsCount(newbrand_id);
							
							for (Integer id:agentlevelidmap.keySet()) {
								Integer newid = agentlevelidmap.get(id);
								if(id > 0){
									goodsprice.setAgentlevel_id(id);
									retailgoodsprice = goodsMapper.queryGoodsPriceById(goodsprice);
									newgoodsprice = new GoodsPrice();
									if(retailgoodsprice!=null){
										newgoodsprice.setGoods_price(retailgoodsprice.getGoods_price());
									}else{
										newgoodsprice.setGoods_price(0f);
									}
									newgoodsprice.setAccount_id(download_id);
									newgoodsprice.setGoods_id(goods.getGoods_id());
									newgoodsprice.setAgentlevel_id(newid);
									goodsMapper.insertGoodsPrice(newgoodsprice);
								}
							}
						}
					}
					result = newbrand_id;
				}
			}
		}
		return result;
	}
	
	public GoodsStockResult queryNoSetStockGoods(Integer account_id){
		GoodsStockResult result = new GoodsStockResult();
		if(account_id!=null && account_id > 0){
			result.setList(goodsMapper.queryGoodsStockList(account_id));
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public GoodsStockResult queryNoStockGoods(Integer account_id,Integer brand_id){
		GoodsStockResult result = new GoodsStockResult();
		if(account_id!=null && account_id > 0){
			if(brand_id!=null && brand_id > 0){
				result.setList(goodsMapper.queryGoodsNoStockList(account_id,brand_id));
			}else{
				result.setList(goodsMapper.queryGoodsAllNoStockList(account_id));
			}
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public GoodsStockResult queryHasStockGoods(Integer account_id,Integer brand_id){
		GoodsStockResult result = new GoodsStockResult();
		if(account_id!=null && account_id > 0){
			if(brand_id!=null && brand_id > 0){
				result.setList(goodsMapper.queryGoodsHasStockList(account_id,brand_id));
			}else{
				result.setList(goodsMapper.queryGoodsAllHasStockList(account_id));
			}
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public GoodsStockTotalResult queryStockTotal(Integer account_id){
		GoodsStockTotalResult result = new GoodsStockTotalResult();
		if(account_id!=null && account_id > 0){
			result.setTotal_money(goodsMapper.queryGoodsTotalMoney(account_id));
			result.setTotal_goods(goodsMapper.queryGoodsTotal(account_id));
			result.setTotal_nostock(goodsMapper.queryGoodsNoStockTotal(account_id));
			result.setTotal_setstock(goodsMapper.queryGoodsSetStockTotal(account_id));
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public GoodsStockListResult queryStockGoodsList(Integer account_id,Integer type,String time){
		GoodsStockListResult result = new GoodsStockListResult();
		if(account_id!=null && account_id > 0 && type!=null && type >= 1 && type <= 6){
			if(StringUtil.isEmpty(time)){
				time = goodsMapper.queryNowdate();
			}
			result.setList(goodsMapper.queryGoodsTypeStockList(account_id,type,time));
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public Result updateAllGoodsThreshold(Integer account_id,Integer goodsthreshold){
		Result result = new Result();
		if(account_id!=null && account_id > 0){
			goodsMapper.updateAllGoodsThreshold(account_id,goodsthreshold);
			result.setCode(0);
			result.setMsg("修改成功");
		}else{
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public GoodsStockResult queryAllStockGoods(Integer account_id,Integer brand_id){
		GoodsStockResult result = new GoodsStockResult();
		if(account_id!=null && account_id > 0){
			if(brand_id!=null && brand_id > 0){
				result.setList(goodsMapper.queryGoodsAllStockList(account_id,brand_id));
			}else{
				result.setList(goodsMapper.queryGoodsAllAllStockList(account_id));
			}
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public BrandHotListResult queryAllHotBrand(Integer account_id){
		BrandHotListResult result = new BrandHotListResult();
		if(account_id!=null && account_id > 0){
			result.setList(goodsMapper.queryAllHotBrand());
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}

	public Result setBrandStockRemain(Brand brand) {
		Result result = new Result();
		if(brand.getBrand_id()==null || brand.getStock_remind()==null){
			result.setCode(1);
			result.setMsg("必填参数为空");
			return result;
		}
		if(goodsMapper.setBrandStockRemain(brand) > 0 ){
			result.setMsg("设置成功");
			result.setCode(0);
		} else{
			result.setMsg("设置失败");
			result.setCode(1);
		}
		return result;
	}

	public Object selectHotBrand() {
		Result result = new Result();
		try {
			List<Brand> brands = goodsMapper.selectHotBrand();
			List<HotBrandResult> hotBrandResults = new ArrayList<>();
			HotBrandResult hotBrandResult;
			for (Brand brand:brands) {
				hotBrandResult = new HotBrandResult();
				hotBrandResult.setBrand(brand);
				List<BrandImagesResult> brandImagesResults = goodsMapper.queryBrandImages(brand);
				hotBrandResult.setBrandImagesResults(brandImagesResults);
				List<AgentLevel> agentLevels = goodsMapper.queryAgentLevel(brand);
				hotBrandResult.setAgentLevels(agentLevels);
				hotBrandResults.add(hotBrandResult);
			}
			if(!CollectionUtils.isEmpty(hotBrandResults)){
				result.setCode(0);
				result.setMsg("success");
				result.setData(hotBrandResults);
			}else{
				result.setCode(1);
				result.setMsg("fail");
			}
		} catch (Exception e) {
			LOGGER.error("GoodsService selectHotBrand error: "+e.getMessage());
			result.setCode(1);
			result.setMsg("fail");
		}
		return result;
	}

    public BrandInfo getBrandInfo(Integer brand_id) {
		BrandInfo brandInfo = new BrandInfo();
		if(brand_id==null){
			brandInfo.setMsg("查询失败");
			brandInfo.setCode(1);
			return brandInfo;
		}
		try {
			brandInfo.setBrand(goodsMapper.getBrandInfo(brand_id));
			Brand brand = new Brand();
			brand.setAccount_id(0);
			brand.setBrand_id(brand_id);
			brandInfo.setBrandAndImages(goodsMapper.queryBrandImages(brand));
			brandInfo.setMsg("查询成功");
			brandInfo.setCode(0);
		} catch (Exception e) {
			LOGGER.error("GoodsService getBrandInfo error: "+e.getMessage());
			brandInfo.setMsg("查询失败");
			brandInfo.setCode(1);
		}
		return brandInfo;
    }
}