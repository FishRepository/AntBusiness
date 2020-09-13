package com.api.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.api.account.entity.Account;
import com.api.account.entity.AccountCode;
import com.api.account.entity.AccountLoginResult;
import com.api.account.entity.Advise;
import com.api.account.entity.AdviseResult;
import com.api.account.entity.Integral;
import com.api.account.entity.IntegralRule;
import com.api.account.entity.ListPageAdvise;
import com.api.account.entity.ListPageIntegral;
import com.api.account.entity.ListPageMemorandum;
import com.api.account.entity.Memorandum;
import com.api.account.entity.ShareAccount;

public interface AccountMapper{
	public int insertAccount(Account account);
	
	public int updateAccount(Account account);
	
	public int updateAccountByPhone(Account account);
	
	public int updateLastLoginTime(Integer account_id);
	
	public int updateAccountIntegral(Account account);
	
	public Integer queryAccountIntegral(Integer account_id);
	
	public String queryPwdById(Integer account_id);
	
	public String queryPwdByPhone(String account_userphone);
	
	public Integer queryIdByPhone(String account_userphone);
	
	public Account queryAccountById(Integer account_id);
	
	public AccountLoginResult queryAccountByPhone(String account_userphone);
	
	public AccountLoginResult queryAccountByWechat(String account_wechat);
	
	public AccountLoginResult queryAccountByQQ(String account_qq);

	public AccountLoginResult queryAccountByAppleId(String account_appleid);
	
	public Account queryAccountWechatById(Integer account_id);
	
	public Account queryAccountQQById(Integer account_id);
	
	public Account queryAccountWechat(String account_userphone);
	
	public Account queryAccountQQ(String account_userphone);

	public Account queryAccountAppleId(String account_userphone);
	
	public int checkAccountPhone(Account account);
	
	public int checkAccountWechat(Account account);
	
	public int checkAccountQQ(Account account);

	public int checkAccountAppleId(Account account);
	
	public int checkAccountIdAndPhone(Account account);
	
	public int insertAccountCode(AccountCode accountcode);
	
	public int deleteAccountCode(String account_userphone);
	
	public int checkAccountCode(AccountCode accountcode);
	
	public IntegralRule queryIntegralRule(Integer rule_type);
	
	public int insertIntegral(Integral integral);
	
	public List<Integral> listPageIntegral(ListPageIntegral integral);
	
	public int insertAdvise(Advise advise);
	
	public List<AdviseResult> listPageAdvise(ListPageAdvise advise);
	
	public int insertMemorandum(Memorandum memorandum);
	
	public int updateMemorandum(Memorandum memorandum);
	
	public int deleteMemorandum(Memorandum memorandum);
	
	public List<Memorandum> listPageMemorandum(ListPageMemorandum memorandum);
	
	public List<Integer> queryMemorandumList(Memorandum memorandum);
	
	public Memorandum queryMemorandum(Integer memorandum_id);
	
	public int checkMemorandum(Memorandum memorandum);
	
	public int insertShareAccount(ShareAccount shareaccount);
	
	public List<Integer> queryShareAccount(String account_userphone);
	
	public int deleteShareAccount(String account_userphone);
	
	public int queryTotalAccount();
	
	public int queryTotalTimeAccount(@Param(value="startdate")String startdate,@Param(value="enddate")String enddate);
	
	public int queryTotalTimeNoLoginAccount(@Param(value="startdate")String startdate,@Param(value="enddate")String enddate);
	
	public List<String> queryTimeAccount(@Param(value="startdate")String startdate,@Param(value="enddate")String enddate);
	
	public List<String> queryTimeNoLoginAccount(@Param(value="startdate")String startdate,@Param(value="enddate")String enddate);

	public Account getAccountInfo(Integer account_id);

	public List<Account> getAllAccount();

	public int batchUpdateVipState(List<Integer> list);

	void stopNew(Integer account_id);

}