package com.backend.admin.service;

import com.api.account.entity.Account;
import com.api.account.mapper.AccountMapper;
import com.backend.admin.entity.PayOrder;
import com.backend.admin.entity.ToastSwith;
import com.backend.admin.entity.VipPay;
import com.backend.admin.mapper.PayOrderMapper;
import com.backend.admin.mapper.ToastSwithMapper;
import com.backend.admin.mapper.VipPayMapper;
import com.backend.common.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountV2Service {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ToastSwithMapper toastSwithMapper;

    @Autowired
    private VipPayMapper vipPayMapper;

    @Autowired
    private PayOrderMapper payOrderMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountV2Service.class);

    /**
     * 查询用户VIP剩余天数
     * @param account_id
     * @return
     */
    public Account getAccountInfo(Integer account_id) {
        if(account_id==null || account_id==0){
            return null;
        }
        Integer remain_time;
        Account account=null;
        try {
            account = accountMapper.getAccountInfo(account_id);
            if(account==null){
                return null;
            }
            if(account.getIs_vip()==0){
                account.setRemain_time(0);
                return account;
            }
            remain_time = Math.toIntExact(DateUtil.startToEnd(new Date(), account.getVip_time()));
            account.setRemain_time(remain_time);
        }catch (Exception e){
            LOGGER.error("query account error: "+e);
        }
        return account;
    }

    /**
     * 查询VIP付费信息列表
     * @return
     */
    public List<VipPay> getVipPayList() {
        return vipPayMapper.queryAll(new VipPay());
    }

    /**
     * 查询系统开关
     * @param id
     * @return
     */
    public Integer getSwitchById(Integer id) {
        if(id==null || id==0){
            return null;
        }
        ToastSwith toastSwith = toastSwithMapper.queryById(id);
        if(toastSwith==null){
            return null;
        }
        return toastSwith.getIsShow();
    }

    public List<PayOrder> queryByAccountId(Integer account_id){
        if(account_id==null || account_id==0){
            return null;
        }
        return payOrderMapper.queryByAccountId(account_id);
    }

    public Integer getOrderState(String order_no) {
        if(StringUtils.isBlank(order_no)){
            return null;
        }
        PayOrder payOrder = payOrderMapper.queryById(order_no);
        if(payOrder==null){
            return null;
        }
        return payOrder.getState();
    }
}
