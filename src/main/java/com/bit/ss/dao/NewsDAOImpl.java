package com.bit.ss.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.ss.domain.News;
import com.bit.ss.domain.NewsComment;
import com.bit.ss.mapper.NewsCommentMapper;
import com.bit.ss.mapper.NewsMapper;

/**   
 * @Title: NewsDAOImpl.java 
 * @Package com.bit.ss.dao 
 * @Description:  新闻模块
 * @author CCX
 * @date 2015年11月30日 上午8:45:07 
 * @version V1.0   
 */
@Repository
public class NewsDAOImpl implements INewsDAO {

	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private NewsCommentMapper newsCommentMapper;

	@Override
	public List<News> findList(int start, int num, int type) {
		return newsMapper.findList(start, num, type);
	}
	
	@Override
	public News findNews(int newsID) {
		return newsMapper.findNews(newsID);
	}
	
	@Override
	public List<NewsComment> findCommentList(int start, int num, int newsID) {
		return newsCommentMapper.findList(start, num, newsID);
	}
	
	@Override
	public int getCommentNum(int newsID) {
		return newsCommentMapper.getNum(newsID);
	}
	
	@Override
	public int insertComment(NewsComment comment) {
		return newsCommentMapper.intsertComment(comment);
	}
}
