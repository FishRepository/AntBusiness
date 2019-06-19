package com.backend.admin.controller;

import com.api.common.entity.Images;
import com.api.common.service.ImagesService;
import com.backend.admin.entity.AdImg;
import com.backend.admin.entity.Introduction;
import com.backend.admin.entity.IntroductionType;
import com.backend.admin.service.AdImgService;
import com.backend.admin.service.IntroductionService;
import com.backend.admin.service.IntroductionTypeService;
import com.backend.admin.service.UpdateService;
import com.backend.common.PayUtil;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/v2")
public class ApiController extends BaseController{

    @Autowired
    private ImagesService imagesService;

    @Autowired
    private IntroductionService introductionService;

    @Autowired
    private IntroductionTypeService introductionTypeService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private AdImgService adImgService;

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
            IntroductionType type = introductionTypeService.getIntroductionTypeById(entry.getKey());
            if (type == null) {
                // 未知类型不予展示
                continue;
            }
            JSONObject object = new JSONObject();
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

    public static String TimeStamp2Date(String timestampString, String formats){
        Long timestamp = Long.parseLong(timestampString)*1000;
        String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));
        return date;
    }

    @ResponseBody
    @RequestMapping("/getOrderListByMonth")
    public Object getOrderListByMonth(@RequestParam("accountId") Integer accountId,
                                      @RequestParam(value = "month", required = false) String month,
                                      @RequestParam(value = "year", required = false) String year) {
        return updateService.getOrderListByMonth(accountId, month, year);
    }

    public static void main(String[] args) {
        System.out.printf(TimeStamp2Date(String.valueOf(1524994998),"yyyy-MM-dd HH:mm:ss"));
    }

    @ResponseBody
    @RequestMapping("/updateOrderTime")
    public Object updateOrderTime(@RequestParam("order_id") Integer order_id,
                                  @RequestParam("updateTime") Long updateTime) {
        return updateService.updateOrderTime(order_id, new Date(updateTime*1000));
    }

    /**
     * 获取广告图list
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAdList")
    public Object getAdList(){
        List<AdImg> adList = adImgService.getAdList();
        if(CollectionUtils.isNotEmpty(adList)){
            return successData(adList.get(0));
        }
        return error();
    }

    @RequestMapping(params="method=payMoney", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> payMoney(HttpServletRequest request) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();

        String payClass = request.getParameter("payClass");
        String payMoney = request.getParameter("payMoney");
        String openid = request.getParameter("openid");
        String ipString = request.getRemoteAddr();
//		ipString = "119.97.231.230";
        PayUtil payUtil = new PayUtil();

        String orderNumb = payUtil.returnOrderNumb();
        String orderTime = payUtil.returnOrderTime();

        resultMap.put("result", payUtil.returnPackage(payClass, payMoney , orderNumb, orderTime, openid, ipString));
        resultMap.put("orderNumber", orderNumb);
        resultMap.put("payMoney", payMoney);
        resultMap.put("orderType", payClass);
        resultMap.put("orderTime", orderTime);

        return resultMap;
    }

}
