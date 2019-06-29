package com.api.sync.service;

import com.api.account.entity.Account;
import com.api.common.utils.EncryptUtil;
import com.api.common.utils.StringUtil;
import com.api.sync.entity.*;
import com.api.sync.mapper.SyncMapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class SyncService {
	@Autowired
    private SyncMapper syncMapper;
	
	public AccountSync accountsync(Integer account_id,String account_userphone){
		AccountSync result = new AccountSync();
		try{
			if(account_id!=null && account_id > 0 && StringUtil.isValid(account_userphone)){
				account_userphone = EncryptUtil.base64Decode(account_userphone);
				Account account = new Account();
				account.setAccount_id(account_id);
				account.setAccount_userphone(account_userphone);
				if(syncMapper.checkAccountIdAndPhone(account) > 0){
					result.setBrandlist(syncMapper.queryBrand(account_id));
					result.setBrandimageslist(syncMapper.queryBrandImages(account_id));
					result.setAgentlevellist(syncMapper.queryAgentLevel(account_id));
					result.setGoodslist(syncMapper.queryGoods(account_id));
					result.setGoodspricelist(syncMapper.queryGoodsPrice(account_id));
					result.setCustomerlist(syncMapper.queryCustomer(account_id));
					result.setDetaillist(syncMapper.queryDetail(account_id));
					result.setOrderlist(syncMapper.queryOrder(account_id));
					result.setOrdergoodslist(syncMapper.queryOrderGoods(account_id));
					result.setCode(0);
					result.setMsg("同步成功");
				}else{
					result.setCode(1);
					result.setMsg("同步失败");
				}
			}else{
				result.setCode(1);
				result.setMsg("同步失败");
			}
		}catch(Exception e){
			result = new AccountSync();
			result.setCode(1);
			result.setMsg("同步失败");
		}
		return result;
	}
	
	public ProfitTotal totalprofit(Integer account_id,String account_userphone){
		ProfitTotal result = null;
		try{
			if(account_id!=null && account_id > 0 && StringUtil.isValid(account_userphone)){
				account_userphone = EncryptUtil.base64Decode(account_userphone);
				Account account = new Account();
				account.setAccount_id(account_id);
				account.setAccount_userphone(account_userphone);
				if(syncMapper.checkAccountIdAndPhone(account) > 0){
					result = syncMapper.queryProfitTotal(account_id);
					if(result!=null){
						result.setProfitlist(syncMapper.queryProfitSevenDay(account_id));
					}else{
						result = new ProfitTotal();
					}
					result.setSevenday(syncMapper.querySevenDay());
					result.setCode(0);
					result.setMsg("查询成功");
				}else{
					result = new ProfitTotal();
					result.setCode(1);
					result.setMsg("查询失败");
				}
			}else{
				result = new ProfitTotal();
				result.setCode(1);
				result.setMsg("查询失败");
			}
		}catch(Exception e){
			result = new ProfitTotal();
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
	
	public ProfitList monthprofit(AccountDay accountDay){
		ProfitList result = null;
		try{
			if(accountDay.getAccount_id()!=null && accountDay.getAccount_id() > 0 && StringUtil.isValid(accountDay.getAccount_userphone()) && StringUtil.isValid(accountDay.getTime()) && accountDay.getTime().length() == 4){
				accountDay.setAccount_userphone(EncryptUtil.base64Decode(accountDay.getAccount_userphone()));
				Account account = new Account();
				account.setAccount_id(accountDay.getAccount_id());
				account.setAccount_userphone(accountDay.getAccount_userphone());
				if(syncMapper.checkAccountIdAndPhone(account) > 0){
					result = new ProfitList();
					result.setProfitlist(syncMapper.queryProfitMonth(accountDay));
					if(sdf1.format(new Date()).equals(accountDay.getTime())){
						result.setIsnow(1);
					}else{
						result.setIsnow(0);
					}
					result.setNowtime(sdf2.format(new Date()));
					result.setCode(0);
					result.setMsg("查询成功");
				}else{
					result = new ProfitList();
					result.setCode(1);
					result.setMsg("查询失败");
				}
			}else{
				result = new ProfitList();
				result.setCode(1);
				result.setMsg("查询失败");
			}
		}catch(Exception e){
			result = new ProfitList();
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	private SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMM");
	private SimpleDateFormat sdf4 = new SimpleDateFormat("MM/dd");
	
	public ProfitList dayprofit(AccountDay accountDay){
		ProfitList result = null;
		try{
			if(accountDay.getAccount_id()!=null && accountDay.getAccount_id() > 0 && StringUtil.isValid(accountDay.getAccount_userphone()) && StringUtil.isValid(accountDay.getTime()) && accountDay.getTime().length() == 6){
				accountDay.setAccount_userphone(EncryptUtil.base64Decode(accountDay.getAccount_userphone()));
				Account account = new Account();
				account.setAccount_id(accountDay.getAccount_id());
				account.setAccount_userphone(accountDay.getAccount_userphone());
				if(syncMapper.checkAccountIdAndPhone(account) > 0){
					result = new ProfitList();
					result.setProfitlist(syncMapper.queryProfitDay(accountDay));
					if(sdf3.format(new Date()).equals(accountDay.getTime())){
						result.setIsnow(1);
					}else{
						result.setIsnow(0);
					}
					result.setNowtime(sdf4.format(new Date()));
					result.setCode(0);
					result.setMsg("查询成功");
				}else{
					result = new ProfitList();
					result.setCode(1);
					result.setMsg("查询失败");
				}
			}else{
				result = new ProfitList();
				result.setCode(1);
				result.setMsg("查询失败");
			}
		}catch(Exception e){
			result = new ProfitList();
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public TopResult queryCustomerTop(CustomerOrGoodsTop customerOrGoodsTop){
		TopResult result = null;
		try{
			if(customerOrGoodsTop.getAccount_id()!=null && customerOrGoodsTop.getAccount_id() > 0){
				result = new TopResult();
				if(StringUtil.isEmpty(customerOrGoodsTop.getTime())){
					customerOrGoodsTop.setTime(syncMapper.queryNowtime());
				}
				if(customerOrGoodsTop.getShowcount()!=null && customerOrGoodsTop.getShowcount() > 0){
					result.setList(syncMapper.queryCustomerLimitTop(customerOrGoodsTop));
				}else{
					result.setList(syncMapper.queryCustomerTop(customerOrGoodsTop));
				}
				result.setCode(0);
				result.setMsg("查询成功");
			}else{
				result = new TopResult();
				result.setCode(1);
				result.setMsg("查询失败");
			}
		}catch(Exception e){
			result = new TopResult();
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public TopResult queryGoodsTop(CustomerOrGoodsTop customerOrGoodsTop){
		TopResult result = null;
		try{
			if(customerOrGoodsTop.getAccount_id()!=null && customerOrGoodsTop.getAccount_id() > 0){
				result = new TopResult();
				if(StringUtil.isEmpty(customerOrGoodsTop.getTime())){
					customerOrGoodsTop.setTime(syncMapper.queryNowtime());
				}
				if(customerOrGoodsTop.getShowcount()!=null && customerOrGoodsTop.getShowcount() > 0){
					result.setList(syncMapper.queryGoodsLimitTop(customerOrGoodsTop));
				}else{
					result.setList(syncMapper.queryGoodsTop(customerOrGoodsTop));
				}
				result.setCode(0);
				result.setMsg("查询成功");
			}else{
				result = new TopResult();
				result.setCode(1);
				result.setMsg("查询失败");
			}
		}catch(Exception e){
			result = new TopResult();
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public TimeResult queryTime(Integer account_id){
		TimeResult result = new TimeResult();
		try{
			if(account_id!=null && account_id > 0){
				String time = syncMapper.queryCreatetime(account_id);
				if(StringUtil.isValid(time)){
					result.setCreatetime(time);
					result.setNowtime(syncMapper.queryNowtime());
					result.setCode(0);
					result.setMsg("查询成功");
				}else{
					result.setCode(1);
					result.setMsg("查询失败");
				}
			}else{
				result.setCode(1);
				result.setMsg("查询失败");
			}
		}catch(Exception e){
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public NoticeList queryNotice(Integer account_id){
		NoticeList noticeList = new NoticeList();
		int issales = syncMapper.checkProfitDay(account_id);
		if(issales > 0){
			noticeList.setIssale(1);
		}else{
			noticeList.setIssale(0);
		}
		Float lastmonth = syncMapper.queryProfitLastMonth(account_id);
		if(lastmonth == null){
			lastmonth = 0f;
		}
		Float lasttwomonth = syncMapper.queryProfitLastTwoMonth(account_id);
		if(lasttwomonth == null){
			lasttwomonth = 0f;
		}
		if(issales == 1){
			noticeList.setList(syncMapper.queryNotice(1));
		}else{
			if(lastmonth.floatValue() >= lasttwomonth.floatValue()){
				noticeList.setList(syncMapper.queryNotice(2));
			}else{
				noticeList.setList(syncMapper.queryNotice(3));
			}
		}
		noticeList.setCode(0);
		noticeList.setMsg("查询成功");
		return noticeList;
	}
	
	public StatisticsResult queryStatistics(Integer account_id,String account_userphone){
		StatisticsResult result = new StatisticsResult();
		try{
			if(account_id!=null && account_id > 0 && StringUtil.isValid(account_userphone)){
				account_userphone = EncryptUtil.base64Decode(account_userphone);
				Account account = new Account();
				account.setAccount_id(account_id);
				account.setAccount_userphone(account_userphone);
				if(syncMapper.checkAccountIdAndPhone(account) > 0){
					result.setProfittotal(syncMapper.queryProfitTotal(account_id));
					CustomerOrGoodsTop customerOrGoodsTop = new CustomerOrGoodsTop();
					customerOrGoodsTop.setAccount_id(account_id);
					customerOrGoodsTop.setTime(syncMapper.queryNowtime());
					customerOrGoodsTop.setShowcount(3);
					result.setCustomerlist(syncMapper.queryCustomerTop(customerOrGoodsTop));
					result.setGoodslist(syncMapper.queryGoodsTop(customerOrGoodsTop));
					int issales = syncMapper.checkProfitDay(account_id);
					if(issales > 0){
						result.setIssale(1);
					}else{
						result.setIssale(0);
					}
					Float lastmonth = syncMapper.queryProfitLastMonth(account_id);
					if(lastmonth == null){
						lastmonth = 0f;
					}
					Float lasttwomonth = syncMapper.queryProfitLastTwoMonth(account_id);
					if(lasttwomonth == null){
						lasttwomonth = 0f;
					}
					if(issales == 1){
						result.setNoticelist(syncMapper.queryNotice(1));
					}else{
						if(lastmonth.floatValue() >= lasttwomonth.floatValue()){
							result.setNoticelist(syncMapper.queryNotice(2));
						}else{
							result.setNoticelist(syncMapper.queryNotice(3));
						}
					}
					result.setCode(0);
					result.setMsg("查询成功");
				}
			}else{
				result.setCode(1);
				result.setMsg("查询失败");
			}
		}catch(Exception e){
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public AppVersion appversion(Integer app_type){
		AppVersion result = new AppVersion();
		if(app_type != null && (app_type == 1 || app_type == 2)){
			result = syncMapper.queryAppVersion(app_type);
			if(result!=null){
				result.setCode(0);
				result.setMsg("查询成功");
			}else{
				result = new AppVersion();
				result.setCode(1);
				result.setMsg("查询失败");
			}
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}

	public Object getReport(Integer account_id,String account_userphone){
		ProfitTotal totalprofit = totalprofit(account_id, account_userphone);
		CustomerOrGoodsTop customerOrGoodsTop = new CustomerOrGoodsTop();
		customerOrGoodsTop.setAccount_id(account_id);
		customerOrGoodsTop.setShowcount(3);
		TopResult customerTop = queryCustomerTop(customerOrGoodsTop);
		TopResult goodsTop = queryGoodsTop(customerOrGoodsTop);
		NoticeList noticeList = queryNotice(account_id);
		Map<String, Object> result = new HashMap<>();
		List<String> sevendayList = new ArrayList<>();
		List<Float> saleList = new ArrayList<>();
		if(totalprofit.getCode().equals(0)){
			SevenDay sevenday = totalprofit.getSevenday();
			sevendayList.add(0, sevenday.getToday6());
			sevendayList.add(1, sevenday.getToday5());
			sevendayList.add(2, sevenday.getToday4());
			sevendayList.add(3, sevenday.getToday3());
			sevendayList.add(4, sevenday.getToday2());
			sevendayList.add(5, sevenday.getToday1());
			sevendayList.add(6, sevenday.getToday());
			List<Profit> profitlist = totalprofit.getProfitlist();
			if(CollectionUtils.isEmpty(profitlist)){
				for(int i=0;i<7;i++){
					saleList.add(0f);
				}
			}else{
				//组装7天销售数据
				Map<String, Float> salesMap = new HashMap<>();
				for (Profit profit: profitlist) {
					salesMap.put(profit.getTotal_time(), profit.getTotal_sales());
				}
				for (String day: sevendayList) {
					saleList.add(salesMap.getOrDefault(day, 0f));
				}
			}
			result.put("sevendayList", sevendayList);
			result.put("saleList", saleList);
		}
		result.put("totalprofit", totalprofit);
		result.put("customerTop", customerTop);
		result.put("goodsTop", goodsTop);
		result.put("noticeList", noticeList);
		return result;
	}

}