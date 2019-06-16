package com.backend.admin.mapper;

import com.backend.admin.entity.AdImg;

import java.util.List;

public interface AdImgMapper{

    List<AdImg> getAdImgs();

    Integer saveAdImg(AdImg adImg);

    AdImg getById(Integer id);

    Integer updateById(AdImg adImg);
}
