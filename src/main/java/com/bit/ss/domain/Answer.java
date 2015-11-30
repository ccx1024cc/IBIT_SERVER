package com.bit.ss.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Title: Answer.java
 * @Package com.bit.ss.domain
 * @Description: 回答信息
 * @author CCX
 * @date 2015年10月15日 下午5:41:27
 * @version V1.0
 */
@XmlRootElement
public class Answer implements DefinedBean{

	private int id;
	private int userId;
	private int questionId;
	@JsonIgnore
	private int adoption; // 采纳情况，0：未采纳，1：采纳
	private String content;
	private Date time;
	private int agreementNum; // 点赞总数
	private int commentNum; // 评论总数

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

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getAdoption() {
		return adoption;
	}

	public void setAdoption(int adoption) {
		this.adoption = adoption;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getAgreementNum() {
		return agreementNum;
	}

	public void setAgreementNum(int agreementNum) {
		this.agreementNum = agreementNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

}
