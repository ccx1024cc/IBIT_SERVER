package com.bit.ss.service;

import java.util.List;

import com.bit.ss.model.Bus;
import com.bit.ss.model.News;

/**   
 * @Title: IBus.java 
 * @Package com.bit.ss.service 
 * @Description:  校车模块服务接口
 * @author CCX
 * @date 2016年2月8日 下午4:43:44 
 * @version V1.0   
 */
public interface IBusService {

	public static final int PC_BUS_NEWS_PAGE_SIZE = 20;// 每一页显示的车辆新闻条数
	public static final int PC_TEMP_BUS_PAGE_SIZE = 20;// 每一页显示的临时班车条数

	/**
	 * 
	 * @Title: getTempBusByStartDate 
	 * @Description: 获取某一天出发的所有临时班车
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	public List<Bus> getTempBusByStartDate(String startDate);

	/**
	 * 
	 * @Title: getLatestBusList 
	 * @Description: 获取最新的定时班车表
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	public List<Bus> getLatestBusList();

	/**
	 * 
	 * @Title: getTempBusByPoints 
	 * @Description: 获取某两点间运行的临时班车表
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	public List<Bus> getTempBusByPoints(String startPoint, String aimPoint);

	/**
	 * 
	 * @Title: getBusNewsList 
	 * @Description: 后台服务，获取最新的校车新闻列表
	 * @return List<News>    返回类型 
	 * @throws
	 */
	public List<News> getBusNewsList(int page);

	/**
	 * 
	 * @Title: getBusNewsNum 
	 * @Description: 后台服务，获取校车新闻的数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getBusNewsNum();

	/**
	 * 
	 * @Title: getTempBusNum 
	 * @Description: 后台服务，获取临时班车数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getTempBusNum();

	/**
	 * 
	 * @Title: getTempBusList 
	 * @Description: 后台服务，获取临时班车列表
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	public List<Bus> getTempBusList(int page);

	/**
	 * 
	 * @Title: deleteBus 
	 * @Description: 后台服务,删除班车信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int deleteBus(int busId);

	/**
	 * 
	 * @Title: getBusList 
	 * @Description: 后台服务，获取定时班车列表
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	public List<Bus> getBusList(int page);

	/**
	 * 
	 * @Title: getBusNum 
	 * @Description: 后台服务，获取定时班车数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getBusNum();
	
	/**
	 * 
	 * @Title: addBus 
	 * @Description: 后台服务，添加班车信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int addBus(Bus bus);
}
