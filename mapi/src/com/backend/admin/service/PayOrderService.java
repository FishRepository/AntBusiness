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
     * 查询订单列表
     * @param payOrder
     * @return
     */
    public List<PayOrder> queryAll(PayOrder payOrder){
        if(payOrder == null){
            payOrder = new PayOrder();
        }
        return payOrderMapper.queryAll(payOrder);
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