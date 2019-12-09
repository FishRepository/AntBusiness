package com.backend.admin.mapper;

import com.api.goods.entity.BrandImages;
import com.backend.admin.entity.Brand;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2018-05-05
 */
public interface BrandMgrMapper{

    List<Brand> selectAll();

    Brand getBrandById(int id);

    void saveBrand(Brand brand);

    int updateBrand(Brand brand);

    int removeBrandById(int id);

    int increaseGoodsCount(int id);

    int decreaseGoodsCount(int id);

    int setBrandHot(int id);

    int cancelBrandHot(int id);

    List<BrandImages> queryBrandImages(int id);

}
