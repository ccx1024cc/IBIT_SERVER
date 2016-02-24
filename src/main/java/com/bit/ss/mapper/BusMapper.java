package com.bit.ss.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bit.ss.model.Bus;
import com.bit.ss.model.News;

/**   
 * @Title: BusMapper.java 
 * @Package com.bit.ss.mapper 
 * @Description:  校车模块DAO
 * @author CCX
 * @date 2016年2月8日 下午3:42:26 
 * @version V1.0   
 */
@Repository
public interface BusMapper {

	/**
	 * 
	 * @Title: getTempBusListByStartDate 
	 * @Description: 获取某一天出发的所有临时班车
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	public List<Bus> getTempBusListByStartDate(@Param("startDate") String startDate);

	/**
	 * 
	 * @Title: getLatestUpdateDate 
	 * @Description: 获取永久班车最近的更新日期
	 * @return Date    返回类型 
	 * @throws
	 */
	public Date getLatestUpdateDate();

	/**
	 * 
	 * @Title: getBusListByModifyDate 
	 * @Description:  获取某一天更新的永久班车表
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	public List<Bus> getBusListByModifyDate(@Param("modifyDate") String modifyDate);

	/**
	 * 
	 * @Title: getTempBusListByPoints 
	 * @Description: 获取两点间运行的临时班车表
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	public List<Bus> getTempBusListByPoints(@Param("startPoint") String startPoint, @Param("aimPoint") String aimPoint);

	/**
	 * 
	 * @Title: getBusNewsList 
	 * @Description: 获取最新的班车新闻列表
	 * @return List<BusNews>    返回类型 
	 * @throws
	 */
	public List<News> getBusNewsList(@Param("start") int start, @Param("num") int num);

	/**
	 * 
	 * @Title: getBusNewsNum 
	 * @Description: 获取校车新闻的数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getBusNewsNum();

	/**
	 * 
	 * @Title: getTempBusNum 
	 * @Description: 获取临时班车数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getTempBusNum();

	/**
	 * 
	 * @Title: getTempBusList 
	 * @Description: 获取临时班车列表
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	public List<Bus> getTempBusList(@Param("start") int start, @Param("num") int num);

	/**
	 * 
	 * @Title: deleteBus 
	 * @Description: 删除班车信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int deleteBus(@Param("busId") int busId);

	/**
	 * 
	 * @Title: getBusList 
	 * @Description: 获取定时班车列表
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	public List<Bus> getBusList(@Param("start") int start, @Param("num") int num);

	/**
	 * 
	 * @Title: getBusNum 
	 * @Description: 获取定时班车数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getBusNum();

	/**
	 * 
	 * @Title: addBus 
	 * @Description: 添加校车信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int addBus(Bus bus);
}
