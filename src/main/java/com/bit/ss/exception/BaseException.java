package com.bit.ss.exception;

/**   
* @Title: BaseException.java 
* @Package com.bit.ss.exception 
* @Description:  异常基类
* @author CCX
* @date 2016年1月31日 下午5:03:00 
* @version V1.0   
*/
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 2440817944498475788L;

	protected String shortcut = "BASE";
	protected String description = "异常基类";
	

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
