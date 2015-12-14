package com.bit.ss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.ss.dao.INewsDAO;
import com.bit.ss.domain.News;
import com.bit.ss.domain.NewsComment;

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
	private INewsDAO newsDAO;

	@Override
	public int findCount(int type, String pubTime, String keyword) {
		int num = newsDAO.getNewsNum(type, pubTime, keyword);
		return num;
	}

	@Override
	public List<News> findList(int page, int type, String keyword) {
		return newsDAO.findList(pageSize, page, type, keyword);
	}

	@Override
	public News findNews(int newsID) {
		return newsDAO.findNews(newsID);
	}

	@Override
	public List<NewsComment> findCommentList(int start, int num, int newsID) {
		return newsDAO.findCommentList(start, num, newsID);
	}

	@Override
	public int getCommentNum(int newsID) {
		return newsDAO.getCommentNum(newsID);
	}

	@Override
	public void insertComment(NewsComment comment) {
		newsDAO.insertComment(comment);
	}
}
