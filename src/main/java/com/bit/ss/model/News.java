package com.bit.ss.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.bit.ss.jackson.JsonDateDeserializer;
import com.bit.ss.jackson.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Title: News.java
 * @Package com.bit.ss.domain
 * @Description: 新闻信息
 * @author CCX
 * @date 2015年10月15日 下午2:47:30
 * @version V1.0
 */
@XmlRootElement
public class News {

	private Integer id;
	private String title;
	private String content;
	private Date pubTime;
	/**
	 * 新闻类型,0 新闻快讯; 1 教务处通知;
	 * 2 校方通知;3 招聘就业;4 本科招生;
	 * 5 研究生招生; 6 学工事务;7 讲座预告;
	 * 8 教育教学; 9 学术研究;10 网络通告;
	 * 11 行政办公;12 人事公告;13 外事交流;
	 * 14 生活琐事,15校车信息
	 */
	private Integer type;
	private String url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getPubTime() {
		return pubTime;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
