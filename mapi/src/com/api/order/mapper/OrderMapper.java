package com.api.order.mapper;

import java.util.List;

import com.api.order.entity.ListPageOrder;
import com.api.order.entity.Order;
import com.api.order.entity.OrderDetail;
import com.api.order.entity.OrderGoods;
import com.api.order.entity.OrderList;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
	public int insertOrder(Order order);
	
	public int updateOrder(Order order);
	
	public Order queryOrderByIdAndType(Order order);
	
	public OrderDetail queryOrderById(Order order);
	
	public int queryOrderType(Integer order_id);
	
	public List<OrderList> listPageOrder(ListPageOrder order);
	
	public int deleteOrder(Order order);
	
	public int insertOrderGoods(OrderGoods ordergoods);
	
	public int updateOrderGoods(OrderGoods ordergoods);
	
	public OrderGoods queryOrderGoodsById(OrderGoods ordergoods);
	
	public int checkOrderGoods(OrderGoods ordergoods);
	
	public List<OrderGoods> queryOrderGoods(Order order);

	public List<OrderGoods> queryDeleteOrderGoods(Order order);
	
	public int deleteOrderGoodsById(OrderGoods ordergoods);
	
	public int deleteOrderGoods(Order order);

	public int updateTag(Order order);
}