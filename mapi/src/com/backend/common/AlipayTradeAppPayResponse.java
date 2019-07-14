package com.backend.common;

import java.io.Serializable;

/**
 * 阿里支付网关返回json
 */
public class AlipayTradeAppPayResponse implements Serializable {
    private static final long serialVersionUID = 5223699260132690264L;

    private String code;//网关返回码,详见文档 https://docs.open.alipay.com/api_1/alipay.trade.app.pay/
    private String msg;//网关返回码描述
    private String out_trade_no;//商户网站唯一订单号
    private String trade_no;//	该交易在支付宝系统中的交易流水号
    private String total_amount;//该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。
    private String seller_id;//收款支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字
    private String merchant_order_no;//商户原始订单号，最大长度限制32位
    private String sub_code;//业务返回码，参见具体的API接口文档
    private String sub_msg;//业务返回码描述，参见具体的API接口文档

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getMerchant_order_no() {
        return merchant_order_no;
    }

    public void setMerchant_order_no(String merchant_order_no) {
        this.merchant_order_no = merchant_order_no;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSub_msg() {
        return sub_msg;
    }

    public void setSub_msg(String sub_msg) {
        this.sub_msg = sub_msg;
    }
}
