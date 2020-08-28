package com.api.job;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.api.account.entity.Account;
import com.api.account.mapper.AccountMapper;
import com.api.common.utils.JPushUtil;
import com.api.customer.entity.Customer;
import com.api.customer.entity.PushLog;
import com.api.customer.mapper.CustomerMapper;
import com.api.customer.mapper.PushLogMapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Transactional(rollbackFor = Exception.class)
public class Jobs {

    private final static Logger logger = LoggerFactory.getLogger(Jobs.class);

    private final static String NOTIFYCONTENT = "亲爱的，番茄管家提醒你有客户即将来例假了哦~赶紧去呵护她吧!";

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private PushLogMapper pushLogMapper;

    private ExecutorService executorService;

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

//    @Scheduled(cron = "0 0/1 * * * ?")
    @Scheduled(cron = "0 0 20 * * ?")//每天20:00执行一次推送
    public void pushClientJob() throws Exception{
        List<Customer> customerList = customerMapper.getCustomerByPeriod();
        if(CollectionUtil.isEmpty(customerList)){
            return;
        }
        //设置period_notified=1已提醒 period_state=1即将生理期的集合  notifyList
        //设置period_notified=0恢复未提醒状态 period_state=0生理期已过的集合 resetNotifiedList
        //设置period_state=2 处于生理期的集合 inPeriodList
        List<Customer> notifyList = new ArrayList<>();
        List<Integer> resetNotifiedList = new ArrayList<>();
        List<Integer> inPeriodList = new ArrayList<>();
        //accountId, List<Customer> 存储用户id对应的customer集合
        Map<Integer, List<Customer>> accountMsgMap = new HashMap<>();
        for (Customer customer: customerList) {
            //未提醒的
            if(ObjectUtil.equal(0, customer.getPeriod_notified()) && ! ObjectUtil.equal(getPeriodState(customer.getPeriod()), 0)){
                setAccountMsgMap(customer, accountMsgMap);
                if(ObjectUtil.equal(0, customer.getPeriod_state())){
                    customer.setPeriod_state(getPeriodState(customer.getPeriod()));
                }
                notifyList.add(customer);
            }
            //已过5天生理期
            if(ObjectUtil.equal(1, customer.getPeriod_notified()) && ObjectUtil.equal(2, customer.getPeriod_state()) && ObjectUtil.equal(getPeriodState(customer.getPeriod()), 0)){
                resetNotifiedList.add(customer.getCustomer_id());
            }
            //处于5天生理期内
            if(ObjectUtil.equal(1, customer.getPeriod_notified()) && ObjectUtil.equal(1, customer.getPeriod_state()) && ObjectUtil.equal(getPeriodState(customer.getPeriod()), 2)){
                inPeriodList.add(customer.getCustomer_id());
            }
        }
        if(CollectionUtil.isNotEmpty(notifyList)){
            Set<Map.Entry<Integer, List<Customer>>> entries = accountMsgMap.entrySet();
            //待插入提醒记录
            List<PushLog> pushLogs = new ArrayList<>();
            PushLog pushLog;
            executorService = Executors.newFixedThreadPool(entries.size());
            for (Map.Entry<Integer, List<Customer>> entry:entries) {
                Integer accountId = entry.getKey();
                List<Customer> accountCustomerList = entry.getValue();
                //List<Customer> 转jsonArray push
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        JPushUtil.sendCustomerPush(NOTIFYCONTENT, accountId, JSONUtil.parseArray(accountCustomerList).toString());
                    }
                });
                StringBuilder builder = new StringBuilder();
                pushLog = new PushLog();
                for (Customer item : accountCustomerList) {
                    builder.append(item.getCustomer_id()).append(",");
                }
                pushLog.setAccount_id(accountId);
                pushLog.setCustomers(builder.toString());
                pushLog.setType(1);
                pushLog.setMessage(NOTIFYCONTENT);
                pushLogs.add(pushLog);
            }
            executorService.shutdown();
            while(!executorService.awaitTermination(1, TimeUnit.SECONDS)){
                executorService.shutdownNow();
            }
            //保存用户提醒推送记录
            pushLogMapper.savePushLogBatch(pushLogs);
            customerMapper.updateNotifyTime(notifyList);
        }
        if(CollectionUtil.isNotEmpty(resetNotifiedList)){
            customerMapper.resetNotifyState(resetNotifiedList);
        }
        if(CollectionUtil.isNotEmpty(inPeriodList)){
            customerMapper.updatePeriodState(inPeriodList);
        }
    }

    private void setAccountMsgMap(Customer customer, Map<Integer, List<Customer>> accountMsgMap) {
        try {
            if(accountMsgMap.containsKey(customer.getAccount_id())){
                List<Customer> customerList = accountMsgMap.get(customer.getAccount_id());
                ArrayList<Customer> customers = ListUtil.toList(customerList);
                customers.add(customer);
                accountMsgMap.put(customer.getAccount_id(), customers);
                return;
            }
            accountMsgMap.put(customer.getAccount_id(), Collections.singletonList(customer));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //判断customer period是否在3天内
    private boolean periodIsIn3days(String periodStr){
        if(StrUtil.isEmpty(periodStr)){
            return false;
        }
        int today = DateUtil.dayOfMonth(new Date());
        int endOfMonthDay = DateUtil.endOfMonth(new Date()).dayOfMonth();
        int periodDay = Integer.parseInt(periodStr.substring(0, periodStr.indexOf("号")));
        if(periodDay > today && periodDay <= (3 + today)){
            return true;
        }
        return periodDay < today && (endOfMonthDay - today + periodDay) <= 3;
    }

    //判断生理期是否已过去
    private boolean periodIsPassed(Customer customer) {
        if(ObjectUtil.isEmpty(customer.getPeriod_notify_time())){
            return false;
        }
        return DateUtil.betweenDay(customer.getPeriod_notify_time(), new Date(), true) > 8;
    }

    //判断生理期是否生理期内
    private boolean isInperiod(Customer customer) {
        if(ObjectUtil.isEmpty(customer.getPeriod_notify_time())){
            return false;
        }
        return DateUtil.betweenDay(customer.getPeriod_notify_time(), new Date(), true) <= 8 && DateUtil.betweenDay(customer.getPeriod_notify_time(), new Date(), true) >= 3;
    }

    //生理期状态 0无状态 1即将到达生理期 2在生理期中
    private static int getPeriodState(String periodStr) {
        if(StrUtil.isEmpty(periodStr)){
            return 0;
        }
        int today = DateUtil.dayOfMonth(new Date());
        int endOfMonthDay = DateUtil.endOfMonth(new Date()).dayOfMonth();
        int periodDay = Integer.parseInt(periodStr.substring(0, periodStr.indexOf("号")));
        if(periodDay > today && periodDay <= (3 + today)){
            return 1;
        }
        if(periodDay < today && (endOfMonthDay - today + periodDay) <= 3){
            return 1;
        }
        if(today >= periodDay && today <= (5 + periodDay)){
            return 2;
        }
        if(periodDay >= today && (endOfMonthDay - periodDay + periodDay) <= 5){
            return 2;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(getPeriodState("31号-4号"));
        System.out.println(getPeriodState("27号-31号"));
        System.out.println(getPeriodState("22号-27号"));
        System.out.println(getPeriodState("20号-24号"));
    }
}

