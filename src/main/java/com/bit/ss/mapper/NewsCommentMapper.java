package com.bit.ss.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.bit.ss.domain.NewsComment;

/**   
 * @Title: NewsCommentMapper.java 
 * @Package com.bit.ss.mapper 
 * @Description:  新闻评论信息
 * @author CCX
 * @date 2015年11月30日 上午10:23:45 
 * @version V1.0   
 */
@Component
public interface NewsCommentMapper {

	/**
	 * 
	 * @Title: findList 
	 * @Description: 查询单条新闻的评论列表
	 * @return List<NewsComment>    返回类型 
	 * @throws
	 */
	public List<NewsComment> findList(@Param("start") int start, @Param("num") int num, @Param("newsID") int newsID);
	
	/**
	 * 
	 * @Title: getNum 
	 * @Description: 获取单条新闻评论数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getNum(@Param("newsID")int newsID);
	
	/**
	 * 
	 * @Title: intsertComment 
	 * @Description: 插入单条评论信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int intsertComment(NewsComment comment);
}
