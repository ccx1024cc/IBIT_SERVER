package com.bit.ss.service;

import java.util.List;

import com.bit.ss.model.Answer;
import com.bit.ss.model.Question;

/**   
 * @Title: IQuestionService.java 
 * @Package com.bit.ss.service 
 * @Description:  问答模块服务接口
 * @author CCX
 * @date 2016年2月11日 上午11:04:06 
 * @version V1.0   
 */
public interface IQuestionService {

	public static final int QUESTION_PAGESIZE = 15;// 每一页显示的问题数量
	public static final int ANSWER_PAGESIZE = 10;// 每一页显示的回答数量

	/**
	 * 
	 * @Title: getHotResolvedQuestionList 
	 * @Description: 查询热门的已经解决的问题
	 * @return List<Question>    返回类型 
	 * @throws
	 */
	public List<Question> getHotResolvedQuestionList(int page);

	/**
	 * 
	 * @Title: getUnresolvedQuestionList 
	 * @Description: 获取最新的未解决问题列表
	 * @return List<Question>    返回类型 
	 * @throws
	 */
	public List<Question> getUnresolvedQuestionList(int page);

	/**
	 * 
	 * @Title: getQuestionById 
	 * @Description: 查询问题详情
	 * @return Question    返回类型 
	 * @throws
	 */
	public Question getQuestionById(int id);

	/**
	 * 
	 * @Title: getAnswerListByQuestionId 
	 * @Description: 
	 * @return List<Answer>    返回类型 
	 * @throws
	 */
	public List<Answer> getAnswerListByQuestionId(int questionId, int page, Integer userId);

	/**
	 * 
	 * @Title: getQuestionNumByUserId 
	 * @Description: 查询是否存在某用户发布的某问题
	 * @return int    返回类型 
	 * @throws
	 */
	public int getQuestionNumByUserId(int userId, int answerId);

	/**
	 * 
	 * @Title: updateBestAnswer 
	 * @Description: 采纳最佳答案
	 * @return int    返回类型 
	 * @throws
	 */
	public int updateBestAnswer(int answerId);

	/**
	 * 
	 * @Title: addAnswerAgreement 
	 * @Description: 添加赞
	 * @return int    返回类型 
	 * @throws
	 */
	public int addAnswerAgreement(int userId, int answerId);

	/**
	 * 
	 * @Title: deleteAnswerAgreement 
	 * @Description: 撤销赞
	 * @return int    返回类型 
	 * @throws
	 */
	public int deleteAnswerAgreement(int userId, int answerId);

	/**
	 * 
	 * @Title: addAnswer 
	 * @Description: 添加回答
	 * @return int    返回类型 
	 * @throws
	 */
	public int addAnswer(Answer answer);

	/**
	 * 
	 * @Title: addQuestion 
	 * @Description: 提出问题
	 * @return int    返回类型 
	 * @throws
	 */
	public int addQuestion(Question question, String tags);

	/**
	 * 
	 * @Title: getPersonalResovledQuestions 
	 * @Description: 获取某人已经解决的问题列表
	 * @return List<Question>    返回类型 
	 * @throws
	 */
	public List<Question> getPersonalResovledQuestions(int userId, int page);

	/**
	 * 
	 * @Title: getPersonalUnresolvedQuestions 
	 * @Description: 获取某人未解决的问题列表
	 * @return List<Question>    返回类型 
	 * @throws
	 */
	public List<Question> getPersonalUnresolvedQuestions(int userId, int page);

}
