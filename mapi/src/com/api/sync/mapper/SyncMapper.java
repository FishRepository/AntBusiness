package com.api.sync.mapper;

import java.util.List;

import com.api.account.entity.Account;
import com.api.sync.entity.AccountDay;
import com.api.sync.entity.AgentLevel;
import com.api.sync.entity.AppVersion;
import com.api.sync.entity.Brand;
import com.api.sync.entity.BrandImages;
import com.api.sync.entity.Customer;
import com.api.sync.entity.CustomerOrGoodsTop;
import com.api.sync.entity.CustomerTotal;
import com.api.sync.entity.Detail;
import com.api.sync.entity.Goods;
import com.api.sync.entity.GoodsPrice;
import com.api.sync.entity.GoodsTotal;
import com.api.sync.entity.Notice;
import com.api.sync.entity.Order;
import com.api.sync.entity.OrderGoods;
import com.api.sync.entity.Profit;
import com.api.sync.entity.ProfitTotal;
import com.api.sync.entity.SevenDay;

public interface SyncMapper {
	public int checkAccountIdAndPhone(Account account);
	public List<Brand> queryBrand(Integer account_id);
	public List<BrandImages> queryBrandImages(Integer account_id);
	public List<AgentLevel> queryAgentLevel(Integer account_id);
	public List<Goods> queryGoods(Integer account_id);
	public List<GoodsPrice> queryGoodsPrice(Integer account_id);
	public List<Customer> queryCustomer(Integer account_id);
	public List<Detail> queryDetail(Integer account_id);
	public List<Order> queryOrder(Integer account_id);
	public List<OrderGoods> queryOrderGoods(Integer account_id);
	public ProfitTotal queryProfitTotal(Integer account_id);
	public SevenDay querySevenDay();
	public List<Profit> queryProfitSevenDay(Integer account_id);
	public List<Profit> queryProfitMonth(AccountDay accountDay);
	public List<Profit> queryProfitDay(AccountDay accountDay);
	public Float queryProfitLastMonth(Integer account_id);
	public Float queryProfitLastTwoMonth(Integer account_id);
	public int checkProfitDay(Integer account_id);
	public List<CustomerTotal> queryCustomerTop(CustomerOrGoodsTop customerOrGoodsTop);
	public List<GoodsTotal> queryGoodsTop(CustomerOrGoodsTop customerOrGoodsTop);
	public List<CustomerTotal> queryCustomerLimitTop(CustomerOrGoodsTop customerOrGoodsTop);
	public List<GoodsTotal> queryGoodsLimitTop(CustomerOrGoodsTop customerOrGoodsTop);
	public String queryNowtime();
	public String queryCreatetime(Integer account_id);
	public List<Notice> queryNotice(Integer notice_type);
	public AppVersion queryAppVersion(Integer app_type);
}