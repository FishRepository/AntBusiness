package com.api.account.service;

import com.api.account.entity.*;
import com.api.account.mapper.AccountMapper;
import com.api.common.entity.Result;
import com.api.common.utils.*;
import com.api.goods.entity.*;
import com.api.goods.mapper.GoodsMapper;
import com.backend.common.DateUtil;
import com.key.KeyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

	private ExecutorService executorService;

	private DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	@Autowired
    private AccountMapper accountMapper;
	@Autowired
    private GoodsMapper goodsMapper;
	
	public Result sendcode(String account_userphone,Integer type){
		Result result = new Result();
		try{
			if(StringUtil.isValid(account_userphone) && type != null){
				account_userphone = EncryptUtil.base64Decode(account_userphone);
				if(account_userphone.matches("1\\d{10}")){
					Account account = new Account();
					account.setAccount_userphone(account_userphone);
					if(type == 1){//注册或修改手机号码
						if(accountMapper.checkAccountPhone(account) <= 0){
							String random = RandomNum.genRandomNum(6);
							if(SmsUtil.postSms(account_userphone, random)){
								accountMapper.deleteAccountCode(account_userphone);
								AccountCode accountcode = new AccountCode();
								accountcode.setAccount_userphone(account_userphone);
								accountcode.setAccount_code(random);
								accountMapper.insertAccountCode(accountcode);
								result.setCode(0);
								result.setMsg("发送成功");
							}else{
								result.setCode(1);
								result.setMsg("发送失败");
							}
						}else{
							result.setCode(2);
							result.setMsg("手机号码已经注册，不能再发验证码");
						}
					}else if(type == 2){//重置密码
						if(accountMapper.checkAccountPhone(account) > 0){
							String random = RandomNum.genRandomNum(6);
							if(SmsUtil.postSms(account_userphone, random)){
								accountMapper.deleteAccountCode(account_userphone);
								AccountCode accountcode = new AccountCode();
								accountcode.setAccount_userphone(account_userphone);
								accountcode.setAccount_code(random);
								accountMapper.insertAccountCode(accountcode);
								result.setCode(0);
								result.setMsg("发送成功");
							}else{
								result.setCode(1);
								result.setMsg("发送失败");
							}
						}else{
							result.setCode(2);
							result.setMsg("手机号码未注册，不能发验证码");
						}
					}else if(type == 3){//微信或QQ绑定手机号码
						String random = RandomNum.genRandomNum(6);
						if(SmsUtil.postSms(account_userphone, random)){
							accountMapper.deleteAccountCode(account_userphone);
							AccountCode accountcode = new AccountCode();
							accountcode.setAccount_userphone(account_userphone);
							accountcode.setAccount_code(random);
							accountMapper.insertAccountCode(accountcode);
							result.setCode(0);
							result.setMsg("发送成功");
						}else{
							result.setCode(1);
							result.setMsg("发送失败");
						}
					}else{
						result.setCode(1);
						result.setMsg("发送失败");
					}
				}else{
					result.setCode(1);
					result.setMsg("手机号码格式错误");
				}
			}else{
				result.setCode(1);
				result.setMsg("手机号码不能为空");
			}
		}catch(Exception e){
			result = new Result();
			result.setCode(1);
			result.setMsg("发送失败");
		}
		return result;
	}
	
	public Result checkcode(String account_userphone,String account_code){
		Result result = new Result();
		try{
			if(StringUtil.isValid(account_userphone) && StringUtil.isValid(account_code)){
				account_userphone = EncryptUtil.base64Decode(account_userphone);
				if(account_userphone.matches("1\\d{10}")){
					AccountCode accountcode = new AccountCode();
					accountcode.setAccount_userphone(account_userphone);
					accountcode.setAccount_code(account_code);
					if(accountMapper.checkAccountCode(accountcode) > 0){
						accountMapper.deleteAccountCode(account_userphone);
						result.setCode(0);
						result.setMsg("校验通过");
					}else{
						result.setCode(1);
						result.setMsg("校验失败");
					}
				}else{
					result.setCode(1);
					result.setMsg("手机号码格式错误");
				}
			}else{
				result.setCode(1);
				result.setMsg("手机号码或验证码不能为空");
			}
		}catch(Exception e){
			result = new Result();
			result.setCode(1);
			result.setMsg("校验失败");
		}
		return result;
	}
	
	public AccountRegResult reg(Account account,String account_code){
		AccountRegResult result = new AccountRegResult();
		try{
			if(account!=null && StringUtil.isValid(account.getAccount_userphone()) && StringUtil.isValid(account_code) && StringUtil.isValid(account.getAccount_imei()) && (account.getAccount_id() == null || account.getAccount_id() <= 0)){
				if(account.getAccount_guideid()==null){
					account.setAccount_guideid(0);
				}
				account.setAccount_userphone(EncryptUtil.base64Decode(account.getAccount_userphone()));
				AccountCode accountcode = new AccountCode();
				accountcode.setAccount_userphone(account.getAccount_userphone());
				accountcode.setAccount_code(account_code);
				account.setIs_new(1);
				account.setIs_vip(1);
				//注册送一个月VIP日期
				Date vipTime = DateUtil.adjustDateByDay(new Date() ,30,1);
				account.setVip_time(DateUtil.setEnd(vipTime,1));
				account.setVip_type(1);
				if(accountMapper.checkAccountCode(accountcode) > 0){
					accountMapper.deleteAccountCode(account.getAccount_userphone());
					if(StringUtil.isValid(account.getAccount_wechat())){
						account.setAccount_wechat(EncryptUtil.base64Decode(account.getAccount_wechat()));
						if(StringUtil.isValid(account.getAccount_qq())){
							account.setAccount_qq(null);
						}
						if(accountMapper.checkAccountWechat(account) <= 0){
							if(StringUtil.isValid(account.getAccount_password())){
								account.setAccount_password(KeyUtils.encodepwd(EncryptUtil.base64Decode(account.getAccount_password())));
							}
							Account wechat = accountMapper.queryAccountWechat(account.getAccount_userphone());
							if(wechat == null){
								if(accountMapper.insertAccount(account) > 0){
									result.setCode(0);
									result.setMsg("注册成功");
									result.setAccount_id(account.getAccount_id());
									result.setReg_integral(addAccountIntegral(account.getAccount_id(),2));
									insertDefaultMemorandum(account.getAccount_id());
									if(account.getAccount_guideid()!=null && account.getAccount_guideid() > 0){
										addAccountIntegral(account.getAccount_guideid(),1);
									}
									List<Integer> ids = accountMapper.queryShareAccount(account.getAccount_userphone());
									if(ids!=null && !ids.isEmpty()){
										for(Integer id:ids){
											addAccountIntegral(id,1);
										}
										accountMapper.deleteShareAccount(account.getAccount_userphone());
									}
								}else{
									result.setCode(1);
									result.setMsg("注册失败");
								}
							}else{
								if(StringUtil.isEmpty(wechat.getAccount_wechat())){
									if(!account.getAccount_imei().equalsIgnoreCase(wechat.getAccount_imei())){										
										result.setIschange(1);
									}
									if(accountMapper.updateAccountByPhone(account) > 0){
										result.setCode(0);
										result.setMsg("绑定成功");
										result.setAccount_id(wechat.getAccount_id());
									}else{
										result.setCode(1);
										result.setMsg("绑定失败");
									}
								}else{
									result.setCode(3);
									result.setMsg("该手机号已经绑定微信，不能重复绑定");
								}
							}
						}else{
							result.setCode(3);
							result.setMsg("微信已存在，不能重复注册");
						}
					}else if(StringUtil.isValid(account.getAccount_qq())){
						account.setAccount_qq(EncryptUtil.base64Decode(account.getAccount_qq()));
						if(accountMapper.checkAccountQQ(account) <= 0){
							if(StringUtil.isValid(account.getAccount_password())){
								account.setAccount_password(KeyUtils.encodepwd(EncryptUtil.base64Decode(account.getAccount_password())));
							}
							Account qq = accountMapper.queryAccountQQ(account.getAccount_userphone());
							if(qq == null){
								if(accountMapper.insertAccount(account) > 0){
									result.setCode(0);
									result.setMsg("注册成功");
									result.setAccount_id(account.getAccount_id());
									result.setReg_integral(addAccountIntegral(account.getAccount_id(),2));
									insertDefaultMemorandum(account.getAccount_id());
									if(account.getAccount_guideid()!=null && account.getAccount_guideid() > 0){
										addAccountIntegral(account.getAccount_guideid(),1);
									}
									List<Integer> ids = accountMapper.queryShareAccount(account.getAccount_userphone());
									if(ids!=null && !ids.isEmpty()){
										for(Integer id:ids){
											addAccountIntegral(id,1);
										}
										accountMapper.deleteShareAccount(account.getAccount_userphone());
									}
								}else{
									result.setCode(1);
									result.setMsg("注册失败");
								}
							}else{
								if(StringUtil.isEmpty(qq.getAccount_qq())){
									if(!account.getAccount_imei().equalsIgnoreCase(qq.getAccount_imei())){										
										result.setIschange(1);
									}
									if(accountMapper.updateAccountByPhone(account) > 0){
										result.setCode(0);
										result.setMsg("绑定成功");
										result.setAccount_id(qq.getAccount_id());
									}else{
										result.setCode(1);
										result.setMsg("绑定失败");
									}
								}else{
									result.setCode(3);
									result.setMsg("该手机号已经绑定QQ，不能重复绑定");
								}
							}
						}else{
							result.setCode(3);
							result.setMsg("QQ已存在，不能重复注册");
						}
					}else{
						if(accountMapper.checkAccountPhone(account) <= 0){
							if(StringUtil.isValid(account.getAccount_password())){
								account.setAccount_password(KeyUtils.encodepwd(EncryptUtil.base64Decode(account.getAccount_password())));
								if(accountMapper.insertAccount(account) > 0){
									result.setCode(0);
									result.setMsg("注册成功");
									result.setAccount_id(account.getAccount_id());
									result.setReg_integral(addAccountIntegral(account.getAccount_id(),2));
									insertDefaultMemorandum(account.getAccount_id());
									if(account.getAccount_guideid()!=null && account.getAccount_guideid() > 0){
										addAccountIntegral(account.getAccount_guideid(),1);
									}
									List<Integer> ids = accountMapper.queryShareAccount(account.getAccount_userphone());
									if(ids!=null && !ids.isEmpty()){
										for(Integer id:ids){
											addAccountIntegral(id,1);
										}
										accountMapper.deleteShareAccount(account.getAccount_userphone());
									}
								}else{
									result.setCode(1);
									result.setMsg("注册失败");
								}
							}else{
								result.setCode(1);
								result.setMsg("密码不能为空");
							}
						}else{
							result.setCode(3);
							result.setMsg("手机号码已存在，不能重复注册");
						}
					}
				}else{
					result.setCode(2);
					result.setMsg("验证码错误");
				}
			}else{
				result.setCode(1);
				result.setMsg("手机号码、验证码等不能为空");
			}
		}catch(Exception e){
			result = new AccountRegResult();
			result.setCode(1);
			result.setMsg("注册失败");
		}
		return result;
	}
	
	public AccountLoginResult login(Account account){
		AccountLoginResult result = new AccountLoginResult();
		try{
			if(account!=null && StringUtil.isValid(account.getAccount_imei())){
				if(StringUtil.isValid(account.getAccount_wechat())){
					account.setAccount_wechat(EncryptUtil.base64Decode(account.getAccount_wechat()));
					if(StringUtil.isValid(account.getAccount_qq())){
						account.setAccount_qq(null);
					}
					result = accountMapper.queryAccountByWechat(account.getAccount_wechat());
				}else if(StringUtil.isValid(account.getAccount_qq())){
					account.setAccount_qq(EncryptUtil.base64Decode(account.getAccount_qq()));
					result = accountMapper.queryAccountByQQ(account.getAccount_qq());
				}else{
					if(StringUtil.isValid(account.getAccount_userphone()) && StringUtil.isValid(account.getAccount_password())){
						account.setAccount_userphone(EncryptUtil.base64Decode(account.getAccount_userphone()));
						String pwd = accountMapper.queryPwdByPhone(account.getAccount_userphone());
						if(StringUtil.isValid(pwd)){
							account.setAccount_password(EncryptUtil.base64Decode(account.getAccount_password()));
							if(account.getAccount_password().equalsIgnoreCase(KeyUtils.decodepwd(pwd))){
								result = accountMapper.queryAccountByPhone(account.getAccount_userphone());
							}else{
								result.setCode(1);
								result.setMsg("密码错误，登录失败");
							}
						}else{
							result.setCode(1);
							result.setMsg("该手机号码未注册或未设置密码，登录失败");
						}
					}else{
						result.setCode(1);
						result.setMsg("手机号码或密码不能为空");
					}
				}
				if(result != null){
					if(result.getCode() == null){
						if(StringUtil.isValid(result.getAccount_icon()) && !result.getAccount_icon().startsWith("http")){
							result.setAccount_icon(PropertiesUtil.getKeyValue("img.showpath")+result.getAccount_icon());
						}
						if(result.getAccount_imei() == null || !result.getAccount_imei().equalsIgnoreCase(account.getAccount_imei())){							
							result.setIschange(1);
							Account imei = new Account();
							imei.setAccount_id(result.getAccount_id());
							imei.setAccount_imei(account.getAccount_imei());
							if(accountMapper.updateAccount(imei) > 0){
								result.setAccount_imei(account.getAccount_imei());
							}
						}
						if(result.getLast_time()==null || !sdf.format(new Date()).equals(sdf.format(result.getLast_time()))){
							result.setLogin_integral(addAccountIntegral(result.getAccount_id(),3));
						}
						accountMapper.updateLastLoginTime(result.getAccount_id());
						account.setAccount_id(result.getAccount_id());
						account.setAccount_userphone(PropertiesUtil.getKeyValue("advisephone"));
						if(accountMapper.checkAccountIdAndPhone(account) > 0){
							result.setIsboss(1);
						}
						result.setCode(0);
						result.setMsg("登录成功");
					}
				}else{
					result = new AccountLoginResult();
					result.setCode(1);
					result.setMsg("登录失败");
				}
			}else{
				result.setCode(1);
				result.setMsg("登录失败");
			}
		}catch(Exception e){
			result = new AccountLoginResult();
			result.setCode(1);
			result.setMsg("登录失败");
		}
		return result;
	}
	
	public Account queryAccountById(Integer account_id){
		return accountMapper.queryAccountById(account_id);
	}
	
	public Result updateaccount(Account account){
		Result result = new Result();
		try{
			if(account.getAccount_id()!=null && account.getAccount_id() >= 0){
				if(accountMapper.updateAccount(account) > 0){
					result.setCode(0);
					result.setMsg("更新成功");
				}else{
					result.setCode(1);
					result.setMsg("更新失败");
				}
			}else{
				result.setCode(1);
				result.setMsg("更新失败");
			}
		}catch(Exception e){
			result = new Result();
			result.setCode(1);
			result.setMsg("更新失败");
		}
		return result;
	}
	
	public AccountIconResult updateicon(Integer account_id,String account_icon){
		AccountIconResult result = new AccountIconResult();
		try{
			if(account_id!=null && account_id >= 0 && StringUtil.isValid(account_icon)){
				Account account = new Account();
				account.setAccount_id(account_id);
				account.setAccount_icon(account_icon);
				if(accountMapper.updateAccount(account) > 0){
					result.setCode(0);
					result.setMsg("更新成功");
				}else{
					result.setCode(1);
					result.setMsg("更新失败");
				}
			}else{
				result.setCode(1);
				result.setMsg("更新失败");
			}
		}catch(Exception e){
			result = new AccountIconResult();
			result.setCode(1);
			result.setMsg("更新失败");
		}
		return result;
	}
	
	public Result updatepwd(Integer account_id,String account_password,String account_newpassword){
		Result result = new Result();
		try{
			if(account_id!=null && account_id >= 0 && StringUtil.isValid(account_password) && StringUtil.isValid(account_newpassword)){
				String pwd = accountMapper.queryPwdById(account_id);
				if(StringUtil.isValid(pwd)){
					account_password = EncryptUtil.base64Decode(account_password);
					if(account_password.equalsIgnoreCase(KeyUtils.decodepwd(pwd))){
						account_newpassword = KeyUtils.encodepwd(EncryptUtil.base64Decode(account_newpassword));
						Account account = new Account();
						account.setAccount_id(account_id);
						account.setAccount_password(account_newpassword);
						if(accountMapper.updateAccount(account) > 0){
							result.setCode(0);
							result.setMsg("更新成功");
						}else{
							result.setCode(1);
							result.setMsg("更新失败");
						}
					}else{
						result.setCode(1);
						result.setMsg("原密码错误，更新失败");
					}
				}else{
					result.setCode(1);
					result.setMsg("该手机号码未注册或未设置密码，更新失败");
				}
			}else{
				result.setCode(1);
				result.setMsg("密码不能为空");
			}
		}catch(Exception e){
			result = new Result();
			result.setCode(1);
			result.setMsg("更新失败");
		}
		return result;
	}
	
	public AccountResetResult resetpwd(String account_userphone,String account_code,String account_newpassword){
		AccountResetResult result = new AccountResetResult();
		try{
			if(StringUtil.isValid(account_userphone) && StringUtil.isValid(account_code) && StringUtil.isValid(account_newpassword)){
				account_userphone = EncryptUtil.base64Decode(account_userphone);
				AccountCode accountcode = new AccountCode();
				accountcode.setAccount_userphone(account_userphone);
				accountcode.setAccount_code(account_code);
				if(accountMapper.checkAccountCode(accountcode) > 0){
					accountMapper.deleteAccountCode(account_userphone);
					Account account = new Account();
					account.setAccount_userphone(account_userphone);
					account_newpassword = KeyUtils.encodepwd(EncryptUtil.base64Decode(account_newpassword));
					account.setAccount_password(account_newpassword);
					if(accountMapper.updateAccountByPhone(account) > 0){
						result.setAccount_id(accountMapper.queryIdByPhone(account_userphone));
						result.setCode(0);
						result.setMsg("重置成功");
					}else{
						result.setCode(1);
						result.setMsg("重置失败");
					}
				}else{
					result.setCode(2);
					result.setMsg("验证码错误");
				}
			}else{
				result.setCode(1);
				result.setMsg("手机号码或验证码不能为空");
			}
		}catch(Exception e){
			result = new AccountResetResult();
			result.setCode(1);
			result.setMsg("重置失败");
		}
		return result;
	}
	
	public AccountQueryResult queryaccount(String startdate,String enddate,Integer isphone,Integer isnologin){
		AccountQueryResult result = new AccountQueryResult();
		try{
			result.setTotalreg(accountMapper.queryTotalAccount());
			if(StringUtil.isValid(startdate) && StringUtil.isValid(enddate)){
				if(isnologin!=null && isnologin == 1){
					result.setTotaltimereg(accountMapper.queryTotalTimeNoLoginAccount(startdate,enddate));
				}else{
					result.setTotaltimereg(accountMapper.queryTotalTimeAccount(startdate,enddate));
				}
				if(isphone!=null && isphone == 1){
					if((sdf.parse(enddate).getTime()-sdf.parse(startdate).getTime())/1000/60/60/24 <= 31){
						if(isnologin!=null && isnologin == 1){
							result.setPhonelist(accountMapper.queryTimeNoLoginAccount(startdate,enddate));
						}else{
							result.setPhonelist(accountMapper.queryTimeAccount(startdate,enddate));
						}
						result.setCode(0);
						result.setMsg("查询成功");
					}else{
						result.setCode(0);
						result.setMsg("汇总信息查询成功,但由于查询时间段超过31天手机号列表查询失败");
					}
				}else{
					result.setCode(0);
					result.setMsg("查询成功");
				}
			}else{
				result.setCode(0);
				result.setMsg("查询成功");
			}
		}catch(Exception e){
			result = new AccountQueryResult();
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public Result bindwechat(Integer account_id,String account_wechat){
		Result result = new Result();
		try{
			if(account_id!=null && account_id >= 0 && StringUtil.isValid(account_wechat)){
				account_wechat = EncryptUtil.base64Decode(account_wechat);
				Account account = new Account();
				account.setAccount_wechat(account_wechat);
				if(accountMapper.checkAccountWechat(account) <= 0){
					Account wechat = accountMapper.queryAccountWechatById(account_id);
					if(wechat != null){
						if(StringUtil.isEmpty(wechat.getAccount_wechat())){
							account.setAccount_id(account_id);
							if(accountMapper.updateAccount(account) > 0){
								result.setCode(0);
								result.setMsg("绑定成功");
							}else{
								result.setCode(1);
								result.setMsg("绑定失败");
							}
						}else{
							result.setCode(2);
							result.setMsg("该手机号已经绑定微信，不能重复绑定");
						}
					}else{
						result.setCode(1);
						result.setMsg("绑定失败");
					}
				}else{
					result.setCode(2);
					result.setMsg("微信已被其它用户绑定，不能重复绑定");
				}
			}else{
				result.setCode(1);
				result.setMsg("微信不能为空");
			}
		}catch(Exception e){
			result = new Result();
			result.setCode(1);
			result.setMsg("绑定失败");
		}
		return result;
	}
	
	public Result bindqq(Integer account_id,String account_qq){
		Result result = new Result();
		try{
			if(account_id!=null && account_id >= 0 && StringUtil.isValid(account_qq)){
				account_qq = EncryptUtil.base64Decode(account_qq);
				Account account = new Account();
				account.setAccount_qq(account_qq);
				if(accountMapper.checkAccountQQ(account) <= 0){
					Account qq = accountMapper.queryAccountQQById(account_id);
					if(qq != null){
						if(StringUtil.isEmpty(qq.getAccount_qq())){
							account.setAccount_id(account_id);
							if(accountMapper.updateAccount(account) > 0){
								result.setCode(0);
								result.setMsg("绑定成功");
							}else{
								result.setCode(1);
								result.setMsg("绑定失败");
							}
						}else{
							result.setCode(2);
							result.setMsg("该手机号已经绑定QQ，不能重复绑定");
						}
					}else{
						result.setCode(1);
						result.setMsg("绑定失败");
					}
				}else{
					result.setCode(2);
					result.setMsg("QQ已被其它用户绑定，不能重复绑定");
				}
			}else{
				result.setCode(1);
				result.setMsg("QQ不能为空");
			}
		}catch(Exception e){
			result = new Result();
			result.setCode(1);
			result.setMsg("绑定失败");
		}
		return result;
	}
	
	public Result changephone(Integer account_id,String account_userphone,String account_code){
		Result result = new Result();
		try{
			if(account_id!=null && account_id >= 0 && StringUtil.isValid(account_userphone)){
				account_userphone = EncryptUtil.base64Decode(account_userphone);
				if(account_userphone.matches("1\\d{10}")){
					Account account = new Account();
					account.setAccount_userphone(account_userphone);
					if(accountMapper.checkAccountPhone(account) <= 0){
						accountMapper.deleteAccountCode(account_userphone);
						account.setAccount_id(account_id);
						if(accountMapper.updateAccount(account) > 0){
							result.setCode(0);
							result.setMsg("修改成功");
						}else{
							result.setCode(1);
							result.setMsg("修改失败");
						}
					}else{
						result.setCode(2);
						result.setMsg("手机号码已经注册，不能修改成此手机号码");
					}
				}else{
					result.setCode(1);
					result.setMsg("手机号码格式错误");
				}
			}else{
				result.setCode(1);
				result.setMsg("手机号码不能为空");
			}
		}catch(Exception e){
			result = new Result();
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public InsertAdviseResult insertAdvise(Advise advise){
		InsertAdviseResult result = new InsertAdviseResult();
		if(advise!=null && advise.getAccount_id()!=null && advise.getAccount_id() > 0 && StringUtil.isValid(advise.getAdvise_content())){
			if(accountMapper.insertAdvise(advise) > 0){
				result.setCode(0);
				result.setMsg("添加成功");
				result.setAdvise_id(advise.getAdvise_id());
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
	
	public ListPageAdviseResult listPageAdvise(ListPageAdvise advise){
		ListPageAdviseResult result = new ListPageAdviseResult();
		if(advise!=null && advise.getAccount_id()!=null && advise.getAccount_id() > 0 && advise.getCurrentPage() > 0){
			Account account = new Account();
			account.setAccount_id(advise.getAccount_id());
			account.setAccount_userphone(PropertiesUtil.getKeyValue("advisephone"));
			if(accountMapper.checkAccountIdAndPhone(account) > 0){
				result.setList(accountMapper.listPageAdvise(advise));
				result.setTotalresult(advise.getTotalResult());
				result.setTotalpage(advise.getTotalPage());
				result.setCode(0);
				result.setMsg("查询成功");
			}else{
				result.setCode(1);
				result.setMsg("您无权查看意见反馈");
			}
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public InsertMemorandumResult insertMemorandum(Memorandum memorandum){
		InsertMemorandumResult result = new InsertMemorandumResult();
		if(memorandum!=null && memorandum.getAccount_id()!=null && memorandum.getAccount_id() > 0 && memorandum.getMemorandum_type()!=null && memorandum.getMemorandum_type() > 0 && StringUtil.isValid(memorandum.getMemorandum_content())){
			memorandum.setMemorandum_id(null);
			if(memorandum.getMemorandum_type() == 2 && accountMapper.checkMemorandum(memorandum) > 0){
				result.setCode(2);
				result.setMsg("内容不能重复");
			}else{
				if(accountMapper.insertMemorandum(memorandum) > 0){
					result.setCode(0);
					result.setMsg("添加成功");
					result.setMemorandum_id(memorandum.getMemorandum_id());
				}else{
					result.setCode(1);
					result.setMsg("添加失败");
				}
			}
		}else{
			result = new InsertMemorandumResult();
			result.setCode(1);
			result.setMsg("添加失败");
		}
		return result;
	}
	
	public Result updateMemorandum(Memorandum memorandum){
		Result result = new Result();
		if(memorandum!=null && memorandum.getMemorandum_id()!=null && memorandum.getMemorandum_id() > 0 && memorandum.getAccount_id()!=null && memorandum.getAccount_id() > 0 && memorandum.getMemorandum_type()!=null && memorandum.getMemorandum_type() > 0 && StringUtil.isValid(memorandum.getMemorandum_content())){
			if(memorandum.getMemorandum_type() == 2 && accountMapper.checkMemorandum(memorandum) > 0){
				result.setCode(2);
				result.setMsg("内容不能重复");
			}else{
				if(accountMapper.updateMemorandum(memorandum) > 0){
					result.setCode(0);
					result.setMsg("修改成功");
				}else{
					result.setCode(1);
					result.setMsg("修改失败");
				}
			}
		}else{
			result = new Result();
			result.setCode(1);
			result.setMsg("修改失败");
		}
		return result;
	}
	
	public Result deleteMemorandum(Memorandum memorandum){
		Result result = new Result();
		if(memorandum!=null && memorandum.getMemorandum_id()!=null && memorandum.getMemorandum_id() > 0 && memorandum.getAccount_id()!=null && memorandum.getAccount_id() > 0){
			if(accountMapper.deleteMemorandum(memorandum) > 0){
				result.setCode(0);
				result.setMsg("删除成功");
			}else{
				result.setCode(1);
				result.setMsg("删除失败");
			}
		}else{
			result = new Result();
			result.setCode(1);
			result.setMsg("删除失败");
		}
		return result;
	}
	
	public Result deleteMoreMemorandum(Integer account_id,String memorandum_ids){
		Result result = new Result();
		try{
			if(account_id!=null && account_id!=null && account_id > 0 && StringUtil.isValid(memorandum_ids)){
				Memorandum memorandum = new Memorandum();
				memorandum.setAccount_id(account_id);
				String[] ids = memorandum_ids.split(",");
				if(ids!=null){
					for(String id:ids){
						memorandum.setMemorandum_id(Integer.parseInt(id));
						accountMapper.deleteMemorandum(memorandum);
					}
					result.setCode(0);
					result.setMsg("删除成功");
				}else{
					result.setCode(1);
					result.setMsg("删除失败");
				}
			}else{
				result = new Result();
				result.setCode(1);
				result.setMsg("删除失败");
			}
		}catch(Exception e){
			result = new Result();
			result.setCode(1);
			result.setMsg("删除失败");
		}
		return result;
	}
	
	public ListPageMemorandumResult listPageMemorandum(ListPageMemorandum memorandum){
		ListPageMemorandumResult result = new ListPageMemorandumResult();
		if(memorandum!=null && memorandum.getAccount_id()!=null && memorandum.getAccount_id() > 0 && memorandum.getType()!=null && memorandum.getType() > 0 && memorandum.getCurrentPage() > 0){
			result.setList(accountMapper.listPageMemorandum(memorandum));
			result.setTotalresult(memorandum.getTotalResult());
			result.setTotalpage(memorandum.getTotalPage());
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	private void insertDefaultMemorandum(Integer account_id){
		Memorandum memorandum = new Memorandum();
		memorandum.setMemorandum_type(2);
		memorandum.setMemorandum_content("额外费用");
		memorandum.setAccount_id(account_id);
		insertMemorandum(memorandum);
	}
	
	public Integer addAccountIntegral(Integer account_id,Integer rule_type){
		Integer integralvalue = 0;
		if(account_id!=null && account_id > 0){
			IntegralRule integralrule = accountMapper.queryIntegralRule(rule_type);
			if(integralrule!=null && integralrule.getIntegral_value()!=null && integralrule.getIntegral_value() > 0){
				Account account = new Account();
				account.setAccount_id(account_id);
				account.setAccount_integral(integralrule.getIntegral_value());
				Integer old_value = accountMapper.queryAccountIntegral(account_id);
				if(old_value!=null){
					if(accountMapper.updateAccountIntegral(account) > 0){
						Integral integral = new Integral();
						integral.setExt_type(1);
						integral.setExt_id(integralrule.getRule_id());
						integral.setIntegral_value(integralrule.getIntegral_value());
						integral.setOld_value(old_value);
						integral.setNew_value(old_value.intValue() + integralrule.getIntegral_value().intValue());
						integral.setAccount_id(account_id);
						accountMapper.insertIntegral(integral);
						integralvalue = integralrule.getIntegral_value();
					}
				}
			}
		}
		return integralvalue;
	}

	@Transactional(rollbackFor = Exception.class)
	public DownloadResult downloadbrand(Integer account_id,Integer brand_id){
		DownloadResult result = new DownloadResult();
		if(account_id!=null && account_id > 0 && brand_id!=null && brand_id > 0){
			Brand brand = new Brand();
			brand.setAccount_id(account_id);
			brand.setBrand_from(brand_id);
			if(goodsMapper.checkRecommendBrand(brand) <= 0){
				String brand_name = goodsMapper.queryBrandNameById(brand_id);
				if(StringUtil.isValid(brand_name)){
					brand.setBrand_name(brand_name);
					BrandPrice brandprice = goodsMapper.queryBrandPrice(brand_id);
					if(brandprice!=null){
						Account account = new Account();
						account.setAccount_id(account_id);
						account.setAccount_integral(-brandprice.getBrand_price());
						Integer old_value = accountMapper.queryAccountIntegral(account_id);
						if(old_value!=null){
							if(old_value.intValue() >= brandprice.getBrand_price().intValue()){
								if(accountMapper.updateAccountIntegral(account) > 0){
									Integral integral = new Integral();
									integral.setExt_type(2);
									integral.setExt_id(brandprice.getBrandprice_id());
									integral.setIntegral_value(-brandprice.getBrand_price());
									integral.setOld_value(old_value);
									integral.setNew_value(old_value.intValue() - brandprice.getBrand_price());
									integral.setAccount_id(account_id);
									accountMapper.insertIntegral(integral);
									
									brand.setBrand_id(brand_id);
									brand.setAccount_id(0);
									List<AgentLevel> alist = goodsMapper.queryAgentLevel(brand);
									List<Goods> glist = goodsMapper.queryGoodsList(brand);
									List<Goods> newglist = new ArrayList<Goods>();
									List<GoodsPrice> gplist = new ArrayList<GoodsPrice>();
									if(alist!=null && !alist.isEmpty() && glist!=null && !glist.isEmpty()){
										brand.setAccount_id(account_id);
										if(goodsMapper.insertBrand(brand) > 0){
											Integer oldagentlevel = 0;
											Integer newagentlevel = 0;
											GoodsPrice goodsprice = null;
											GoodsPrice oldgoodsprice = null;
											GoodsPrice newgoodsprice = null;
											Goods oldgoods = null;
											Goods newgoods = null;
 											List<Integer> gidlist = new ArrayList<Integer>();
											int i=0;
											for(AgentLevel agentlevel:alist){
												if(agentlevel.getAgentlevel_default()!=null && agentlevel.getAgentlevel_default() == 1){
													agentlevel.setAccount_id(account_id);
													agentlevel.setBrand_id(brand.getBrand_id());
													if("零售价".equals(agentlevel.getAgentlevel_name())){
														agentlevel.setAgentlevel_default(1);
													}else{
														agentlevel.setAgentlevel_default(0);
													}
													oldagentlevel = agentlevel.getAgentlevel_id();
													goodsMapper.insertAgentLevel(agentlevel);
													newagentlevel = agentlevel.getAgentlevel_id();
		
													goodsprice = new GoodsPrice();
													goodsprice.setAccount_id(0);
													goodsprice.setAgentlevel_id(oldagentlevel);
													for(int j=0;j<glist.size();j++){
														oldgoods = glist.get(j);
														if(i==0){
															newgoods = new Goods();
															newgoods.setAccount_id(account_id);
															newgoods.setBrand_id(brand.getBrand_id());
															newgoods.setGoods_price(oldgoods.getGoods_price());
															newgoods.setGoods_name(oldgoods.getGoods_name());
															newgoods.setGoods_scale(oldgoods.getGoods_scale());
															goodsMapper.insertGoods(newgoods);
															goodsMapper.incBrandGoodsCount(brand.getBrand_id());
															gidlist.add(newgoods.getGoods_id());
															newglist.add(newgoods);
														}
														newgoodsprice = new GoodsPrice();
														goodsprice.setGoods_id(oldgoods.getGoods_id());
														oldgoodsprice = goodsMapper.queryGoodsPriceById(goodsprice);
														if(oldgoodsprice!=null){
															newgoodsprice.setGoods_price(oldgoodsprice.getGoods_price());
														}else{
															newgoodsprice.setGoods_price(0f);
														}
														newgoodsprice.setAccount_id(account_id);
														newgoodsprice.setGoods_id(gidlist.get(j));
														newgoodsprice.setAgentlevel_id(newagentlevel);
														goodsMapper.insertGoodsPrice(newgoodsprice);
														gplist.add(newgoodsprice);
													}
													i++;
												}else{
													agentlevel.setAccount_id(account_id);
													agentlevel.setBrand_id(brand.getBrand_id());
													if("零售价".equals(agentlevel.getAgentlevel_name())){
														agentlevel.setAgentlevel_default(1);
													}else{
														agentlevel.setAgentlevel_default(0);
													}
													goodsMapper.insertAgentLevel(agentlevel);
												}
											}
											result.setIntegral_value(integral.getIntegral_value());
											result.setOld_value(integral.getOld_value());
											result.setNew_value(integral.getNew_value());
											result.setCode(0);
											result.setMsg("下载成功");
											result.setBrand(brand);
											result.setAgentlevellist(alist);
											result.setGoodslist(newglist);
											result.setGoodspricelist(gplist);
										}else{
											result.setCode(1);
											result.setMsg("推荐品牌不存在");
										}
									}else{
										result.setCode(1);
										result.setMsg("推荐品牌不存在");
									}
								}else{
									result.setCode(1);
									result.setMsg("下载失败");
								}
							}else{
								result.setCode(1);
								result.setMsg("积分不足，不能下载");
							}
						}else{
							result.setCode(1);
							result.setMsg("下载失败");
						}
					}else{
						result.setCode(1);
						result.setMsg("推荐品牌不存在");
					}
				}else{
					result.setCode(1);
					result.setMsg("推荐品牌不存在");
				}
			}else{
				result.setCode(1);
				result.setMsg("推荐品牌已经下载，不能重复下载");
			}
		}else{
			result.setCode(1);
			result.setMsg("推荐品牌不存在");
		}
		return result;
	}

	public DownloadResult checkExchangeCode(Integer account_id, Integer brand_id, String code){
		DownloadResult result = new DownloadResult();
		if(brand_id == null || StringUtils.isBlank(code) ||account_id==null){
			result.setCode(1);
			result.setMsg("参数错误");
			return result;
		}
		Brand brand = new Brand();
		brand.setAccount_id(account_id);
		brand.setBrand_from(brand_id);
		if(goodsMapper.checkRecommendBrand(brand) > 0){
			result.setCode(1);
			result.setMsg("品牌已下载");
			return result;
		}
		try{
			int checkResult = goodsMapper.checkExchangeCode(brand_id, code);
			if(checkResult > 0){
				result.setCode(0);
				result.setMsg("兑换码正确，可以下载");
			}else {
				result.setCode(1);
				result.setMsg("兑换码不正确，下载失败");
			}
		}catch (Exception e){
			result.setCode(1);
			result.setMsg("兑换码不正确，下载失败");
		}
		return result;
	}

	public DownloadResult downloadbrandByCode(Integer account_id,Integer brand_id,String code){
		DownloadResult result = new DownloadResult();
		try {
			if(account_id!=null && account_id > 0 && brand_id!=null && brand_id > 0 && StringUtil.isValid(code) && goodsMapper.checkExchangeCode(brand_id,code) > 0){
				Brand brand = new Brand();
				brand.setAccount_id(account_id);
				brand.setBrand_from(brand_id);
				long checkRecommendBrandBegin = System.currentTimeMillis();
				if(goodsMapper.checkRecommendBrand(brand) <= 0){
					brand.setBrand_id(brand_id);
					brand.setAccount_id(0);
					List<AgentLevel> alist = goodsMapper.queryAgentLevel(brand);
					List<Goods> glist = goodsMapper.queryGoodsList(brand);
					List<Goods> newglist = new ArrayList<>();
					List<GoodsPrice> gplist = new ArrayList<>();
					if(alist!=null && !alist.isEmpty() && glist!=null && !glist.isEmpty()){
						Brand brandInfo = goodsMapper.getBrandInfo(brand_id);
						if(brandInfo!=null && StringUtil.isValid(brandInfo.getBrand_name())){
							brand.setBrand_name(brandInfo.getBrand_name());
							brand.setAccount_id(account_id);
							brand.setLogo_url(brandInfo.getLogo_url());
							brand.setTitle(brandInfo.getLogo_url());
							if(goodsMapper.insertBrand(brand) > 0){
								Integer oldagentlevel = 0;
								Integer newagentlevel = 0;
								GoodsPrice goodsprice = null;
								GoodsPrice oldgoodsprice = null;
								GoodsPrice newgoodsprice = null;
								Goods oldgoods = null;
								Goods newgoods = null;
								List<Integer> gidlist = new ArrayList<>();
								int i=0;
								for(AgentLevel agentlevel:alist){
									agentlevel.setAccount_id(account_id);
									agentlevel.setBrand_id(brand.getBrand_id());
									if("零售价".equals(agentlevel.getAgentlevel_name())){
										agentlevel.setAgentlevel_default(1);
									}else{
										agentlevel.setAgentlevel_default(0);
									}
									oldagentlevel = agentlevel.getAgentlevel_id();
									goodsMapper.insertAgentLevel(agentlevel);
									newagentlevel = agentlevel.getAgentlevel_id();
									goodsprice = new GoodsPrice();
									goodsprice.setAccount_id(0);
									goodsprice.setAgentlevel_id(oldagentlevel);
									for(int j=0;j<glist.size();j++){
										oldgoods = glist.get(j);
										goodsprice.setGoods_id(oldgoods.getGoods_id());
										oldgoodsprice = goodsMapper.queryGoodsPriceById(goodsprice);
										if(i==0){
											newgoods = new Goods();
											newgoods.setAccount_id(account_id);
											newgoods.setBrand_id(brand.getBrand_id());
	//										if("官方".equals(agentlevel.getAgentlevel_name())){
												if(oldgoodsprice!=null){
													newgoods.setGoods_price(oldgoodsprice.getGoods_price());
												}else{
													newgoods.setGoods_price(0f);
												}
	//										}else{
	//											newgoods.setGoods_price(0f);
	//										}
											//newgoods.setGoods_price(oldgoods.getGoods_price());
											newgoods.setGoods_name(oldgoods.getGoods_name());
											newgoods.setGoods_scale(oldgoods.getGoods_scale());
											goodsMapper.insertGoods(newgoods);
											goodsMapper.incBrandGoodsCount(brand.getBrand_id());
											gidlist.add(newgoods.getGoods_id());
											newglist.add(newgoods);
										}
										newgoodsprice = new GoodsPrice();
										if(oldgoodsprice!=null){
											newgoodsprice.setGoods_price(oldgoodsprice.getGoods_price());
										}else{
											newgoodsprice.setGoods_price(0f);
										}
										newgoodsprice.setAccount_id(account_id);
										newgoodsprice.setGoods_id(gidlist.get(j));
										newgoodsprice.setAgentlevel_id(newagentlevel);
	//									goodsMapper.insertGoodsPrice(newgoodsprice);
										gplist.add(newgoodsprice);
									}
									i++;
								}
								long insertGoodsPriceBegin = System.currentTimeMillis();
								logger.info("gplist size: "+gplist.size());
								executorService = Executors.newFixedThreadPool(20);
								executorService.execute(new Runnable() {
									@Override
									public void run() {
										goodsMapper.insertBatch(gplist);
										long insertGoodsPriceEnd = System.currentTimeMillis();
										logger.info("insertBatchGoodsPriceTime: "+ (insertGoodsPriceEnd - insertGoodsPriceBegin));
										logger.info("downloadbrandByCodeTime: "+ (insertGoodsPriceEnd- checkRecommendBrandBegin));
									}
								});
								executorService.shutdown();
								while(!executorService.awaitTermination(1, TimeUnit.SECONDS)){
									executorService.shutdownNow();
								}
								result.setCode(0);
								result.setMsg("下载成功");
								result.setBrand(brand);
								result.setAgentlevellist(alist);
								result.setGoodslist(newglist);
								result.setGoodspricelist(gplist);
							}else{
								result.setCode(1);
								result.setMsg("推荐品牌不存在");
							}
						}else{
							result.setCode(1);
							result.setMsg("推荐品牌不存在");
						}
					}else{
						result.setCode(1);
						result.setMsg("推荐品牌不存在");

					}
				}else{
					result.setCode(1);
					result.setMsg("推荐品牌已经下载，不能重复下载");
				}
			}else{
				result.setCode(1);
				result.setMsg("兑换码不正确，下载失败");
			}
		} catch (Exception e) {
			result.setCode(1);
			result.setMsg("系统忙");
			logger.error("downloadbrandByCode error: "+e.getMessage());
		}
		return result;
	}
	
	public AccountIntegral queryAccountIntegral(Integer account_id){
		AccountIntegral result = new AccountIntegral();
		try{
			if(account_id!=null && account_id >= 0){
				result.setAccount_integral(accountMapper.queryAccountIntegral(account_id));
				result.setCode(0);
				result.setMsg("查询成功");		
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
	
	public ListPageIntegralResult listPageIntegral(ListPageIntegral integral){
		ListPageIntegralResult result = new ListPageIntegralResult();
		if(integral!=null && integral.getAccount_id()!=null && integral.getAccount_id() > 0 && integral.getCurrentPage() > 0){
			result.setList(accountMapper.listPageIntegral(integral));
			result.setTotalresult(integral.getTotalResult());
			result.setTotalpage(integral.getTotalPage());
			result.setCode(0);
			result.setMsg("查询成功");
		}else{
			result.setCode(1);
			result.setMsg("查询失败");
		}
		return result;
	}
	
	public Integer queryShare(Integer share_id){
		return goodsMapper.queryShare(share_id);
	}

	public List<ShareQueryBrand> queryShareBrand(Integer share_id){
		return goodsMapper.queryShareBrand(share_id);
	}
	
	public ShareQueryBrand queryOneShareBrand(Integer share_id,Integer brand_id){
		return goodsMapper.queryOneShareBrand(share_id,brand_id);
	}
	
	public List<ShareQueryAgentLevel> queryShareAgentLevel(Integer sharebrand_id){
		return goodsMapper.queryShareAgentLevel(sharebrand_id);
	}
	
	public List<ShareQueryAgentLevel> queryShareAgentLevelByShare(Integer share_id,Integer brand_id){
		return goodsMapper.queryShareAgentLevelByShare(share_id,brand_id);
	}
	
	public ShareQueryAgentLevel queryOneShareAgentLevelByShare(Integer share_id,Integer brand_id,Integer agentlevel_id){
		return goodsMapper.queryOneShareAgentLevelByShare(share_id,brand_id,agentlevel_id);
	}
	
	public List<GoodsAgentLevelPrice> queryShareGoods(Integer brand_id,Integer agentlevel_id,Integer account_id){
		GoodsPriceQuery goodsPriceQuery = new GoodsPriceQuery();
		goodsPriceQuery.setBrand_id(brand_id);
		goodsPriceQuery.setAgentlevel_id(agentlevel_id);
		goodsPriceQuery.setAccount_id(account_id);
		return goodsMapper.queryGoodsAgentLevelPrice(goodsPriceQuery);
	}
	
	public Result shareUse(Integer account_id,Integer share_id){
		Result result = new Result();
		try{
			if(account_id!=null && account_id > 0 && share_id!=null && share_id > 0){
				ShareUse shareuse = new ShareUse();
				shareuse.setAccount_id(account_id);
				shareuse.setShare_id(share_id);
				if(goodsMapper.checkShareUse(shareuse)<=0){
					Integer shareaccount_id = goodsMapper.queryShare(share_id);
					if(shareaccount_id!=null && shareaccount_id > 0){
						if(account_id.intValue() != shareaccount_id.intValue()){
							if(goodsMapper.insertShareUse(shareuse) > 0){
								result.setCode(0);
								result.setMsg("使用成功");
							}else{
								result.setCode(1);
								result.setMsg("使用失败");
							}
						}else{
							result.setCode(1);
							result.setMsg("您不能使用自己分享的商品");
						}
					}else{
						result.setCode(1);
						result.setMsg("使用失败");
					}
				}else{
					result.setCode(2);
					result.setMsg("您已经使用好友分享的商品，不能重复使用");
				}
			}else{
				result.setCode(1);
				result.setMsg("使用失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			result.setCode(1);
			result.setMsg("使用失败");
		}
		return result;
	}
	
	public Result sharereg(Integer account_id,String account_userphone){
		Result result = new Result();
		try{
			if(account_id!=null && account_id > 0 && StringUtil.isValid(account_userphone)){
				Account account = new Account();
				account.setAccount_userphone(account_userphone);
				if(accountMapper.checkAccountPhone(account) <= 0){
					ShareAccount shareaccount = new ShareAccount();
					shareaccount.setAccount_id(account_id);
					shareaccount.setAccount_userphone(account_userphone);
					if(accountMapper.insertShareAccount(shareaccount) > 0){
						result.setCode(0);
						result.setMsg("领取成功，请下载番茄管家APP查看");
					}else{
						result.setCode(1);
						result.setMsg("领取失败");
					}
				}else{
					result.setCode(1);
					result.setMsg("仅限新用户领取");
				}
			}else{
				result.setCode(1);
				result.setMsg("领取失败");
			}
		}catch(Exception e){
			result = new Result();
			result.setCode(1);
			result.setMsg("领取失败");
		}
		return result;
	}

	public Object stopNew(Account account) {
		Result result;
		if(account==null || account.getAccount_id()==null){
			result = new Result();
			result.setCode(1);
			result.setMsg("设置失败");
			return result;
		}
		try {
			account.setIs_new(0);
			accountMapper.stopNew(account.getAccount_id());
			result = new Result();
			result.setCode(0);
			result.setMsg("设置成功");
		} catch (Exception e) {
			result = new Result();
			result.setCode(1);
			result.setMsg("设置失败");
		}
		return result;
	}
}