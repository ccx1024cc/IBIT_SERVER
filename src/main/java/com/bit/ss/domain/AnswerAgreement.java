package com.bit.ss.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: AnswerAgreement.java
 * @Package com.bit.ss.domain
 * @Description: 回答赞同信息
 * @author CCX
 * @date 2015年10月15日 下午5:55:29
 * @version V1.0
 */
@XmlRootElement
public class AnswerAgreement {

	private int id;
	private int userId;
	private int answerId;
	private Date time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
