package com.backend.admin.mapper;

import com.backend.admin.entity.ToastSwith;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ToastSwith)表数据库访问层
 *
 * @author makejava
 * @since 2019-07-23 10:58:43
 */
public interface ToastSwithMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param  id
     * @return 实例对象
     */
    ToastSwith queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ToastSwith> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param toastSwith 实例对象
     * @return 对象列表
     */
    List<ToastSwith> queryAll(ToastSwith toastSwith);

    /**
     * 新增数据
     *
     * @param toastSwith 实例对象
     * @return 影响行数
     */
    int insert(ToastSwith toastSwith);

    /**
     * 修改数据
     *
     * @param toastSwith 实例对象
     * @return 影响行数
     */
    int update(ToastSwith toastSwith);

    /**
     * 通过主键删除数据
     *
     * @param  id
     * @return 影响行数
     */
    int deleteById(Integer id);

}