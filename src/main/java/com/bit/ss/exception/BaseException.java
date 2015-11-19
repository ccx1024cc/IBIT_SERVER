package com.bit.ss.exception;

/**
 * @Title: BaseException.java
 * @Package com.bit.ss.exception
 * @Description: 系统异常基类
 * @author CCX
 * @date 2015年10月29日 上午9:41:52
 * @version V1.0
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -5720861015297238892L;
	// message key
	private String code;
	// message params
	private Object[] values;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public BaseException(String message, Throwable cause, String code, Object[] values) {
		super(message, cause);
		this.code = code;
		this.values = values;
	}
}
