package com.bit.ss.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.bit.ss.jackson.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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

	private Integer id;
	private String title;
	// 商品类型,0:代步工具，1：书籍资料，2：服装鞋包
	// ，3：数码产品，4：体育用品
	// ，5：爱心赠送，6：音乐乐器，7：票券，8：其他
	private Integer kind;
	private String content;
	private Float price;
	private Integer readTimes;
	private Date pubTime;// 发布时间
	private Date modifyTime;// 最后修改时间
	private Date staleTime;// 过期时间
	@JsonIgnore
	private int validation;// 0待审核，1通过，2未通过
	private List<GoodPic> iconUrls;
	private User owner;// 信息发布者

	public Integer getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(Integer readTimes) {
		this.readTimes = readTimes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getStaleTime() {
		return staleTime;
	}

	public void setStaleTime(Date staleTime) {
		this.staleTime = staleTime;
	}

	public int getValidation() {
		return validation;
	}

	public void setValidation(int validation) {
		this.validation = validation;
	}

	public List<GoodPic> getIconUrls() {
		return iconUrls;
	}

	public void setIconUrls(List<GoodPic> iconUrls) {
		this.iconUrls = iconUrls;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

}