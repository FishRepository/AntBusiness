package com.api.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.api.common.service.ImagesService;
import com.api.common.utils.StringUtil;

@Controller
@RequestMapping("/images")
public class ImagesController {
	@Autowired
	private ImagesService imagesService;
	
	@RequestMapping(value = "/uploadimages")
    @ResponseBody
	public Object uploadImages(MultipartFile file,String imgdir){
		if(StringUtil.isEmpty(imgdir)){
			imgdir = "icon";
		}
		return imagesService.uploadImages(file,imgdir,1);
	}
}