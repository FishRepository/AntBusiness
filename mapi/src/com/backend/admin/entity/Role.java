package com.backend.admin.entity;

import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2019-12-14 12:15:27
 */
public class Role implements Serializable {
    private static final long serialVersionUID = -78274769939796217L;
    //主键
    private Integer id;
    //菜单url
    private String menuUrl;
    //菜单名称
    private String menuName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

}