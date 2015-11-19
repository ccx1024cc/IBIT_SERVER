package com.bit.ss.domain;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Title: Good.java
 * @Package com.bit.ss.domain
 * @Description: 商品信息
 * @author CCX
 * @date 2015年10月15日 下午5:10:59
 * @version V1.0
 */
@XmlRootElement
public class Good {

	private int id;
	private int UserId;
	private String title;
	// 商品类型,0:代步工具，1：书籍资料，2：服装鞋包
	// ，3：数码产品，4：体育用品
	// ，5：爱心赠送，6：音乐乐器，7：票券，8：其他
	private int kind;
	private String content;
	private float price;
	private Date pubTime;
	private Date modifyTime;
	@JsonIgnore
	private int validation;
	@JsonIgnore
	private int isStale; // 是否过期
	private List<String> picUrls;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getValidation() {
		return validation;
	}

	public void setValidation(int validation) {
		this.validation = validation;
	}

	public int getIsStale() {
		return isStale;
	}

	public void setIsStale(int isStale) {
		this.isStale = isStale;
	}

	public List<String> getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(List<String> picUrls) {
		this.picUrls = picUrls;
	}

}
