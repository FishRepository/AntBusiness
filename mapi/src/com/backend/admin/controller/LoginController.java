package com.backend.admin.controller;

import com.backend.admin.entity.AjaxResult;
import com.backend.admin.entity.BackendUser;
import com.backend.admin.entity.Role;
import com.backend.admin.service.BackendUserService;
import com.backend.admin.service.RoleService;
import com.backend.common.Constant;
import com.backend.common.RequestResultVO;
import com.backend.common.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author caojiantao
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

    @Autowired
    private RoleService roleService;

    @Autowired
    private BackendUserService backendUserService;

    @RequestMapping("")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        if (SessionUtils.isLogin(request)) {
            model.setViewName("admin/introduction");
        } else {
            model.setViewName("admin/page-login");
        }
        return model;
    }

    @RequestMapping("/validate")
    @ResponseBody
    public RequestResultVO validate(@RequestParam(value = "username") String username,
                                    @RequestParam(value = "password") String password,
                                    HttpSession session) {
        RequestResultVO vo = new RequestResultVO();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            vo.setSuccess(false);
            return vo;
        }
        BackendUser user = new BackendUser();
        user.setUserName(username);
        user.setPassword(password);
        if (!backendUserService.validate(user)) {
            vo.setSuccess(false);
            return vo;
        }
        session.setAttribute("username", username);
        //权限菜单
        BackendUser backendUser = backendUserService.getUserByName(username);
        List<Role> roleList = roleService.getListByUserId(backendUser);
        session.setAttribute(Constant.SESSION_ATTR_ROLE_LIST, roleList);
        vo.setSuccess(true);
        return vo;
    }

    @RequestMapping("/changePwd")
    @ResponseBody
    public AjaxResult changePwd(@RequestParam(value = "password") String password,
                                @RequestParam(value = "newPassword") String newPassword,
                                HttpSession session){
        if(StringUtils.isBlank(password) || StringUtils.isBlank(newPassword)){
            return errorMsg("请输入密码");
        }
        String username = session.getAttribute(Constant.SESSION_ATTR_LOGIN_NAME).toString();
        BackendUser user = backendUserService.getUserByName(username);
        if(user==null || !StringUtils.equals(password, user.getPassword())){
            return errorMsg("旧密码错误");
        }
        user.setPassword(newPassword);
        Boolean result = backendUserService.changePwd(user);
        if(result){
            return success();
        }
        return errorMsg("修改失败");
    }

    @RequestMapping("/quit")
    public ModelAndView quit(HttpSession session) {
        ModelAndView model = new ModelAndView();
        session.removeAttribute(Constant.SESSION_ATTR_LOGIN_NAME);
        session.removeAttribute(Constant.SESSION_ATTR_ROLE_LIST);
        model.setViewName("admin/page-login");
        return model;
    }
}
