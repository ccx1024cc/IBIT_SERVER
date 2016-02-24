package com.bit.ss.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.bit.ss.jackson.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Title: User.java
 * @Package com.bit.ss.domain
 * @Description:用户信息
 * @author CCX
 * @date 2015年10月15日 下午4:47:49
 * @version V1.0
 */
@XmlRootElement
public class User {

	private Integer id;
	private String name;
	private String password;
	private String phone;
	private Integer gender;
	private String email;
	private String autograph; // 签名
	private String iconUrl; // 头像地址
	private Date date;// 注册时间
	private List<Authority> auths;// 特殊权限列表

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAutograph() {
		return autograph;
	}

	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Authority> getAuths() {
		return auths;
	}

	public void setAuths(List<Authority> auths) {
		this.auths = auths;
	}

}
