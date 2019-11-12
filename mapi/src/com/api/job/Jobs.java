package com.api.job;

import com.api.account.entity.Account;
import com.api.account.mapper.AccountMapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Jobs {

    private final static Logger logger = LoggerFactory.getLogger(Jobs.class);

    @Autowired
    private AccountMapper accountMapper;

//    @Scheduled(cron = "*/5 * * * * ?")
    @Scheduled(cron = "0 30 0 * * ?")//每天凌晨00:30执行一次  将会员时间到期的会员状态清空
    public void vipStateJob(){
        List<Account> accountList = accountMapper.getAllAccount();
        if(CollectionUtils.isEmpty(accountList)){
            logger.error("vipTimeJob getAllAccount error");
            return;
        }
        //1000条数据更新
        List<Integer> preUpdateList = new ArrayList<>();
        int index=0;
        int times=0;
        for (Account account:accountList) {
           times++;
           if(account.getVip_time()!=null && account.getIs_vip().equals(1) && new Date().after(account.getVip_time())){
                index++;
                preUpdateList.add(account.getAccount_id());
           }
           if(index>=1000 || times>=accountList.size()){
               index = 0;
               try{
                   accountMapper.batchUpdateVipState(preUpdateList);
               }catch (Exception e){
                   logger.error("vipTimeJob batchUpdateVipState error: "+e.getMessage());
               }
               preUpdateList.clear();
           }
        }
        logger.info("vipTimeJob done");
    }
}
