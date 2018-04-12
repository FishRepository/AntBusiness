package com.backend.admin.service;

import com.backend.admin.entity.Introduction;
import com.backend.admin.mapper.IntroductionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IntroductionService {

    @Autowired
    private IntroductionMapper introductionMapper;

    public List<Introduction> listIntroduction(Map<String, Object> map) {
        return introductionMapper.listIntroduction(map);
    }

    public Introduction getIntroductionById(int id) {
        return introductionMapper.getIntroductionById(id);
    }

    public boolean saveIntroduction(Introduction introduction) {
        introductionMapper.saveIntroduction(introduction);
        return introduction.getId() != null && introduction.getId() > 0;
    }

    public boolean updateIntroduction(Introduction introduction) {
        return introductionMapper.updateIntroduction(introduction) > 0;
    }

    public boolean enableIntroductionById(int id) {
        Introduction introduction = introductionMapper.getIntroductionById(id);
        introduction.setStatus(1);
        return updateIntroduction(introduction);
    }

    public boolean disableIntroductionById(int id) {
        Introduction introduction = introductionMapper.getIntroductionById(id);
        introduction.setStatus(0);
        return updateIntroduction(introduction);
    }

    public boolean removeIntroductionById(int id) {
        Introduction introduction = introductionMapper.getIntroductionById(id);
        introduction.setDeleted(true);
        return updateIntroduction(introduction);
    }

    public void resetTypeByTypeId(int typeId) {
        introductionMapper.resetTypeByTypeId(typeId);
    }
}
