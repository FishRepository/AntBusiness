package com.backend.admin.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.backend.admin.entity.AjaxResult;
import com.backend.admin.entity.BackendUser;
import com.backend.admin.entity.Role;
import com.backend.admin.service.BackendUserService;
import com.backend.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * (Role)表控制层
 *
 * @author makejava
 * @since 2019-12-14 12:15:29
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController{

    /**
     * 服务对象
     */
    @Autowired
    private RoleService roleService;

    @Autowired
    private BackendUserService backendUserService;

    @RequestMapping
    public ModelAndView index(){
        return new ModelAndView("admin/role");
    }

    @RequestMapping("getUserList")
    @ResponseBody
    public AjaxResult getUserList(){
        List<BackendUser> userList = backendUserService.getAllUser();
        if(CollectionUtil.isNotEmpty(userList)){
            return successData(userList);
        }
        return error();
    }

    @RequestMapping("getUserById")
    @ResponseBody
    public AjaxResult getUserById(Integer id){
        BackendUser user = backendUserService.getUserById(id);
        if(user==null){
           return error();
        }
        return successData(user);
    }

    @RequestMapping("deleteUserById")
    @ResponseBody
    public AjaxResult deleteUserById(Integer id){
        boolean result = backendUserService.deleteUserById(id);
        if(result){
            return success();
        }
        return error();
    }



    @RequestMapping("saveUser")
    @ResponseBody
    public AjaxResult saveUser(BackendUser user){
        boolean result = backendUserService.saveUser(user);
        if(result){
            return success();
        }
        return error();
    }
}