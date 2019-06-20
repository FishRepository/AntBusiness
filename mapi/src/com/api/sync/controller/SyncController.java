package com.api.sync.controller;

import com.api.sync.entity.AccountDay;
import com.api.sync.entity.CustomerOrGoodsTop;
import com.api.sync.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sync")
public class SyncController {
	@Autowired
	private SyncService syncService;
	
	@RequestMapping(value = "/accountsync")
    @ResponseBody
	public Object accountsync(Integer account_id,String account_userphone){
		return syncService.accountsync(account_id,account_userphone);
	}
	
	@RequestMapping(value = "/totalprofit")
    @ResponseBody
	public Object totalprofit(Integer account_id,String account_userphone){
		return syncService.totalprofit(account_id,account_userphone);
	}
	
	@RequestMapping(value = "/monthprofit")
    @ResponseBody
	public Object monthprofit(AccountDay accountDay){
		return syncService.monthprofit(accountDay);
	}
	
	@RequestMapping(value = "/dayprofit")
    @ResponseBody
	public Object dayprofit(AccountDay accountDay){
		return syncService.dayprofit(accountDay);
	}
	
	@RequestMapping(value = "/customertop")
    @ResponseBody
	public Object customertop(CustomerOrGoodsTop customerOrGoodsTop){
		return syncService.queryCustomerTop(customerOrGoodsTop);
	}
	
	@RequestMapping(value = "/goodstop")
    @ResponseBody
	public Object goodstop(CustomerOrGoodsTop customerOrGoodsTop){
		return syncService.queryGoodsTop(customerOrGoodsTop);
	}
	
	@RequestMapping(value = "/queryTime")
    @ResponseBody
	public Object queryTime(Integer account_id){
		return syncService.queryTime(account_id);
	}

	@RequestMapping(value = "/notice")
    @ResponseBody
	public Object notice(Integer account_id){
		return syncService.queryNotice(account_id);
	}
	
	@RequestMapping(value = "/statistics")
    @ResponseBody
	public Object statistics(Integer account_id,String account_userphone){
		return syncService.queryStatistics(account_id,account_userphone);
	}
	
	@RequestMapping(value = "/appversion")
    @ResponseBody
	public Object appversion(Integer app_type){
		return syncService.appversion(app_type);
	}

	@RequestMapping(value = "/getReport")
	@ResponseBody
	public Object getReport(Integer account_id,String account_userphone){
		return syncService.getReport(account_id, account_userphone);
	}
}