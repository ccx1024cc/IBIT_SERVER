package com.bit.ss.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bit.ss.model.Answer;
import com.bit.ss.model.Question;

/**   
 * @Title: QuestionMapper.java 
 * @Package com.bit.ss.mapper 
 * @Description:  问题模块DAO接口
 * @author CCX
 * @date 2016年2月11日 上午9:50:24 
 * @version V1.0   
 */
@Repository
public interface QuestionMapper {

	/**
	 * 
	 * @Title: getHotResolvedQuestion 
	 * @Description: 获取已经解决的热门问题列表
	 * @return List<Question>    返回类型 
	 * @throws
	 */
	public List<Question> getHotResolvedQuestions(@Param("start") int start, @Param("num") int num);

	/**
	 * 
	 * @Title: getUnresolvedQuestions 
	 * @Description: 按照时间顺序获取最近的未解决问题列表
	 * @return List<Question>    返回类型 
	 * @throws
	 */
	public List<Question> getUnresolvedQuestions(@Param("start") int start, @Param("num") int num);

	/**
	 * 
	 * @Title: getQuestionByQuestionId 
	 * @Description: 获取单条问题详情
	 * @return Question    返回类型 
	 * @throws
	 */
	public Question getQuestionByQuestionId(@Param("questionId") int questionId);

	/**
	 * 
	 * @Title: addReadTimesBy1 
	 * @Description: 问题的浏览次数加1
	 * @return int    返回类型 
	 * @throws
	 */
	public int addReadTimesBy1(@Param("questionId") int questionId);

	/**
	 * 
	 * @Title: getAnswerListByQuestionId 
	 * @Description: 获取某个问题的回答列表
	 * @return List<Answer>    返回类型 
	 * @throws
	 */
	public List<Answer> getAnswerListByQuestionId(@Param("questionId") int questionId, @Param("userId") Integer userId,
			@Param("start") int start, @Param("num") int num);

	/**
	 * 
	 * @Title: getQuestionNumByUserId 
	 * @Description: 查询某用户发布的某问题是否存在
	 * @return int    返回类型 
	 * @throws
	 */
	public int getQuestionNumByUserId(@Param("userId") int userId, @Param("questionId") int questionId);

	/**
	 * 
	 * @Title: updateBestAnswer 
	 * @Description: 采纳最佳答案
	 * @return int    返回类型 
	 * @throws
	 */
	public int updateBestAnswer(@Param("answerId") int answerId);

	/**
	 * 
	 * @Title: getQuestionId 
	 * @Description: 由回答获取问题id
	 * @return int    返回类型 
	 * @throws
	 */
	public int getQuestionIdByAnswerId(@Param("answerId") int answerId);

	/**
	 * 
	 * @Title: getIsResovled 
	 * @Description: 判断问题是否已经解决
	 * @return int    返回类型 
	 * @throws
	 */
	public int getIsResovled(@Param("questionId") int questionId);

	/**
	 * 
	 * @Title: updateQuestionResovled 
	 * @Description: 更新问题的状态id：未解决-》已解决
	 * @return int    返回类型 
	 * @throws
	 */
	public int updateQuestionResovled(@Param("questionId") int questionId);

	/**
	 * 
	 * @Title: addAnswerAgreement 
	 * @Description: 添加赞
	 * @return int    返回类型 
	 * @throws
	 */
	public int addAnswerAgreement(@Param("answerId") int answerId, @Param("userId") int userId,
			@Param("pubTime") Date pubTime);

	/**
	 * 
	 * @Title: getAgreementExist 
	 * @Description: 判断用户是否已经赞过
	 * @return int    返回类型 
	 * @throws
	 */
	public int getAgreementExist(@Param("answerId") int answerId, @Param("userId") int userId);

	/**
	 * 
	 * @Title: deleteAnswerAgreement 
	 * @Description: 撤销赞
	 * @return int    返回类型 
	 * @throws
	 */
	public int deleteAnswerAgreement(@Param("answerId") int answerId, @Param("userId") int userId);

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
	 * @Title: getMaxAnswerFloorByQuestion 
	 * @Description: 获取某问题的最大楼层
	 * @return integer    返回类型 
	 * @throws
	 */
	public Integer getMaxAnswerFloorByQuestion(@Param("questionId") int questionId);

	/**
	 * 
	 * @Title: addQuestion 
	 * @Description: 提问
	 * @return int    问题主键
	 * @throws
	 */
	public int addQuestion(Question question);

	/**
	 * 
	 * @Title: addTags 
	 * @Description: 添加标签
	 * @return int    返回类型 
	 * @throws
	 */
	public int addTags(@Param("questionId") int questionId, @Param("tagName") String tagName);

	/**
	 * 
	 * @Title: getPersonalResovledQuestions 
	 * @Description: 获取某人已经解决的问题列表
	 * @return List<Question>    返回类型 
	 * @throws
	 */
	public List<Question> getPersonalResovledQuestions(@Param("userId") int userId, @Param("start") int start,
			@Param("num") int num);

	/**
	 * 
	 * @Title: getPersonalUnresolvedQuestions 
	 * @Description: 获取某人未解决的问题列表
	 * @return List<Question>    返回类型 
	 * @throws
	 */
	public List<Question> getPersonalUnresolvedQuestions(@Param("userId") int userId, @Param("start") int start,
			@Param("num") int num);

}
