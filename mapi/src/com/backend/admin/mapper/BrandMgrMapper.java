package com.backend.admin.mapper;

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

    public List<Brand> selectAll();
}
