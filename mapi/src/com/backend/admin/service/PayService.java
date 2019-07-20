package com.backend.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.backend.admin.entity.IOSPayVerifyRequest;
import com.backend.admin.entity.PayOrder;
import com.backend.admin.entity.PayRequest;
import com.backend.admin.entity.PayResponse;
import com.backend.common.AliPayUtil;
import com.backend.common.IosVerifyUtil;
import com.backend.common.WxPayUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class PayService {

    @Autowired
    private PayOrderService payOrderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PayService.class);

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
                    }
                    return aliPayResponse;
                case 2:
                    PayResponse wxPayResponse = WxPayUtil.doPay(payRequest);
                    if(wxPayResponse!=null && wxPayResponse.isResult()){
                        saveOrder(payRequest);
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
        payOrderService.insert(payOrder);
    }

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
            //TODO 处理业务逻辑
            LOGGER.info("交易成功，新增并处理订单：{}",iosPayVerifyRequest.getTransaction_id());
        //"充值成功"
            return true;
        }
        return false;
    }



}
