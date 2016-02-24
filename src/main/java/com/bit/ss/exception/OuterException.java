package com.bit.ss.exception;

import javax.ws.rs.core.Response.Status;

/**   
 * @Title: OuterException.java 
 * @Package com.bit.ss.exception 
 * @Description:  可向服务器外传播的异常
 * @author CCX
 * @date 2016年1月31日 下午5:24:02 
 * @version V1.0   
 */
public class OuterException extends BaseException {

	private static final long serialVersionUID = -4703136208727646524L;

	public static final String CHECKCODE_WRONG = "CHECKCODE_WRONG";
	public static final String PHONE_REGISTED = "PHONE_REGISTED";
	public static final String CHECKCODE_NOT_CHECKED = "CHECKCODE_NOT_CHECKED";
	public static final String LOGIN_PWDERROR = "LOGIN_PWDERROR";
	public static final String NOT_LOGIN = "NOT_LOGIN";
	public static final String NOT_ENOUGH_AUTH = "NOT_ENOUGH_AUTH";
	public static final String ICON_FORMAT_UNACCEPTABLE = "ICON_FORMAT_UNACCEPTABLE";
	public static final String QUESTION_HAS_RESOLVED = "QUESTION_HAS_RESOLVED";
	public static final String GOOD_STALED = "GOOD_STALED";
	public static final String HAS_AGREED = "HAS_AGREED";

	protected Status status; // 网络传播代码

	public OuterException() {
		this.shortcut = "OUTER_BASEEXCEPTION";
		this.description = "系统可向外传播异常";
	}

	public OuterException(Status status, String shortcut) {
		this.status = status;
		this.shortcut = shortcut;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
