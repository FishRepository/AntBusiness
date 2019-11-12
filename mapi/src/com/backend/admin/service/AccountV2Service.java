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
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    public boolean editeState(PayOrder payOrder) {
        if(payOrder==null || payOrder.getAccount_id()==null || payOrder.getState()==null
                || StringUtils.isBlank(payOrder.getOrder_no())){
            return false;
        }
        PayOrder payOrderLast = payOrderMapper.queryById(payOrder.getOrder_no());
        //若库中订单状态不是等待支付  返回
        if(payOrderLast==null || !payOrderLast.getState().equals(0)){
            return false;
        }
        //订单状态修改成功后更新相应的服务
        int update = payOrderMapper.update(payOrder);
        if(update<0){
            return false;
        }
        //交易取消，返回
        if(payOrder.getState().equals(2)){
            return false;
        }
        try {
            Account account = accountMapper.queryAccountById(payOrder.getAccount_id());
            //消费类型1年费;2连续包月;3月付费;4兑换下载
            //1年费会员2月费会员
            int days = 30;
            int vipType = 2;
            //原本的会员到期
            Date endTime = account.getVip_time();
            if(endTime==null || endTime.before(new Date())){
                endTime = new Date();
            }
            switch (payOrderLast.getOrder_type()){
                case 1:{
                    days = 365;
                    vipType = 1;
                } break;
                case 2:
                case 3: {
                    days = 30;
                    vipType = 2;
                } break;
                case 4:{

                } break;
                default: break;
            }
            //设置用户的vip信息
            Date vipTime = DateUtil.adjustDateByDay(endTime ,days,1);
            account.setVip_time(DateUtil.setEnd(vipTime,1));
            account.setIs_vip(1);
            account.setVip_type(vipType);
            accountMapper.updateAccount(account);

            //设置订单信息
            payOrder.setVip_time(days);
            payOrder.setRemain_time(vipTime);
            payOrderMapper.update(payOrder);
        }catch (Exception e){
            LOGGER.error("editeState error: "+e.getMessage());
        }
        return true;
    }
}
