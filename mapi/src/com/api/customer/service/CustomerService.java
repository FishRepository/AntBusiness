package com.api.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.common.entity.Result;
import com.api.common.utils.StringUtil;
import com.api.customer.entity.Customer;
import com.api.customer.entity.CustomerDetailDefaultResult;
import com.api.customer.entity.CustomerDetailListResult;
import com.api.customer.entity.CustomerDetailResult;
import com.api.customer.entity.CustomerGoodsTop;
import com.api.customer.entity.CustomerListResult;
import com.api.customer.entity.CustomerTopResult;
import com.api.customer.entity.Detail;
import com.api.customer.entity.DetailResult;
import com.api.customer.entity.InsertCustomerResult;
import com.api.customer.entity.InsertDetailResult;
import com.api.customer.mapper.CustomerMapper;

@Service
@Transactional
public class CustomerService {
	@Autowired
    private CustomerMapper customerMapper;
	
	public InsertCustomerResult insertCustomer(Customer customer,String phonelist,String addresslist){
		InsertCustomerResult result = new InsertCustomerResult();
		if(customer!=null && customer.getAccount_id()!=null && customer.getAccount_id() > 0 && StringUtil.isValid(customer.getCustomer_username())){
			if(customerMapper.insertCustomer(customer) > 0){
				result.setCode(0);
				result.setMsg("添加成功");
				result.setCustomer_id(customer.getCustomer_id());
				if(StringUtil.isValid(phonelist)){
					String[] phones = phonelist.split("\\|");
					if(phones!=null){
						Detail detailphone = null;
						DetailResult detailResult = null;
						List<DetailResult> phoneidlist = new ArrayList<DetailResult>();
						for(String phone:phones){
							detailphone = new Detail();
							detailphone.setDetail_name(phone);
							detailphone.setDetail_type(1);//手机号码
							detailphone.setCustomer_id(customer.getCustomer_id());
							detailphone.setAccount_id(customer.getAccount_id());
							if(customerMapper.insertDetail(detailphone) > 0){
								detailResult = new DetailResult();
								detailResult.setDetail_id(detailphone.getDetail_id());
								detailResult.setDetail_name(phone);
								phoneidlist.add(detailResult);
							}
						}
						if(phoneidlist!=null && !phoneidlist.isEmpty()){
							detailphone = new Detail();
							detailphone.setDetail_type(1);//手机号码
							detailphone.setCustomer_id(customer.getCustomer_id());
							detailphone.setAccount_id(customer.getAccount_id());
							detailphone.setDetail_id(phoneidlist.get(phoneidlist.size()-1).getDetail_id());
							customerMapper.updateDetailDefault(detailphone);
							result.setPhoneidlist(phoneidlist);
						}
					}
				}
				if(StringUtil.isValid(addresslist)){
					String[] addresss = addresslist.split("\\|");
					if(addresss!=null){
						Detail detailaddress = null;
						DetailResult detailResult = null;
						List<DetailResult> addressidlist = new ArrayList<DetailResult>();
						for(String address:addresss){
							detailaddress = new Detail();
							detailaddress.setDetail_name(address);
							detailaddress.setDetail_type(2);//地址
							detailaddress.setCustomer_id(customer.getCustomer_id());
							detailaddress.setAccount_id(customer.getAccount_id());
							if(customerMapper.insertDetail(detailaddress) > 0){
								detailResult = new DetailResult();
								detailResult.setDetail_id(detailaddress.getDetail_id());
								detailResult.setDetail_name(address);
								addressidlist.add(detailResult);
							}
						}
						if(addressidlist!=null && !addressidlist.isEmpty()){
							detailaddress = new Detail();
							detailaddress.setDetail_type(2);//地址
							detailaddress.setCustomer_id(customer.getCustomer_id());
							detailaddress.setAccount_id(customer.getAccount_id());
							detailaddress.setDetail_id(addressidlist.get(addressidlist.size()-1).getDetail_id());
							customerMapper.updateDetailDefault(detailaddress);
							result.setAddressidlist(addressidlist);
						}
					}
				}
			}else{
				result.setCode(1);
				result.setMsg("添加失败");
			}
		}else{
			result.setCode(1);
			result.setMsg("添加失败");
		}
		return result;
	}
	
	public Result updateCustomer(Customer customer){
		Result result = new Result();
		if(customer!=null && customer.getCustomer_id()!=null && customer.getCustomer_id() > 0 && customer.getAccount_id()!=null && customer.getAccount_id() > 0){
			if(customerMapper.updateCustomer(customer) > 0){
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
		return result;
	}
	
	public Result deleteCustomer(Customer customer){
		Result result = new Result();
		if(customer!=null && customer.getCustomer_id()!=null && customer.getCustomer_id() > 0 && customer.getAccount_id()!=null && customer.getAccount_id() > 0){
			if(customerMapper.deleteCustomer(customer) > 0){
				result.setCode(0);
				result.setMsg("删除成功");
				Detail detail = new Detail();
				detail.setCustomer_id(customer.getCustomer_id());
				detail.setAccount_id(customer.getAccount_id());
				customerMapper.deleteDetail(detail);
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
	
	public CustomerListResult queryCustomer(Integer account_id,Integer type){
		CustomerListResult result = new CustomerListResult();
		if(account_id!=null && account_id > 0){
			if(type!=null){
				if(type == 1){//名称和商品数量
					List<CustomerDetailResult> list = customerMapper.queryCustomer(account_id);
					if(list!=null && !list.isEmpty()){
						Detail detail = new Detail();
						detail.setAccount_id(account_id);
						for(CustomerDetailResult c:list){
							detail.setCustomer_id(c.getCustomer_id());
							detail.setDetail_type(1);
							c.setCustomerphone(customerMapper.queryDetailDefault(detail));
							detail.setDetail_type(2);
							c.setCustomeraddress(customerMapper.queryDetailDefault(detail));
						}
					}
					result.setList(list);
				}else{
					result.setList(customerMapper.queryCustomer(account_id));
				}
			}else{
				result.setList(customerMapper.queryCustomer(account_id));
			}
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public InsertDetailResult insertDetail(Detail detail){
		InsertDetailResult result = new InsertDetailResult();
		if(detail!=null && detail.getCustomer_id()!=null && detail.getCustomer_id() > 0 && detail.getAccount_id()!=null && detail.getAccount_id() > 0 && (detail.getDetail_type()!=null && (detail.getDetail_type() == 1 || detail.getDetail_type() == 2)) && StringUtil.isValid(detail.getDetail_name())){
			if(customerMapper.insertDetail(detail) > 0){
				customerMapper.cancelDetailDefault(detail);
				customerMapper.updateDetailDefault(detail);
				result.setCode(0);
				result.setMsg("添加成功");
				result.setDetail_id(detail.getDetail_id());
			}else{
				result.setCode(1);
				result.setMsg("添加失败");
			}
		}else{
			result.setCode(1);
			result.setMsg("添加失败");
		}
		return result;
	}
	
	public Result updateDetailDefault(Detail detail){
		Result result = new Result();
		if(detail!=null && detail.getDetail_id()!=null && detail.getDetail_id() > 0 && detail.getCustomer_id()!=null && detail.getCustomer_id() > 0 && detail.getAccount_id()!=null && detail.getAccount_id() > 0 && (detail.getDetail_type()!=null && (detail.getDetail_type() == 1 || detail.getDetail_type() == 2))){
			customerMapper.cancelDetailDefault(detail);
			if(customerMapper.updateDetailDefault(detail) > 0){
				result.setCode(0);
				result.setMsg("设置成功");
			}else{
				result.setCode(1);
				result.setMsg("设置失败");
			}
		}else{
			result.setCode(1);
			result.setMsg("设置失败");
		}
		return result;
	}
	
	public Result updateDetail(Detail detail){
		Result result = new Result();
		if(detail!=null && detail.getDetail_id()!=null && detail.getDetail_id() > 0 && detail.getCustomer_id()!=null && detail.getCustomer_id() > 0 && detail.getAccount_id()!=null && detail.getAccount_id() > 0 && (detail.getDetail_type()!=null && (detail.getDetail_type() == 1 || detail.getDetail_type() == 2)) && StringUtil.isValid(detail.getDetail_name())){
			if(customerMapper.updateDetail(detail) > 0){
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
		return result;
	}
	
	public Result deleteDetail(Detail detail){
		Result result = new Result();
		if(detail!=null && detail.getDetail_id()!=null && detail.getDetail_id() > 0 && detail.getCustomer_id()!=null && detail.getCustomer_id() > 0 && detail.getAccount_id()!=null && detail.getAccount_id() > 0){
			Detail d = customerMapper.queryDetailDefaultById(detail);
			if(d!=null){
				if(customerMapper.deleteDetail(detail) > 0){
					result.setCode(0);
					result.setMsg("删除成功");
					if(d.getDetail_default()!=null && d.getDetail_type()!=null && d.getDetail_default() == 1){
						detail.setDetail_type(d.getDetail_type());
						Integer detail_id = customerMapper.selectLastDetailId(detail);
						if(detail_id!=null && detail_id > 0){
							detail.setDetail_id(detail_id);
							customerMapper.updateDetailDefault(detail);
						}
					}
				}else{
					result.setCode(1);
					result.setMsg("删除失败");
				}
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
	
	public CustomerDetailListResult queryOneCustomer(Customer customer){
		CustomerDetailListResult result = new CustomerDetailListResult();
		if(customer!=null && customer.getCustomer_id()!=null && customer.getCustomer_id() > 0 && customer.getAccount_id()!=null && customer.getAccount_id() > 0){
			result.setCustomer(customerMapper.queryCustomerResultById(customer));
			Detail detail = new Detail();
			detail.setCustomer_id(customer.getCustomer_id());
			detail.setAccount_id(customer.getAccount_id());
			detail.setDetail_type(1);
			result.setPhonelist(customerMapper.queryDetail(detail));
			detail.setDetail_type(2);
			result.setAddresslist(customerMapper.queryDetail(detail));
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public CustomerDetailDefaultResult queryDefaultOneCustomer(Customer customer){
		CustomerDetailDefaultResult result = new CustomerDetailDefaultResult();
		if(customer!=null && customer.getCustomer_id()!=null && customer.getCustomer_id() > 0 && customer.getAccount_id()!=null && customer.getAccount_id() > 0){
			result.setCustomer(customerMapper.queryCustomerResultById(customer));
			Detail detail = new Detail();
			detail.setCustomer_id(customer.getCustomer_id());
			detail.setAccount_id(customer.getAccount_id());
			detail.setDetail_type(1);
			result.setCustomerphone(customerMapper.queryDetailDefault(detail));
			detail.setDetail_type(2);
			result.setCustomeraddress(customerMapper.queryDetailDefault(detail));
			result.setCustomerprofit(customerMapper.queryCustomerProfit(customer));
			CustomerGoodsTop customerGoodsTop = new CustomerGoodsTop();
			customerGoodsTop.setAccount_id(customer.getAccount_id());
			customerGoodsTop.setCustomer_id(customer.getCustomer_id());
			customerGoodsTop.setTime(customerMapper.queryMonthTime());
			result.setGoodslist(customerMapper.queryCustomerGoodsTop(customerGoodsTop));
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public CustomerTopResult queryCustomerGoodsTop(CustomerGoodsTop customerGoodsTop){
		CustomerTopResult result = null;
		try{
			if(customerGoodsTop.getAccount_id()!=null && customerGoodsTop.getAccount_id() > 0 && customerGoodsTop.getCustomer_id()!=null && customerGoodsTop.getCustomer_id() > 0){
				result = new CustomerTopResult();
				if(StringUtil.isEmpty(customerGoodsTop.getTime())){
					customerGoodsTop.setTime(customerMapper.queryMonthTime());
				}
				result.setList(customerMapper.queryCustomerGoodsTop(customerGoodsTop));
				result.setCode(0);
				result.setMsg("查询成功");
			}else{
				result = new CustomerTopResult();
				result.setCode(1);
				result.setMsg("查询失败");
			}
		}catch(Exception e){
			result = new CustomerTopResult();
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
}