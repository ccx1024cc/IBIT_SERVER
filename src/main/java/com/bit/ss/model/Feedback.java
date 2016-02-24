package com.bit.ss.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.bit.ss.jackson.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Title: Feedback.java
 * @Package com.bit.ss.domain
 * @Description: 反馈信息
 * @author CCX
 * @date 2015年10月15日 下午2:59:02
 * @version V1.0
 */
@XmlRootElement
public class Feedback {

	private Integer id;
	private Integer platForm;// 反馈信息平台，0：Android，1：IOS，2：PC
	private String content;
	private Integer status;// 反馈问题状态，0：待解决，1：已解决,2：设计如此
	private Date pubTime;
	private Date fixedTime;
	private String fixedPerson;
	private User publisher;// 反馈者

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlatForm() {
		return platForm;
	}

	public void setPlatForm(Integer platForm) {
		this.platForm = platForm;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
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

	public User getPublisher() {
		return publisher;
	}

	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}

}
