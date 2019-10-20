package com.backend.admin.controller;

import com.api.common.entity.Images;
import com.api.common.service.ImagesService;
import com.api.order.entity.Order;
import com.backend.admin.entity.*;
import com.backend.admin.service.*;
import com.backend.common.AlipayConfig;
import com.backend.common.IpUtil;
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
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    private TagService tagService;

    @Autowired
    private PayService payService;

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private AccountV2Service accountV2Service;

    @Autowired
    private BrandMgrService brandMgrService;

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

    private static String TimeStamp2Date(String timestampString, String formats){
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
        System.out.print(TimeStamp2Date(String.valueOf(1524994998), "yyyy-MM-dd HH:mm:ss"));
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

    /**
     * 获取微信预支付订单号或预支付ID
     * @param payRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value="getPayOrderId", method = RequestMethod.POST)
    @ResponseBody
    public Object getPayOrderId(PayRequest payRequest, HttpServletRequest request){
        String remoteIp = IpUtil.getRemoteIp(request);
        if(StringUtils.isBlank(remoteIp)){
            return errorMsg("can not get client ip");
        }
        payRequest.setIp(remoteIp);
        PayResponse payResponse = payService.doPay(payRequest);
        if(null == payResponse){
            return error();
        }
        if(!payResponse.isResult()){
            return errorData(payResponse);
        }
        return successData(payResponse);
    }

    /**
     * 保存自定义标签
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveTag")
    public Object saveTag(Tag tag){
        if(tag==null){
            return error();
        }
        tag.setTag_type(2);
        tag = tagService.saveTag(tag);
        if(tag!=null){
            return successData(tag);
        }
        return error();
    }

    /**
     * 查询用户标签列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserTagList")
    public Object getUserTagList(Tag tag){
        if(tag==null||tag.getAccount_id()==null||tag.getOrder_type()==null){
            return error();
        }
        Tag defaultTag = new Tag();
        defaultTag.setOrder_type(tag.getOrder_type());
        defaultTag.setTag_type(1);
        List<Tag> defaultTags = tagService.queryAll(defaultTag);
        if(CollectionUtils.isEmpty(defaultTags)){
            return error();
        }
        List<Tag> tags = tagService.queryAll(tag);
        if(CollectionUtils.isNotEmpty(tags)){
            defaultTags.addAll(tags);
        }
        return successData(defaultTags);
    }


    /**
     * 查询用户标签列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveOrderTag")
    public Object saveOrderTag(Order order){
        if(order==null||order.getOrder_id()==null||order.getAccount_id()==null){
            return error();
        }
        boolean result = tagService.saveOrderTag(order);
        if(result){
           return success();
        }
        return error();
    }

    /**
     * 支付宝回调请求notify
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/alipayCallback")
    public String alipayCallback(HttpServletRequest request){
        boolean callback = aliPayService.callback(request);
        if(callback){
            return AlipayConfig.SUCCESS;
        }
        return AlipayConfig.FAILURE;
    }

    /**
     * 微信回调请求notify
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/wxpayCallback")
    public String wxpayCallback(HttpServletRequest request){
        return payService.wxpayCallback(request);
    }

    /**
     * 苹果内购交易验证
     * @param iosPayVerifyRequest
     * @return
     */
    @ResponseBody
    @RequestMapping("/iosPayVerify")
    public Object iosPayVerify(IOSPayVerifyRequest iosPayVerifyRequest){
        boolean result = payService.iosPayVerify(iosPayVerifyRequest);
        if(result){
            return success();
        }
        return error();
    }

    /**
     * 查询用户VIP剩余时间
     * @param account_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getVIPTime")
    public Object getVIPTime(Integer account_id){
        Integer remain_time = accountV2Service.getVipTime(account_id);
        if(remain_time==null){
            return error();
        }
        return successData(remain_time);
    }

    /**
     * 查询vip付费列表
     * @param os_type
     * @return
     */
    @ResponseBody
    @RequestMapping("/getVipPayList")
    public Object getVipPayList(Integer os_type){
        List<VipPay> vipPayList = accountV2Service.getVipPayList();
        if(CollectionUtils.isEmpty(vipPayList)){
            return error();
        }
        return successData(vipPayList);
    }

    /**
     * 查询系统开关
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getSwitch")
    public Object getSwitch(Integer id){
        Integer is_show = accountV2Service.getSwitchById(id);
        if(is_show==null){
            return error();
        }
        return successData(is_show);
    }

    /**
     * 查询用户订单记录
     * @param account_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getConsumList")
    public Object getConsumList(Integer account_id){
        List<PayOrder> payOrders = accountV2Service.queryByAccountId(account_id);
        if(CollectionUtils.isEmpty(payOrders)){
            return error();
        }
        return successData(payOrders);
    }

    /**
     * 查询订单状态接口
     * @param order_no
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOrderState")
    public Object getOrderState(String order_no){
        Integer state = accountV2Service.getOrderState(order_no);
        if(state==null){
            return error();
        }
        return successData(state);
    }

    /**
     * 品牌图片上传
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadBrandIcon")
    public Object uploadBrandIcon(MultipartFile file, Integer brand_id, Integer account_id){
        return brandMgrService.uploadBrandIcon(file, brand_id, account_id);
//        return imagesService.uploadImages(file,"brand",null);
    }

}
