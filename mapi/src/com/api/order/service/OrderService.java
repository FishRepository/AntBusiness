package com.api.order.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.common.entity.Result;
import com.api.common.utils.PropertiesUtil;
import com.api.common.utils.StringUtil;
import com.api.customer.entity.Customer;
import com.api.customer.entity.Detail;
import com.api.customer.mapper.CustomerMapper;
import com.api.goods.entity.AgentLevel;
import com.api.goods.entity.Brand;
import com.api.goods.entity.Goods;
import com.api.goods.entity.GoodsPrice;
import com.api.goods.mapper.GoodsMapper;
import com.api.goods.service.GoodsService;
import com.api.order.entity.InsertMoreOrder;
import com.api.order.entity.InsertOrder;
import com.api.order.entity.ListPageOrder;
import com.api.order.entity.Order;
import com.api.order.entity.OrderAllDetailResult;
import com.api.order.entity.OrderAndGoods;
import com.api.order.entity.OrderBrandGoods;
import com.api.order.entity.OrderDetailResult;
import com.api.order.entity.OrderGoods;
import com.api.order.entity.OrderGoodsResult;
import com.api.order.entity.OrderList;
import com.api.order.entity.OrderListResult;
import com.api.order.entity.OrderMonth;
import com.api.order.entity.OrderMonthListResult;
import com.api.order.entity.OrderReportResult;
import com.api.order.entity.OrderResult;
import com.api.order.entity.UpdateMoreOrder;
import com.api.order.entity.UpdateOrder;
import com.api.order.entity.UpdateOrderRemark;
import com.api.order.mapper.OrderMapper;

@Service
@Transactional
public class OrderService {
	@Autowired
    private OrderMapper orderMapper;
	@Autowired
    private CustomerMapper customerMapper;
	@Autowired
    private GoodsMapper goodsMapper;
	@Autowired
    private GoodsService goodsService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
	private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年");
	
	public OrderReportResult insertOrder(OrderAndGoods orderinfo){
		OrderReportResult result = new OrderReportResult();
		try{
			if(orderinfo!=null && orderMapper.insertOrder(orderinfo.getOrder()) > 0){
				result.setOrder_id(orderinfo.getOrder().getOrder_id());
				if(orderinfo.getOrdergoodslist()!=null && !orderinfo.getOrdergoodslist().isEmpty()){
					List<OrderGoodsResult> list = new ArrayList<OrderGoodsResult>();
					OrderGoodsResult orderGoodsResult = null;
					for(OrderGoods ordergoods:orderinfo.getOrdergoodslist()){
						ordergoods.setOrder_id(orderinfo.getOrder().getOrder_id());
						if(orderMapper.insertOrderGoods(ordergoods) > 0){
							orderGoodsResult = new OrderGoodsResult();
							orderGoodsResult.setGoods_id(ordergoods.getGoods_id());
							orderGoodsResult.setOrdergoods_id(ordergoods.getOrdergoods_id());
							list.add(orderGoodsResult);
						}
					}
					result.setList(list);
				}
				result.setCode(0);
				result.setMsg("下单成功");
			}else{
				result.setCode(1);
				result.setMsg("下单失败");
			}
		}catch(Exception e){
			result = new OrderReportResult();
			result.setCode(1);
			result.setMsg("下单失败");
		}
		return result;
	}
	
	public Result updateOrder(OrderAndGoods orderinfo){
		Result result = new Result();
		try{
			if(orderinfo!=null && orderMapper.updateOrder(orderinfo.getOrder()) > 0){
				if(orderinfo.getOrdergoodslist()!=null && !orderinfo.getOrdergoodslist().isEmpty()){
					for(OrderGoods ordergoods:orderinfo.getOrdergoodslist()){
						orderMapper.updateOrderGoods(ordergoods);
					}
				}
				result.setCode(0);
				result.setMsg("修改订单成功");
			}else{
				result.setCode(1);
				result.setMsg("修改订单失败");
			}
		}catch(Exception e){
			result = new Result();
			result.setCode(1);
			result.setMsg("修改订单失败");
		}
		return result;
	}
	
	public OrderResult insertOrder(InsertOrder order){
		OrderResult result = new OrderResult();
		try{
			if(order!=null && order.getAccount_id()!=null && order.getAccount_id() > 0 && order.getBrand_id()!=null && order.getBrand_id() > 0 
					&& order.getAgentlevel_id()!=null && order.getAgentlevel_id() >= 0 
					&& StringUtil.isValid(order.getGoodslist())){
				Brand brand = new Brand();
				brand.setBrand_id(order.getBrand_id());
				brand.setAccount_id(order.getAccount_id());
				String brand_name = goodsMapper.queryBrandById(brand);
				if(StringUtil.isValid(brand_name)){
					String agentlevel_name = null;
					if(order.getOrder_type() == 2){
						agentlevel_name = "进货价";
						order.setAgentlevel_id(0);
					}else{
						if(order.getAgentlevel_id() == 0){
							agentlevel_name = "按成本价出售";
						}else{
							AgentLevel agentlevel = new AgentLevel();
							agentlevel.setAgentlevel_id(order.getAgentlevel_id());
							agentlevel.setBrand_id(order.getBrand_id());
							agentlevel.setAccount_id(order.getAccount_id());
							agentlevel_name = goodsMapper.queryAgentLevelById(agentlevel);
						}
					}
					if(StringUtil.isValid(agentlevel_name)){
						List<OrderGoods> ordergoodslist = new ArrayList<OrderGoods>();
						float order_goodssales = 0f;
						float order_goodscost = 0f;
						float order_goodsprofit = 0f;
						int goods_count = 0;
						List<Integer> faillist = new ArrayList<Integer>();
						String[] goodss = order.getGoodslist().split("\\|");
						if(goodss!=null){
							String[] goodsarray = null;
							Goods goods = null;
							GoodsPrice goodsprice = null;
							OrderGoods ordergoods = null;
							for(String goodssstr:goodss){
								goodsarray = goodssstr.split(",");
								if(goodsarray!=null && goodsarray.length == 2){
									try{
										ordergoods = new OrderGoods();
										ordergoods.setAccount_id(order.getAccount_id());
										ordergoods.setGoods_id(Integer.parseInt(goodsarray[0]));
										ordergoods.setGoods_num(Integer.parseInt(goodsarray[1]));
										if(order.getOrder_type() == 1){
											ordergoods.setGoods_cnum(-ordergoods.getGoods_num());
										}else{
											ordergoods.setGoods_cnum(ordergoods.getGoods_num());
										}
										ordergoods.setBrand_name(brand_name);
										ordergoods.setBrand_id(order.getBrand_id());
										ordergoods.setAgentlevel_name(agentlevel_name);
										ordergoods.setAgentlevel_id(order.getAgentlevel_id());
										goods = new Goods();
										goods.setGoods_id(ordergoods.getGoods_id());
										goods.setBrand_id(order.getBrand_id());
										goods.setAccount_id(order.getAccount_id());
										goods = goodsMapper.queryGoodsById(goods);
										if(goods!=null){
											ordergoods.setGoods_name(goods.getGoods_name());
											ordergoods.setGoods_costprice(goods.getGoods_price());
										}else{
											faillist.add(ordergoods.getGoods_id());
											continue;
										}
										if(order.getAgentlevel_id() == 0){
											ordergoods.setGoodsprice_id(0);
											ordergoods.setGoods_price(goods.getGoods_price());
											ordergoods.setOrdergoods_sales(ordergoods.getGoods_price() * ordergoods.getGoods_num());
											ordergoods.setOrdergoods_cost(ordergoods.getGoods_costprice() * ordergoods.getGoods_num());
											ordergoods.setOrdergoods_profit(0f);
											order_goodssales += ordergoods.getOrdergoods_sales().floatValue();
											order_goodscost += ordergoods.getOrdergoods_cost().floatValue();
										}else{
											goodsprice = new GoodsPrice();
											goodsprice.setAgentlevel_id(order.getAgentlevel_id());
											goodsprice.setGoods_id(ordergoods.getGoods_id());
											goodsprice.setAccount_id(order.getAccount_id());
											goodsprice = goodsMapper.queryGoodsPriceById(goodsprice);
											if(goodsprice!=null){
												ordergoods.setGoodsprice_id(goodsprice.getGoodsprice_id());
												ordergoods.setGoods_price(goodsprice.getGoods_price());
												ordergoods.setOrdergoods_sales(ordergoods.getGoods_price().floatValue() * ordergoods.getGoods_num().intValue());
												ordergoods.setOrdergoods_cost(ordergoods.getGoods_costprice().floatValue() * ordergoods.getGoods_num().intValue());
												ordergoods.setOrdergoods_profit(ordergoods.getOrdergoods_sales().floatValue() - ordergoods.getOrdergoods_cost().floatValue());
												order_goodssales += ordergoods.getOrdergoods_sales().floatValue();
												order_goodscost += ordergoods.getOrdergoods_cost().floatValue();
												order_goodsprofit += ordergoods.getOrdergoods_profit().floatValue();
											}else{
												faillist.add(ordergoods.getGoods_id());
												continue;
											}
										}
										goods_count = goods_count + ordergoods.getGoods_num().intValue();
										ordergoodslist.add(ordergoods);
									}catch(Exception e){
										faillist.add(ordergoods.getGoods_id());
									}
								}
							}
						}
						if(ordergoodslist!=null && !ordergoodslist.isEmpty()){
							Order insertorder = new Order();
							insertorder.setOrder_type(order.getOrder_type());
							insertorder.setGoods_count(goods_count);
							if(order.getOrder_premium() == null){
								order.setOrder_premium(0f);
							}
							if(order.getOrder_type() == 1){
								if(order.getOrder_premium() > 0){
									insertorder.setOrder_sales(order_goodssales + order.getOrder_premium().floatValue());
									insertorder.setOrder_cost(order_goodscost + order.getOrder_premium().floatValue());
								}else if(order.getOrder_premium() < 0){
									insertorder.setOrder_sales(order_goodssales + order.getOrder_premium().floatValue());
									insertorder.setOrder_cost(order_goodscost);
								}else{
									insertorder.setOrder_sales(order_goodssales);
									insertorder.setOrder_cost(order_goodscost);
								}
							}else{
								insertorder.setOrder_sales(order_goodssales + order.getOrder_premium().floatValue());
								insertorder.setOrder_cost(order_goodscost);
							}
							insertorder.setOrder_profit(insertorder.getOrder_sales().floatValue() - insertorder.getOrder_cost().floatValue());
							insertorder.setOrder_premium(order.getOrder_premium());
							insertorder.setOrder_goodssales(order_goodssales);
							insertorder.setOrder_goodscost(order_goodscost);
							insertorder.setOrder_goodsprofit(order_goodsprofit);
							insertorder.setAccount_id(order.getAccount_id());
							insertorder.setPremium_id(0);
							insertorder.setPremium_name(PropertiesUtil.getKeyValue("defaultph"));
							insertorder.setBrand_names(brand_name);
							if(order.getCustomer_id()!=null && order.getCustomer_id() > 0){
								Customer customer = new Customer();
								customer.setCustomer_id(order.getCustomer_id());
								customer.setAccount_id(order.getAccount_id());
								customer = customerMapper.queryCustomerById(customer);
								if(customer!=null){
									insertorder.setCustomer_id(order.getCustomer_id());
									insertorder.setCustomer_username(customer.getCustomer_username());
									insertorder.setCustomer_icon(customer.getCustomer_icon());
									if(order.getCustomer_phoneid()!=null && order.getCustomer_phoneid() > 0){
										Detail detailphone = new Detail();
										detailphone.setDetail_id(order.getCustomer_phoneid());
										detailphone.setDetail_type(1);
										detailphone.setCustomer_id(order.getCustomer_id());
										detailphone.setAccount_id(order.getAccount_id());
										String phone = customerMapper.queryDetailById(detailphone);
										if(StringUtil.isValid(phone)){
											insertorder.setCustomer_phone(phone);
											insertorder.setCustomer_phoneid(order.getCustomer_phoneid());
										}
									}
									if(order.getCustomer_addressid()!=null && order.getCustomer_addressid() > 0){
										Detail detailaddress = new Detail();
										detailaddress.setDetail_id(order.getCustomer_addressid());
										detailaddress.setDetail_type(2);
										detailaddress.setCustomer_id(order.getCustomer_id());
										detailaddress.setAccount_id(order.getAccount_id());
										String address = customerMapper.queryDetailById(detailaddress);
										if(StringUtil.isValid(address)){
											insertorder.setCustomer_address(address);
											insertorder.setCustomer_addressid(order.getCustomer_addressid());
										}
									}
									insertorder.setCreate_time(new Date());
									insertorder.setUpdate_time(new Date());
									insertorder.setState(1);
									if(orderMapper.insertOrder(insertorder) > 0){
										for(OrderGoods ordergoods:ordergoodslist){
											ordergoods.setOrder_id(insertorder.getOrder_id());
											ordergoods.setCreate_time(new Date());
											ordergoods.setUpdate_time(new Date());
											ordergoods.setState(1);
											orderMapper.insertOrderGoods(ordergoods);
										}
										goodsService.changeOrderGoodsStock(ordergoodslist,order.getOrder_type());
										result.setOrder(insertorder);
										result.setOrdergoodslist(ordergoodslist);
										result.setFaillist(faillist);
										result.setCode(0);
										result.setMsg("下单成功");
									}else{
										result.setCode(1);
										result.setMsg("下单失败");
									}
								}else{
									result.setCode(1);
									result.setMsg("客户不存在，下单失败");
								}
							}else if(order.getCustomer_id()==null || order.getCustomer_id() <= 0){
								insertorder.setCustomer_id(0);
								insertorder.setCustomer_username("");
								insertorder.setCustomer_icon("");
								insertorder.setCustomer_phone("");
								insertorder.setCustomer_phoneid(0);
								insertorder.setCustomer_address("");
								insertorder.setCustomer_addressid(0);
								insertorder.setCreate_time(new Date());
								insertorder.setUpdate_time(new Date());
								if(order.getOrder_type() == 1){
									insertorder.setState(0);
								}else{
									insertorder.setState(1);
								}
								if(orderMapper.insertOrder(insertorder) > 0){
									for(OrderGoods ordergoods:ordergoodslist){
										ordergoods.setOrder_id(insertorder.getOrder_id());
										ordergoods.setCreate_time(new Date());
										ordergoods.setUpdate_time(new Date());
										ordergoods.setState(1);
										orderMapper.insertOrderGoods(ordergoods);
									}
									goodsService.changeOrderGoodsStock(ordergoodslist,order.getOrder_type());
									result.setOrder(insertorder);
									result.setOrdergoodslist(ordergoodslist);
									result.setFaillist(faillist);
									result.setCode(0);
									result.setMsg("下单成功");
								}else{
									result.setCode(1);
									result.setMsg("下单失败");
								}
							}else{
								result.setCode(1);
								result.setMsg("客户资料录入不全，下单失败");
							}
						}else{
							result.setCode(1);
							result.setMsg("未选择商品，下单失败");
						}
					}else{
						result.setMsg("代理层级不存在，下单失败");
					}
				}else{
					result.setCode(1);
					result.setMsg("品牌不存在，下单失败");
				}
			}else{
				result.setCode(1);
				result.setMsg("下单失败");
			}
		}catch(Exception e){
			result = new OrderResult();
			result.setCode(1);
			result.setMsg("下单失败");
		}
		return result;
	}
	
	public OrderResult insertMoreOrder(InsertMoreOrder order){
		OrderResult result = new OrderResult();
		try{
			if(order!=null && order.getAccount_id()!=null && order.getAccount_id() > 0 && StringUtil.isValid(order.getGoodslist())){
				List<OrderGoods> ordergoodslist = new ArrayList<OrderGoods>();
				float order_goodssales = 0f;
				float order_goodscost = 0f;
				float order_goodsprofit = 0f;
				int goods_count = 0;
				Set<String> brandlist = new HashSet<String>();
				List<Integer> faillist = new ArrayList<Integer>();
				String[] goodss = order.getGoodslist().split("\\|");
				if(goodss!=null){
					String[] goodsarray = null;
					Goods goods = null;
					GoodsPrice goodsprice = null;
					OrderGoods ordergoods = null;
					for(String goodssstr:goodss){
						goodsarray = goodssstr.split(",");
						if(goodsarray!=null && goodsarray.length == 4){
							try{
								ordergoods = new OrderGoods();
								ordergoods.setAccount_id(order.getAccount_id());
								ordergoods.setGoods_id(Integer.parseInt(goodsarray[2]));
								ordergoods.setGoods_num(Integer.parseInt(goodsarray[3]));
								if(order.getOrder_type() == 1){
									ordergoods.setGoods_cnum(-ordergoods.getGoods_num().intValue());
								}else{
									ordergoods.setGoods_cnum(ordergoods.getGoods_num().intValue());
								}
								ordergoods.setBrand_id(Integer.parseInt(goodsarray[0]));
								Brand brand = new Brand();
								brand.setBrand_id(ordergoods.getBrand_id());
								brand.setAccount_id(order.getAccount_id());
								String brand_name = goodsMapper.queryBrandById(brand);
								if(StringUtil.isValid(brand_name)){
									ordergoods.setBrand_name(brand_name);
									ordergoods.setAgentlevel_id(Integer.parseInt(goodsarray[1]));
									String agentlevel_name = null;
									if(order.getOrder_type() == 2){
										agentlevel_name = "进货价";
										ordergoods.setAgentlevel_id(0);
									}else{
										if(ordergoods.getAgentlevel_id() == 0){
											agentlevel_name = "按成本价出售";
										}else{
											AgentLevel agentlevel = new AgentLevel();
											agentlevel.setAgentlevel_id(ordergoods.getAgentlevel_id());
											agentlevel.setBrand_id(ordergoods.getBrand_id());
											agentlevel.setAccount_id(order.getAccount_id());
											agentlevel_name = goodsMapper.queryAgentLevelById(agentlevel);
										}
									}
									if(StringUtil.isValid(agentlevel_name)){
										ordergoods.setAgentlevel_name(agentlevel_name);
										goods = new Goods();
										goods.setGoods_id(ordergoods.getGoods_id());
										goods.setBrand_id(ordergoods.getBrand_id());
										goods.setAccount_id(order.getAccount_id());
										goods = goodsMapper.queryGoodsById(goods);
										if(goods!=null){
											ordergoods.setGoods_name(goods.getGoods_name());
											ordergoods.setGoods_costprice(goods.getGoods_price());
										}else{
											faillist.add(ordergoods.getGoods_id());
											continue;
										}
										if(ordergoods.getAgentlevel_id() == 0){
											ordergoods.setGoodsprice_id(0);
											ordergoods.setGoods_price(goods.getGoods_price());
											ordergoods.setOrdergoods_sales(ordergoods.getGoods_price() * ordergoods.getGoods_num());
											ordergoods.setOrdergoods_cost(ordergoods.getGoods_costprice() * ordergoods.getGoods_num());
											ordergoods.setOrdergoods_profit(0f);
											order_goodssales += ordergoods.getOrdergoods_sales().floatValue();
											order_goodscost += ordergoods.getOrdergoods_cost().floatValue();
										}else{
											goodsprice = new GoodsPrice();
											goodsprice.setAgentlevel_id(ordergoods.getAgentlevel_id());
											goodsprice.setGoods_id(ordergoods.getGoods_id());
											goodsprice.setAccount_id(order.getAccount_id());
											goodsprice = goodsMapper.queryGoodsPriceById(goodsprice);
											if(goodsprice!=null){
												ordergoods.setGoodsprice_id(goodsprice.getGoodsprice_id());
												ordergoods.setGoods_price(goodsprice.getGoods_price());
												ordergoods.setOrdergoods_sales(ordergoods.getGoods_price().floatValue() * ordergoods.getGoods_num().intValue());
												ordergoods.setOrdergoods_cost(ordergoods.getGoods_costprice().floatValue() * ordergoods.getGoods_num().intValue());
												ordergoods.setOrdergoods_profit(ordergoods.getOrdergoods_sales().floatValue() - ordergoods.getOrdergoods_cost().floatValue());
												order_goodssales += ordergoods.getOrdergoods_sales().floatValue();
												order_goodscost += ordergoods.getOrdergoods_cost().floatValue();
												order_goodsprofit += ordergoods.getOrdergoods_profit().floatValue();
											}else{
												faillist.add(ordergoods.getGoods_id());
												continue;
											}
										}
										goods_count = goods_count + ordergoods.getGoods_num().intValue();
										brandlist.add(ordergoods.getBrand_name());
										ordergoodslist.add(ordergoods);
									}else{
										faillist.add(ordergoods.getGoods_id());
									}
								}else{
									faillist.add(ordergoods.getGoods_id());
								}
							}catch(Exception e){
								faillist.add(ordergoods.getGoods_id());
							}
						}
					}
				}
				if(ordergoodslist!=null && !ordergoodslist.isEmpty()){
					Order insertorder = new Order();
					insertorder.setOrder_type(order.getOrder_type());
					insertorder.setGoods_count(goods_count);
					if(order.getOrder_premium() == null){
						order.setOrder_premium(0f);
					}
					if(order.getOrder_type() == 1){
						if(order.getOrder_premium() > 0){
							insertorder.setOrder_sales(order_goodssales + order.getOrder_premium().floatValue());
							insertorder.setOrder_cost(order_goodscost + order.getOrder_premium().floatValue());
						}else if(order.getOrder_premium() < 0){
							insertorder.setOrder_sales(order_goodssales + order.getOrder_premium().floatValue());
							insertorder.setOrder_cost(order_goodscost);
						}else{
							insertorder.setOrder_sales(order_goodssales);
							insertorder.setOrder_cost(order_goodscost);
						}
					}else{
						insertorder.setOrder_sales(order_goodssales + order.getOrder_premium().floatValue());
						insertorder.setOrder_cost(order_goodscost);
					}
					insertorder.setOrder_profit(insertorder.getOrder_sales().floatValue() - insertorder.getOrder_cost().floatValue());
					insertorder.setOrder_premium(order.getOrder_premium());
					insertorder.setOrder_goodssales(order_goodssales);
					insertorder.setOrder_goodscost(order_goodscost);
					insertorder.setOrder_goodsprofit(order_goodsprofit);
					insertorder.setAccount_id(order.getAccount_id());
					if(order.getPremium_id()!=null && order.getPremium_id() > 0){
						insertorder.setPremium_id(order.getPremium_id());
					}else{
						insertorder.setPremium_id(0);
					}
					if(StringUtil.isValid(order.getPremium_name())){
						insertorder.setPremium_name(order.getPremium_name());
					}else{
						insertorder.setPremium_name(PropertiesUtil.getKeyValue("defaultph"));
					}
					String brand_names = "";
					if(brandlist!=null && !brandlist.isEmpty()){
						for(String name:brandlist){
							brand_names = brand_names + "、" + name;
						}
						brand_names = brand_names.substring(1);
					}
					insertorder.setBrand_names(brand_names);
					if(order.getCustomer_id()!=null && order.getCustomer_id() > 0){
						Customer customer = new Customer();
						customer.setCustomer_id(order.getCustomer_id());
						customer.setAccount_id(order.getAccount_id());
						customer = customerMapper.queryCustomerById(customer);
						if(customer!=null){
							insertorder.setCustomer_id(order.getCustomer_id());
							insertorder.setCustomer_username(customer.getCustomer_username());
							insertorder.setCustomer_icon(customer.getCustomer_icon());
							if(order.getCustomer_phoneid()!=null && order.getCustomer_phoneid() > 0){
								Detail detailphone = new Detail();
								detailphone.setDetail_id(order.getCustomer_phoneid());
								detailphone.setDetail_type(1);
								detailphone.setCustomer_id(order.getCustomer_id());
								detailphone.setAccount_id(order.getAccount_id());
								String phone = customerMapper.queryDetailById(detailphone);
								if(StringUtil.isValid(phone)){
									insertorder.setCustomer_phone(phone);
									insertorder.setCustomer_phoneid(order.getCustomer_phoneid());
								}
							}
							if(order.getCustomer_addressid()!=null && order.getCustomer_addressid() > 0){
								Detail detailaddress = new Detail();
								detailaddress.setDetail_id(order.getCustomer_addressid());
								detailaddress.setDetail_type(2);
								detailaddress.setCustomer_id(order.getCustomer_id());
								detailaddress.setAccount_id(order.getAccount_id());
								String address = customerMapper.queryDetailById(detailaddress);
								if(StringUtil.isValid(address)){
									insertorder.setCustomer_address(address);
									insertorder.setCustomer_addressid(order.getCustomer_addressid());
								}
							}
							insertorder.setCreate_time(new Date());
							insertorder.setUpdate_time(new Date());
							insertorder.setState(1);
							if(orderMapper.insertOrder(insertorder) > 0){
								for(OrderGoods ordergoods:ordergoodslist){
									ordergoods.setOrder_id(insertorder.getOrder_id());
									ordergoods.setCreate_time(new Date());
									ordergoods.setUpdate_time(new Date());
									ordergoods.setState(1);
									orderMapper.insertOrderGoods(ordergoods);
								}
								goodsService.changeOrderGoodsStock(ordergoodslist,order.getOrder_type());
								result.setOrder(insertorder);
								result.setOrdergoodslist(ordergoodslist);
								result.setFaillist(faillist);
								result.setCode(0);
								result.setMsg("下单成功");
							}else{
								result.setCode(1);
								result.setMsg("下单失败");
							}
						}else{
							result.setCode(1);
							result.setMsg("客户不存在，下单失败");
						}
					}else if(order.getCustomer_id()==null || order.getCustomer_id() <= 0){
						insertorder.setCustomer_id(0);
						insertorder.setCustomer_username("");
						insertorder.setCustomer_icon("");
						insertorder.setCustomer_phone("");
						insertorder.setCustomer_phoneid(0);
						insertorder.setCustomer_address("");
						insertorder.setCustomer_addressid(0);
						insertorder.setCreate_time(new Date());
						insertorder.setUpdate_time(new Date());
						if(order.getOrder_type() == 1){
							insertorder.setState(0);
						}else{
							insertorder.setState(1);
						}
						if(orderMapper.insertOrder(insertorder) > 0){
							for(OrderGoods ordergoods:ordergoodslist){
								ordergoods.setOrder_id(insertorder.getOrder_id());
								ordergoods.setCreate_time(new Date());
								ordergoods.setUpdate_time(new Date());
								ordergoods.setState(1);
								orderMapper.insertOrderGoods(ordergoods);
							}
							goodsService.changeOrderGoodsStock(ordergoodslist,order.getOrder_type());
							result.setOrder(insertorder);
							result.setOrdergoodslist(ordergoodslist);
							result.setFaillist(faillist);
							result.setCode(0);
							result.setMsg("下单成功");
						}else{
							result.setCode(1);
							result.setMsg("下单失败");
						}
					}else{
						result.setCode(1);
						result.setMsg("客户资料录入不全，下单失败");
					}
				}else{
					result.setCode(1);
					result.setMsg("未选择商品，下单失败");
				}
			}else{
				result.setCode(1);
				result.setMsg("下单失败");
			}
		}catch(Exception e){
			result = new OrderResult();
			result.setCode(1);
			result.setMsg("下单失败");
		}
		return result;
	}
	
	public OrderResult updateOrder(UpdateOrder order){
		OrderResult result = new OrderResult();
		try{
			if(order!=null && order.getOrder_id()!=null && order.getOrder_id() > 0 && order.getAccount_id()!=null && order.getAccount_id() > 0){
				List<OrderGoods> ordergoodslist = new ArrayList<OrderGoods>();
				float order_goodssales = 0f;
				float order_goodscost = 0f;
				float order_goodsprofit = 0f;
				int goods_count = 0;
				boolean flag = true;
				if(StringUtil.isValid(order.getGoodslist())){
					String[] goodss = order.getGoodslist().split("\\|");
					if(goodss!=null){
						String[] goodsarray = null;
						OrderGoods ordergoods = null;
						int oldnum = 0;
						int newnum = 0;
						for(String goodssstr:goodss){
							goodsarray = goodssstr.split(",");
							if(goodsarray!=null && goodsarray.length == 2){
								try{
									ordergoods = new OrderGoods();
									ordergoods.setOrdergoods_id(Integer.parseInt(goodsarray[0]));
									ordergoods.setOrder_id(order.getOrder_id());
									ordergoods.setAccount_id(order.getAccount_id());
									ordergoods = orderMapper.queryOrderGoodsById(ordergoods);
									if(ordergoods!=null){
										oldnum = ordergoods.getGoods_num().intValue();
										newnum = Integer.parseInt(goodsarray[1]);
										if(oldnum != newnum){
											ordergoods.setOrdergoods_id(Integer.parseInt(goodsarray[0]));
											ordergoods.setGoods_num(newnum);
											ordergoods.setOrdergoods_sales(ordergoods.getGoods_price().floatValue() * newnum);
											ordergoods.setOrdergoods_cost(ordergoods.getGoods_costprice().floatValue() * newnum);
											ordergoods.setOrdergoods_profit(ordergoods.getOrdergoods_sales().floatValue() - ordergoods.getOrdergoods_cost().floatValue());
											order_goodssales += (ordergoods.getGoods_price().floatValue() * (newnum - oldnum));
											order_goodscost += (ordergoods.getGoods_costprice().floatValue() * (newnum - oldnum));
											order_goodsprofit += ((ordergoods.getGoods_price().floatValue() - ordergoods.getGoods_costprice().floatValue()) * (newnum - oldnum));
											if(order.getOrder_type() == 1){
												ordergoods.setGoods_cnum(oldnum - newnum);
											}else{
												ordergoods.setGoods_cnum(newnum - oldnum);
											}
											goods_count += (newnum - oldnum);
											ordergoodslist.add(ordergoods);
										}
									}else{
										flag = false;
										break;
									}
								}catch(Exception e){
								}
							}
						}
					}
				}
				if(flag){
					Order updateorder = new Order();
					updateorder.setOrder_id(order.getOrder_id());
					updateorder.setOrder_type(order.getOrder_type());
					updateorder.setAccount_id(order.getAccount_id());
					updateorder = orderMapper.queryOrderByIdAndType(updateorder);
					if(updateorder!=null){
						if(order.getOrder_premium()!=null){
							if(order.getOrder_premium().compareTo(updateorder.getOrder_premium()) != 0){
								updateorder.setOrder_premium(order.getOrder_premium());
								if(order_goodssales != 0f || order_goodscost != 0f || order_goodsprofit != 0f){
									updateorder.setOrder_goodssales(updateorder.getOrder_goodssales().floatValue() + order_goodssales);
									updateorder.setOrder_goodscost(updateorder.getOrder_goodscost().floatValue() + order_goodscost);
									updateorder.setOrder_goodsprofit(updateorder.getOrder_goodsprofit().floatValue() + order_goodsprofit);
									if(order.getOrder_type() == 1){
										if(order.getOrder_premium() > 0){
											updateorder.setOrder_sales(updateorder.getOrder_goodssales().floatValue() + order.getOrder_premium().floatValue());
											updateorder.setOrder_cost(updateorder.getOrder_goodscost().floatValue() + order.getOrder_premium().floatValue());
										}else if(order.getOrder_premium() < 0){
											updateorder.setOrder_sales(updateorder.getOrder_goodssales() + order.getOrder_premium().floatValue());
											updateorder.setOrder_cost(updateorder.getOrder_goodscost().floatValue());
										}else{
											updateorder.setOrder_sales(updateorder.getOrder_goodssales());
											updateorder.setOrder_cost(updateorder.getOrder_goodscost());
										}
									}else{
										updateorder.setOrder_sales(updateorder.getOrder_goodssales().floatValue() + order.getOrder_premium().floatValue());
										updateorder.setOrder_cost(updateorder.getOrder_goodscost());
									}
									updateorder.setOrder_profit(updateorder.getOrder_sales().floatValue() - updateorder.getOrder_cost().floatValue());
								}else{
									if(order.getOrder_type() == 1){
										if(order.getOrder_premium() > 0){
											updateorder.setOrder_sales(updateorder.getOrder_goodssales().floatValue() + order.getOrder_premium().floatValue());
											updateorder.setOrder_cost(updateorder.getOrder_goodscost().floatValue() + order.getOrder_premium().floatValue());
										}else if(order.getOrder_premium() < 0){
											updateorder.setOrder_sales(updateorder.getOrder_goodssales() + order.getOrder_premium().floatValue());
											updateorder.setOrder_cost(updateorder.getOrder_goodscost().floatValue());
										}else{
											updateorder.setOrder_sales(updateorder.getOrder_goodssales());
											updateorder.setOrder_cost(updateorder.getOrder_goodscost());
										}
									}else{
										updateorder.setOrder_sales(updateorder.getOrder_goodssales().floatValue() + order.getOrder_premium().floatValue());
										updateorder.setOrder_cost(updateorder.getOrder_goodscost());
									}
									updateorder.setOrder_profit(updateorder.getOrder_sales().floatValue() - updateorder.getOrder_cost().floatValue());
									updateorder.setOrder_goodssales(null);
									updateorder.setOrder_goodscost(null);
									updateorder.setOrder_goodsprofit(null);
								}
							}else{
								if(order_goodssales != 0f || order_goodscost != 0f || order_goodsprofit != 0f){
									updateorder.setOrder_sales(updateorder.getOrder_sales().floatValue() + order_goodssales);
									updateorder.setOrder_cost(updateorder.getOrder_cost().floatValue() + order_goodscost);
									updateorder.setOrder_profit(updateorder.getOrder_profit().floatValue() + order_goodsprofit);
									updateorder.setOrder_premium(null);
									updateorder.setOrder_goodssales(updateorder.getOrder_goodssales().floatValue() + order_goodssales);
									updateorder.setOrder_goodscost(updateorder.getOrder_goodscost().floatValue() + order_goodscost);
									updateorder.setOrder_goodsprofit(updateorder.getOrder_goodsprofit().floatValue() + order_goodsprofit);
								}else{
									updateorder.setOrder_sales(null);
									updateorder.setOrder_cost(null);
									updateorder.setOrder_profit(null);
									updateorder.setOrder_premium(null);
									updateorder.setOrder_goodssales(null);
									updateorder.setOrder_goodscost(null);
									updateorder.setOrder_goodsprofit(null);
								}
							}
						}else{
							if(order_goodssales != 0f || order_goodscost != 0f || order_goodsprofit != 0f){
								updateorder.setOrder_sales(updateorder.getOrder_sales().floatValue() + order_goodssales);
								updateorder.setOrder_cost(updateorder.getOrder_cost().floatValue() + order_goodscost);
								updateorder.setOrder_profit(updateorder.getOrder_profit().floatValue() + order_goodsprofit);
								updateorder.setOrder_premium(null);
								updateorder.setOrder_goodssales(updateorder.getOrder_goodssales().floatValue() + order_goodssales);
								updateorder.setOrder_goodscost(updateorder.getOrder_goodscost().floatValue() + order_goodscost);
								updateorder.setOrder_goodsprofit(updateorder.getOrder_goodsprofit().floatValue() + order_goodsprofit);
							}else{
								updateorder.setOrder_sales(null);
								updateorder.setOrder_cost(null);
								updateorder.setOrder_profit(null);
								updateorder.setOrder_premium(null);
								updateorder.setOrder_goodssales(null);
								updateorder.setOrder_goodscost(null);
								updateorder.setOrder_goodsprofit(null);
							}
						}
						if(order.getCustomer_id()!=null && order.getCustomer_id() > 0){
							Customer customer = new Customer();
							customer.setCustomer_id(order.getCustomer_id());
							customer.setAccount_id(order.getAccount_id());
							customer = customerMapper.queryCustomerById(customer);
							if(customer!=null){
								updateorder.setCustomer_id(order.getCustomer_id());
								updateorder.setCustomer_username(customer.getCustomer_username());
								updateorder.setCustomer_icon(customer.getCustomer_icon());
								if(order.getCustomer_phoneid()!=null && order.getCustomer_phoneid() > 0){
									Detail detailphone = new Detail();
									detailphone.setDetail_id(order.getCustomer_phoneid());
									detailphone.setDetail_type(1);
									detailphone.setCustomer_id(order.getCustomer_id());
									detailphone.setAccount_id(order.getAccount_id());
									String phone = customerMapper.queryDetailById(detailphone);
									if(StringUtil.isValid(phone)){
										updateorder.setCustomer_phone(phone);
										updateorder.setCustomer_phoneid(order.getCustomer_phoneid());
									}
								}
								if(order.getCustomer_addressid()!=null && order.getCustomer_addressid() > 0){
									Detail detailaddress = new Detail();
									detailaddress.setDetail_id(order.getCustomer_addressid());
									detailaddress.setDetail_type(2);
									detailaddress.setCustomer_id(order.getCustomer_id());
									detailaddress.setAccount_id(order.getAccount_id());
									String address = customerMapper.queryDetailById(detailaddress);
									if(StringUtil.isValid(address)){
										updateorder.setCustomer_address(address);
										updateorder.setCustomer_addressid(order.getCustomer_addressid());
									}
								}
								if(goods_count!=0){
									updateorder.setGoods_count(updateorder.getGoods_count() + goods_count);
								}else{
									updateorder.setGoods_count(null);
								}
								updateorder.setState(1);
								if(orderMapper.updateOrder(updateorder) > 0){
									if(ordergoodslist!=null && !ordergoodslist.isEmpty()){
										for(OrderGoods ordergoods:ordergoodslist){
											orderMapper.updateOrderGoods(ordergoods);
										}
									}
									goodsService.changeOrderGoodsStock(ordergoodslist,order.getOrder_type());
									result.setOrder(updateorder);
									result.setOrdergoodslist(ordergoodslist);
									result.setCode(0);
									result.setMsg("修改订单成功");
								}else{
									result.setCode(1);
									result.setMsg("修改订单失败");
								}
							}else{
								result.setCode(1);
								result.setMsg("客户不存在，下单失败");
							}
						}else if(order.getCustomer_id()==null || order.getCustomer_id() <= 0){
							if(goods_count!=0){
								updateorder.setGoods_count(updateorder.getGoods_count() + goods_count);
							}else{
								updateorder.setGoods_count(null);
							}
							updateorder.setState(null);
							if(orderMapper.updateOrder(updateorder) > 0){
								if(ordergoodslist!=null && !ordergoodslist.isEmpty()){
									for(OrderGoods ordergoods:ordergoodslist){
										orderMapper.updateOrderGoods(ordergoods);
									}
								}
								goodsService.changeOrderGoodsStock(ordergoodslist,order.getOrder_type());
								result.setOrder(updateorder);
								result.setOrdergoodslist(ordergoodslist);
								result.setCode(0);
								result.setMsg("修改订单成功");
							}else{
								result.setCode(1);
								result.setMsg("修改订单失败");
							}
						}else{
							result.setCode(1);
							result.setMsg("客户资料录入不全，修改订单失败");
						}
					}else{
						result.setCode(1);
						result.setMsg("修改订单失败");
					}
				}else{
					result.setCode(1);
					result.setMsg("修改订单失败");
				}
			}else{
				result.setCode(1);
				result.setMsg("修改订单失败");
			}
		}catch(Exception e){
			result = new OrderResult();
			result.setCode(1);
			result.setMsg("修改订单失败");
		}
		return result;
	}
	
	public OrderResult updateMoreOrder(UpdateMoreOrder order){
		OrderResult result = new OrderResult();
		try{
			if(order!=null && order.getOrder_id()!=null && order.getOrder_id() > 0 && order.getAccount_id()!=null && order.getAccount_id() > 0){
				List<OrderGoods> ordergoodslist = new ArrayList<OrderGoods>();
				float order_goodssales = 0f;
				float order_goodscost = 0f;
				float order_goodsprofit = 0f;
				int goods_count = 0;
				boolean flag = true;
				if(StringUtil.isValid(order.getOldgoodslist())){
					String[] goodss = order.getOldgoodslist().split("\\|");
					if(goodss!=null){
						String[] goodsarray = null;
						OrderGoods ordergoods = null;
						int oldnum = 0;
						int newnum = 0;
						for(String goodssstr:goodss){
							goodsarray = goodssstr.split(",");
							if(goodsarray!=null && goodsarray.length == 2){
								try{
									ordergoods = new OrderGoods();
									ordergoods.setOrdergoods_id(Integer.parseInt(goodsarray[0]));
									ordergoods.setOrder_id(order.getOrder_id());
									ordergoods.setAccount_id(order.getAccount_id());
									ordergoods = orderMapper.queryOrderGoodsById(ordergoods);
									if(ordergoods!=null){
										oldnum = ordergoods.getGoods_num().intValue();
										newnum = Integer.parseInt(goodsarray[1]);
										if(oldnum != newnum){
											ordergoods.setOrdergoods_id(Integer.parseInt(goodsarray[0]));
											ordergoods.setGoods_num(newnum);
											ordergoods.setOrdergoods_sales(ordergoods.getGoods_price().floatValue() * newnum);
											ordergoods.setOrdergoods_cost(ordergoods.getGoods_costprice().floatValue() * newnum);
											ordergoods.setOrdergoods_profit(ordergoods.getOrdergoods_sales().floatValue() - ordergoods.getOrdergoods_cost().floatValue());
											order_goodssales += (ordergoods.getGoods_price().floatValue() * (newnum - oldnum));
											order_goodscost += (ordergoods.getGoods_costprice().floatValue() * (newnum - oldnum));
											order_goodsprofit += ((ordergoods.getGoods_price().floatValue() - ordergoods.getGoods_costprice().floatValue()) * (newnum - oldnum));
											if(order.getOrder_type() == 1){
												ordergoods.setGoods_cnum(oldnum - newnum);
											}else{
												ordergoods.setGoods_cnum(newnum - oldnum);
											}
											goods_count += (newnum - oldnum);
											ordergoodslist.add(ordergoods);
										}
									}else{
										flag = false;
										break;
									}
								}catch(Exception e){
								}
							}
						}
					}
				}
				if(StringUtil.isValid(order.getNewgoodslist())){
					String[] goodss = order.getNewgoodslist().split("\\|");
					if(goodss!=null){
						String[] goodsarray = null;
						Goods goods = null;
						GoodsPrice goodsprice = null;
						OrderGoods ordergoods = null;
						for(String goodssstr:goodss){
							goodsarray = goodssstr.split(",");
							if(goodsarray!=null && goodsarray.length == 4){
								try{
									ordergoods = new OrderGoods();
									ordergoods.setOrder_id(order.getOrder_id());
									ordergoods.setAccount_id(order.getAccount_id());
									ordergoods.setGoods_id(Integer.parseInt(goodsarray[2]));
									if(orderMapper.checkOrderGoods(ordergoods) <= 0){
										ordergoods.setGoods_num(Integer.parseInt(goodsarray[3]));
										if(order.getOrder_type() == 1){
											ordergoods.setGoods_cnum(-ordergoods.getGoods_num().intValue());
										}else{
											ordergoods.setGoods_cnum(ordergoods.getGoods_num().intValue());
										}
										ordergoods.setBrand_id(Integer.parseInt(goodsarray[0]));
										Brand brand = new Brand();
										brand.setBrand_id(ordergoods.getBrand_id());
										brand.setAccount_id(order.getAccount_id());
										String brand_name = goodsMapper.queryBrandById(brand);
										if(StringUtil.isValid(brand_name)){
											ordergoods.setBrand_name(brand_name);
											ordergoods.setAgentlevel_id(Integer.parseInt(goodsarray[1]));
											String agentlevel_name = null;
											if(order.getOrder_type() == 2){
												agentlevel_name = "进货价";
												ordergoods.setAgentlevel_id(0);
											}else{
												if(ordergoods.getAgentlevel_id() == 0){
													agentlevel_name = "按成本价出售";
												}else{
													AgentLevel agentlevel = new AgentLevel();
													agentlevel.setAgentlevel_id(ordergoods.getAgentlevel_id());
													agentlevel.setBrand_id(ordergoods.getBrand_id());
													agentlevel.setAccount_id(order.getAccount_id());
													agentlevel_name = goodsMapper.queryAgentLevelById(agentlevel);
												}
											}
											if(StringUtil.isValid(agentlevel_name)){
												ordergoods.setAgentlevel_name(agentlevel_name);
												goods = new Goods();
												goods.setGoods_id(ordergoods.getGoods_id());
												goods.setBrand_id(ordergoods.getBrand_id());
												goods.setAccount_id(order.getAccount_id());
												goods = goodsMapper.queryGoodsById(goods);
												if(goods!=null){
													ordergoods.setGoods_name(goods.getGoods_name());
													ordergoods.setGoods_costprice(goods.getGoods_price());
												}else{
													flag = false;
													break;
												}
												if(ordergoods.getAgentlevel_id() == 0){
													ordergoods.setGoodsprice_id(0);
													ordergoods.setGoods_price(goods.getGoods_price());
													ordergoods.setOrdergoods_sales(ordergoods.getGoods_price() * ordergoods.getGoods_num());
													ordergoods.setOrdergoods_cost(ordergoods.getGoods_costprice() * ordergoods.getGoods_num());
													ordergoods.setOrdergoods_profit(0f);
													order_goodssales += ordergoods.getOrdergoods_sales().floatValue();
													order_goodscost += ordergoods.getOrdergoods_cost().floatValue();
												}else{
													goodsprice = new GoodsPrice();
													goodsprice.setAgentlevel_id(ordergoods.getAgentlevel_id());
													goodsprice.setGoods_id(ordergoods.getGoods_id());
													goodsprice.setAccount_id(order.getAccount_id());
													goodsprice = goodsMapper.queryGoodsPriceById(goodsprice);
													if(goodsprice!=null){
														ordergoods.setGoodsprice_id(goodsprice.getGoodsprice_id());
														ordergoods.setGoods_price(goodsprice.getGoods_price());
														ordergoods.setOrdergoods_sales(ordergoods.getGoods_price().floatValue() * ordergoods.getGoods_num().intValue());
														ordergoods.setOrdergoods_cost(ordergoods.getGoods_costprice().floatValue() * ordergoods.getGoods_num().intValue());
														ordergoods.setOrdergoods_profit(ordergoods.getOrdergoods_sales().floatValue() - ordergoods.getOrdergoods_cost().floatValue());
														order_goodssales += ordergoods.getOrdergoods_sales().floatValue();
														order_goodscost += ordergoods.getOrdergoods_cost().floatValue();
														order_goodsprofit += ordergoods.getOrdergoods_profit().floatValue();
													}else{
														flag = false;
														break;
													}
												}
												goods_count += ordergoods.getGoods_num().intValue();
												ordergoodslist.add(ordergoods);
											}else{
												flag = false;
												break;
											}
										}else{
											flag = false;
											break;
										}
									}else{
										flag = false;
										break;
									}
								}catch(Exception e){
									flag = false;
									break;
								}
							}
						}
					}
				}
				if(flag){
					Order updateorder = new Order();
					updateorder.setOrder_id(order.getOrder_id());
					updateorder.setOrder_type(order.getOrder_type());
					updateorder.setAccount_id(order.getAccount_id());
					updateorder = orderMapper.queryOrderByIdAndType(updateorder);
					if(updateorder!=null){
						if(order.getOrder_premium()!=null){
							if(order.getOrder_premium().compareTo(updateorder.getOrder_premium()) != 0){
								updateorder.setOrder_premium(order.getOrder_premium());
								if(order_goodssales != 0f || order_goodscost != 0f || order_goodsprofit != 0f){
									updateorder.setOrder_goodssales(updateorder.getOrder_goodssales().floatValue() + order_goodssales);
									updateorder.setOrder_goodscost(updateorder.getOrder_goodscost().floatValue() + order_goodscost);
									updateorder.setOrder_goodsprofit(updateorder.getOrder_goodsprofit().floatValue() + order_goodsprofit);
									if(order.getOrder_type() == 1){
										if(order.getOrder_premium() > 0){
											updateorder.setOrder_sales(updateorder.getOrder_goodssales().floatValue() + order.getOrder_premium().floatValue());
											updateorder.setOrder_cost(updateorder.getOrder_goodscost().floatValue() + order.getOrder_premium().floatValue());
										}else if(order.getOrder_premium() < 0){
											updateorder.setOrder_sales(updateorder.getOrder_goodssales() + order.getOrder_premium().floatValue());
											updateorder.setOrder_cost(updateorder.getOrder_goodscost().floatValue());
										}else{
											updateorder.setOrder_sales(updateorder.getOrder_goodssales());
											updateorder.setOrder_cost(updateorder.getOrder_goodscost());
										}
									}else{
										updateorder.setOrder_sales(updateorder.getOrder_goodssales().floatValue() + order.getOrder_premium().floatValue());
										updateorder.setOrder_cost(updateorder.getOrder_goodscost());
									}
									updateorder.setOrder_profit(updateorder.getOrder_sales().floatValue() - updateorder.getOrder_cost().floatValue());
								}else{
									if(order.getOrder_type() == 1){
										if(order.getOrder_premium() > 0){
											updateorder.setOrder_sales(updateorder.getOrder_goodssales().floatValue() + order.getOrder_premium().floatValue());
											updateorder.setOrder_cost(updateorder.getOrder_goodscost().floatValue() + order.getOrder_premium().floatValue());
										}else if(order.getOrder_premium() < 0){
											updateorder.setOrder_sales(updateorder.getOrder_goodssales() + order.getOrder_premium().floatValue());
											updateorder.setOrder_cost(updateorder.getOrder_goodscost().floatValue());
										}else{
											updateorder.setOrder_sales(updateorder.getOrder_goodssales());
											updateorder.setOrder_cost(updateorder.getOrder_goodscost());
										}
									}else{
										updateorder.setOrder_sales(updateorder.getOrder_goodssales().floatValue() + order.getOrder_premium().floatValue());
										updateorder.setOrder_cost(updateorder.getOrder_goodscost());
									}
									updateorder.setOrder_profit(updateorder.getOrder_sales().floatValue() - updateorder.getOrder_cost().floatValue());
									updateorder.setOrder_goodssales(null);
									updateorder.setOrder_goodscost(null);
									updateorder.setOrder_goodsprofit(null);
								}
							}else{
								if(order_goodssales != 0f || order_goodscost != 0f || order_goodsprofit != 0f){
									updateorder.setOrder_sales(updateorder.getOrder_sales().floatValue() + order_goodssales);
									updateorder.setOrder_cost(updateorder.getOrder_cost().floatValue() + order_goodscost);
									updateorder.setOrder_profit(updateorder.getOrder_profit().floatValue() + order_goodsprofit);
									updateorder.setOrder_premium(null);
									updateorder.setOrder_goodssales(updateorder.getOrder_goodssales().floatValue() + order_goodssales);
									updateorder.setOrder_goodscost(updateorder.getOrder_goodscost().floatValue() + order_goodscost);
									updateorder.setOrder_goodsprofit(updateorder.getOrder_goodsprofit().floatValue() + order_goodsprofit);
								}else{
									updateorder.setOrder_sales(null);
									updateorder.setOrder_cost(null);
									updateorder.setOrder_profit(null);
									updateorder.setOrder_premium(null);
									updateorder.setOrder_goodssales(null);
									updateorder.setOrder_goodscost(null);
									updateorder.setOrder_goodsprofit(null);
								}
							}
						}else{
							if(order_goodssales != 0f || order_goodscost != 0f || order_goodsprofit != 0f){
								updateorder.setOrder_sales(updateorder.getOrder_sales().floatValue() + order_goodssales);
								updateorder.setOrder_cost(updateorder.getOrder_cost().floatValue() + order_goodscost);
								updateorder.setOrder_profit(updateorder.getOrder_profit().floatValue() + order_goodsprofit);
								updateorder.setOrder_premium(null);
								updateorder.setOrder_goodssales(updateorder.getOrder_goodssales().floatValue() + order_goodssales);
								updateorder.setOrder_goodscost(updateorder.getOrder_goodscost().floatValue() + order_goodscost);
								updateorder.setOrder_goodsprofit(updateorder.getOrder_goodsprofit().floatValue() + order_goodsprofit);
							}else{
								updateorder.setOrder_sales(null);
								updateorder.setOrder_cost(null);
								updateorder.setOrder_profit(null);
								updateorder.setOrder_premium(null);
								updateorder.setOrder_goodssales(null);
								updateorder.setOrder_goodscost(null);
								updateorder.setOrder_goodsprofit(null);
							}
						}
						if(order.getCustomer_id()!=null && order.getCustomer_id() > 0){
							Customer customer = new Customer();
							customer.setCustomer_id(order.getCustomer_id());
							customer.setAccount_id(order.getAccount_id());
							customer = customerMapper.queryCustomerById(customer);
							if(customer!=null){
								updateorder.setCustomer_id(order.getCustomer_id());
								updateorder.setCustomer_username(customer.getCustomer_username());
								updateorder.setCustomer_icon(customer.getCustomer_icon());
								if(order.getCustomer_phoneid()!=null && order.getCustomer_phoneid() > 0){
									Detail detailphone = new Detail();
									detailphone.setDetail_id(order.getCustomer_phoneid());
									detailphone.setDetail_type(1);
									detailphone.setCustomer_id(order.getCustomer_id());
									detailphone.setAccount_id(order.getAccount_id());
									String phone = customerMapper.queryDetailById(detailphone);
									if(StringUtil.isValid(phone)){
										updateorder.setCustomer_phone(phone);
										updateorder.setCustomer_phoneid(order.getCustomer_phoneid());
									}
								}
								if(order.getCustomer_addressid()!=null && order.getCustomer_addressid() > 0){
									Detail detailaddress = new Detail();
									detailaddress.setDetail_id(order.getCustomer_addressid());
									detailaddress.setDetail_type(2);
									detailaddress.setCustomer_id(order.getCustomer_id());
									detailaddress.setAccount_id(order.getAccount_id());
									String address = customerMapper.queryDetailById(detailaddress);
									if(StringUtil.isValid(address)){
										updateorder.setCustomer_address(address);
										updateorder.setCustomer_addressid(order.getCustomer_addressid());
									}
								}
								if(goods_count!=0){
									updateorder.setGoods_count(updateorder.getGoods_count() + goods_count);
								}else{
									updateorder.setGoods_count(null);
								}
								updateorder.setState(1);
								if(order.getPremium_id()!=null && order.getPremium_id() > 0){
									updateorder.setPremium_id(order.getPremium_id());
								}
								if(StringUtil.isValid(order.getPremium_name())){
									updateorder.setPremium_name(order.getPremium_name());
								}
								if(orderMapper.updateOrder(updateorder) > 0){
									if(ordergoodslist!=null && !ordergoodslist.isEmpty()){
										for(OrderGoods ordergoods:ordergoodslist){
											if(ordergoods.getOrdergoods_id()!=null && ordergoods.getOrdergoods_id() > 0){
												if(ordergoods.getGoods_num()!=null && ordergoods.getGoods_num() > 0){
													orderMapper.updateOrderGoods(ordergoods);
												}else{
													orderMapper.deleteOrderGoodsById(ordergoods);
												}
											}else{
												ordergoods.setCreate_time(new Date());
												ordergoods.setUpdate_time(new Date());
												ordergoods.setState(1);
												orderMapper.insertOrderGoods(ordergoods);
											}
										}
									}
									goodsService.changeOrderGoodsStock(ordergoodslist,order.getOrder_type());
									result.setOrder(updateorder);
									result.setOrdergoodslist(ordergoodslist);
									result.setCode(0);
									result.setMsg("修改订单成功");
								}else{
									result.setCode(1);
									result.setMsg("修改订单失败");
								}
							}else{
								result.setCode(1);
								result.setMsg("客户不存在，下单失败");
							}
						}else if(order.getCustomer_id()==null || order.getCustomer_id() <= 0){
							if(goods_count!=0){
								updateorder.setGoods_count(updateorder.getGoods_count() + goods_count);
							}else{
								updateorder.setGoods_count(null);
							}
							updateorder.setState(null);
							if(order.getPremium_id()!=null && order.getPremium_id() > 0){
								updateorder.setPremium_id(order.getPremium_id());
							}
							if(StringUtil.isValid(order.getPremium_name())){
								updateorder.setPremium_name(order.getPremium_name());
							}
							if(orderMapper.updateOrder(updateorder) > 0){
								if(ordergoodslist!=null && !ordergoodslist.isEmpty()){
									for(OrderGoods ordergoods:ordergoodslist){
										if(ordergoods.getOrdergoods_id()!=null && ordergoods.getOrdergoods_id() > 0){
											if(ordergoods.getGoods_num()!=null && ordergoods.getGoods_num() > 0){
												orderMapper.updateOrderGoods(ordergoods);
											}else{
												orderMapper.deleteOrderGoodsById(ordergoods);
											}
										}else{
											ordergoods.setCreate_time(new Date());
											ordergoods.setUpdate_time(new Date());
											ordergoods.setState(1);
											orderMapper.insertOrderGoods(ordergoods);
										}
									}
								}
								goodsService.changeOrderGoodsStock(ordergoodslist,order.getOrder_type());
								result.setOrder(updateorder);
								result.setOrdergoodslist(ordergoodslist);
								result.setCode(0);
								result.setMsg("修改订单成功");
							}else{
								result.setCode(1);
								result.setMsg("修改订单失败");
							}
						}else{
							result.setCode(1);
							result.setMsg("客户资料录入不全，修改订单失败");
						}
					}else{
						result.setCode(1);
						result.setMsg("修改订单失败");
					}
				}else{
					result.setCode(1);
					result.setMsg("修改订单失败");
				}
			}else{
				result.setCode(1);
				result.setMsg("修改订单失败");
			}
		}catch(Exception e){
			result = new OrderResult();
			result.setCode(1);
			result.setMsg("修改订单失败");
		}
		return result;
	}
	
	public Result updateOrderRemark(UpdateOrderRemark orderremark){
		Result result = new Result();
		try{
			if(orderremark!=null && orderremark.getOrder_id()!=null && orderremark.getOrder_id() > 0 && StringUtil.isValid(orderremark.getOrder_remark())){
				Order order = new Order();
				order.setOrder_id(orderremark.getOrder_id());
				order.setOrder_remark(orderremark.getOrder_remark());
				if(orderMapper.updateOrder(order) > 0){
					result.setCode(0);
					result.setMsg("修改成功");
				}else{
					result.setCode(1);
					result.setMsg("修改失败");
				}
			}else{
				result.setCode(1);
				result.setMsg("修改失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			result = new OrderResult();
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public OrderListResult listPageOrder(ListPageOrder listPageOrder){
		OrderListResult result = new OrderListResult();
		try{
			if(listPageOrder!=null && listPageOrder.getAccount_id()!=null && listPageOrder.getAccount_id() > 0 && listPageOrder.getCurrentPage() > 0){
				result.setList(orderMapper.listPageOrder(listPageOrder));
				result.setTotalresult(listPageOrder.getTotalResult());
				result.setTotalpage(listPageOrder.getTotalPage());
				result.setCode(0);
				result.setMsg("查询订单成功");
			}else{
				result.setCode(1);
				result.setMsg("查询订单失败");
			}
		}catch(Exception e){
			result.setCode(1);
			result.setMsg("查询订单失败");
		}
		return result;
	}
	
	public OrderMonthListResult listPageAllOrder(ListPageOrder listPageOrder){
		OrderMonthListResult result = new OrderMonthListResult();
		try{
			if(listPageOrder!=null && listPageOrder.getAccount_id()!=null && listPageOrder.getAccount_id() > 0 && listPageOrder.getCurrentPage() > 0){
				Map<String,Integer> ordermap = new HashMap<String,Integer>();
				List<OrderList> newlist = null;
				OrderMonth ordermonth = null;
				List<OrderMonth> ordermonthlist = new ArrayList<OrderMonth>();
				List<OrderList> orderlist = orderMapper.listPageOrder(listPageOrder);
				if(orderlist!=null && !orderlist.isEmpty()){
					for(OrderList order:orderlist){
						String key = sdf.format(order.getCreate_time());
						if(ordermap.containsKey(key)){
							ordermonthlist.get(ordermap.get(key)).getOrderlist().add(order);
						}else{
							newlist = new ArrayList<OrderList>();
							newlist.add(order);
							ordermap.put(key, ordermonthlist.size());
							ordermonth = new OrderMonth();
							ordermonth.setMonth(key);
							ordermonth.setOrderlist(newlist);
							ordermonthlist.add(ordermonth);
						}
					}
					result.setList(ordermonthlist);	
				}
				result.setTotalresult(listPageOrder.getTotalResult());
				result.setTotalpage(listPageOrder.getTotalPage());
				result.setYear(sdf1.format(new Date()));
				result.setCode(0);
				result.setMsg("查询订单成功");
			}else{
				result.setCode(1);
				result.setMsg("查询订单失败");
			}
		}catch(Exception e){
			result.setCode(1);
			result.setMsg("查询订单失败");
		}
		return result;
	}
	
	public OrderDetailResult queryOneOrder(Order order){
		OrderDetailResult result = new OrderDetailResult();
		try{
			if(order!=null && order.getOrder_id()!=null && order.getOrder_id() > 0 && order.getAccount_id()!=null && order.getAccount_id() > 0){
				result.setOrder(orderMapper.queryOrderById(order));
				result.setList(orderMapper.queryOrderGoods(order));
				result.setCode(0);
				result.setMsg("查询订单成功");
			}else{
				result.setCode(1);
				result.setMsg("查询订单失败");
			}
		}catch(Exception e){
			result.setCode(1);
			result.setMsg("查询订单失败");
		}
		return result;
	}
	
	public OrderAllDetailResult queryOrder(Order order){
		OrderAllDetailResult result = new OrderAllDetailResult();
		try{
			if(order!=null && order.getOrder_id()!=null && order.getOrder_id() > 0 && order.getAccount_id()!=null && order.getAccount_id() > 0){
				result.setOrder(orderMapper.queryOrderById(order));
				Map<String,Integer> goodsmap = new HashMap<String,Integer>();
				List<OrderGoods> newlist = null;
				OrderBrandGoods brandgoods = null;
				List<OrderBrandGoods> brandgoodslist = new ArrayList<OrderBrandGoods>();
				List<OrderGoods> goodslist = orderMapper.queryOrderGoods(order);
				if(goodslist!=null && !goodslist.isEmpty()){
					for(OrderGoods ordergoods:goodslist){
						String key = ordergoods.getBrand_name() + "-" + ordergoods.getAgentlevel_name();
						if(goodsmap.containsKey(key)){
							brandgoodslist.get(goodsmap.get(key)).getGoodslist().add(ordergoods);
						}else{
							newlist = new ArrayList<OrderGoods>();
							newlist.add(ordergoods);
							goodsmap.put(key, brandgoodslist.size());
							brandgoods = new OrderBrandGoods();
							brandgoods.setName(key);
							brandgoods.setGoodslist(newlist);
							brandgoodslist.add(brandgoods);
						}
					}
					result.setList(brandgoodslist);
				}
				result.setCode(0);
				result.setMsg("查询订单成功");
			}else{
				result.setCode(1);
				result.setMsg("查询订单失败");
			}
		}catch(Exception e){
			result.setCode(1);
			result.setMsg("查询订单失败");
		}
		return result;
	}
	
	public Result deleteOrder(Order order){
		Result result = new Result();
		if(order!=null && order.getOrder_id()!=null && order.getOrder_id() > 0 && order.getAccount_id()!=null && order.getAccount_id() > 0){
			if(orderMapper.deleteOrder(order) > 0){
				result.setCode(0);
				result.setMsg("删除成功");
				goodsService.changeOrderGoodsStock(orderMapper.queryDeleteOrderGoods(order),orderMapper.queryOrderType(order.getOrder_id()));
				orderMapper.deleteOrderGoods(order);
			}else{
				result.setCode(1);
				result.setMsg("删除失败");
			}
		}else{
			result.setCode(1);
			result.setMsg("删除失败");
		}
		return result;
	}
}