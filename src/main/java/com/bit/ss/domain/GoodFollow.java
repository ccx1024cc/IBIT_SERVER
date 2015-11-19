package com.bit.ss.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: GoodFollow.java
 * @Package com.bit.ss.domain
 * @Description: 商品关注信息
 * @author CCX
 * @date 2015年10月15日 下午5:30:05
 * @version V1.0
 */
@XmlRootElement
public class GoodFollow {

	private int id;
	private int goodId;
	private int UserId;
	private Date time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGoodId() {
		return goodId;
	}

	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
