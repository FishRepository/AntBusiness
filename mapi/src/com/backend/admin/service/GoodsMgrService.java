package com.backend.admin.service;

import com.backend.admin.mapper.GoodsMgrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsMgrService {

    @Autowired
    private GoodsMgrMapper goodsMgrMapper;
}
