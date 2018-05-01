package com.backend.admin.service;

import com.api.common.entity.Result;
import com.api.common.utils.StringUtil;
import com.backend.admin.entity.OrderListResult;
import com.backend.admin.entity.OrderListVo;
import com.backend.admin.entity.Ordergoods;
import com.backend.admin.mapper.AccountorderMapper;
import com.backend.admin.mapper.UpdateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UpdateService {

    protected static final Logger log = LoggerFactory.getLogger(UpdateService.class);

    @Autowired
    private UpdateMapper updateMapper;

    @Autowired
    private AccountorderMapper accountorderMapper;

    public Result updateOrderTime(int orderId, Date createTime) {
        Result result = new Result();
        try {
            if(orderId==0 || createTime==null){
                result.setCode(500);
                result.setMsg("param is empty!");
                return result;
            }
            if(updateMapper.updateOrderTime(orderId, createTime) > 0){
                result.setCode(0);
                result.setMsg("success");
            }else{
                result.setCode(200);
                result.setMsg("updateOrderTime error!");
            }
        } catch (Exception e) {
            log.error("updateOrderTime error!"+e.getMessage());
            result.setCode(501);
            result.setMsg("updateOrderTime error!");
            return result;
        }
        return result;
    }

    public OrderListResult getOrderListByMonth(Integer accountId, String month, String year){
        OrderListResult result = new OrderListResult();
        if(accountId==null || accountId==0){
            result.setCode(500);
            result.setMsg("accountId is empty!");
            return result;
        }
        Double totalSals=0.0;

        Double totalprofit=0.0;
        try {
            List<OrderListVo> sellsList = accountorderMapper.selectOrderListBytype(1, accountId, StringUtil.isEmpty(month) ? "" : month, StringUtil.isEmpty(year) ? "" : year);
            if(!CollectionUtils.isEmpty(sellsList)){
                List<Ordergoods> sellsGoodsList = new ArrayList<>();
                for (OrderListVo orderListVo: sellsList) {
                    if(orderListVo!=null && !orderListVo.getOrdergoodsList().isEmpty()){
                        sellsGoodsList.addAll(orderListVo.getOrdergoodsList());
                        orderListVo.setOrdergoodsList(new ArrayList<>());
                    }
                }
                result.setSellsList(sellsList);
                result.setSellsGoodsList(mergeList(sellsGoodsList));
            }

            if(!CollectionUtils.isEmpty(sellsList)){
                for (OrderListVo orderListVo:sellsList) {
                    totalSals += orderListVo.getOrderSales();
                    totalprofit += orderListVo.getOrderProfit();
                }
            }
            result.setTotalprofit(totalprofit);
            result.setTotalSals(totalSals);
        } catch (Exception e) {
            log.error("getOrderListByMonth error!"+e.getMessage());
            result.setCode(500);
            result.setMsg("getOrderListByMonth error!");
            return result;
        }
        result.setCode(0);
        result.setMsg("success");
        return result;
    }

    public static List<Ordergoods> mergeList(List<Ordergoods> list) {
        HashMap<Integer, Ordergoods> map = new HashMap<>();
        for (Ordergoods bean : list) {
            if (map.containsKey(bean.getGoodsId())) {
                bean.setGoodsNum(map.get(bean.getGoodsId()).getGoodsNum() + bean.getGoodsNum());
            }
            map.put(bean.getGoodsId(), bean);
        }
        list.clear();
        list.addAll(map.values());
        return list;
    }

}
