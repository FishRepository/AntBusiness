package com.backend.admin.mapper;


import com.backend.admin.entity.GoodsPrice;

import java.util.List;

public interface GoodsPriceMapper {

    List<GoodsPrice> listGoodsPriceByGoodsId(int goodsId);

    void saveGoodsPrice(List<GoodsPrice> goodsPrices);

    void updateGoodsPriceBatch(List<GoodsPrice> goodsPrices);

    int removeGoodsPriceByGoodsId(int goodsId);
}
