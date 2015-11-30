package com.bit.ss.dao;

import java.util.List;

import com.bit.ss.domain.News;
import com.bit.ss.domain.NewsComment;

/**   
 * @Title: INewsDAO.java 
 * @Package com.bit.ss.dao 
 * @Description:  新闻模块
 * @author CCX
 * @date 2015年11月30日 上午8:07:43 
 * @version V1.0   
 */
public interface INewsDAO {

	/**
	 * 
	 * @Title: findListBrief 
	 * @Description: 查询简要新闻信息，不包括具体内容
	 * @return List<News>    返回类型 
	 * @throws
	 */
	public List<News> findList(int start, int num, int type);
	
	/**
	 * 
	 * @Title: findNews 
	 * @Description: 查询单条新闻
	 * @return News    返回类型 
	 * @throws
	 */
	public News findNews(int newsID);
	
	/**
	 * 
	 * @Title: findCommentList 
	 * @Description: 获取评论列表
	 * @return List<NewsComment>    返回类型 
	 * @throws
	 */
	public List<NewsComment> findCommentList(int start,int num,int newsID);

	/**
	 * 
	 * @Title: getNum 
	 * @Description: 得到单条新闻评论数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getCommentNum(int newsID);
	
	/**
	 * 
	 * @Title: insertComment 
	 * @Description: 发布评论
	 * @return int    返回类型 
	 * @throws
	 */
	public int insertComment(NewsComment comment);
}
