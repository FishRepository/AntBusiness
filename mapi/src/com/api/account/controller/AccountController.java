package com.api.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.api.account.entity.Account;
import com.api.account.entity.AccountIconResult;
import com.api.account.entity.AccountLoginResult;
import com.api.account.entity.AccountRegResult;
import com.api.account.entity.AccountResetResult;
import com.api.account.entity.Advise;
import com.api.account.entity.ListPageAdvise;
import com.api.account.entity.ListPageIntegral;
import com.api.account.entity.ListPageMemorandum;
import com.api.account.entity.Memorandum;
import com.api.account.service.AccountService;
import com.api.common.entity.Images;
import com.api.common.entity.Result;
import com.api.common.service.ImagesService;
import com.api.common.utils.EncryptUtil;
import com.api.goods.entity.GoodsAgentLevelPrice;
import com.api.goods.entity.ShareQueryAgentLevel;
import com.api.goods.entity.ShareQueryBrand;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ImagesService imagesService;
	
	@RequestMapping(value = "/sendcode")
    @ResponseBody
	public Object sendcode(String account_userphone,Integer type){
		return accountService.sendcode(account_userphone,type);
	}
	
	@RequestMapping(value = "/checkcode")
    @ResponseBody
	public Object checkcode(String account_userphone,String account_code){
		return accountService.checkcode(account_userphone,account_code);
	}
	
	@RequestMapping(value = "/reg")
    @ResponseBody
	public Object reg(Account account,String account_code){
		return accountService.reg(account, account_code);
	}
	
	@RequestMapping(value = "/login")
    @ResponseBody
	public Object insertBrand(Account account){
		return accountService.login(account);
	}

	@RequestMapping(value = "/stopNewAccount")
	@ResponseBody
	public Object stopNewAccount(Account account){
		return accountService.stopNew(account);
	}
	
	@RequestMapping(value = "/updateaccount")
    @ResponseBody
	public Object updateaccount(Account account){
		return accountService.updateaccount(account);
	}
	
	@RequestMapping(value = "/updateicon")
    @ResponseBody
	public Object updateicon(Integer account_id,MultipartFile file){
		AccountIconResult result = null;
		Images images = imagesService.uploadImages(file,"icon",null);
		if(images!=null){
			if(images.getCode() == 0){
				result = accountService.updateicon(account_id,images.getPath());
				if(result!=null){
					if(result.getCode() == 0){
						result.setUrlPath(images.getUrlPath());
					}
				}else{
					result = new AccountIconResult();
					result.setCode(1);
					result.setMsg("上传失败");
				}
			}else{
				result = new AccountIconResult();
				result.setCode(images.getCode());
				result.setMsg(images.getMsg());
			}
		}else{
			result = new AccountIconResult();
			result.setCode(1);
			result.setMsg("上传失败");
		}
		return result;
	}
	
	@RequestMapping(value = "/updatepwd")
    @ResponseBody
	public Object updatepwd(Integer account_id,String account_password,String account_newpassword){
		return accountService.updatepwd(account_id,account_password,account_newpassword);
	}
	
	@RequestMapping(value = "/resetpwd")
    @ResponseBody
	public Object resetpwd(String account_userphone,String account_code,String account_newpassword){
		return accountService.resetpwd(account_userphone,account_code,account_newpassword);
	}
	
	@RequestMapping(value = "/queryaccount")
    @ResponseBody
	public Object queryaccount(String startdate,String enddate,Integer isphone,Integer isnologin){
		return accountService.queryaccount(startdate,enddate,isphone,isnologin);
	}
	
	@RequestMapping(value = "/bindwechat")
    @ResponseBody
	public Object bindwechat(Integer account_id,String account_wechat){
		return accountService.bindwechat(account_id,account_wechat);
	}
	
	@RequestMapping(value = "/bindqq")
    @ResponseBody
	public Object bindqq(Integer account_id,String account_qq){
		return accountService.bindqq(account_id,account_qq);
	}
	
	@RequestMapping(value = "/changephone")
    @ResponseBody
	public Object changephone(Integer account_id,String account_userphone,String account_code){
		return accountService.changephone(account_id,account_userphone,account_code);
	}
	
	@RequestMapping(value = "/insertAdvise")
    @ResponseBody
	public Object insertAdvise(Advise advise){
		return accountService.insertAdvise(advise);
	}
	
	@RequestMapping(value = "/listPageAdvise")
    @ResponseBody
	public Object listPageAdvise(ListPageAdvise advise){
		return accountService.listPageAdvise(advise);
	}
	
	@RequestMapping(value = "/insertMemorandum")
    @ResponseBody
	public Object insertMemorandum(Memorandum memorandum){
		return accountService.insertMemorandum(memorandum);
	}
	
	@RequestMapping(value = "/updateMemorandum")
    @ResponseBody
	public Object updateMemorandum(Memorandum memorandum){
		return accountService.updateMemorandum(memorandum);
	}
	
	@RequestMapping(value = "/deleteMemorandum")
    @ResponseBody
	public Object deleteMemorandum(Memorandum memorandum){
		return accountService.deleteMemorandum(memorandum);
	}
	
	@RequestMapping(value = "/deleteMoreMemorandum")
    @ResponseBody
	public Object deleteMoreMemorandum(Integer account_id,String memorandum_ids){
		return accountService.deleteMoreMemorandum(account_id,memorandum_ids);
	}
	
	@RequestMapping(value = "/listPageMemorandum")
    @ResponseBody
	public Object listPageMemorandum(ListPageMemorandum memorandum){
		return accountService.listPageMemorandum(memorandum);
	}
	
	@RequestMapping(value = "/downloadbrand")
    @ResponseBody
	public Object downloadbrand(Integer account_id,Integer brand_id){
		return accountService.downloadbrand(account_id,brand_id);
	}
	
	@RequestMapping(value = "/downloadbrandByCode")
    @ResponseBody
	public Object downloadbrandByCode(Integer account_id,Integer brand_id,String code){
		return accountService.downloadbrandByCode(account_id,brand_id,code);
	}

	@RequestMapping(value = "/checkExchangeCode")
	@ResponseBody
	public Object checkExchangeCode(Integer account_id, Integer brand_id, String code){
		return accountService.checkExchangeCode(account_id,brand_id,code);
	}

	@RequestMapping(value = "/queryAccountIntegral")
    @ResponseBody
	public Object queryAccountIntegral(Integer account_id){
		return accountService.queryAccountIntegral(account_id);
	}
	
	@RequestMapping(value = "/listPageIntegral")
    @ResponseBody
	public Object listPageIntegral(ListPageIntegral integral){
		return accountService.listPageIntegral(integral);
	}
	
	@RequestMapping(value = "/sharebrand")
	public ModelAndView sharebrand(Integer share_id,Integer brand_id,Integer agentlevel_id,Integer download_id){
		ModelAndView mv = new ModelAndView();
		mv.addObject("share_id", share_id);
		Integer account_id = accountService.queryShare(share_id);
		if(account_id!=null && account_id > 0){
			List<ShareQueryBrand> brandlist = accountService.queryShareBrand(share_id);
			if(brandlist!=null && !brandlist.isEmpty()){
				if(download_id!=null && download_id > 0){
					mv.addObject("brandlist", brandlist);
				}
				ShareQueryBrand brand = null;
				List<ShareQueryAgentLevel> agentlevellist = null;
				if(brand_id!=null && brand_id > 0){
					brand = accountService.queryOneShareBrand(share_id,brand_id);
					agentlevellist = accountService.queryShareAgentLevelByShare(share_id,brand_id);
				}else{
					brand = brandlist.get(0);
					brand_id = brand.getBrand_id();
					agentlevellist = accountService.queryShareAgentLevel(brandlist.get(0).getSharebrand_id());
				}
				mv.addObject("brand", brand);
				mv.addObject("brand_id", brand_id);
				if(agentlevellist!=null && !agentlevellist.isEmpty()){
					if(download_id!=null && download_id > 0){
						mv.addObject("agentlevellist", agentlevellist);
					}
					ShareQueryAgentLevel agentlevel = null;
					if(agentlevel_id==null || agentlevel_id <= 0){
						agentlevel = agentlevellist.get(0);
						agentlevel_id = agentlevel.getAgentlevel_id();
					}else{
						agentlevel = accountService.queryOneShareAgentLevelByShare(share_id,brand_id,agentlevel_id);
					}
					mv.addObject("agentlevel", agentlevel);
					List<GoodsAgentLevelPrice> goodslist = accountService.queryShareGoods(brand_id, agentlevel_id, account_id);
					if(goodslist!=null && !goodslist.isEmpty()){
						mv.addObject("goodslist", goodslist);
					}
				}
			}
		}
		if(download_id!=null && download_id > 0){
			mv.addObject("download_id", download_id);
			mv.setViewName("sharebranduse");
		}else{
			mv.setViewName("sharebrandshow");
		}
		return mv;
	}
	
	@RequestMapping(value = "/wapreg")
	public ModelAndView wapreg(Integer share_id){
		ModelAndView mv = new ModelAndView();
		mv.addObject("share_id",share_id);
		mv.addObject("account_guideid",accountService.queryShare(share_id));
		mv.setViewName("wapreg");
		return mv;
	}
	
	@RequestMapping(value = "/wapsendcode")
    @ResponseBody
	public Object wapsendcode(String account_userphone){
		account_userphone = EncryptUtil.base64Encode(account_userphone);
		return accountService.sendcode(account_userphone,1);
	}

	@RequestMapping(value = "/wapregsave")
	public ModelAndView wapregsave(Account account,Integer share_id,String account_code){
		ModelAndView mv = new ModelAndView();
		account.setAccount_userphone(EncryptUtil.base64Encode(account.getAccount_userphone()));
		account.setAccount_password(EncryptUtil.base64Encode(account.getAccount_password()));
		account.setAccount_imei("0");
		AccountRegResult result = accountService.reg(account, account_code);
		if(result!=null){
			if(result.getCode() == 0){
				mv.setViewName("redirect:/account/sharebrand.do?share_id="+share_id+"&download_id="+result.getAccount_id());
			}else{
				mv.addObject("share_id",share_id);
				mv.addObject("msg", result.getMsg());
				mv.setViewName("wapreg");
			}
		}else{
			mv.addObject("share_id",share_id);
			mv.setViewName("wapreg");
		}
		return mv;
	}
	
	@RequestMapping(value = "/waplogin")
	public ModelAndView waplogin(Integer share_id){
		ModelAndView mv = new ModelAndView();
		mv.addObject("share_id",share_id);
		mv.setViewName("waplogin");
		return mv;
	}
	
	@RequestMapping(value = "/waploginsave")
	public ModelAndView waploginsave(Account account,Integer share_id){
		ModelAndView mv = new ModelAndView();
		account.setAccount_userphone(EncryptUtil.base64Encode(account.getAccount_userphone()));
		account.setAccount_password(EncryptUtil.base64Encode(account.getAccount_password()));
		account.setAccount_imei("0");
		AccountLoginResult result = accountService.login(account);
		if(result!=null){
			if(result.getCode() == 0){
				mv.setViewName("redirect:/account/sharebrand.do?share_id="+share_id+"&download_id="+result.getAccount_id());
			}else{
				mv.addObject("share_id",share_id);
				mv.addObject("msg", result.getMsg());
				mv.setViewName("waplogin");
			}
		}else{
			mv.addObject("share_id",share_id);
			mv.setViewName("waplogin");
		}
		return mv;
	}
	
	@RequestMapping(value = "/wapfind")
	public ModelAndView wapfind(Integer share_id){
		ModelAndView mv = new ModelAndView();
		mv.addObject("share_id",share_id);
		mv.setViewName("wapfind");
		return mv;
	}
	
	@RequestMapping(value = "/wapfindcode")
    @ResponseBody
	public Object wapfindcode(String account_userphone){
		account_userphone = EncryptUtil.base64Encode(account_userphone);
		return accountService.sendcode(account_userphone,2);
	}
	
	@RequestMapping(value = "/wapfindsave")
	public ModelAndView wapfindsave(Account account,Integer share_id,String account_code){
		ModelAndView mv = new ModelAndView();
		account.setAccount_userphone(EncryptUtil.base64Encode(account.getAccount_userphone()));
		account.setAccount_password(EncryptUtil.base64Encode(account.getAccount_password()));
		AccountResetResult result = accountService.resetpwd(account.getAccount_userphone(), account_code, account.getAccount_password());
		if(result!=null){
			if(result.getCode() == 0){
				mv.setViewName("redirect:/account/sharebrand.do?share_id="+share_id+"&download_id="+result.getAccount_id());
			}else{
				mv.addObject("share_id",share_id);
				mv.addObject("msg", result.getMsg());
				mv.setViewName("wapfind");
			}
		}else{
			mv.addObject("share_id",share_id);
			mv.setViewName("wapfind");
		}
		return mv;
	}
	
	@RequestMapping(value = "/shareUse")
    @ResponseBody
	public Object shareUse(Integer account_id,Integer share_id){
		return accountService.shareUse(account_id,share_id);
	}
	
	@RequestMapping(value = "/wapsharereg")
	public ModelAndView wapsharereg(Integer account_id){
		ModelAndView mv = new ModelAndView();
		mv.addObject("account_id",account_id);
		mv.setViewName("wapsharereg");
		return mv;
	}
	
	@RequestMapping(value = "/wapsharesave")
	public ModelAndView wapsharereg(Integer account_id,String account_userphone){
		ModelAndView mv = new ModelAndView();
		Result result = accountService.sharereg(account_id,account_userphone);
		if(result!=null){
			mv.addObject("account_id",account_id);
			mv.addObject("code", result.getCode());
			mv.addObject("msg", result.getMsg());
			mv.setViewName("wapsharereg");
		}else{
			mv.addObject("account_id",account_id);
			mv.addObject("code", 1);
			mv.addObject("msg", "领取失败");
			mv.setViewName("wapsharereg");
		}
		return mv;
	}
	
	@RequestMapping(value = "/wapdownload")
	public ModelAndView wapDownLoad(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("wapdownload");
		return mv;
	}

	@RequestMapping(value = "/androiddownload")
	public ModelAndView androiddownLoad(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("androiddownload");
		return mv;
	}
}