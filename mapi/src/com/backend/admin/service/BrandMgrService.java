package com.backend.admin.service;

import cn.hutool.core.util.ObjectUtil;
import com.api.common.entity.Images;
import com.api.common.service.ImagesService;
import com.api.goods.entity.AgentLevel;
import com.api.goods.entity.BrandImages;
import com.api.goods.entity.BrandImagesResult;
import com.api.goods.mapper.GoodsMapper;
import com.backend.admin.entity.Brand;
import com.backend.admin.mapper.AgentLevelMapper;
import com.backend.admin.mapper.BrandMgrMapper;
import com.backend.admin.mapper.BrandPriceMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandMgrService {

    @Autowired
    private BrandMgrMapper brandMgrMapper;

    @Autowired
    private AgentLevelMapper agentLevelMapper;

    @Autowired
    private BrandPriceMapper brandPriceMapper;

    @Autowired
    private ImagesService imagesService;

    @Autowired
    private GoodsMapper goodsMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandMgrService.class);

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
        //查询品牌下的图片集合
        brand.setImageList(brandMgrMapper.queryBrandImages(brand.getBrandId()));
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
        //保存品牌图
        if(brand.getBrandId()>0 && StringUtils.isNotBlank(brand.getImages())){
            String[] imgArr = brand.getImages().split("##");
            for (String imgUrl:imgArr) {
                BrandImages brandImages = new BrandImages();
                brandImages.setAccount_id(0);
                brandImages.setBrand_id(brand.getBrandId());
                brandImages.setBrandimages_url(imgUrl);
                goodsMapper.insertBrandImages(brandImages);
            }
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
//        agentLevelMapper.removeAgentLevelByBrandId(brand.getBrandId());
        List<Integer> curAgentLevelIds = agentLevelMapper.listAgentLevelIdByBrandId(brand.getBrandId());
        List<Integer> updateIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(brand.getAgents())) {
            for (AgentLevel level : brand.getAgents()) {
                level.setBrand_id(brand.getBrandId());
                if (level.getAgentlevel_id() != null) {
                    updateIds.add(level.getAgentlevel_id());
                    // 更新代理
                    agentLevelMapper.updateAgentById(level);
                } else {
                    // 新增代理
                    agentLevelMapper.saveAgentLevel(level);
                }
            }
//            agentLevelMapper.saveAgentLevelByBrandId(brand.getAgents());
        }
        // 获取需要删除的ID集合
        curAgentLevelIds.removeAll(updateIds);
        if (!CollectionUtils.isEmpty(curAgentLevelIds)) {
            agentLevelMapper.removeAgentLevelByIds(curAgentLevelIds);
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
        //保存品牌图
        if(StringUtils.isNotBlank(brand.getImages())){
            String[] imgArr = brand.getImages().split("##");
            for (String imgUrl:imgArr) {
                BrandImages brandImages = new BrandImages();
                brandImages.setAccount_id(0);
                brandImages.setBrand_id(brand.getBrandId());
                brandImages.setBrandimages_url(imgUrl);
                goodsMapper.insertBrandImages(brandImages);
            }
        }
        return brandMgrMapper.updateBrand(brand) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean removeBrandById(int id) {
        agentLevelMapper.removeAgentLevelByBrandId(id);
        brandPriceMapper.removeBrandPriceByBrandId(id);
        return brandMgrMapper.removeBrandById(id) > 0;
    }

    void increaseGoodsCount(int id) {
        brandMgrMapper.increaseGoodsCount(id);
    }

    void decreaseGoodsCount(int id) {
        brandMgrMapper.decreaseGoodsCount(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Images uploadBrandIcon(MultipartFile file, Integer brand_id, Integer account_id){
        Images images;
        try {
            if(file==null || account_id==null){
                return null;
            }
            images = imagesService.uploadImages(file, "brand", null);
            if(images==null){
                return null;
            }
            //保存品牌图片信息
            if(images.getCode()==0 && brand_id!=null){
                com.api.goods.entity.Brand brand = new com.api.goods.entity.Brand();
                brand.setAccount_id(account_id);
                brand.setBrand_id(brand_id);
                //覆盖旧的品牌图片
                List<BrandImagesResult> brandImagesResults = goodsMapper.queryBrandImages(brand);
                if(!CollectionUtils.isEmpty(brandImagesResults)){
                    //删除旧的品牌图片
                    BrandImages brandImages = new BrandImages();
                    brandImages.setAccount_id(account_id);
                    brandImages.setBrand_id(brand_id);
                    goodsMapper.deleteBrandImages(brandImages);
                }
                //插入新的品牌图片
                BrandImages brandImages = new BrandImages();
                brandImages.setAccount_id(account_id);
                brandImages.setBrand_id(brand_id);
                brandImages.setBrandimages_url(images.getUrlPath());
                goodsMapper.insertBrandImages(brandImages);
            }
        } catch (Exception e) {
            LOGGER.error("BrandMgrService uploadBrandIcon error: "+e.getMessage());
            return null;
        }
        return images;
    }

    public boolean setBrandHot(int id,int type){
        int result;
        if(type==1){
            result = brandMgrMapper.setBrandHot(id);
        }else{
            result = brandMgrMapper.cancelBrandHot(id);
        }
        return result > 0;
    }

}
