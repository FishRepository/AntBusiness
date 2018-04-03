package com.backend.admin.service;

import com.backend.admin.entity.Guide;
import com.backend.admin.mapper.GuideMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideService {

    @Autowired
    private GuideMapper guideMapper;

    public List<Guide> selectAll(){
        return guideMapper.selectAll();
    }

    public Guide selectById(Integer id){
        if(id==null || id==0){
            return null;
        }
        return guideMapper.selectById(id);
    }

    public int saveGuide(Guide guide){
       if(guide!=null){
           guideMapper.saveGuide(guide);
           return guide.getId();
       } else{
           return -1;
       }
    }

    public int updateGuide(Guide guide){
        if(guide!=null && guide.getId()!=null && guide.getId()!=0){
            return guideMapper.updateGuide(guide);
        }else{
            return -1;
        }
    }
}
