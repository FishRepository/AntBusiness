package com.backend.admin.controller;

import com.backend.admin.entity.Guide;
import com.backend.admin.service.GuideService;
import com.backend.common.RequestResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private GuideService guideService;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("admin/index");
        return model;
    }

    @RequestMapping("/listGuide")
    @ResponseBody
    public RequestResultVO listGuide(){
        RequestResultVO vo = new RequestResultVO();
        List<Guide> guideList = guideService.selectAll();
        if(guideList!=null && !guideList.isEmpty()){
            vo.setData(guideList);
            vo.setSuccess(true);
        }else{
            vo.setSuccess(false);
        }
        return  vo;
    }

    @RequestMapping("/saveGuide")
    @ResponseBody
    public RequestResultVO saveGuide(@RequestParam(value = "guide")Guide guide){
        RequestResultVO vo = new RequestResultVO();

        return vo;
    }

}
