package com.backend.admin.service;


import com.backend.admin.entity.BackendUser;
import com.backend.admin.entity.Role;
import com.backend.admin.mapper.RoleMapper;
import com.backend.admin.mapper.RoleUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Role)表服务接口
 *
 * @author makejava
 * @since 2019-12-14 12:15:29
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleUserMapper roleUserMapper;

    /**
     *
     */
    public Role queryById(Integer id){
        return null;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public List<Role> queryAllByLimit(int offset, int limit){
        return null;
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    public Role insert(Role role){
        return null;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    public Role update(Role role){
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id){
        return false;
    }

    public List<Role> getListByUserId(BackendUser user){
        return roleMapper.getListByUserId(user);
    }
}