package com.backend.admin.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author caojiantao
 * @since 2018-05-12 16:44:41
 */
public interface BrandPriceMapper {

    Double getPriceByBrandId(int brandId);

    int updateBrandPriceByBrandId(@Param("brandId") int brandId, @Param("price") double price);

    void saveBrandPrice(@Param("brandId") int brandId, @Param("price") double price);

    int removeBrandPriceByBrandId(int brandId);
}
