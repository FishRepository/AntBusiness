package com.backend.admin.mapper;

import com.backend.admin.entity.Introduction;
import com.backend.admin.entity.IntroductionType;

import java.util.List;
import java.util.Map;

/**
 * @author caojiantao
 */
public interface IntroductionTypeMapper {

    List<IntroductionType> listIntroductionType(Map<String, Object> map);

    IntroductionType getIntroductionTypeById(int id);

    void saveIntroductionType(IntroductionType type);

    int updateIntroductionType(IntroductionType type);
}
