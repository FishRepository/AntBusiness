package com.backend.admin.controller;

import com.api.common.entity.Images;
import com.api.common.service.ImagesService;
import com.backend.admin.entity.Introduction;
import com.backend.admin.entity.IntroductionType;
import com.backend.admin.service.IntroductionService;
import com.backend.admin.service.IntroductionTypeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/v2")
public class ApiController {

    @Autowired
    private ImagesService imagesService;

    @Autowired
    private IntroductionService introductionService;

    @Autowired
    private IntroductionTypeService introductionTypeService;

    @ResponseBody
    @RequestMapping("/upload")
    public Object upload(MultipartRequest request, String imgdir) {
        Images images = imagesService.uploadImages(request.getFile(request.getFileNames().next()), imgdir, 1);
        JSONObject object = new JSONObject();
        object.put("errno", images.getCode());
        String url = images.getUrlPath();
        object.put("data", Collections.singletonList((StringUtils.isBlank(url) ? null : url.replace(File.separator, "/"))));
        object.put("msg", images.getMsg());
        return object;
    }

    @ResponseBody
    @RequestMapping("/listIntroduction")
    public Object listIntroduction() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("status", 1);
        List<Introduction> introductions = introductionService.listIntroduction(map);
        MultiValueMap<Integer, Introduction> multiValueMap = new LinkedMultiValueMap<>();
        for (Introduction introduction : introductions) {
            multiValueMap.add(introduction.getType(), introduction);
        }
        JSONArray result = new JSONArray();
        Set<Map.Entry<Integer, List<Introduction>>> entries = multiValueMap.entrySet();
        for (Map.Entry<Integer, List<Introduction>> entry : entries) {
            JSONObject object = new JSONObject();
            IntroductionType type = introductionTypeService.getIntroductionTypeById(entry.getKey());
            object.put("title", type.getName());
            object.put("items", entry.getValue());
            result.add(object);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getIntroductionById")
    public Introduction getIntroductionById(int id) {
        return introductionService.getIntroductionById(id);
    }
}
