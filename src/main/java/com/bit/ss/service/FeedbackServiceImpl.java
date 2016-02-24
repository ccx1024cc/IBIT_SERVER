package com.bit.ss.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.ss.mapper.FeedbackMapper;
import com.bit.ss.model.Feedback;

/**   
 * @Title: FeedbackServiceImpl.java 
 * @Package com.bit.ss.service 
 * @Description:  反馈模块服务实现类
 * @author CCX
 * @date 2016年2月15日 下午5:34:35 
 * @version V1.0   
 */
@Service
public class FeedbackServiceImpl implements IFeedbackService {

	@Autowired
	private FeedbackMapper feedbackMapper;

	@Override
	public int addFeedback(Feedback feedback) {
		return feedbackMapper.addFeedback(feedback);
	}

	@Override
	public int getFeedbackNumByStatus(Integer status) {
		return feedbackMapper.getFeedbackNumByStatus(status);
	}

	@Override
	public List<Feedback> getLatestFeedback(int page) {
		int start = (page - 1) * PC_FEEDBACK_PAGE_SIZE;
		return feedbackMapper.getFeedbackListByNum(start, PC_FEEDBACK_PAGE_SIZE);
	}

	@Override
	public List<Feedback> getOpenFeedbackList(int page) {
		int start = (page - 1) * PC_FEEDBACK_PAGE_SIZE;
		return feedbackMapper.getOpenFeedbackList(start, PC_FEEDBACK_PAGE_SIZE);
	}

	@Override
	public List<Feedback> getClosedFeedbackList(int page) {
		int start = (page - 1) * PC_FEEDBACK_PAGE_SIZE;
		return feedbackMapper.getClosedFeedbackList(start, PC_FEEDBACK_PAGE_SIZE);
	}

	@Override
	public int updateOpenedFeedbackList(int feedbackId, String fixedPerson, int status) {
		return feedbackMapper.updateOpenedFeedback(feedbackId, new Date(), fixedPerson, status);
	}
}
