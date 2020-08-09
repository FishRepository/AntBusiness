package com.api.customer.mapper;

import java.util.List;

import com.api.customer.entity.Customer;
import com.api.customer.entity.CustomerDetailResult;
import com.api.customer.entity.CustomerGoodsTop;
import com.api.customer.entity.CustomerProfit;
import com.api.customer.entity.CustomerResult;
import com.api.customer.entity.Detail;
import com.api.customer.entity.DetailResult;
import com.api.customer.entity.GoodsNumTotal;

public interface CustomerMapper{
	public int insertCustomer(Customer customer);
	
	public int updateCustomer(Customer customer);
	
	public Customer queryCustomerById(Customer customer);
	
	public CustomerResult queryCustomerResultById(Customer customer);
	
	public List<CustomerDetailResult> queryCustomer(Integer account_id);
	
	public CustomerProfit queryCustomerProfit(Customer customer);
	
	public int deleteCustomer(Customer customer);
	
	public int insertDetail(Detail detail);
	
	public int cancelDetailDefault(Detail detail);
	
	public int updateDetailDefault(Detail detail);
	
	public int updateDetail(Detail detail);
	
	public Integer selectLastDetailId(Detail detail);
	
	public String queryDetailById(Detail detail);
	
	public Detail queryDetailDefaultById(Detail detail);
	
	public List<DetailResult> queryDetail(Detail detail);
	
	public DetailResult queryDetailDefault(Detail detail);
	
	public int deleteDetail(Detail detail);
	
	public List<GoodsNumTotal> queryCustomerGoodsTop(CustomerGoodsTop customerGoodsTop);
	
	public String queryMonthTime();

	public List<Customer> getCustomerByPeriod();

	public List<Customer> getCustomerByBirthday();

	void updateNotifyTime(List<Integer> notifyList);

	void resetNotifyState(List<Integer> resetNotifiedList);

	void updatePeriodState(List<Integer> resetNotifiedList);

	Integer countInPeriod(Integer account_id);
}