package com.backend.admin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.backend.admin.entity.PayOrder;
import com.backend.admin.mapper.PayOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户支付订单信息(PayOrder)表服务接口
 *
 */
@Service
public class PayOrderService {

    @Autowired
    private PayOrderMapper payOrderMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(PayOrderService.class);

    /**
     * 通过ID查询单条数据
     *
     * @param orderNo 主键
     * @return 实例对象
     */
    public PayOrder queryById(String orderNo){
        return payOrderMapper.queryById(orderNo);
    }

    /**
     * 查询订单列表
     * @param payOrder
     * @return
     */
    public List<PayOrder> queryAll(PayOrder payOrder){
        if(payOrder == null){
            payOrder = new PayOrder();
        }
        return payOrderMapper.queryAll(payOrder);
    }

    /**
     * 新增数据
     *
     * @param payOrder 实例对象
     * @return 实例对象
     */
    public int insert(PayOrder payOrder){
        return payOrderMapper.insert(payOrder);
    }

    /**
     * 修改数据
     *
     * @param payOrder 实例对象
     * @return 实例对象
     */
    public int update(PayOrder payOrder){
        return payOrderMapper.update(payOrder);
    }

    /**
     * 通过主键删除数据
     *
     * @param orderNo 主键
     * @return 是否成功
     */
    public boolean deleteById(String orderNo){
        return false;
    }

    /**
     * 导出订单excel
     * @param payOrder
     * @param response
     */
    public void exportOrder(PayOrder payOrder, HttpServletResponse response) {
        if(payOrder == null){
            payOrder = new PayOrder();
        }
        List<PayOrder> payOrders = payOrderMapper.queryAll(payOrder);
        if(CollectionUtil.isEmpty(payOrders)){
            return;
        }
        List<Map<String, Object>> mapList = new ArrayList<>();
        String[] fields = {"order_no", "account_id", "user_phone", "order_amount", "order_type",
                "pay_type", "create_time", "state"};
        String[] fieldsName = {"订单编号", "会员id", "联系方式", "订单金额(元)",
                "订单类型", "支付方式", "订单时间", "订单状态"};
        for (PayOrder item:payOrders) {
            Map<String, Object> map = new LinkedHashMap<>();
            Map<String, Object> tunnelMap = BeanUtil.beanToMap(item);
            for (String key : fields) {
                map.put(key, tunnelMap.get(key));
            }
            map.put("order_amount", new BigDecimal(item.getOrder_amount()/100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            switch (item.getOrder_type()){
                case 1:
                    map.put("order_type","2年费");
                    break;
                case 2:
                    map.put("order_type","1年费");
                    break;
                case 3:
                    map.put("order_type","半年费");
                    break;
                case 4:
                    map.put("order_type","兑换下载");
                    break;
                default:
                    map.put("order_type","充值");
                    break;
            }
            if(null == item.getPay_type()){
                map.put("pay_type","手机充值");
            }else{
                switch (item.getPay_type()){
                    case 1:
                        map.put("pay_type","支付宝");
                        break;
                    case 2:
                        map.put("pay_type","微信");
                        break;
                    case 3:
                        map.put("pay_type","IOS");
                        break;
                    default:
                        map.put("pay_type","手机充值");
                }
            }
            switch (item.getState()){
                case 0:
                    map.put("state","等待支付");
                    break;
                case 1:
                    map.put("state","交易成功");
                    break;
                case 2:
                    map.put("state","交易取消");
                    break;
                default:
                    map.put("state","等待交易");
            }
            mapList.add(map);
        }
        ExcelWriter writer = new ExcelWriter(false, "隧道信息");
        //自定义标题别名
        for (int i = 0; i < fieldsName.length; i++) {
            writer.addHeaderAlias(fields[i], fieldsName[i]);
        }
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(mapList, true);
        // out为OutputStream，需要写出到的目标流
        try {
            // response为HttpServletResponse对象
            response.setHeader("Content-Disposition: attachment","filename='订单统计.xls'");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            // test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
            ServletOutputStream out = response.getOutputStream();
            writer.flush(out, true);
            // 关闭writer，释放内存
            writer.close();
            // 此处记得关闭输出Servlet流
            IoUtil.close(out);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}