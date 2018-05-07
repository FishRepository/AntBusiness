package com.backend.admin.service;

import com.api.goods.entity.AgentLevel;
import com.backend.admin.entity.Brand;
import com.backend.admin.mapper.AgentLevelMapper;
import com.backend.admin.mapper.BrandMgrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class BrandMgrService {

    @Autowired
    private BrandMgrMapper brandMgrMapper;

    @Autowired
    private AgentLevelMapper agentLevelMapper;

    public List<Brand> selectAll() {
        return brandMgrMapper.selectAll();
    }

    public Brand getBrandById(int id) {
        Brand brand = brandMgrMapper.getBrandById(id);
        brand.setAgents(agentLevelMapper.listAgentLevelByBrandId(id));
        return brand;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveBrand(Brand brand) {
        brandMgrMapper.saveBrand(brand);
        if (!CollectionUtils.isEmpty(brand.getAgents())) {
            for (AgentLevel level : brand.getAgents()) {
                level.setBrand_id(brand.getBrandId());
            }
            agentLevelMapper.saveAgentLevelByBrandId(brand.getAgents());
        }
        return brand.getBrandId() > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateBrand(Brand brand) {
        agentLevelMapper.removeAgentLevelByBrandId(brand.getBrandId());
        if (!CollectionUtils.isEmpty(brand.getAgents())) {
            for (AgentLevel level : brand.getAgents()) {
                level.setBrand_id(brand.getBrandId());
            }
            agentLevelMapper.saveAgentLevelByBrandId(brand.getAgents());
        }
        return brandMgrMapper.updateBrand(brand) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean removeBrandById(int id) {
        agentLevelMapper.removeAgentLevelByBrandId(id);
        return brandMgrMapper.removeBrandById(id) > 0;
    }

    public boolean increaseGoodsCount(int id) {
        return brandMgrMapper.increaseGoodsCount(id) > 0;
    }

    public boolean decreaseGoodsCount(int id) {
        return brandMgrMapper.decreaseGoodsCount(id) > 0;
    }
}
