package com.backend.admin.controller;

import com.api.common.utils.StringUtil;
import com.backend.admin.entity.Guide;
import com.backend.admin.entity.Introduction;
import com.backend.admin.service.GuideService;
import com.backend.admin.service.IntroductionService;
import com.backend.admin.service.UploadService;
import com.backend.common.ErrorEnums;
import com.backend.common.RequestResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private GuideService guideService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private IntroductionService introductionService;

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("admin/indexNew");
    }

    @RequestMapping("/listGuide")
    @ResponseBody
    public RequestResultVO listGuide() {
        RequestResultVO<List<Guide>> vo = new RequestResultVO<>();
        List<Guide> guideList = guideService.selectAll();
        if (guideList != null && !guideList.isEmpty()) {
            vo.setData(guideList);
            vo.setSuccess(true);
        } else {
            vo.setSuccess(false);
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping("/getGuideById")
    public Guide getGuideById(int id) {
        return guideService.selectById(id);
    }

    @RequestMapping("/saveGuide")
    @ResponseBody
    public RequestResultVO saveGuide(Guide guide, MultipartHttpServletRequest request) throws IOException {
        RequestResultVO<String> vo = new RequestResultVO<>();
        // 遍历图片
        Map<String, MultipartFile> fileMap = request.getFileMap();
        Collection<MultipartFile> files = fileMap.values();
        for (MultipartFile file : files) {
            // 上传图片到服务器，得到地址
            String fileUrl = uploadService.uploadFile(file);
            if (StringUtil.isEmpty(fileUrl)) {
                vo.error(ErrorEnums.RuntimeError);
                return vo;
            }
            switch (file.getName()) {
                case "img_1":
                    guide.setOpIntroduceImg1(fileUrl);
                    break;
                case "img_2":
                    guide.setOpIntroduceImg2(fileUrl);
                    break;
                case "img_3":
                    guide.setOpIntroduceImg3(fileUrl);
                    break;
                case "img_4":
                    guide.setOpIntroduceImg4(fileUrl);
                    break;
                case "img_5":
                    guide.setOpIntroduceImg5(fileUrl);
                    break;
                default:
                    break;
            }
        }
        if (guide.getId() == null || guide.getId() == 0) {
            // 新增
            guideService.saveGuide(guide);
        } else {
            // 修改
            guideService.updateGuide(guide);
        }
        vo.success("成功");
        return vo;
    }

    @RequestMapping("changeStatus")
    @ResponseBody
    public RequestResultVO enableGuide(int id, int status) {
        Guide guide = guideService.selectById(id);
        guide.setStatus(status);
        guideService.updateGuide(guide);
        RequestResultVO<String> vo = new RequestResultVO<>();
        vo.success("成功");
        return vo;
    }

    @RequestMapping("removeGuide")
    @ResponseBody
    public RequestResultVO removeGuide(int id) {
        Guide guide = guideService.selectById(id);
        guide.setIsDelete(1);
        guideService.updateGuide(guide);
        RequestResultVO<String> vo = new RequestResultVO<>();
        vo.success("成功");
        return vo;
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
}
