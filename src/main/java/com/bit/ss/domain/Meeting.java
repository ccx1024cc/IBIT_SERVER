package com.bit.ss.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: Meeting.java
 * @Package com.bit.ss.domain
 * @Description: 会议信息
 * @author CCX
 * @date 2015年10月15日 下午2:34:09
 * @version V1.0
 */
@XmlRootElement
public class Meeting implements DefinedBean{

	private int id;
	private String title;
	private String content;
	private String place;
	private String peopleIn;
	private Date time;
	private String dayOfWeek;
	private String remarks;
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPeopleIn() {
		return peopleIn;
	}

	public void setPeopleIn(String peopleIn) {
		this.peopleIn = peopleIn;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
