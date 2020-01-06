package com.backend.admin.controller;


import com.api.common.entity.Images;
import com.api.common.service.ImagesService;
import com.api.goods.entity.BrandImages;
import com.api.goods.mapper.GoodsMapper;
import com.backend.admin.entity.AjaxResult;
import com.backend.admin.entity.Brand;
import com.backend.admin.mapper.BrandMgrMapper;
import com.backend.admin.service.BrandMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/brandMgr")
public class BrandMgrController extends BaseController{

    @Autowired
    private BrandMgrService brandMgrService;

    @Autowired
    private BrandMgrMapper brandMgrMapper;

    @Autowired
    private ImagesService imagesService;

    @Autowired
    private GoodsMapper goodsMapper;

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        List<Brand> brands = brandMgrService.selectAll();
        if(!CollectionUtils.isEmpty(brands)){
            mv.addObject("brands",brands);
        }
        return mv;
    }

    @RequestMapping("/deleteImg")
    @ResponseBody
    public AjaxResult deleteImg(BrandImages brandImages){
        int delete = goodsMapper.deleteBrandImagesByImagesUrl(brandImages);
        if(delete>0){
            return success();
        }else{
            return error();
        }
    }

    @RequestMapping("/deleteLogo")
    @ResponseBody
    public AjaxResult deleteLogo(){
        return success();
    }

    @RequestMapping("/uploadLogoImg")
    @ResponseBody
    public AjaxResult uploadLogoImg(MultipartFile file,Integer brand_id){
        Brand brand = brandMgrMapper.getBrandById(brand_id);
        if(brand==null){
            return error();
        }
        Images images = imagesService.uploadImages(file, "", 0);
        if(images == null){
            return errorMsg("系统错误");
        }
        if(images.getCode().equals(0)){
            Brand brandUpdate = new Brand();
            brandUpdate.setBrandId(brand.getBrandId());
            brandUpdate.setLogoUrl(images.getUrlPath());
            brandMgrMapper.updateBrand(brandUpdate);
            return successData(images);
        }
        return error();
    }

    @RequestMapping("/uploadImg")
    @ResponseBody
    public AjaxResult uploadImg(MultipartFile file,Integer brand_id){
        Brand brand = brandMgrMapper.getBrandById(brand_id);
        if(brand==null){
            return error();
        }
        Images images = imagesService.uploadImages(file, "", 0);
        if(images == null){
            return errorMsg("系统错误");
        }
        if(images.getCode().equals(0)){
            //插入新的品牌图片
            BrandImages brandImages = new BrandImages();
            brandImages.setAccount_id(0);
            brandImages.setBrand_id(brand_id);
            brandImages.setBrandimages_url(images.getUrlPath());
            goodsMapper.insertBrandImages(brandImages);
            return successData(images);
        }
        return error();
    }

}
