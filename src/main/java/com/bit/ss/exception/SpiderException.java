package com.bit.ss.exception;

import com.bit.ss.domain.ExceptionCode;

/**   
 * @Title: SpiderException.java 
 * @Package com.bit.ss.exception 
 * @Description:  爬虫出现错误
 * @author CCX
 * @date 2015年11月28日 下午2:12:39 
 * @version V1.0   
 */
public class SpiderException extends BaseException{

	private static final long serialVersionUID = -2848809211024422546L;

	public SpiderException(String message, Throwable cause) {
		super(message, cause, ExceptionCode.SPIDER_ERROR, null);
	}
}
