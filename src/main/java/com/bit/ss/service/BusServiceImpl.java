package com.bit.ss.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.ss.mapper.BusMapper;
import com.bit.ss.model.Bus;
import com.bit.ss.model.News;
import com.bit.ss.util.DateUtil;

/**   
 * @Title: BusServiceImpl.java 
 * @Package com.bit.ss.service 
 * @Description:  班车服务实现类
 * @author CCX
 * @date 2016年2月8日 下午4:46:06 
 * @version V1.0   
 */
@Service
public class BusServiceImpl implements IBusService {

	@Autowired
	private BusMapper busMapper;

	@Override
	public List<Bus> getTempBusByStartDate(String startDate) {
		return busMapper.getTempBusListByStartDate(startDate);
	}

	@Override
	public List<Bus> getLatestBusList() {
		Date date = busMapper.getLatestUpdateDate();
		return busMapper.getBusListByModifyDate(new DateUtil().formatDateTime(date, DateUtil.DATE_FORMAT));
	}

	@Override
	public List<Bus> getTempBusByPoints(String startPoint, String aimPoint) {
		return busMapper.getTempBusListByPoints(startPoint, aimPoint);
	}

	@Override
	public List<News> getBusNewsList(int page) {
		int start = (page - 1) * PC_BUS_NEWS_PAGE_SIZE;
		return busMapper.getBusNewsList(start, PC_BUS_NEWS_PAGE_SIZE);
	}

	@Override
	public int getBusNewsNum() {
		return busMapper.getBusNewsNum();
	}

	@Override
	public int getTempBusNum() {
		return busMapper.getTempBusNum();
	}

	@Override
	public List<Bus> getTempBusList(int page) {
		int start = (page - 1) * PC_TEMP_BUS_PAGE_SIZE;
		return busMapper.getTempBusList(start, PC_TEMP_BUS_PAGE_SIZE);
	}

	@Override
	public int deleteBus(int busId) {
		return busMapper.deleteBus(busId);
	}

	@Override
	public List<Bus> getBusList(int page) {
		int start = (page - 1) * PC_TEMP_BUS_PAGE_SIZE;
		return busMapper.getBusList(start, PC_TEMP_BUS_PAGE_SIZE);
	}

	@Override
	public int getBusNum() {
		return busMapper.getBusNum();
	}

	@Override
	public int addBus(Bus bus) {
		return busMapper.addBus(bus);
	}
}
