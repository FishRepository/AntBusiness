package com.backend.admin.entity;

import java.io.Serializable;

/**
 * 微信支付相关参数
 */
public class WxPayParam implements Serializable {

    private static final long serialVersionUID = 8916559912250302212L;

    private String mchId;

    private String nonceStr;

    private String timestamp;

    private String sign;

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
