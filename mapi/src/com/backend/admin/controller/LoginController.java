package com.backend.admin.controller;

import com.backend.common.RequestResultVO;
import com.backend.common.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("")
    public ModelAndView login(HttpServletRequest request , HttpSession session){

        ModelAndView model = new ModelAndView();
        if(SessionUtils.isLogin(request)){
            model.setViewName("admin/index");
        }else {
            model.setViewName("admin/page-login");
        }
        return model;
    }

    @RequestMapping("/validate")
    @ResponseBody
    public RequestResultVO validate(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){
        RequestResultVO vo = new RequestResultVO();
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            vo.setSuccess(false);
            return vo;
        }
        if(!username.equals("zhangsan") || !password.equals("123456")){
            vo.setSuccess(false);
            return vo;
        }
        vo.setSuccess(true);
        return vo;
    }
}
