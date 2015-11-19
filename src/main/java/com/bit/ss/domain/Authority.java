package com.bit.ss.domain;

/**
 * @Title: Authority.java
 * @Package com.bit.ss.domain
 * @Description: 用户权限信息
 * @author CCX
 * @date 2015年10月15日 下午5:08:52
 * @version V1.0
 */
public class Authority {

	private int id;
	private int userId;
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
