package com.bit.ss.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bit.ss.model.News;
import com.bit.ss.model.NewsComment;
import com.bit.ss.model.NewsConcern;

/**   
 * @Title: NewsMapper.java 
 * @Package com.bit.ss.mapper 
 * @Description:  新闻模块DAO
 * @author CCX
 * @date 2015年11月30日 上午8:46:58 
 * @version V1.0   
 */
@Repository
public interface NewsMapper {

	/**
	 * 
	 * @Title: findList 
	 * @Description: 开始条目数，结束条目数息
	 * @return List<News>    返回类型 
	 * @throws
	 */
	public List<News> findList(@Param("start") int start, @Param("num") int num, @Param("type") int type,
			@Param("keyword") String keyword);

	/**
	 * 
	 * @Title: countNum 
	 * @Description: 通过关键字，计算某类新闻全部个数
	 * @return int    返回类型 
	 * @throws
	 */
	public int findNewsNumByKeyword(@Param("type") int type, @Param("keyword") String keyword);

	/**
	 * 
	 * @Title: findListByType 
	 * @Description: 查询某一类新闻列表
	 * @return List<News>    返回类型 
	 * @throws
	 */
	public List<News> findListByType(@Param("type") int type, @Param("start") int start, @Param("num") int num);

	/**
	 * 
	 * @Title: addNewsConcern 
	 * @Description: 添加关注类型
	 * @return int    返回类型 
	 * @throws
	 */
	public int addNewsConcern(@Param("userId") int userId, @Param("newsType") int newsType,
			@Param("pubTime") Date pubTime);

	/**
	 * 
	 * @Title: deleteNewsConcern 
	 * @Description: 改变关注信息状态
	 * @return int    返回类型 
	 * @throws
	 */
	public int deleteNewsConcern(@Param("id") int concernId, @Param("disConcernDate") Date disConcernDate);

	/**
	 * 
	 * @Title: getNewsConcernList 
	 * @Description: 获取关注的新闻类型列表
	 * @return List<NewsConcern>    返回类型 
	 * @throws
	 */
	public List<NewsConcern> getNewsConcernList(@Param("userId") int userId);

	/**
	 * 
	 * @Title: getConcernNumByUserAndType 
	 * @Description: 获取用户正在关注的某一类信息数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getConcernNumByUserAndType(@Param("userId")int userId,@Param("type")int type);
	
	/**
	 * 
	 * @Title: addNewsComment 
	 * @Description: 添加评论
	 * @return int    返回类型 
	 * @throws
	 */
	public int addNewsComment(NewsComment comment);

	/**
	 * 
	 * @Title: getCommentNumByNews 
	 * @Description: 获取某条新闻的评论数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getCommentNumByNews(@Param("newsId") int newsId);

	/**
	 * 
	 * @Title: getNewsComment 
	 * @Description: 获取新闻的评论列表
	 * @return List<NewsComment>    返回类型 
	 * @throws
	 */
	public List<NewsComment> getNewsCommentList(@Param("newsId") int newsId, @Param("start") int start,
			@Param("num") int num);
}
