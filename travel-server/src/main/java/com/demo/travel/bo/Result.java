package com.demo.travel.bo;

import java.util.Date;

public class Result<T> {

	// 错误码
	private Integer code;

	// 提示信息
	private String msg;

	// 具体内容
	private T data;

	// 时间戳
	private Long timestamp;

	public Result() {
		this.timestamp = new Date().getTime();
	}

	public Long getTimestamps() {
		return timestamp;
	}

	public void setTimestamps(Long timestamps) {
		this.timestamp = new Date().getTime();
	}

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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result{" +
		        "code=" + code +
		        ", msg='" + msg + '\'' +
		        ", data=" + data +
		        '}';
	}
}