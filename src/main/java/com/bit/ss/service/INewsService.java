package com.bit.ss.service;

import java.util.List;

import com.bit.ss.model.News;
import com.bit.ss.model.NewsComment;
import com.bit.ss.model.NewsConcern;

/**   
 * @Title: INewsService.java 
 * @Package com.bit.ss.service 
 * @Description:  新闻查询信息
 * @author CCX
 * @date 2015年11月30日 上午9:27:35 
 * @version V1.0   
 */
public interface INewsService {

	public static final int NEWSPAGESIZE = 30; // 每一页30条信息
	public static final int COMMENTPAGESIZE = 30;//每一页30条评论信息

	/**
	 * 
	 * @Title: findCount 
	 * @Description: 通过关键字获取数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int findCountByKeyword(int type,String keyword);
	

	/**
	 * 
	 * @Title: findListBrief 
	 * @Description: 查询简要新闻信息，不包括具体内容
	 * @return List<News>    返回类型 
	 * @throws
	 */
	public List<News> findList(int page, int type, String keyword);
	
	/**
	 * 
	 * @Title: findNewsListByType 
	 * @Description: 查询某一类新闻列表
	 * @return List<News>    返回类型 
	 * @throws
	 */
	public List<News> findNewsListByType(int type,int page);

	/**
	 * 
	 * @Title: addNewsConcern 
	 * @Description: 添加关注信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int addNewsConcern(int userId,int newsType);
	
	/**
	 * 
	 * @Title: deleteNewsConcern 
	 * @Description: 删除关注信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int deleteNewsConcern(int concernId);
	
	/**
	 * 
	 * @Title: getConcernList 
	 * @Description: 获取某人的关注列表
	 * @return List<NewsConcern>    返回类型 
	 * @throws
	 */
	public List<NewsConcern> getConcernList(int userId);

	/**
	 * 
	 * @Title: addComment 
	 * @Description: 添加评论
	 * @return int    返回类型 
	 * @throws
	 */
	public int addComment(NewsComment comment);
	
	/**
	 * 
	 * @Title: getCommentList 
	 * @Description: 获取新闻评论列表
	 * @return List<NewsComment>    返回类型 
	 * @throws
	 */
	public List<NewsComment> getCommentList(int newsId,int page);
}
