package com.backend.common;

public class AlipayConfig {
    public static final String APPID = "2019062965759225";

    /** 支付宝网关*/
    public static final String GATE = "https://openapi.alipay.com/gateway.do";

    /** (应用私钥)*/
    public static final String APP_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKgCcj6k6aU4Tgy9kzqE5cAZdua94OuRebMVpJmAzoGo1HCCAQoOJYTy48hXKmfW2yf61AMcfi9Ls2HEgEWpOnncd5mSYLk8n9xENJNBPb0o66GxdFhQHkvUP00O0wj012rJbBmOKR/1EegIzcnUs7hhCNZjm7+fPY7WNunGspvhAgMBAAECgYEAjJmHfA9MjHzjLFxV4qonDy0ASTdsDBexTgJ1vhSCPRXJuuHA9uZB1dHid90iWsX+n6WUWYo1u2TRmKb2ZdWqXcU6RSSxyhQr4lZ+k8BGc9xK1rZ7hrk+oZCfuc6hctRvXZsIcr8ZZ5HJtQ8Yd+SNFOhOk/N8VBQI6b9YptW+SLUCQQDqgkunulePFdLR2q1j6Z1rePMNiDkpB/UL4sUxPjpB/T9gyIWFqFg+q0dFqFI3WzB6Su6y2xQqi1TrRKAhm2qvAkEAt2gKE5AInkyhhMGc8RhqBLFGqUByiGqer/iq/jUSHC/ynD16X0/t9W/XvUgA7Jzh7Oo4DwBiyZLdb3cZ//TGbwJALNBxfMATc3iMLSZI9Er0llEqwW9C3SB0J61SWbK2l125+tjXC0+8bV115U3hSErY3MXtD6jmFatCsyMOJt34aQJAFHIqvWePLmaIkw4qes4n84H3yKKAoiiQrDlihphP/ohx2W0ZGUsF0hi3ykgeokITBaBOw41dde9DdEuowFn/TQJBAN5mvI6/CXZJy/OVtRyi5OAtGi1Lp6PsoRjt+SM3bBVxlxeizPOyWq6+y4qccfAOAKoDZBQ1SfvlgSGEmtK4Hzc=";

    /** （应用公钥）*/
    public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoAnI+pOmlOE4MvZM6hOXAGXbmveDrkXmzFaSZgM6BqNRwggEKDiWE8uPIVypn1tsn+tQDHH4vS7NhxIBFqTp53HeZkmC5PJ/cRDSTQT29KOuhsXRYUB5L1D9NDtMI9NdqyWwZjikf9RHoCM3J1LO4YQjWY5u/nz2O1jbpxrKb4QIDAQAB";

    /**  支付宝公钥 */
    public static final String PUBLIC_KEY = "xqL+YEw98nPU5YfYt0CkXQ==";

    /** 合作伙伴ID（支付宝给定） */
    public static final String SELLERID = "2088721372623632";

    /** 商户账户 */
    public static final String SELLER = "此处为商户账号";

    /** 编码方式 */
    public static final String CHARSET = "utf-8";

    /** sign方式*/
    public static final String SIGN_TYPE = "RSA";

    /** 支付结束*/
    public static final String TRADE_FINISHED = "TRADE_FINISHED";

    /** 支付成功*/
    public static final String TRADE_SUCCESS = "TRADE_SUCCESS";

    public static final String SUCCESS = "success";

    public static final String FAILURE = "failure";

    public AlipayConfig() {

    }

}
