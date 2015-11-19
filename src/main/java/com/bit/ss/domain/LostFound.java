package com.bit.ss.domain;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Title: LostFound.java
 * @Package com.bit.ss.domain
 * @Description: 失物信息
 * @author CCX
 * @date 2015年10月15日 下午2:53:33
 * @version V1.0
 */
@XmlRootElement
public class LostFound {

	private int id;
	private int pubUserId;
	private Date pubTime;
	@JsonIgnore
	private int validation; // 0：待审核，1：通过，2：未能通过审核
	private String title;
	private String content;
	private String location;
	private List<String> picUrls;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(List<String> picUrls) {
		this.picUrls = picUrls;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPubUserId() {
		return pubUserId;
	}

	public void setPubUserId(int pubUserId) {
		this.pubUserId = pubUserId;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public int getValidation() {
		return validation;
	}

	public void setValidation(int validation) {
		this.validation = validation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
