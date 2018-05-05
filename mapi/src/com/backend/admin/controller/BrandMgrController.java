package com.backend.admin.controller;


import com.backend.admin.entity.Brand;
import com.backend.admin.service.BrandMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/brandMgr")
public class BrandMgrController {

    @Autowired
    private BrandMgrService brandMgrService;

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        List<Brand> brands = brandMgrService.selectAll();
        if(!CollectionUtils.isEmpty(brands)){
            mv.addObject("brands",brands);
        }
        return mv;
    }
}
