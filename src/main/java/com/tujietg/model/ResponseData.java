package com.tujietg.model;

/**
 * @author tujietg
 *
 */
public class ResponseData {
	// 返回数据的状态码
	private int code;
	// 返回信息
	private String msg;
	// 返回数据
	private Object data;

	public int getCode() {
		return code;
	}

	public ResponseData(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
