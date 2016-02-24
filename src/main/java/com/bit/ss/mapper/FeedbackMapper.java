package com.bit.ss.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bit.ss.model.Feedback;

/**   
 * @Title: FeedbackMapper.java 
 * @Package com.bit.ss.mapper 
 * @Description:  反馈模块DAO接口
 * @author CCX
 * @date 2016年2月15日 下午5:27:46 
 * @version V1.0   
 */
@Repository
public interface FeedbackMapper {

	/**
	 * 
	 * @Title: addFeedback 
	 * @Description: 插入反馈
	 * @return int    返回类型 
	 * @throws
	 */
	public int addFeedback(Feedback feedback);

	/**
	 * 
	 * @Title: getFeedbackNumByStatus 
	 * @Description: 获取某个状态下的反馈总数
	 * @return int    返回类型 
	 * @throws
	 */
	public int getFeedbackNumByStatus(@Param("status") Integer status);

	/**
	 * 
	 * @Title: getFeedbackListByNum 
	 * @Description: 按时间顺序，获取最新的反馈列表
	 * @return List<Feedback>    返回类型 
	 * @throws
	 */
	public List<Feedback> getFeedbackListByNum(@Param("start")int start,@Param("num")int num);
	
	/**
	 * 
	 * @Title: getClosedFeedbackList 
	 * @Description: 获取已经关闭的反馈列表
	 * @return List<Feedback>    返回类型 
	 * @throws
	 */
	public List<Feedback> getClosedFeedbackList(@Param("start") int start, @Param("num") int num);

	/**
	 * 
	 * @Title: getOpenFeedbackList 
	 * @Description: 获取尚未解决的反馈列表
	 * @return List<Feedback>    返回类型 
	 * @throws
	 */
	public List<Feedback> getOpenFeedbackList(@Param("start") int start, @Param("num") int num);

	/**
	 * 
	 * @Title: updateOpenedFeedback 
	 * @Description: 关闭尚未解决的反馈
	 * @return int    返回类型 
	 * @throws
	 */
	public int updateOpenedFeedback(@Param("feedbackId") int feedbackId, @Param("fixedTime") Date fixedTime,
			@Param("fixedPerson") String fixedPerson, @Param("status") int status);
}
