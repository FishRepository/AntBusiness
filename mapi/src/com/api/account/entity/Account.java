package com.api.account.entity;

import java.util.Date;

public class Account{

	private Integer account_id;// 管理员编号
	private String account_userphone;// 管理员手机号码
	private String account_password;// 登录密码（密文）
	private String account_username;// 管理员姓名
	private String account_address;// 管理员地址
	private String account_icon;// 管理员头像
	private String account_qq;// 管理员QQ
	private String account_wechat;// 管理员微信
	private String account_imei;// 手机终端imei
	private Integer account_integral;//账号总积分
	private Integer account_guideid;//引导注册账号
	private Date create_time;// 创建时间
	private Date update_time;// 更新时间
	private Integer state;// 状态0无效1有效
	private Date last_time;//上次登录时间
	private Date vip_time;//vip到期时间
	private Integer is_vip;//是否vip 0不是,1是
	private Integer vip_type;//1，月费会员2，年费会员
	private Integer is_new;//是否新用户 1是 2不是
	private Integer remain_time;//剩余vip天数

	//------------------2020年1月2日新增-------------
	private Date birthday;//生日
	private String email;//邮箱
	private String constellation;//星座
	private Date period;//经期

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
	public String getAccount_password() {
		return account_password;
	}
	public void setAccount_password(String account_password) {
		this.account_password = account_password;
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
	public Integer getAccount_guideid() {
		return account_guideid;
	}
	public void setAccount_guideid(Integer account_guideid) {
		this.account_guideid = account_guideid;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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

	public Date getLast_time() {
		return last_time;
	}

	public void setLast_time(Date last_time) {
		this.last_time = last_time;
	}

	public Integer getRemain_time() {
		return remain_time;
	}

	public void setRemain_time(Integer remain_time) {
		this.remain_time = remain_time;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public Date getPeriod() {
		return period;
	}

	public void setPeriod(Date period) {
		this.period = period;
	}
}