package com.bit.ss.service;

import java.util.List;

import com.bit.ss.model.Feedback;

/**   
 * @Title: IFeedbackService.java 
 * @Package com.bit.ss.service 
 * @Description:  反馈模块服务接口
 * @author CCX
 * @date 2016年2月15日 下午5:33:11 
 * @version V1.0   
 */
public interface IFeedbackService {

	public final static int PC_FEEDBACK_PAGE_SIZE = 20;// 每一页显示的反馈数量

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
	 * @Description: 后台服务，获取某个状态下的反馈数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getFeedbackNumByStatus(Integer status);

	/**
	 * 
	 * @Title: getLatestFeedback 
	 * @Description: 获取最新的反馈列表
	 * @return  List<Feedback>    返回类型 
	 * @throws
	 */
	public List<Feedback> getLatestFeedback(int page);

	/**
	 * 
	 * @Title: getOpenFeedbackList 
	 * @Description: 后台服务，获取尚未关闭的反馈列表
	 * @return List<Feedback>    返回类型 
	 * @throws
	 */
	public List<Feedback> getOpenFeedbackList(int page);

	/**
	 * 
	 * @Title: getClosedFeedbackList 
	 * @Description: 后台服务，获取已经关闭的反馈列表
	 * @return List<Feedback>    返回类型 
	 * @throws
	 */
	public List<Feedback> getClosedFeedbackList(int page);

	/**
	 * 
	 * @Title: updateOpenedFeedbackList 
	 * @Description: 后台服务，关闭尚未解决的反馈信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int updateOpenedFeedbackList(int feedbackId, String fixedPerson, int status);
}
