package com.bit.ss.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

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

	private int id;
	private int type;// 班车类型，0：临时班车，1：不变班车
	private String startPoint;
	private String aimPoint;
	private int seatMeasage; // 座位数
	private Date modifyTime;
	private Date startTime;
	private String midStation;
	private String charger; // 存入信息人的姓名

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

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

	public int getSeatMeasage() {
		return seatMeasage;
	}

	public void setSeatMeasage(int seatMeasage) {
		this.seatMeasage = seatMeasage;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

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
