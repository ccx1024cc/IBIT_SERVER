package com.bit.ss.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.bit.ss.jackson.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Title: Bus.java
 * @Package com.bit.ss.domain
 * @Description: 班车信息
 * @author CCX
 * @date 2015年10月15日 下午3:16:59
 * @version V1.0
 */
@XmlRootElement
public class Bus {

	private Integer id;
	private Integer type;// 班车类型，0：临时班车，1：不变班车
	private String startPoint;
	private String aimPoint;
	private Integer seatMeasage; // 座位数
	private Date modifyTime;
	private Date startTime;
	private String midStation;
	private String charger; // 存入信息人的姓名

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getAimPoint() {
		return aimPoint;
	}

	public void setAimPoint(String aimPoint) {
		this.aimPoint = aimPoint;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSeatMeasage() {
		return seatMeasage;
	}

	public void setSeatMeasage(Integer seatMeasage) {
		this.seatMeasage = seatMeasage;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getMidStation() {
		return midStation;
	}

	public void setMidStation(String midStation) {
		this.midStation = midStation;
	}

	public String getCharger() {
		return charger;
	}

	public void setCharger(String charger) {
		this.charger = charger;
	}

}
