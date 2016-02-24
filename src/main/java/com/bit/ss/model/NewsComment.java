package com.bit.ss.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.bit.ss.jackson.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Title: NewsComment.java
 * @Package com.bit.ss.domain
 * @Description: 新闻评论
 * @author CCX
 * @date 2015年10月16日 上午8:28:37
 * @version V1.0
 */
@XmlRootElement
public class NewsComment {

	private int id;
	private Integer newsId;
	private Integer userId;
	private String content;
	private Integer floor;
	private Date time;

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
