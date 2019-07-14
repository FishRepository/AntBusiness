package com.backend.admin.service;

import com.backend.admin.entity.PayOrder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户支付订单信息(PayOrder)表服务接口
 *
 */
@Service
public class PayOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderNo 主键
     * @return 实例对象
     */
    public PayOrder queryById(String orderNo){
        return null;
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
    public PayOrder insert(PayOrder payOrder){
        return null;
    }

    /**
     * 修改数据
     *
     * @param payOrder 实例对象
     * @return 实例对象
     */
    public PayOrder update(PayOrder payOrder){
        return null;
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