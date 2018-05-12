package com.backend.admin.service;

import com.api.goods.entity.AgentLevel;
import com.backend.admin.entity.Brand;
import com.backend.admin.mapper.AgentLevelMapper;
import com.backend.admin.mapper.BrandMgrMapper;
import com.backend.admin.mapper.BrandPriceMapper;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private BrandPriceMapper brandPriceMapper;

    public List<Brand> selectAll() {
        return brandMgrMapper.selectAll();
    }

    public Brand getBrandById(int id) {
        Brand brand = brandMgrMapper.getBrandById(id);
        // 初始化品牌搜索词
        if (StringUtils.isNotBlank(brand.getBrandShortname())) {
            String[] keys = brand.getBrandShortname().split("\\|");
            StringBuilder builder = new StringBuilder();
            for (String key : keys) {
                if (StringUtils.isBlank(key)) {
                    continue;
                }
                builder.append(key).append(",");
            }
            int len = builder.length();
            brand.setBrandShortname(builder.substring(0, len > 0 ? (len - 1) : len));
        }
        brand.setBrandPrice(brandPriceMapper.getPriceByBrandId(id));
        brand.setAgents(agentLevelMapper.listAgentLevelByBrandId(id));
        return brand;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveBrand(Brand brand) {
        initBrandShortname(brand);
        brandMgrMapper.saveBrand(brand);
        if (!CollectionUtils.isEmpty(brand.getAgents())) {
            for (AgentLevel level : brand.getAgents()) {
                level.setBrand_id(brand.getBrandId());
            }
            agentLevelMapper.saveAgentLevelByBrandId(brand.getAgents());
        }
        if (brand.getBrandPrice() != null) {
            brandPriceMapper.saveBrandPrice(brand.getBrandId(), brand.getBrandPrice());
        }
        return brand.getBrandId() > 0;
    }

    private void initBrandShortname(Brand brand) {
        // 初始化品牌搜索词
        if (StringUtils.isNotBlank(brand.getBrandShortname())) {
            String[] keys = brand.getBrandShortname().split(",");
            StringBuilder builder = new StringBuilder();
            builder.append("|");
            for (String key : keys) {
                if (StringUtils.isBlank(key)) {
                    continue;
                }
                builder.append(key).append("|");
            }
            brand.setBrandShortname(builder.toString());
        }
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
        if (brand.getBrandPrice() != null) {
            Double oldPrice = brandPriceMapper.getPriceByBrandId(brand.getBrandId());
            if (oldPrice == null) {
                brandPriceMapper.saveBrandPrice(brand.getBrandId(), brand.getBrandPrice());
            } else {
                brandPriceMapper.updateBrandPriceByBrandId(brand.getBrandId(), brand.getBrandPrice());
            }
        }
        initBrandShortname(brand);
        return brandMgrMapper.updateBrand(brand) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean removeBrandById(int id) {
        agentLevelMapper.removeAgentLevelByBrandId(id);
        brandPriceMapper.removeBrandPriceByBrandId(id);
        return brandMgrMapper.removeBrandById(id) > 0;
    }

    public boolean increaseGoodsCount(int id) {
        return brandMgrMapper.increaseGoodsCount(id) > 0;
    }

    public boolean decreaseGoodsCount(int id) {
        return brandMgrMapper.decreaseGoodsCount(id) > 0;
    }
}
