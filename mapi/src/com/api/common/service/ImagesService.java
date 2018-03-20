package com.api.common.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.api.common.entity.Images;
import com.api.common.utils.ImageMarkUtil;
import com.api.common.utils.PropertiesUtil;
import com.api.common.utils.StringUtil;

@Transactional
@Service("imaegsService")
public class ImagesService{
	private String basePath = PropertiesUtil.getKeyValue("img.path"); //服务端保存路径
	private String showPath = PropertiesUtil.getKeyValue("img.showpath");//客户端读取路径
	private long imageMaxSize = Long.parseLong(PropertiesUtil.getKeyValue("img.maxsize"));//图片最大比特值
	private SimpleDateFormat sdfShort = new SimpleDateFormat("yyyyMMdd");
	
	public Images uploadImages(MultipartFile file,String imgdir,Integer watermark){
		Images images = new Images();
		try {			
			if (file!=null) {
				String type = file.getOriginalFilename();
				long size= file.getSize();
				if(StringUtil.isValid(type) && size > 0){
					type = type.substring(type.lastIndexOf(".")+1);
					int check_res = check(type, size);
					if(check_res == 0){
						//imgdir 存储图片的文件夹
						if(StringUtil.isEmpty(imgdir)){
							imgdir = "img";
						}
						String file_name = UUID.randomUUID()+"."+type;
						String file_path = File.separator+imgdir+File.separator+sdfShort.format(new Date())+File.separator;
						File imagePath = new File(basePath+file_path);
						//判断路径文件夹 及其子文件夹存不存在 不存在则创建
						if(!imagePath.exists()){
							imagePath.mkdirs();
						}
						File imageFile = new File(imagePath, file_name);
				        FileUtils.copyInputStreamToFile(file.getInputStream(), imageFile);
				        if(watermark != null && watermark == 1){
				        	ImageMarkUtil.markImageByIcon(imageFile, type, null);
				        }
				        images.setCode(0);
				        images.setMsg("上传成功");
				        images.setPath(file_path + file_name);
				        images.setUrlPath(showPath+file_path + file_name);
					}else if(check_res == 1){
						images.setCode(3);
						images.setMsg("您上传的图片大小不能超过"+imageMaxSize/1024/1024+"M");
					}else if(check_res == 2){
						images.setCode(2);
						images.setMsg("您上传的图片格式不被允许,请上传JPG/JPEG/PNG/GIF格式的图片");
					}
				}else{
					images.setCode(1);
					images.setMsg("请选择上传图片");
				}
			}else{
				images.setCode(1);
				images.setMsg("请选择上传图片");
			}
		}catch(Exception e){	
			e.printStackTrace();
			images.setCode(4);
			images.setMsg("上传图片失败");
		}
		return images;
	}
	
	/**
	 * @category 检查图片大小和type
	 * @param type
	 * @param size
	 * @return
	 */
	private int check(String type,long size){
		if(size>imageMaxSize){
			return 1;
		}else if(type!=null && !(type.equalsIgnoreCase("jpg") || type.equalsIgnoreCase("jpeg") || type.equalsIgnoreCase("png") || type.equalsIgnoreCase("gif")) ){
			return 2;
		}else{
			return 0;
		}
	}
}