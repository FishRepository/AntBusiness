package com.api.customer.mapper;

import com.api.customer.entity.PushLog;

import java.util.List;

public interface PushLogMapper {

    int insertPushLog(PushLog pushLog);

    int updatePushLog(PushLog pushLog);

    //查询用户推送记录
    List<PushLog> getPushLogs(Integer account_id);
    //批量保存用户推送记录
    int savePushLogBatch(List<PushLog> pushLogs);
    //消费用户推送记录
    int clearNotify(Integer account_id);
    //查询用户未消费的推送记录
    List<PushLog> getUnConsumePushLog(Integer account_id);
}
