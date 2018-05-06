package com.backend.admin.service;

import com.backend.admin.entity.Brand;
import com.backend.admin.mapper.BrandMgrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandMgrService {

    @Autowired
    private BrandMgrMapper brandMgrMapper;

    public List<Brand> selectAll() {
        return brandMgrMapper.selectAll();
    }

    public Brand getBrandById(int id) {
        return brandMgrMapper.getBrandById(id);
    }

    public boolean saveBrand(Brand brand) {
        brandMgrMapper.saveBrand(brand);
        return brand.getBrandId() > 0;
    }

    public boolean updateBrand(Brand brand) {
        return brandMgrMapper.updateBrand(brand) > 0;
    }

    public boolean removeBrandById(int id) {
        return brandMgrMapper.removeBrandById(id) > 0;
    }

    public boolean increaseGoodsCount(int id) {
        return brandMgrMapper.increaseGoodsCount(id) > 0;
    }

    public boolean decreaseGoodsCount(int id) {
        return brandMgrMapper.decreaseGoodsCount(id) > 0;
    }
}
