package com.backend.admin.controller;

import com.api.common.entity.Images;
import com.api.common.service.ImagesService;
import com.backend.admin.entity.AdImg;
import com.backend.admin.entity.AjaxResult;
import com.backend.admin.service.AdImgService;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 广告图片表(AdImg)表控制层
 *
 * @author makejava
 * @since 2019-06-06 10:43:16
 */
@Controller
@RequestMapping("adImg")
public class AdImgController extends BaseController{
    /**
     * 服务对象
     */
    @Resource
    private AdImgService adImgService;

    @Resource
    private ImagesService imagesService;

    @RequestMapping
    public ModelAndView index(){
        return new ModelAndView("admin/adimg");
    }

    /**
     * 返回广告列表
     */
    @RequestMapping("list")
    @ResponseBody
    public AjaxResult list(){
        List<AdImg> adList = adImgService.getAdList();
        if(CollectionUtils.isEmpty(adList)){
            return error();
        }
        return successData(adList);
    }

    @RequestMapping("uploadImg")
    @ResponseBody
    public AjaxResult uploadImg(MultipartFile file){
        Images images = imagesService.uploadImages(file, "", 0);
        if(images == null){
            return errorMsg("系统错误");
        }
        if(images.getCode().equals(0)){
            return successData(images);
        }
        return errorMsg(StringUtils.isEmpty(images.getMsg()) ?"系统错误":images.getMsg());
    }

    /**
     * 保存广告图
     * @param adImg
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(AdImg adImg){
        if(adImg == null){
            return error();
        }
        boolean save = adImgService.save(adImg);
        if(save){
            return success();
        }
        return error();
    }

    /**
     * 逻辑删除广告图
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public AjaxResult delete(Integer id){
        boolean del = adImgService.delete(id);
        if(del){
            return success();
        }
        return error();
    }

    /**
     * 查询一条记录
     * @param id
     * @return
     */
    @RequestMapping("getAdImgById")
    @ResponseBody
    public AjaxResult getAdImgById(Integer id){
        AdImg adImg = adImgService.getAdImgById(id);
        if(adImg == null){
            return error();
        }
        return successData(adImg);
    }

}