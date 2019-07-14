package com.backend.admin.service;

import com.backend.admin.entity.PayRequest;
import com.backend.admin.entity.PayResponse;
import com.backend.common.AliPayUtil;
import com.backend.common.WxPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliPayUtil.class);

    public PayResponse doPay(PayRequest payRequest){
        if(null==payRequest || null==payRequest.getPay_type() || null==payRequest.getAccount_id() || null==payRequest.getOrder_money() || null==payRequest.getOrder_type()){
            return null;
        }
        try {
            switch (payRequest.getPay_type()){
                case 1:
                    return AliPayUtil.doPay(payRequest);
                case 2:
                    return WxPayUtil.doPay(payRequest);
                default:
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("PayService doPay error, "+e.getMessage());
        }
        return null;
    }

}
