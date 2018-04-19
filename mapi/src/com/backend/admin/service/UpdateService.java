package com.backend.admin.service;

import com.backend.admin.mapper.UpdateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UpdateService {

    @Autowired
    private UpdateMapper updateMapper;

    public boolean updateOrderTime(int orderId, Date createTime) {
        return updateMapper.updateOrderTime(orderId, createTime) > 0;
    }
}
