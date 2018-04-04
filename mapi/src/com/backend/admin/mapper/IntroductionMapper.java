package com.backend.admin.mapper;

import com.backend.admin.entity.Introduction;

import java.util.List;
import java.util.Map;

public interface IntroductionMapper {

    List<Introduction> listIntroduction(Map<String, Object> map);

    Introduction getIntroductionById(int id);

    void saveIntroduction(Introduction introduction);

    int updateIntroduction(Introduction introduction);
}
