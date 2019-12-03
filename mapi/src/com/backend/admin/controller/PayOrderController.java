package com.backend.admin.controller;

import com.backend.admin.entity.AjaxResult;
import com.backend.admin.entity.PayOrder;
import com.backend.admin.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("payorder")
public class PayOrderController extends BaseController{

    @Autowired
    private PayOrderService payOrderService;

    @RequestMapping
    public ModelAndView index(){
        return new ModelAndView("admin/payorder");
    }

    /**
     * 返回订单列表
     * @param payOrder
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public AjaxResult list(PayOrder payOrder){
        List<PayOrder> orderList = payOrderService.queryAll(payOrder);
        return successData(orderList);
    }

    @RequestMapping("export")
    public void export(PayOrder payOrder, HttpServletResponse response) {
        payOrderService.exportOrder(payOrder, response);
    }


    /**
     * 查询订单
     * @param order_no
     * @return
     */
    @RequestMapping("getOrderByNo")
    @ResponseBody
    public AjaxResult getOrderByNo(String order_no){
        PayOrder order = payOrderService.queryById(order_no);
        return successData(order);
    }

    /**
     * 编辑
     * @param payOrder
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public AjaxResult edit(PayOrder payOrder){
        int update = payOrderService.update(payOrder);
        if(update > 0){
            return success();
        }
        return error();
    }
}
