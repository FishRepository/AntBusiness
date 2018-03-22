package com.backend.common;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtils {

    public static boolean isLogin(HttpServletRequest httpServletRequest){
        String username="";
        if(httpServletRequest.getSession().getAttribute(Constant.SESSION_ATTR_LOGIN_NAME)!=null){
            username = httpServletRequest.getSession().getAttribute(Constant.SESSION_ATTR_LOGIN_NAME).toString();
            if(StringUtils.isNotBlank(username)){
                return true;
            }
        }
        if(httpServletRequest.getAttribute(Constant.SESSION_ATTR_LOGIN_NAME)!=null){
            username = httpServletRequest.getAttribute(Constant.SESSION_ATTR_LOGIN_NAME).toString();
        }
        return StringUtils.isNotBlank(username);
    }
}
