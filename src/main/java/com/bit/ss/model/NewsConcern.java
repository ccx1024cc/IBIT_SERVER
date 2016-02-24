package com.bit.ss.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**   
 * @Title: NewsConcern.java 
 * @Package com.bit.ss.model 
 * @Description:  新闻关注信息
 * @author CCX
 * @date 2016年2月8日 上午8:51:04 
 * @version V1.0   
 */
@XmlRootElement
public class NewsConcern {

	private Integer id;
	private Integer userId;
	private Integer newsType;
	private Date pubTime;
	@JsonIgnore
	private Date disconcernTime;
	@JsonIgnore
	private Integer status;// 状态：0关注，1取消关注

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getNewsType() {
		return newsType;
	}

	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public Integer getStatus() {
		return status;
	}

	public Date getDisconcernTime() {
		return disconcernTime;
	}

	public void setDisconcernTime(Date disconcernTime) {
		this.disconcernTime = disconcernTime;
	}

}
