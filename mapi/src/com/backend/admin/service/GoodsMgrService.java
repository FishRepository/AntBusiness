package com.backend.admin.service;

import com.backend.admin.entity.Brand;
import com.backend.admin.entity.Goods;
import com.backend.admin.mapper.GoodsMgrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsMgrService {

    @Autowired
    private GoodsMgrMapper goodsMgrMapper;
    @Autowired
    private BrandMgrService brandMgrService;

    public List<Goods> listGoods(Long brandId) {
        return goodsMgrMapper.listGoods(brandId);
    }

    public Goods getGoodsById(int id) {
        return goodsMgrMapper.getGoodsById(id);
    }

    @Transactional
    public boolean saveGoods(Goods goods) {
        goodsMgrMapper.saveGoods(goods);
        brandMgrService.increaseGoodsCount(goods.getBrandId());
        return goods.getGoodsId() > 0;
    }

    public boolean updateGoods(Goods goods) {
        return goodsMgrMapper.updateGoods(goods) > 0;
    }

    @Transactional
    public boolean removeGoodsById(int id) {
        Goods goods = goodsMgrMapper.getGoodsById(id);
        brandMgrService.decreaseGoodsCount(goods.getBrandId());
        return goodsMgrMapper.removeGoodsById(id) > 0;
    }
}
