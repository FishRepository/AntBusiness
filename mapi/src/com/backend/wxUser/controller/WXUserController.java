package com.backend.wxUser.controller;

import com.alibaba.fastjson.JSONObject;
import com.api.account.service.AccountService;
import com.api.common.entity.Result;
import com.api.common.utils.EncryptUtil;
import com.backend.common.WxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WXUserController {

    private static Logger logger = LoggerFactory.getLogger(WXUserController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping("/wx/getOpenid")
    @ResponseBody
    public Object getOpenid(@RequestParam(value = "accountId") Integer accountId, @RequestParam(value = "code") String code){
        Result bindwechat = new Result();
        try {
            String result = WxUtils.getConvert(code);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if(jsonObject!=null && jsonObject.containsKey("openid")){
                String openid = jsonObject.getString("openid");
                bindwechat = accountService.bindwechat(accountId, EncryptUtil.base64Encode(openid));
            }else{
                bindwechat.setCode(500);
                bindwechat.setMsg("get openid error");
            }
        } catch (Exception e) {
            logger.error("get openid error"+ e.getMessage());
            bindwechat.setCode(500);
            bindwechat.setMsg("get openid error");
        }
        return bindwechat;
    }

}
