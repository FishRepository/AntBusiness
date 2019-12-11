package com.backend.admin.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.backend.admin.entity.AlipayNotifyParam;
import com.backend.admin.entity.PayOrder;
import com.backend.common.AlipayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class AliPayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliPayService.class);

    private ExecutorService executorService;

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private AccountV2Service accountV2Service;
    /**
     * <pre>
     * 第一步:验证签名,签名通过后进行第二步
     * 第二步:按一下步骤进行验证
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
     * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
     * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
     * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
     * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     * </pre>
     *
     * @param request
     * @return
     */
    public boolean callback(HttpServletRequest request) {
        Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
        String paramsJson = JSON.toJSONString(params);
        Map paramMap = JSON.parseObject(paramsJson);
        LOGGER.info("支付宝回调，{}", paramsJson);
        try {
            // 调用SDK验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(paramMap, AlipayConfig.ALIPAY_PUBLIC_KEY,
                    AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);
            if (signVerified) {
                LOGGER.info("支付宝回调签名认证成功");
                // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
//                this.check(params);
                // 另起线程处理业务
                executorService = Executors.newFixedThreadPool(20);
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        AlipayNotifyParam param = buildAlipayNotifyParam(params);
                        String trade_status = param.getTradeStatus();
                        // 支付成功
                        if (trade_status.equals(AlipayConfig.TRADE_SUCCESS)
                                || trade_status.equals(AlipayConfig.TRADE_FINISHED)) {
                            // 处理支付成功逻辑
                            try {
                                PayOrder payOrder = new PayOrder();
                                payOrder.setOrder_no(param.getOutTradeNo());
                                payOrder.setState(1);
                                accountV2Service.editeState(payOrder);
                            } catch (Exception e) {
                                LOGGER.error("支付宝回调业务处理报错,params:" + paramsJson, e);
                            }
                        } else {
                            LOGGER.error("没有处理支付宝回调业务，支付宝交易状态：{},params:{}",trade_status,paramsJson);
                        }
                    }
                });
                executorService.shutdown();
                while(!executorService.awaitTermination(1, TimeUnit.SECONDS)){
                    executorService.shutdownNow();
                }
                // 如果签名验证正确，立即返回success，后续业务另起线程单独处理
                // 业务处理失败，可查看日志进行补偿，跟支付宝已经没多大关系。
                return true;
            } else {
                LOGGER.info("支付宝回调签名认证失败，signVerified=false, paramsJson:{}", paramsJson);
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("支付宝回调签名认证失败,paramsJson:{},errorMsg:{}", paramsJson, e.getMessage());
            return false;
        }
    }

    // 将request中的参数转换成Map
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<>();

        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }
        return retMap;
    }

    private static AlipayNotifyParam buildAlipayNotifyParam(Map<String, String> params) {
        String json = JSON.toJSONString(params);
        return JSON.parseObject(json, AlipayNotifyParam.class);
    }

    /**
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
     * 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
     * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
     * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
     * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     *
     * @param params
     * @throws AlipayApiException
     */
    private void check(Map<String, String> params) throws AlipayApiException {
        String outTradeNo = params.get("out_trade_no");

        // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        PayOrder payOrder = payOrderService.queryById(outTradeNo);// 这个方法自己实现
        if (payOrder == null) {
            throw new AlipayApiException("out_trade_no错误");
        }

        // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        long total_amount = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(100)).longValue();
        if (total_amount != new BigDecimal(payOrder.getOrder_amount()/100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) {
            throw new AlipayApiException("error total_amount");
        }

        // 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
        // 第三步可根据实际情况省略

        // 4、验证app_id是否为该商户本身。
        if (!params.get("app_id").equals(AlipayConfig.APPID)) {
            throw new AlipayApiException("app_id不一致");
        }
    }

    public static void main(String[] args) throws AlipayApiException {
        String params = "{\"gmt_create\":\"2019-12-04 13:41:31\",\"charset\":\"utf-8\",\"seller_email\":\"myws168@sina.com\",\"subject\":\"番茄科技\",\"sign\":\"AFyVQBJXYY1PTJoijjGLeo4QH1WdQGMkoJj6/pSt+pXuWujuLy6ajf3KbcXKMTGaqS77mHFZU2hc+N6uOuvnPEjWAwbKwc4828BmR6dnRc9PV5z8BjDdL1VQL3h6VzDGvjdbb3pHDQG0j1lJ/tZGg7JMKH9Ee8d3vqxrYFhfIoSZb+KChcY2kV6RptxfBDNdpNJxKUpw12cQK9/DzcTgMV+YW3PEsemNzfpzqmVpqaxeKij6kAyaFHgUYMOUkWYHSUaP/nBr1E5o5kOTgdl0mUsp18tfa0IJJSqLIE0l3eQC/JG9I8alj+N3eQ9m3B00q/46u7JJGkbFDkofzdgFfA==\",\"body\":\"我是测试数据\",\"buyer_id\":\"2088302932745012\",\"invoice_amount\":\"0.01\",\"notify_id\":\"2019120400222134132045010542822586\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"0.01\\\",\\\"fundChannel\\\":\\\"PCREDIT\\\"}]\",\"notify_type\":\"trade_status_sync\",\"trade_status\":\"TRADE_SUCCESS\",\"receipt_amount\":\"0.01\",\"app_id\":\"2019120269610261\",\"buyer_pay_amount\":\"0.01\",\"sign_type\":\"RSA2\",\"seller_id\":\"2088721372623632\",\"gmt_payment\":\"2019-12-04 13:41:32\",\"notify_time\":\"2019-12-05 14:10:26\",\"version\":\"1.0\",\"out_trade_no\":\"855c34df0d9c4725ac80135b5865a5fe\",\"total_amount\":\"0.01\",\"trade_no\":\"2019120422001445010527493946\",\"auth_app_id\":\"2019120269610261\",\"buyer_logon_id\":\"czm***@163.com\",\"point_amount\":\"0.00\"}";
        Map paramMap = JSON.parseObject(params);
        // 调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(paramMap, AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);
        AlipayNotifyParam param = buildAlipayNotifyParam(paramMap);
        String trade_status = param.getTradeStatus();
        System.out.println(signVerified);
        System.out.println(trade_status);
    }
}
