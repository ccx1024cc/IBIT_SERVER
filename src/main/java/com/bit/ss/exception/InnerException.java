package com.bit.ss.exception;

/**   
* @Title: InnerException.java 
* @Package com.bit.ss.exception 
* @Description:  屏蔽在系统内部的异常
* @author CCX
* @date 2016年1月31日 下午5:10:08 
* @version V1.0   
*/
public class InnerException extends BaseException {

	private static final long serialVersionUID = 7971051827217851515L;
	protected Exception UnexceptedException;// 非正常情况下产生的异常

	public InnerException() {
		this.shortcut = "INNER_BASEEXCEPTION";
		this.description = "系统内部异常";
	}

	@Override
	public String toString() {
		// 如果不是包装异常的情况，则对异常进行拼接
		if (this.UnexceptedException != null) {
			String message = "innerException : shortcut : " + this.shortcut + ",description : "
					+ this.description + "\n";
			return message;
		} else {
			return "unexceptedException : " + this.UnexceptedException.toString();
		}
	}
	
	public Exception getUnexceptedException() {
		return UnexceptedException;
	}

	public void setUnexceptedException(Exception unexceptedException) {
		UnexceptedException = unexceptedException;
	}
}
