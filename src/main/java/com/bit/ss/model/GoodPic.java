package com.bit.ss.model;

import javax.xml.bind.annotation.XmlRootElement;

/**   
 * @Title: GoodPic.java 
 * @Package com.bit.ss.model 
 * @Description:  商品图像
 * @author CCX
 * @date 2016年2月15日 下午12:59:55 
 * @version V1.0   
 */
@XmlRootElement
public class GoodPic {

	private Integer id;
	private Integer goodId;
	private String url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
