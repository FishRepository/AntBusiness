package com.backend.admin.service;

import com.backend.admin.entity.AdImg;
import com.backend.admin.mapper.AdImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告图片表(AdImg)表服务接口
 *
 */
@Service
public class AdImgService{

   @Autowired
   private AdImgMapper adImgMapper;

   public List<AdImg> getAdList(){
      return adImgMapper.getAdImgs();
   }

   public boolean save(AdImg adImg){
      Integer insert;
      if(adImg.getId() != null){
         insert = adImgMapper.updateById(adImg);
      }else{
         insert = adImgMapper.saveAdImg(adImg);
      }
      return insert > 0;
   }

   public boolean delete(Integer id){
      AdImg adImg = adImgMapper.getById(id);
      if(adImg==null){
         return false;
      }
      adImg.setDel(1);
      Integer updateById = adImgMapper.updateById(adImg);
      return updateById > 0;
   }

   public AdImg getAdImgById(Integer id){
      AdImg adImg = adImgMapper.getById(id);
      return adImg;
   }
}