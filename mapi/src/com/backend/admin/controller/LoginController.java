package com.backend.admin.controller;

import com.backend.common.RequestResultVO;
import com.backend.common.SessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public RequestResultVO validate(){
        RequestResultVO vo = new RequestResultVO();
        vo.setSuccess(true);
        return vo;
    }
}
