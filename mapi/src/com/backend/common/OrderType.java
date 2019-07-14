package com.backend.common;

public enum OrderType {
    VIP(1,"番茄会员充值"),
    PAYDL(2,"商品下载");

    OrderType(int type, String desc) {
    }

    public static String getDescByType(int type){
        switch (type){
            case 1:
                return VIP.name();
            case 2:
                return PAYDL.name();
            default:
                return "";
        }
    }
}
