package com.backend.admin.mapper;

import com.backend.admin.entity.PayOrder;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户支付订单信息(PayOrder)表数据库访问层
 *
 */
public interface PayOrderMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param orderNo 主键
     * @return 实例对象
     */
    PayOrder queryById(String orderNo);

    /**
     * 通过account_id 查询用户订单记录
     * @param account_id
     * @return
     */
    List<PayOrder> queryByAccountId(Integer account_id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PayOrder> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param payOrder 实例对象
     * @return 对象列表
     */
    List<PayOrder> queryAll(PayOrder payOrder);

    /**
     * 新增数据
     *
     * @param payOrder 实例对象
     * @return 影响行数
     */
    int insert(PayOrder payOrder);

    /**
     * 修改数据
     *
     * @param payOrder 实例对象
     * @return 影响行数
     */
    int update(PayOrder payOrder);

    /**
     * 通过主键删除数据
     *
     * @param orderNo 主键
     * @return 影响行数
     */
    int deleteById(String orderNo);

}