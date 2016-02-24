package com.bit.ss.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.bit.ss.jackson.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Title: Question.java
 * @Package com.bit.ss.domain
 * @Description: 问题信息
 * @author CCX
 * @date 2015年10月15日 下午7:13:08
 * @version V1.0
 */
@XmlRootElement
public class Question {

	private Integer id;
	private String title;
	private String content;
	private String iconUrl;
	private Integer answerNum;
	private Integer readTimes;// 浏览次数
	private Date date;
	private Date lastAnswerTime;// 最后回答时间
	private List<QuestionTag> tags; // 问题标签
	private User owner;// 提问者

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getLastAnswerTime() {
		return lastAnswerTime;
	}

	public void setLastAnswerTime(Date lastAnswerTime) {
		this.lastAnswerTime = lastAnswerTime;
	}

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

	public Integer getAnswerNum() {
		return answerNum;
	}

	public Integer getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(Integer readTimes) {
		this.readTimes = readTimes;
	}

	public void setAnswerNum(Integer answerNum) {
		this.answerNum = answerNum;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<QuestionTag> getTags() {
		return tags;
	}

	public void setTags(List<QuestionTag> tags) {
		this.tags = tags;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

}
