package com.backend.admin.service;

import com.backend.admin.entity.PayOrder;
import com.backend.admin.mapper.PayOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户支付订单信息(PayOrder)表服务接口
 *
 */
@Service
public class PayOrderService {

    @Autowired
    private PayOrderMapper payOrderMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param orderNo 主键
     * @return 实例对象
     */
    public PayOrder queryById(String orderNo){
        return payOrderMapper.queryById(orderNo);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public List<PayOrder> queryAllByLimit(int offset, int limit){
        return null;
    }

    /**
     * 新增数据
     *
     * @param payOrder 实例对象
     * @return 实例对象
     */
    public int insert(PayOrder payOrder){
        return payOrderMapper.insert(payOrder);
    }

    /**
     * 修改数据
     *
     * @param payOrder 实例对象
     * @return 实例对象
     */
    public int update(PayOrder payOrder){
        return payOrderMapper.update(payOrder);
    }

    /**
     * 通过主键删除数据
     *
     * @param orderNo 主键
     * @return 是否成功
     */
    public boolean deleteById(String orderNo){
        return false;
    }

}