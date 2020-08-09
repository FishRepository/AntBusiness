package com.api.account.entity;

import java.util.Date;

import com.api.common.entity.Result;

public class AccountLoginResult extends Result{

	private Integer account_id;// 管理员编号
	private String account_userphone;// 管理员手机号码
	private String account_username;// 管理员姓名
	private String account_address;// 管理员地址
	private String account_icon;// 管理员头像
	private String account_qq;// 管理员QQ
	private String account_wechat;// 管理员微信
	private String account_imei;// 手机终端imei
	private Integer account_integral;//账号总积分
	private Date create_time;// 创建时间
	private Date last_time;// 上次登录时间
	private Integer ischange = 0;//imei是否已经改变  0未改变 1已改变
	private Integer isboss = 0;//是否老板账号，用于显示问题反馈还是显示反馈列表
	private Integer login_integral;//登录积分
	private Date vip_time;//vip到期时间
	private Integer is_vip;//是否vip 0不是,1是
	private Integer vip_type;//1，月费会员2，年费会员
	private Integer is_new;//是否新用户 1是 2不是
	private Integer notify_num;//用户提醒小红点数
	
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public String getAccount_userphone() {
		return account_userphone;
	}
	public void setAccount_userphone(String account_userphone) {
		this.account_userphone = account_userphone;
	}
	public String getAccount_username() {
		//if(StringUtil.isValid(account_username)){
			return account_username;
		//}else{
		//	return account_userphone;
		//}
	}
	public void setAccount_username(String account_username) {
		this.account_username = account_username;
	}
	public String getAccount_address() {
		return account_address;
	}
	public void setAccount_address(String account_address) {
		this.account_address = account_address;
	}
	public String getAccount_icon() {
		return account_icon;
	}
	public void setAccount_icon(String account_icon) {
		this.account_icon = account_icon;
	}
	public String getAccount_qq() {
		return account_qq;
	}
	public void setAccount_qq(String account_qq) {
		this.account_qq = account_qq;
	}
	public String getAccount_wechat() {
		return account_wechat;
	}
	public void setAccount_wechat(String account_wechat) {
		this.account_wechat = account_wechat;
	}
	public String getAccount_imei() {
		return account_imei;
	}
	public void setAccount_imei(String account_imei) {
		this.account_imei = account_imei;
	}
	public Integer getAccount_integral() {
		return account_integral;
	}
	public void setAccount_integral(Integer account_integral) {
		this.account_integral = account_integral;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getLast_time() {
		return last_time;
	}
	public void setLast_time(Date last_time) {
		this.last_time = last_time;
	}
	public Integer getIschange() {
		return ischange;
	}
	public void setIschange(Integer ischange) {
		this.ischange = ischange;
	}
	public Integer getIsboss() {
		return isboss;
	}
	public void setIsboss(Integer isboss) {
		this.isboss = isboss;
	}
	public Integer getLogin_integral() {
		return login_integral;
	}
	public void setLogin_integral(Integer login_integral) {
		this.login_integral = login_integral;
	}

	public Date getVip_time() {
		return vip_time;
	}

	public void setVip_time(Date vip_time) {
		this.vip_time = vip_time;
	}

	public Integer getIs_vip() {
		return is_vip;
	}

	public void setIs_vip(Integer is_vip) {
		this.is_vip = is_vip;
	}

	public Integer getVip_type() {
		return vip_type;
	}

	public void setVip_type(Integer vip_type) {
		this.vip_type = vip_type;
	}

	public Integer getIs_new() {
		return is_new;
	}

	public void setIs_new(Integer is_new) {
		this.is_new = is_new;
	}

	public Integer getNotify_num() {
		return notify_num;
	}

	public void setNotify_num(Integer notify_num) {
		this.notify_num = notify_num;
	}
}