package com.api.customer.controller;

import com.api.account.entity.Account;
import com.api.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.api.common.service.ImagesService;
import com.api.customer.entity.Customer;
import com.api.customer.entity.CustomerGoodsTop;
import com.api.customer.entity.Detail;
import com.api.customer.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ImagesService imagesService;
	
	@RequestMapping(value = "/uploadCustomerIcon")
    @ResponseBody
	public Object uploadCustomerIcon(MultipartFile file){
		return imagesService.uploadImages(file,"customer",null);
	}
	
	@RequestMapping(value = "/insertCustomer")
    @ResponseBody
	public Object insertCustomer(Customer customer,String phonelist,String addresslist){
		return customerService.insertCustomer(customer,phonelist,addresslist);
	}
	
	@RequestMapping(value = "/updateCustomer")
    @ResponseBody
	public Object updateCustomer(Customer customer){
		return customerService.updateCustomer(customer);
	}
	
	@RequestMapping(value = "/deleteCustomer")
    @ResponseBody
	public Object deleteCustomer(Customer customer){
		return customerService.deleteCustomer(customer);
	}
	
	@RequestMapping(value = "/queryCustomer")
    @ResponseBody
	public Object queryCustomer(Integer account_id,Integer type){
		return customerService.queryCustomer(account_id,type);
	}
	
	@RequestMapping(value = "/insertDetail")
    @ResponseBody
	public Object insertDetail(Detail detail){
		return customerService.insertDetail(detail);
	}
	
	@RequestMapping(value = "/updateDetailDefault")
    @ResponseBody
	public Object updateDetailDefault(Detail detail){
		return customerService.updateDetailDefault(detail);
	}
	
	@RequestMapping(value = "/updateDetail")
    @ResponseBody
	public Object updateDetail(Detail detail){
		return customerService.updateDetail(detail);
	}
	
	@RequestMapping(value = "/deleteDetail")
    @ResponseBody
	public Object deleteDetail(Detail detail){
		return customerService.deleteDetail(detail);
	}
	
	@RequestMapping(value = "/queryOneCustomer")
    @ResponseBody
	public Object queryOneCustomer(Customer customer){
		return customerService.queryOneCustomer(customer);
	}
	
	@RequestMapping(value = "/queryDefaultOneCustomer")
    @ResponseBody
	public Object queryDefaultOneCustomer(Customer customer){
		return customerService.queryDefaultOneCustomer(customer);
	}
	
	@RequestMapping(value = "/customergoodstop")
    @ResponseBody
	public Object customergoodstop(CustomerGoodsTop customerGoodsTop){
		return customerService.queryCustomerGoodsTop(customerGoodsTop);
	}

	@RequestMapping(value = "/clearNotify")
	@ResponseBody
	public Result<Void> clearNotify(Integer account_id){
		return customerService.clearNotify(account_id);
	}
}