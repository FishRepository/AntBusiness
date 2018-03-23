package com.backend.common;

import java.io.Serializable;

public class RequestResultVO<T> implements Serializable {
	private static final long serialVersionUID = -72731801745817847L;

	private boolean success=false;
	private String errorCode;
	private String errorMessage;
	private T data;
	
	public void error(ErrorEnums error){
		this.success=false;
		this.errorCode=error.getCode();
		this.errorMessage=error.getMessage();
	}
	
	public void error(String errorCode, String message){
		this.success=false;
		this.errorCode=errorCode;
		this.errorMessage=message;
	}
	
	public void success(T data){
		this.success=true;
		this.data=data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
