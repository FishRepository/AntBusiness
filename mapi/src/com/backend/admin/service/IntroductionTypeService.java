package com.backend.admin.service;

import com.backend.admin.entity.IntroductionType;
import com.backend.admin.mapper.IntroductionTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author caojiantao
 */
@Service
public class IntroductionTypeService {

    @Autowired
    private IntroductionTypeMapper introductionTypeMapper;

    @Autowired
    private IntroductionService introductionService;

    public List<IntroductionType> listIntroductionType(Map<String, Object> map) {
        return introductionTypeMapper.listIntroductionType(map);
    }

    public IntroductionType getIntroductionTypeById(int id) {
        return introductionTypeMapper.getIntroductionTypeById(id);
    }

    public boolean saveIntroductionType(IntroductionType introduction) {
        introductionTypeMapper.saveIntroductionType(introduction);
        return introduction.getId() != null && introduction.getId() > 0;
    }

    public boolean updateIntroductionType(IntroductionType introduction) {
        return introductionTypeMapper.updateIntroductionType(introduction) > 0;
    }

    public boolean removeIntroductionTypeById(int id) {
        IntroductionType type = introductionTypeMapper.getIntroductionTypeById(id);
        type.setDeleted(true);
        // 解除关联关系
        introductionService.resetTypeByTypeId(id);
        return updateIntroductionType(type);
    }
}
