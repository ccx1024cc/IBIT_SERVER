package com.bit.ss.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.bit.ss.jackson.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**   
 * @Title: Message.java 
 * @Package com.bit.ss.model 
 * @Description:  用户消息
 * @author CCX
 * @date 2016年2月17日 下午5:56:47 
 * @version V1.0   
 */
@XmlRootElement
public class Message {

	public static final String MODULE_BUS = "MODULE_BUS";
	public static final String MODULE_FEEDBACK = "MODULE_FEEDBACK";
	public static final String MODULE_GOOD = "MODULE_GOOD";
	public static final String MODULE_LOSTFOUND = "MODULE_LOSTFOUND";
	public static final String MODULE_NEWS = "MODULE_NEWS";
	public static final String MODULE_QUESTION = "MODULE_QUESTION";
	public static final String MODULE_USER = "MODULE_USER";
	public static final String MODULE_VERSION = "MODULE_VERSION";

	private Integer id;
	private String service;// 产生消息的服务
	private String module;// 产生消息的模块
	private String content;// 消息内容
	private Date pubTime;// 消息产生时间
	private User owner;// 消息的拥有者

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
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

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

}
