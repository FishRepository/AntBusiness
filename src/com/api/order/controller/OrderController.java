package com.api.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.order.entity.InsertMoreOrder;
import com.api.order.entity.InsertOrder;
import com.api.order.entity.ListPageOrder;
import com.api.order.entity.Order;
import com.api.order.entity.OrderAndGoods;
import com.api.order.entity.UpdateMoreOrder;
import com.api.order.entity.UpdateOrder;
import com.api.order.entity.UpdateOrderRemark;
import com.api.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/insertOrder")
    @ResponseBody
	public Object insertOrder(@RequestBody OrderAndGoods orderinfo){
		return orderService.insertOrder(orderinfo);
	}
	
	@RequestMapping(value = "/updateOrder")
    @ResponseBody
	public Object updateOrder(@RequestBody OrderAndGoods orderinfo){
		return orderService.updateOrder(orderinfo);
	}
	
	@RequestMapping(value = "/insertInOrder")
    @ResponseBody
	public Object insertInOrder(InsertOrder order){
		order.setOrder_type(2);
		order.setAgentlevel_id(0);
		order.setCustomer_id(null);
		order.setCustomer_phoneid(null);
		order.setCustomer_addressid(null);
		return orderService.insertOrder(order);
	}
	
	@RequestMapping(value = "/insertOutOrder")
    @ResponseBody
	public Object insertOutOrder(InsertOrder order){
		order.setOrder_type(1);
		return orderService.insertOrder(order);
	}
	
	@RequestMapping(value = "/updateInOrder")
    @ResponseBody
	public Object updateInOrder(UpdateOrder order){
		order.setOrder_type(2);
		order.setCustomer_id(null);
		order.setCustomer_phoneid(null);
		order.setCustomer_addressid(null);
		return orderService.updateOrder(order);
	}
	
	@RequestMapping(value = "/updateOutOrder")
    @ResponseBody
	public Object updateOutOrder(UpdateOrder order){
		order.setOrder_type(1);
		return orderService.updateOrder(order);
	}
	
	@RequestMapping(value = "/insertMoreInOrder")
    @ResponseBody
	public Object insertMoreInOrder(InsertMoreOrder order){
		order.setOrder_type(2);
		order.setCustomer_id(null);
		order.setCustomer_phoneid(null);
		order.setCustomer_addressid(null);
		return orderService.insertMoreOrder(order);
	}
	
	@RequestMapping(value = "/insertMoreOutOrder")
    @ResponseBody
	public Object insertMoreOutOrder(InsertMoreOrder order){
		order.setOrder_type(1);
		return orderService.insertMoreOrder(order);
	}
	
	@RequestMapping(value = "/updateMoreInOrder")
    @ResponseBody
	public Object updateMoreInOrder(UpdateMoreOrder order){
		order.setOrder_type(2);
		order.setCustomer_id(null);
		order.setCustomer_phoneid(null);
		order.setCustomer_addressid(null);
		return orderService.updateMoreOrder(order);
	}
	
	@RequestMapping(value = "/updateMoreOutOrder")
    @ResponseBody
	public Object updateMoreOutOrder(UpdateMoreOrder order){
		order.setOrder_type(1);
		return orderService.updateMoreOrder(order);
	}
	
	@RequestMapping(value = "/updateOrderRemark")
    @ResponseBody
	public Object updateOrderRemark(UpdateOrderRemark orderremark){
		return orderService.updateOrderRemark(orderremark);
	}
	
	@RequestMapping(value = "/listPageOrder")
    @ResponseBody
	public Object listPageOrder(ListPageOrder listPageOrder){
		return orderService.listPageOrder(listPageOrder);
	}
	
	@RequestMapping(value = "/listPageAllOrder")
    @ResponseBody
	public Object listPageAllOrder(ListPageOrder listPageOrder){
		return orderService.listPageAllOrder(listPageOrder);
	}
	
	@RequestMapping(value = "/queryOneOrder")
    @ResponseBody
	public Object queryOneOrder(Order order){
		return orderService.queryOneOrder(order);
	}
	
	@RequestMapping(value = "/queryOrder")
    @ResponseBody
	public Object queryOrder(Order order){
		return orderService.queryOrder(order);
	}
	
	@RequestMapping(value = "/deleteOrder")
    @ResponseBody
	public Object deleteOrder(Order order){
		return orderService.deleteOrder(order);
	}
}