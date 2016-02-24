package com.bit.ss.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.bit.ss.jackson.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Title: Answer.java
 * @Package com.bit.ss.domain
 * @Description: 回答信息
 * @author CCX
 * @date 2015年10月15日 下午5:41:27
 * @version V1.0
 */
@XmlRootElement
public class Answer {

	private Integer id;
	private Integer questionId;
	private String content;// 回答内容
	private Date time;// 回答时间
	private Integer agreementNum; // 点赞总数
	private Integer floor;// 楼层数
	private Boolean adoption; // 采纳情况
	private Boolean hasAgreement;// 当前用户是否赞过

	private User owner;// 提问人

	public Boolean getHasAgreement() {
		return hasAgreement;
	}

	public void setHasAgreement(Boolean hasAgreement) {
		this.hasAgreement = hasAgreement;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Boolean getAdoption() {
		return adoption;
	}

	public void setAdoption(Boolean adoption) {
		this.adoption = adoption;
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

	public Integer getAgreementNum() {
		return agreementNum;
	}

	public void setAgreementNum(Integer agreementNum) {
		this.agreementNum = agreementNum;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

}
