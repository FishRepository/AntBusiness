package com.backend.admin.controller;

import com.backend.admin.entity.Introduction;
import com.backend.admin.entity.IntroductionType;
import com.backend.admin.service.IntroductionService;
import com.backend.admin.service.IntroductionTypeService;
import com.backend.common.ErrorEnums;
import com.backend.common.RequestResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author caojiantao
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IntroductionService introductionService;

    @Autowired
    private IntroductionTypeService introductionTypeService;

    @RequestMapping("/introduction")
    public ModelAndView index() {
        return new ModelAndView("admin/introduction");
    }

    @ResponseBody
    @RequestMapping("/listIntroduction")
    public RequestResultVO<List<Introduction>> listIntroduction() {
        RequestResultVO<List<Introduction>> vo = new RequestResultVO<>();
        vo.success(introductionService.listIntroduction(null));
        return vo;
    }

    @ResponseBody
    @RequestMapping("/getIntroductionById")
    public Introduction getIntroductionById(int id) {
        return introductionService.getIntroductionById(id);
    }

    @ResponseBody
    @RequestMapping("/enableIntroductionById")
    public RequestResultVO<String> enableIntroductionById(int id) {
        RequestResultVO<String> vo = new RequestResultVO<>();
        if (introductionService.enableIntroductionById(id)) {
            vo.success("操作成功");
        } else {
            vo.error(ErrorEnums.DbError);
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping("/disableIntroductionById")
    public RequestResultVO<String> disableIntroductionById(int id) {
        RequestResultVO<String> vo = new RequestResultVO<>();
        if (introductionService.disableIntroductionById(id)) {
            vo.success("操作成功");
        } else {
            vo.error(ErrorEnums.DbError);
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping("/saveIntroduction")
    public RequestResultVO<String> saveIntroduction(Introduction introduction) {
        RequestResultVO<String> vo = new RequestResultVO<>();
        if (introduction.getId() == null) {
            if (introductionService.saveIntroduction(introduction)) {
                vo.success("操作成功");
            } else {
                vo.error(ErrorEnums.DbError);
            }
        } else {
            if (introductionService.updateIntroduction(introduction)) {
                vo.success("操作成功");
            } else {
                vo.error(ErrorEnums.DbError);
            }
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping("/removeIntroductionById")
    public RequestResultVO<String> removeIntroductionById(int id) {
        RequestResultVO<String> vo = new RequestResultVO<>();
        if (introductionService.removeIntroductionById(id)) {
            vo.success("操作成功");
        } else {
            vo.error(ErrorEnums.DbError);
        }
        return vo;
    }

    @RequestMapping(value = {"/introductionType", "/index"})
    public String introductionType() {
        return "admin/introductionType";
    }

    @ResponseBody
    @RequestMapping("/listIntroductionType")
    public RequestResultVO<List<IntroductionType>> listIntroductionType() {
        RequestResultVO<List<IntroductionType>> vo = new RequestResultVO<>();
        vo.success(introductionTypeService.listIntroductionType(null));
        return vo;
    }

    @ResponseBody
    @RequestMapping("/getIntroductionTypeById")
    public IntroductionType getIntroductionTypeById(int id) {
        return introductionTypeService.getIntroductionTypeById(id);
    }

    @ResponseBody
    @RequestMapping("/removeIntroductionTypeById")
    public RequestResultVO<String> removeIntroductionTypeById(int id) {
        RequestResultVO<String> vo = new RequestResultVO<>();
        if (introductionTypeService.removeIntroductionTypeById(id)) {
            vo.success("操作成功");
        } else {
            vo.error(ErrorEnums.DbError);
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping("/saveIntroductionType")
    public RequestResultVO<String> saveIntroductionType(IntroductionType type) {
        RequestResultVO<String> vo = new RequestResultVO<>();
        if (type.getId() == null) {
            if (introductionTypeService.saveIntroductionType(type)) {
                vo.success("操作成功");
            } else {
                vo.error(ErrorEnums.DbError);
            }
        } else {
            if (introductionTypeService.updateIntroductionType(type)) {
                vo.success("操作成功");
            } else {
                vo.error(ErrorEnums.DbError);
            }
        }
        return vo;
    }
}
