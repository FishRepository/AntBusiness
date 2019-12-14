package com.backend.admin.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 后台用户(BackendUser)实体类
 *
 * @author makejava
 * @since 2019-12-14 11:25:58
 */
public class BackendUser implements Serializable {
    private static final long serialVersionUID = -49700452286862657L;
    //主键
    private Integer id;
    //登录名
    private String userName;
    //登录密码
    private String password;
    
    private String userPhone;

    private Integer state;

    private List<Role> roleList;

    private List<RoleUser> roleUserList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<RoleUser> getRoleUserList() {
        return roleUserList;
    }

    public void setRoleUserList(List<RoleUser> roleUserList) {
        this.roleUserList = roleUserList;
    }
}