package com.backend.admin.mapper;

import com.backend.admin.entity.VipPay;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * vip付费信息(VipPay)表数据库访问层
 *
 * @author makejava
 * @since 2019-07-23 10:46:35
 */
public interface VipPayMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VipPay queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<VipPay> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param vipPay 实例对象
     * @return 对象列表
     */
    List<VipPay> queryAll(VipPay vipPay);

    /**
     * 新增数据
     *
     * @param vipPay 实例对象
     * @return 影响行数
     */
    int insert(VipPay vipPay);

    /**
     * 修改数据
     *
     * @param vipPay 实例对象
     * @return 影响行数
     */
    int update(VipPay vipPay);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}