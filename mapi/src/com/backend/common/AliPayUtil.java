package com.backend.common;


import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.backend.admin.entity.PayRequest;
import com.backend.admin.entity.PayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.UUID;

public class AliPayUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliPayUtil.class);

    /**
     * 创建商户订单  请求支付宝
     * @param amount
     * @param random
     * @return
     */
    private static AlipayTradeAppPayResponse getsign(String amount, String random){
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATE,
                AlipayConfig.APPID,
                AlipayConfig.APP_PRIVATE_KEY,
                "json", AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                "RSA2");
        if (Double.valueOf(amount) <= 0){ // 一些必要的验证，防止抓包恶意修改支付金额
            return null;
        }
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("番茄科技");
        model.setOutTradeNo(random);  //订单号
        model.setTimeoutExpress("30m");  // 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        model.setTotalAmount(amount); // 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]这里调试每次支付1分钱，在项目上线前应将此处改为订单的总金额
        model.setProductCode("QUICK_MSECURITY_PAY");// 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
        request.setBizModel(model);
        // 设置后台异步通知的地址，在手机端支付成功后支付宝会通知后台，手机端的真实支付结果依赖于此地址
        request.setNotifyUrl("https://www.ta521.com/mapi/v2/alipayCallback");
        AlipayTradeAppPayResponse response;
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (Exception e) {
            LOGGER.error("Interface call error: "+e.getMessage());
            return null;
        }
        return response;
    }


    //    {
    //        "alipay_trade_app_pay_response": {
    //        "code": "10000",
    //                "msg": "Success",
    //                "out_trade_no": "70501111111S001111119",
    //                "trade_no": "2014112400001000340011111118",
    //                "total_amount": "9.00",
    //                "seller_id": "2088111111116894",
    //                "merchant_order_no": "20161008001"
    //    },
    //        "sign": "ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE"
    //    }
    //请求支付宝支付统一下单接口 返回payResponse
    public static PayResponse doPay(PayRequest payRequest){
        PayResponse payResponse = null;
        try {
        payResponse= new PayResponse();
        double orderAmount = new BigDecimal((float)payRequest.getOrder_money()/100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        AlipayTradeAppPayResponse tradeAppPayResponse = getsign(String.valueOf(orderAmount), payRequest.getOrderNo());
        if(tradeAppPayResponse==null){
            return null;
        }
//            if("10000".equals(tradeAppPayResponse.getCode())){
        payResponse.setResult(true);
        payResponse.setSign(tradeAppPayResponse.getBody());
//            }else {
//                payResponse.setResult(false);
//                payResponse.setErr_code(tradeAppPayResponse.getSubCode());
//                payResponse.setErr_code_des(tradeAppPayResponse.getSubMsg());
//            }
        } catch (Exception e) {
            LOGGER.error("AliPayUtil doPay error: "+e.getMessage());
            return null;
        }
        return payResponse;
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String uuid_ = uuid.replaceAll("-","");
        System.out.println(uuid+"  "+uuid_);
    }
}
