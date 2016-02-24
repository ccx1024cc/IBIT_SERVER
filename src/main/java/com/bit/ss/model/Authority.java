package com.bit.ss.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.bit.ss.jackson.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Title: Authority.java
 * @Package com.bit.ss.domain
 * @Description: 用户的特殊权限信息
 * @author CCX
 * @date 2015年10月15日 下午5:08:52
 * @version V1.0
 */
@XmlRootElement
public class Authority {

	private Integer id;
	private Integer userId;
	private String name;// "admin":管理员，"root":为超级管理员
	private String grandUser;// 授权用户
	private Date grandTime;// 授权时间

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrandUser() {
		return grandUser;
	}

	public void setGrandUser(String grandUser) {
		this.grandUser = grandUser;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getGrandTime() {
		return grandTime;
	}

	public void setGrandTime(Date grandTime) {
		this.grandTime = grandTime;
	}

}
