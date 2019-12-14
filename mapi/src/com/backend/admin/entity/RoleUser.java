package com.backend.admin.entity;

import java.io.Serializable;

/**
 * (RoleUser)实体类
 *
 * @author makejava
 * @since 2019-12-14 12:15:30
 */
public class RoleUser implements Serializable {
    private static final long serialVersionUID = 853334211987150019L;
    //主键
    private Integer id;
    //权限id
    private Integer roleId;
    //用户id
    private Integer userId;
    //0无效1有效
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}