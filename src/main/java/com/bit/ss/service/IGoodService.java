package com.bit.ss.service;

import java.util.List;

import com.bit.ss.model.Good;
import com.bit.ss.model.GoodComment;
import com.bit.ss.model.GoodPic;

/**   
* @Title: IGoodService.java 
* @Package com.bit.ss.service 
* @Description:  二手市场模块服务接口
* @author CCX
* @date 2016年2月15日 下午2:18:50 
* @version V1.0   
*/
public interface IGoodService {

	public static final int GOOD_PAGESIZE = 15;// 每一页商品数量
	public static final int COMMENT_PAGESIZE = 20;// 每一评论页数量
	public static final int DAYS_SPACE = 7;// 显示的时间跨度为一周（7天)

	/**
	 * 
	 * @Title: getLatestGoodsByType 
	 * @Description: 获取最近某类商品信息
	 * @return List<Good>    返回类型 
	 * @throws
	 */
	public List<Good> getLatestGoodsByType(int type, int page);

	/**
	 * 
	 * @Title: getHotGoodsByType 
	 * @Description: 获取热门商品信息
	 * @return List<Good>    返回类型 
	 * @throws
	 */
	public List<Good> getHotGoodsByType(int type, int page);

	/**
	 * 
	 * @Title: getGoodById 
	 * @Description: 获取问题详情
	 * @return Good    返回类型 
	 * @throws
	 */
	public Good getGoodById(int goodId);

	/**
	 * 
	 * @Title: getCommentListByGood 
	 * @Description: 获取某个商品的评价列表
	 * @return List<GoodComment>    返回类型 
	 * @throws
	 */
	public List<GoodComment> getCommentListByGood(int goodId, int page);

	/**
	 * 
	 * @Title: addComment 
	 * @Description: 添加评论
	 * @return int    返回类型 
	 * @throws
	 */
	public int addComment(GoodComment comment);

	/**
	 * 
	 * @Title: getPersonalLatestGoodList 
	 * @Description: 获取某用户最近发布的商品列表
	 * @return Good    返回类型 
	 * @throws
	 */
	public List<Good> getPersonalLatestGoodList(int userId, int page);

	/**
	 * 
	 * @Title: getPersonalStaleGoodList 
	 * @Description: 获取用户发布的已经下架的商品列表
	 * @return List<Good>    返回类型 
	 * @throws
	 */
	public List<Good> getPersonalStaleGoodList(int userId, int page);

	/**
	 * 
	 * @Title: updateStaleTime 
	 * @Description: 下架商品
	 * @return int    返回类型 
	 * @throws
	 */
	public int updateStaleTime(int goodId);

	/**
	 * 
	 * @Title: updateGood 
	 * @Description: 更新商品信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int updateGood(int goodId, String title, String content, Float price);
	
	/**
	 * 
	 * @Title: getGoodExistByUser 
	 * @Description: 判断用户是否发送过商品信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int getGoodExistByUser(int goodId,int userId);
	
	/**
	 * 
	 * @Title: getGoodIsStale 
	 * @Description: 判断商品信息是否下架
	 * @return int    返回类型 
	 * @throws
	 */
	public int getGoodIsStale(int goodId);

	/**
	 * 
	 * @Title: addGood 
	 * @Description: 发布商品信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int addGood(Good good, List<GoodPic> icons);
}
