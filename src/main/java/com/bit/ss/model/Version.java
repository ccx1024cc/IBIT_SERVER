package com.bit.ss.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.bit.ss.jackson.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**   
 * @Title: Version.java 
 * @Package com.bit.ss.model 
 * @Description:  系统版本信息
 * @author CCX
 * @date 2016年2月15日 下午5:43:48 
 * @version V1.0   
 */
@XmlRootElement
public class Version {

	private Integer id;
	private String number;// 版本编号
	private Date pubTime;// 版本发布时间
	private String publisher;// 版本发布人
	private String description;// 版本变动内容
	private Integer isNew;// 是否是最新版本

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
