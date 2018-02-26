package com.api.common.entity;


public class Result {
	private Integer code;  //结果编号
	private String msg;  //结果描述
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}