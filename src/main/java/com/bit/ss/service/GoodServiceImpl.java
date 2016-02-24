package com.bit.ss.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.ss.mapper.GoodMapper;
import com.bit.ss.model.Good;
import com.bit.ss.model.GoodComment;
import com.bit.ss.model.GoodPic;
import com.bit.ss.util.DateUtil;

/**   
 * @Title: GoodServiceImpl.java 
 * @Package com.bit.ss.service 
 * @Description:  二手市场模块服务实现类
 * @author CCX
 * @date 2016年2月15日 下午2:30:15 
 * @version V1.0   
 */
@Service
public class GoodServiceImpl implements IGoodService {

	@Autowired
	private GoodMapper goodMapper;

	@Override
	public List<Good> getLatestGoodsByType(int type, int page) {
		int start = (page - 1) * GOOD_PAGESIZE;
		return goodMapper.getLatestGoodsByType(type, start, GOOD_PAGESIZE, new Date());
	}

	@Override
	public List<Good> getHotGoodsByType(int type, int page) {
		int start = (page - 1) * GOOD_PAGESIZE;
		return goodMapper.getHotGoodsByType(type, start, GOOD_PAGESIZE, new Date());
	}

	@Override
	public Good getGoodById(int goodId) {
		goodMapper.addReadTimeBy1(goodId);
		return goodMapper.getGoodById(goodId);
	}

	@Override
	public List<GoodComment> getCommentListByGood(int goodId, int page) {
		int start = (page - 1) * COMMENT_PAGESIZE;
		return goodMapper.getCommentsByGood(goodId, start, COMMENT_PAGESIZE);
	}

	@Override
	public int addComment(GoodComment comment) {
		int maxFloor = goodMapper.getCommentMaxFloor(comment.getGoodId());
		comment.setFloor(maxFloor + 1);
		return goodMapper.addGoodComment(comment);
	}

	@Override
	public List<Good> getPersonalLatestGoodList(int userId, int page) {
		int start = (page - 1) * GOOD_PAGESIZE;
		return goodMapper.getPersonalLatestGoodList(userId, new Date(), start, GOOD_PAGESIZE);
	}

	@Override
	public List<Good> getPersonalStaleGoodList(int userId, int page) {
		int start = (page - 1) * GOOD_PAGESIZE;
		return goodMapper.getPersonalStaleGoodList(userId, new Date(), start, GOOD_PAGESIZE);
	}

	@Override
	public int updateStaleTime(int goodId) {
		return goodMapper.updateStaleTime(goodId, new Date());
	}

	@Override
	public int updateGood(int goodId, String title, String content, Float price) {
		goodMapper.updateLastModifyTime(goodId, new Date());
		return goodMapper.updateGood(goodId, title, content, price);
	}

	@Override
	public int addGood(Good good, List<GoodPic> icons) {
		good.setPubTime(new Date());
		good.setModifyTime(good.getPubTime());
		good.setStaleTime(new DateUtil().nDaysAfterOneDate(good.getPubTime(), DAYS_SPACE));
		goodMapper.addGood(good);
		int goodId = good.getId();
		for (GoodPic icon : icons) {
			icon.setGoodId(goodId);
			goodMapper.addGoodIcon(icon);
		}
		return 0;
	}

	@Override
	public int getGoodExistByUser(int goodId, int userId) {
		return goodMapper.getGoodExistByUser(goodId, userId);
	}

	@Override
	public int getGoodIsStale(int goodId) {
		return goodMapper.getGoodIsStale(goodId, new Date());
	}
}
