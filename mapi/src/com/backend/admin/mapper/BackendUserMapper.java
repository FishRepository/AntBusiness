package com.backend.admin.mapper;

import com.backend.admin.entity.BackendUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 后台用户(BackendUser)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-14 11:25:58
 */
public interface BackendUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BackendUser queryById(Integer id);

    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    BackendUser queryByUserName(String name);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BackendUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param backendUser 实例对象
     * @return 对象列表
     */
    List<BackendUser> queryAll(BackendUser backendUser);

    /**
     * 新增数据
     *
     * @param backendUser 实例对象
     * @return 影响行数
     */
    int insert(BackendUser backendUser);

    /**
     * 修改数据
     *
     * @param backendUser 实例对象
     * @return 影响行数
     */
    int update(BackendUser backendUser);

    int checkUser(@Param(value = "userName") String username);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 级联查询用户信息  以及roleList
     * @param id
     * @return
     */
    BackendUser getUserById(Integer id);

}