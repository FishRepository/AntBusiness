package com.backend.admin.mapper;


import com.backend.admin.entity.Guide;

import java.util.List;

/**
 * <p>
 * 番茄管家使用指南 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2018-03-23
 */
public interface GuideMapper {

    List<Guide> selectAll();

    Guide selectById(Integer id);

    void saveGuide(Guide guide);

    int updateGuide(Guide guide);

}
