package com.backend.admin.mapper;


import com.backend.admin.entity.GoodsPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsPriceMapper {

    List<GoodsPrice> listGoodsPriceByGoodsId(int goodsId);

    void saveGoodsPriceBatch(List<GoodsPrice> goodsPrices);

    void updateGoodsPriceBatch(List<GoodsPrice> goodsPrices);

    int removeGoodsPriceByGoodsId(int goodsId);

    int checkGoodsPrice(@Param("goodsId") int goodsId, @Param("agentLevelId") int agentLevelId);

    void saveGoodsPrice(GoodsPrice goodsPrice);

    int updateGoodsPrice(GoodsPrice goodsPrice);
}
