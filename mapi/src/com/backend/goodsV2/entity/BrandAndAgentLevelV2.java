package com.backend.goodsV2.entity;

import com.api.goods.entity.BrandAndAgentLevel;
import com.api.goods.entity.GoodsAndGoodsPrice;

import java.util.List;

public class BrandAndAgentLevelV2 extends BrandAndAgentLevel {

    private List<GoodsAndGoodsPrice> goodsAndGoodsPricelist;

    public List<GoodsAndGoodsPrice> getGoodsAndGoodsPricelist() {
        return goodsAndGoodsPricelist;
    }

    public void setGoodsAndGoodsPricelist(List<GoodsAndGoodsPrice> goodsAndGoodsPricelist) {
        this.goodsAndGoodsPricelist = goodsAndGoodsPricelist;
    }
}
