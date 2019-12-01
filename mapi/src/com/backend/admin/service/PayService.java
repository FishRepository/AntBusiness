package com.backend.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.api.account.entity.Account;
import com.api.account.mapper.AccountMapper;
import com.backend.admin.entity.IOSPayVerifyRequest;
import com.backend.admin.entity.PayOrder;
import com.backend.admin.entity.PayRequest;
import com.backend.admin.entity.PayResponse;
import com.backend.common.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PayService {

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private AccountV2Service accountV2Service;

    @Autowired
    private AccountMapper accountMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(PayService.class);

    private ExecutorService executorService = Executors.newFixedThreadPool(20);

    /**
     * 处理微信支付宝支付请求
     * @param payRequest
     * @return
     */
    public PayResponse doPay(PayRequest payRequest){
        if(null==payRequest || null==payRequest.getPay_type() || null==payRequest.getAccount_id() || null==payRequest.getOrder_money() || null==payRequest.getOrder_type()){
            return null;
        }
        try {
            String orderNo = UUID.randomUUID().toString().replaceAll("-", "");
            payRequest.setOrderNo(orderNo);
            switch (payRequest.getPay_type()){
                case 1:
                    PayResponse aliPayResponse = AliPayUtil.doPay(payRequest);
                    if(aliPayResponse!=null && aliPayResponse.isResult()){
                        saveOrder(payRequest);
                        aliPayResponse.setOrder_number(orderNo);
                    }
                    return aliPayResponse;
                case 2:
                    PayResponse wxPayResponse = WxPayUtil.doPay(payRequest);
                    if(wxPayResponse!=null && wxPayResponse.isResult()){
                        saveOrder(payRequest);
                        wxPayResponse.setOrder_number(orderNo);
                    }
                    return wxPayResponse;
                default:
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("PayService doPay error, "+e.getMessage());
        }
        return null;
    }

    private void saveOrder(PayRequest payRequest) {
        PayOrder payOrder = new PayOrder();
        payOrder.setOrder_no(payRequest.getOrderNo());
        payOrder.setOrder_amount(payRequest.getOrder_money());
        payOrder.setOrder_type(payRequest.getOrder_type());
        payOrder.setState(0);
        payOrder.setAccount_id(payRequest.getAccount_id());
        Account accountInfo = accountMapper.getAccountInfo(payRequest.getAccount_id());
        if(accountInfo!=null){
            payOrder.setUser_phone(accountInfo.getAccount_userphone());
        }
        payOrder.setPay_type(payOrder.getPay_type());
        payOrderService.insert(payOrder);
    }

    /**
     * iosPay 校验
     * @param iosPayVerifyRequest
     * @return
     */
    public boolean iosPayVerify(IOSPayVerifyRequest iosPayVerifyRequest){
        if(StringUtils.isBlank(iosPayVerifyRequest.getTransaction_id())||
                StringUtils.isBlank(iosPayVerifyRequest.getPayload())||
                null==iosPayVerifyRequest.getAccount_id()||
                null==iosPayVerifyRequest.getOrder_money()||
                null==iosPayVerifyRequest.getOrder_type()){
            return false;
        }
        LOGGER.info("苹果内购校验开始，交易ID：" + iosPayVerifyRequest.getTransaction_id() + " base64校验体：" + iosPayVerifyRequest.getPayload());
//        Shipper shipper = getLoginShipper();
//        if (shipper == null) {
//            return failure("未登录");
//        }
        //线上环境验证
        String verifyResult = IosVerifyUtil.buyAppVerify(iosPayVerifyRequest.getPayload(), 1);
        if (verifyResult == null) {
            return false;
        }
        LOGGER.info("线上，苹果平台返回JSON:" + verifyResult);
        JSONObject appleReturn = JSONObject.parseObject(verifyResult);
        String states = appleReturn.getString("status");
        //无数据则沙箱环境验证
        if ("21007".equals(states)) {
            verifyResult = IosVerifyUtil.buyAppVerify(iosPayVerifyRequest.getPayload(), 0);
            LOGGER.info("沙盒环境，苹果平台返回JSON:" + verifyResult);
            appleReturn = JSONObject.parseObject(verifyResult);
            states = appleReturn.getString("status");
        }
        LOGGER.info("苹果平台返回值：appleReturn" + appleReturn);
        // 前端所提供的收据是有效的    验证成功
        //"支付失败，错误码：" + states
        if (!states.equals("0")){
            return false;
        }
        String receipt = appleReturn.getString("receipt");
        JSONObject returnJson = JSONObject.parseObject(receipt);
        String inApp = returnJson.getString("in_app");
        List<HashMap> inApps = JSONObject.parseArray(inApp, HashMap.class);
        if (CollectionUtils.isEmpty(inApps)) {
            return false;
        }
        ArrayList<String> transactionIds = new ArrayList<>();
        for (HashMap app : inApps) {
            transactionIds.add((String) app.get("transaction_id"));
        }
        //交易列表包含当前交易，则认为交易成功
        if (transactionIds.contains(iosPayVerifyRequest.getTransaction_id())) {
            //"充值成功"
            //异步处理本地订单状态
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    LOGGER.info("交易成功，新增并处理订单：{}",iosPayVerifyRequest.getTransaction_id());
                    PayOrder payOrder = new PayOrder();
                    String orderNo = UUID.randomUUID().toString().replaceAll("-", "");
                    payOrder.setAccount_id(iosPayVerifyRequest.getAccount_id());
                    payOrder.setOrder_amount(iosPayVerifyRequest.getOrder_money());
                    payOrder.setOrder_no(orderNo);
                    payOrder.setState(0);
                    payOrder.setPay_type(3);
                    payOrder.setOrder_type(iosPayVerifyRequest.getOrder_type());
                    Account accountInfo = accountMapper.getAccountInfo(iosPayVerifyRequest.getAccount_id());
                    if(accountInfo!=null){
                        payOrder.setUser_phone(accountInfo.getAccount_userphone());
                    }
                    int insert = payOrderService.insert(payOrder);
                    if(insert>0){
                        payOrder = new PayOrder();
                        payOrder.setOrder_no(orderNo);
                        payOrder.setState(1);
                        accountV2Service.editeState(payOrder);
                    }else{
                        LOGGER.error("IOS插入订单失败,单号: "+iosPayVerifyRequest.getTransaction_id());
                    }
                }
            });
            return true;
        }
        return false;
    }


    /**
     * 微信支付回调
     * @param request
     * @return
     * @throws Exception
     */
    public String wxpayCallback(HttpServletRequest request) {
        LOGGER.info("微信支付回调");
        Map<String,String> return_data = new HashMap<>();
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            String resultxml = new String(outSteam.toByteArray(), StandardCharsets.UTF_8);
            Map<String, String> params = PayCommonUtil.doXMLParse(resultxml);
            outSteam.close();
            inStream.close();
            if (!PayCommonUtil.isTenpaySign(params)) {
                // 支付失败
                LOGGER.info("微信支付回调TenpaySign错误");
                return_data.put("return_code", "FAIL");
                return_data.put("return_msg", "return_code不正确");
                return GetMapToXML(return_data);
            }
            System.out.println("===============付款成功==============");
            String total_fee = params.get("total_fee");
            double v = Double.valueOf(total_fee) / 100;
            String out_trade_no = params.get("out_trade_no").split("O")[0];
            LOGGER.info("out_trade_no: "+out_trade_no);
            Date accountTime = DateUtil.stringtoDate(params.get("time_end"), "yyyyMMddHHmmss");
            String ordertime = DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
            String totalAmount = String.valueOf(v);
            String appId = params.get("appid");
            String tradeNo = params.get("transaction_id");
            // 另起线程处理业务
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    // 支付成功
                    // 处理支付成功逻辑
                    try {
                        if(StringUtils.isNotBlank(out_trade_no)){
                            PayOrder payOrder = new PayOrder();
                            payOrder.setOrder_no(out_trade_no);
                            payOrder.setState(1);
                            accountV2Service.editeState(payOrder);
                            LOGGER.info("微信支付回调正常处理");
                        }else {
                            LOGGER.error("支付宝回调返回订单号为空,params:" + params);
                        }
                    } catch (Exception e) {
                        LOGGER.error("支付宝回调业务处理报错,params:" + params, e);
                    }

                }
            });
            executorService.shutdown();
        }catch (Exception e){
            LOGGER.info("微信支付回调异常: "+e.getMessage());
            return_data.put("return_code", "FAIL");
            return_data.put("return_msg", "SYSTEM ERROR");
            return GetMapToXML(return_data);
        }
        return_data.put("return_code", "SUCCESS");
        return_data.put("return_msg", "OK");
        return GetMapToXML(return_data);
    }

    private static String GetMapToXML(Map<String,String> param){
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        for (Map.Entry<String,String> entry : param.entrySet()) {
            sb.append("<"+ entry.getKey() +">");
            sb.append(entry.getValue());
            sb.append("</"+ entry.getKey() +">");
        }
        sb.append("</xml>");
        return sb.toString();
    }
}
