package com.bit.ss.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bit.ss.model.Good;
import com.bit.ss.model.GoodComment;
import com.bit.ss.model.GoodPic;

/**   
 * @Title: GoodMapper.java 
 * @Package com.bit.ss.mapper 
 * @Description:  二手市场模块DAO接口
 * @author CCX
 * @date 2016年2月15日 下午1:12:16 
 * @version V1.0   
 */
@Repository
public interface GoodMapper {

	/**
	 * 
	 * @Title: addGood 
	 * @Description: 插入商品信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int addGood(Good good);

	/**
	 * 
	 * @Title: addGoodIcon 
	 * @Description: 插入商品图片
	 * @return int    返回类型 
	 * @throws
	 */
	public int addGoodIcon(GoodPic icon);

	/**
	 * 
	 * @Title: getCommentMaxFloor 
	 * @Description: 获取某个商品的评论的最大楼层
	 * @return int    返回类型 
	 * @throws
	 */
	public int getCommentMaxFloor(@Param("goodId") int goodId);

	/**
	 * 
	 * @Title: addGoodComment 
	 * @Description: 发表商品评论
	 * @return int    返回类型 
	 * @throws
	 */
	public int addGoodComment(GoodComment comment);

	/**
	 * 
	 * @Title: updateStale 
	 * @Description: 使商品下架
	 * @return int    返回类型 
	 * @throws
	 */
	public int updateStaleTime(@Param("goodId") int goodId, @Param("staleTime") Date staleTime);

	/**
	 * 
	 * @Title: getGoodExistByUser 
	 * @Description: 判断用户是否发送过商品信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int getGoodExistByUser(@Param("goodId") int goodId, @Param("userId") int userId);

	/**
	 * 
	 * @Title: getGoodIsStale 
	 * @Description: 判断商品信息是否下架
	 * @return int    返回类型 
	 * @throws
	 */
	public int getGoodIsStale(@Param("goodId") int goodId, @Param("nowTime") Date nowTime);

	/**
	 * 
	 * @Title: updateGood 
	 * @Description: 更新商品的基本信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int updateGood(@Param("goodId") int goodId, @Param("title") String title, @Param("content") String content,
			@Param("price") Float price);

	/**
	 * 
	 * @Title: updateLastModifyTime 
	 * @Description: 更新最后更改时间
	 * @return int    返回类型 
	 * @throws
	 */
	public int updateLastModifyTime(@Param("goodId") int goodId, @Param("modifyTime") Date modifyTime);

	/**
	 * 
	 * @Title: getLatestGoodsByType 
	 * @Description: 获取最近某类商品信息
	 * @return List<Good>    返回类型 
	 * @throws
	 */
	public List<Good> getLatestGoodsByType(@Param("type") int type, @Param("start") int start, @Param("num") int num,
			@Param("nowTime") Date nowTime);

	/**
	 * 
	 * @Title: getHotGoodsByType 
	 * @Description: 获取热门商品信息
	 * @return List<Good>    返回类型 
	 * @throws
	 */
	public List<Good> getHotGoodsByType(@Param("type") int type, @Param("start") int start, @Param("num") int num,
			@Param("nowTime") Date nowTime);

	/**
	 * 
	 * @Title: getGoodById 
	 * @Description: 获取商品详情
	 * @return Good    返回类型 
	 * @throws
	 */
	public Good getGoodById(@Param("goodId") int goodId);

	/**
	 * 
	 * @Title: addReadTimeBy1 
	 * @Description: 更新商品的浏览次数（+1）
	 * @return int    返回类型 
	 * @throws
	 */
	public int addReadTimeBy1(@Param("goodId") int goodId);

	/**
	 * 
	 * @Title: getCommentsByGood 
	 * @Description: 获取某个商品的评论信息
	 * @return List<GoodComment>    返回类型 
	 * @throws
	 */
	public List<GoodComment> getCommentsByGood(@Param("goodId") int goodId, @Param("start") int start,
			@Param("num") int num);

	/**
	 * 
	 * @Title: getPersonalLatestGoodList 
	 * @Description: 获取某用户发布的未下架的商品信息
	 * @return List<Good>    返回类型 
	 * @throws
	 */
	public List<Good> getPersonalLatestGoodList(@Param("userId") int userId, @Param("nowTime") Date nowTime,
			@Param("start") int start, @Param("num") int num);

	/**
	 * 
	 * @Title: getPersonalStaleGoodList 
	 * @Description: 获取某用户发布的已经下架的商品信息
	 * @return List<Good>    返回类型 
	 * @throws
	 */
	public List<Good> getPersonalStaleGoodList(@Param("userId") int userId, @Param("nowTime") Date nowTime,
			@Param("start") int start, @Param("num") int num);

}
