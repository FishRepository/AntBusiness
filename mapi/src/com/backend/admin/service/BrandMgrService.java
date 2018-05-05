package com.backend.admin.service;

import com.backend.admin.entity.Brand;
import com.backend.admin.mapper.BrandMgrMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandMgrService {

    @Autowired
    private BrandMgrMapper brandMgrMapper;

    public List<Brand> selectAll(){
        return brandMgrMapper.selectAll();
    }
}
