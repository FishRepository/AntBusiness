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

/**
 * @author caojiantao
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private final static String USERNAME = "zhangsan";

    private final static String PASSWORD = "123456";

    @RequestMapping("")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        if (SessionUtils.isLogin(request)) {
            model.setViewName("admin/index");
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
        if (!username.equals(USERNAME) || !password.equals(PASSWORD)) {
            vo.setSuccess(false);
            return vo;
        }
        session.setAttribute("username", username);
        vo.setSuccess(true);
        return vo;
    }
}
