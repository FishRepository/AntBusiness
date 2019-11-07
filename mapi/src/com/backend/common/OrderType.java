package com.backend.common;

public enum OrderType {
    //订单类型：1年费;2连续包月;3月付费;4兑换码下载
    YEAR_VIP(1,"年费"),
    SERIAL_MONTH_VIP(2,"连续包月"),
    MONTH_VIP(3,"月付费"),
    PAYDL(4,"商品下载");

    OrderType(int type, String desc) {
    }

    public static String getDescByType(int type){
        switch (type){
            case 1:
                return YEAR_VIP.name();
            case 2:
                return SERIAL_MONTH_VIP.name();
            case 3:
                return MONTH_VIP.name();
            case 4:
                return PAYDL.name();
            default:
                return "";
        }
    }
}
