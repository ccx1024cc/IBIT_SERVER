package com.bit.ss.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.ss.mapper.NewsMapper;
import com.bit.ss.model.News;
import com.bit.ss.model.NewsComment;
import com.bit.ss.model.NewsConcern;

/**   
 * @Title: NewsServiceImpl.java 
 * @Package com.bit.ss.service 
 * @Description:  
 * @author CCX
 * @date 2015年11月30日 上午9:30:34 
 * @version V1.0   
 */
@Service
public class NewsServiceImpl implements INewsService {

	@Autowired
	private NewsMapper newsMapper;

	@Override
	public int findCountByKeyword(int type, String keyword) {
		return newsMapper.findNewsNumByKeyword(type, keyword);
	}

	@Override
	public List<News> findList(int page, int type, String keyword) {
		int start = (page - 1) * NEWSPAGESIZE;
		return newsMapper.findList(start, NEWSPAGESIZE, type, keyword);
	}

	@Override
	public List<News> findNewsListByType(int type, int page) {
		int start = (page - 1) * NEWSPAGESIZE;
		return newsMapper.findListByType(type, start, NEWSPAGESIZE);
	}

	@Override
	public int addNewsConcern(int userId, int newsType) {
		int num = newsMapper.getConcernNumByUserAndType(userId, newsType);
		if (num > 0)
			return num;
		return newsMapper.addNewsConcern(userId, newsType, new Date());
	}

	@Override
	public int deleteNewsConcern(int concernId) {
		return newsMapper.deleteNewsConcern(concernId, new Date());
	}

	@Override
	public List<NewsConcern> getConcernList(int userId) {
		return newsMapper.getNewsConcernList(userId);
	}

	@Override
	public int addComment(NewsComment comment) {
		int floor = newsMapper.getCommentNumByNews(comment.getNewsId()) + 1;
		comment.setFloor(floor);
		return newsMapper.addNewsComment(comment);
	}

	@Override
	public List<NewsComment> getCommentList(int newsId, int page) {
		int start = page - 1;
		return newsMapper.getNewsCommentList(newsId, start, COMMENTPAGESIZE);
	}
}
