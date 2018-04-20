package com.backend.goodsV2.service;

import com.api.goods.entity.BrandAndAgentLevel;
import com.api.goods.entity.BrandResult;
import com.api.goods.entity.GoodsResult;
import com.api.goods.service.GoodsService;
import com.backend.goodsV2.entity.BrandAndAgentLevelV2;
import com.backend.goodsV2.entity.BrandResultV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceV2 {

    @Autowired
    private GoodsService goodsService;

    //查询全量品牌和商品
    public BrandResultV2 queryBrandAndGoods(Integer account_id){
        BrandResultV2 brandResultV2 = new BrandResultV2();
        if(account_id==null){
            brandResultV2.setCode(201);
            brandResultV2.setMsg("空账号");
            return brandResultV2;
        }
        BrandResult brandResult = goodsService.queryBrand(account_id, 4);
        List<BrandAndAgentLevel> brandAndAgentLevelList = brandResult.getList();
        if(CollectionUtils.isEmpty(brandAndAgentLevelList)){
            brandResultV2.setCode(201);
            brandResultV2.setMsg("无品牌信息");
            return brandResultV2;
        }
        List<BrandAndAgentLevelV2> brandAndAgentLevelV2List = new ArrayList<>();
        BrandAndAgentLevelV2 brandAndAgentLevelV2;
        for (int i = 0; i < brandAndAgentLevelList.size(); i++) {
            brandAndAgentLevelV2 = new BrandAndAgentLevelV2();
            if(brandAndAgentLevelList.get(i)==null){
                continue;
            }
            setBrandValue(brandAndAgentLevelList.get(i),brandAndAgentLevelV2);
            GoodsResult goodsResult = goodsService.queryGoods(account_id, brandAndAgentLevelV2.getBrand_id(), 1);
//            brandAndAgentLevelV2.setGoodsAndGoodsPricelist(CollectionUtils.isEmpty(goodsResult.getList())?new ArrayList<>():goodsResult.getList());
            brandAndAgentLevelV2List.add(brandAndAgentLevelV2);
        }
        brandResultV2.setCode(0);
        brandResultV2.setList(brandAndAgentLevelV2List);
        return brandResultV2;
    }

    void setBrandValue(BrandAndAgentLevel b1,BrandAndAgentLevelV2 b2){
        b2.setBrand_id(b1.getBrand_id());
        b2.setBrand_name(b1.getBrand_name());
        b2.setGoods_count(b1.getGoods_count()==null?0:b1.getGoods_count());
        b2.setImages_count(b1.getImages_count()==null?0:b1.getImages_count());
        b2.setThreshold_count(b1.getThreshold_count()==null?0:b1.getThreshold_count());
        b2.setBrand_from(b1.getBrand_from()==null?0:b1.getBrand_from());
        b2.setBrand_index(b1.getBrand_index()==null?0:b1.getBrand_index());
        b2.setAccount_id(b1.getAccount_id()==null?0:b1.getAccount_id());
//        b2.setAgentlevellist(CollectionUtils.isEmpty(b1.getAgentlevellist())?new ArrayList<>():b1.getAgentlevellist());
    }
}
