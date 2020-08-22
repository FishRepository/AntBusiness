package com.backend.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.api.common.utils.JPushUtil;
import com.backend.admin.entity.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("sendMsg")
public class SendMsgController extends BaseController{

    @RequestMapping
    public ModelAndView index(){
        return new ModelAndView("admin/sendMsg");
    }

    @RequestMapping("send")
    @ResponseBody
    public AjaxResult<Void> sendMsg(String msg){
        if(StrUtil.isEmpty(msg)){
            return error();
        }
        boolean result = JPushUtil.sendBroadcastPush("番茄管家", msg);
        if(result){
            return success();
        }
        return error();
    }
}
