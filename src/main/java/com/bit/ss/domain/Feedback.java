package com.bit.ss.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: Feedback.java
 * @Package com.bit.ss.domain
 * @Description: 反馈信息
 * @author CCX
 * @date 2015年10月15日 下午2:59:02
 * @version V1.0
 */
@XmlRootElement
public class Feedback implements DefinedBean{

	private int id;
	private int userId;
	private int platForm;// 反馈信息平台，0：Android，1：IOS，2：PC
	private String content;
	private int status;// 反馈问题状态，0：待解决，1：已解决
	private Date pubTime;
	private Date fixedTime;
	private String fixedPerson;

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

	public int getPlatForm() {
		return platForm;
	}

	public void setPlatForm(int platForm) {
		this.platForm = platForm;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public Date getFixedTime() {
		return fixedTime;
	}

	public void setFixedTime(Date fixedTime) {
		this.fixedTime = fixedTime;
	}

	public String getFixedPerson() {
		return fixedPerson;
	}

	public void setFixedPerson(String fixedPerson) {
		this.fixedPerson = fixedPerson;
	}

}
