package com.backend.admin.service;

import com.backend.admin.entity.Goods;
import com.backend.admin.entity.GoodsPrice;
import com.backend.admin.mapper.GoodsMgrMapper;
import com.backend.admin.mapper.GoodsPriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class GoodsMgrService {

    @Autowired
    private GoodsMgrMapper goodsMgrMapper;
    @Autowired
    private BrandMgrService brandMgrService;
    @Autowired
    private GoodsPriceMapper goodsPriceMapper;

    public List<Goods> listGoods(Long brandId) {
        return goodsMgrMapper.listGoods(brandId);
    }

    public Goods getGoodsById(int id) {
        Goods goods = goodsMgrMapper.getGoodsById(id);
        goods.setGoodsPriceList(goodsPriceMapper.listGoodsPriceByGoodsId(id));
        return goods;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveGoods(Goods goods) {
        goodsMgrMapper.saveGoods(goods);
        brandMgrService.increaseGoodsCount(goods.getBrandId());
        if (!CollectionUtils.isEmpty(goods.getGoodsPriceList())) {
            // 得到成功插入的goodsId，然后在进行赋值插入
            for (GoodsPrice goodsPrice : goods.getGoodsPriceList()) {
                goodsPrice.setGoods_id(goods.getGoodsId());
            }
            goodsPriceMapper.saveGoodsPriceBatch(goods.getGoodsPriceList());
        }
        return goods.getGoodsId() > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateGoods(Goods goods) {
        if (!CollectionUtils.isEmpty(goods.getGoodsPriceList())) {
//            for (GoodsPrice goodsPrice : goods.getGoodsPriceList()) {
//                if (goodsPriceMapper.checkGoodsPrice(goods.getGoodsId(), goodsPrice.getAgentlevel_id()) > 0) {
//                    goodsPriceMapper.updateGoodsPrice(goodsPrice);
//                } else {
//                    goodsPriceMapper.saveGoodsPrice(goodsPrice);
//                }
//            }
            goodsPriceMapper.updateGoodsPriceBatch(goods.getGoodsPriceList());
        }
        return goodsMgrMapper.updateGoods(goods) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean removeGoodsById(int id) {
        Goods goods = goodsMgrMapper.getGoodsById(id);
        brandMgrService.decreaseGoodsCount(goods.getBrandId());
        return goodsMgrMapper.removeGoodsById(id) > 0;
    }
}
