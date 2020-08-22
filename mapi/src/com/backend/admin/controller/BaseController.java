package com.backend.admin.controller;

import com.backend.admin.entity.AjaxResult;
import com.backend.common.Constant;

public abstract class BaseController {


    public <T> AjaxResult<T> successData(T data){
        AjaxResult<T> result=new AjaxResult<>();
        result.setCode(Constant.SUCCESS_CODE);
        result.setData(data);
        return result;
    }

    public <T> AjaxResult<T> errorData(T data){
        AjaxResult<T> result=new AjaxResult<>();
        result.setCode(Constant.ERROR_CODE);
        result.setData(data);
        return result;
    }

    public AjaxResult<Void> success(){
        AjaxResult<Void> result=new AjaxResult<>();
        result.setCode(Constant.SUCCESS_CODE);
        return result;
    }

    public AjaxResult<Void> errorMsg(String msg){
        AjaxResult<Void> result=new AjaxResult<>();
        result.setCode(Constant.ERROR_CODE);
        result.setMsg(msg);
        return result;
    }

    public AjaxResult<Void> error(){
        AjaxResult<Void> result=new AjaxResult<>();
        result.setCode(Constant.ERROR_CODE);
        result.setMsg(Constant.SYSTEM_ERROR);
        return result;
    }
}
