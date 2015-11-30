package com.bit.ss.service;

import java.util.List;

import com.bit.ss.domain.News;
import com.bit.ss.domain.NewsComment;

/**   
 * @Title: INewsService.java 
 * @Package com.bit.ss.service 
 * @Description:  新闻查询信息
 * @author CCX
 * @date 2015年11月30日 上午9:27:35 
 * @version V1.0   
 */
public interface INewsService {

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
	 * @Description: 查询单条信息
	 * @return News    返回类型 
	 * @throws
	 */
	public News findNews(int newsID);
	
	/**
	 * 
	 * @Title: findCommentList 
	 * @Description: 获取单条新闻的评论列表
	 * @return List<NewsComment>    返回类型 
	 * @throws
	 */
	public List<NewsComment> findCommentList(int start,int num,int newsID);
	
	/**
	 * 
	 * @Title: getCommentNum 
	 * @Description: 获取单条新闻评论数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getCommentNum(int newsID);
	
	/**
	 * 
	 * @Title: insertComment 
	 * @Description: 插入单条新闻评论
	 * @return void    返回类型 
	 * @throws
	 */
	public void insertComment(NewsComment comment);
}
