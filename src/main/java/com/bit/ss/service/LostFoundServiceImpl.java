package com.bit.ss.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.ss.mapper.LostFoundMapper;
import com.bit.ss.model.LostFound;
import com.bit.ss.util.DateUtil;

/**   
 * @Title: LostFoundServiceImpl.java 
 * @Package com.bit.ss.service 
 * @Description:  失物招领模块服务实现类
 * @author CCX
 * @date 2016年2月14日 下午3:02:34 
 * @version V1.0   
 */
@Service
public class LostFoundServiceImpl implements ILostFoundService {

	@Autowired
	private LostFoundMapper lostFoundMapper;

	@Override
	public List<LostFound> getLatestStuffList(int page) {
		int start = page - 1;
		DateUtil util = new DateUtil();
		return lostFoundMapper.getLatestStuffList(start, LOSTFOUND_PAGESIZE,
				util.nDaysBeforeOneDate(new Date(), DAYS_SPACE));
	}

	@Override
	public List<LostFound> getOldStuffList(int page) {
		int start = page - 1;
		DateUtil util = new DateUtil();
		return lostFoundMapper.getOldStuffList(start, LOSTFOUND_PAGESIZE,
				util.nDaysBeforeOneDate(new Date(), DAYS_SPACE));
	}

	@Override
	public int addLostFound(LostFound lostFound) {
		lostFound.setOutDate(new DateUtil().nDaysAfterOneDate(lostFound.getPubTime(), DAYS_SPACE));
		return lostFoundMapper.addLostFound(lostFound);
	}
}
