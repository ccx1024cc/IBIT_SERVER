package com.bit.ss.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: BusNews.java
 * @Package com.bit.ss.domain
 * @Description: 公交新闻
 * @author CCX
 * @date 2015年10月15日 下午3:05:49
 * @version V1.0
 */
@XmlRootElement
public class BusNews implements DefinedBean {

	private int id;
	private String title;
	private String url;
	private Date pubTime;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

}
