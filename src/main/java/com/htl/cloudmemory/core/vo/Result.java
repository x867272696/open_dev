package com.htl.cloudmemory.core.vo;

public class Result {
	
	private String code = "0".intern();
	
	private String message = "success".intern();
	
	private Object data = null;

	public Result(String code, String message, Object obj){
		this.code = code;
		this.message = message;
		this.data = obj;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
