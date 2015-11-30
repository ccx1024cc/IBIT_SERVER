package com.bit.ss.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.bit.ss.domain.News;

/**   
 * @Title: NewsMapper.java 
 * @Package com.bit.ss.mapper 
 * @Description:  
 * @author CCX
 * @date 2015年11月30日 上午8:46:58 
 * @version V1.0   
 */
@Component
public interface NewsMapper {

	/**
	 * 
	 * @Title: findList 
	 * @Description: 开始条目数，结束条目数息
	 * @return List<News>    返回类型 
	 * @throws
	 */
	public List<News> findList(@Param("start") int start, @Param("num") int num, @Param("type") int type);

	/**
	 * 
	 * @Title: findNews 
	 * @Description: 查询单条新闻信息
	 * @return News    返回类型 
	 * @throws
	 */
	public News findNews(@Param("newsID")int newsID);
}
