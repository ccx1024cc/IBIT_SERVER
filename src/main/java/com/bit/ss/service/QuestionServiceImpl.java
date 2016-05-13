package com.bit.ss.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.ss.exception.OuterException;
import com.bit.ss.mapper.QuestionMapper;
import com.bit.ss.model.Answer;
import com.bit.ss.model.Question;

/**   
 * @Title: QuestionServiceImpl.java 
 * @Package com.bit.ss.service 
 * @Description:  问答模块服务实现类
 * @author CCX
 * @date 2016年2月11日 上午11:09:34 
 * @version V1.0   
 */
@Service
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public List<Question> getHotResolvedQuestionList(int page) {
		int start = (page - 1) * QUESTION_PAGESIZE;
		return questionMapper.getHotResolvedQuestions(start, QUESTION_PAGESIZE);
	}

	@Override
	public List<Question> getUnresolvedQuestionList(int page) {
		int start = (page - 1) * QUESTION_PAGESIZE;
		return questionMapper.getUnresolvedQuestions(start, QUESTION_PAGESIZE);
	}

	@Override
	public Question getQuestionById(int id) {
		questionMapper.addReadTimesBy1(id);
		return questionMapper.getQuestionByQuestionId(id);
	}

	@Override
	public List<Answer> getAnswerListByQuestionId(int questionId, int page, Integer userId) {
		int start = ANSWER_PAGESIZE * (page - 1);
		return questionMapper.getAnswerListByQuestionId(questionId, userId, start, ANSWER_PAGESIZE);
	}

	@Override
	public int getQuestionNumByUserId(int userId, int answerId) {
		int questionId = questionMapper.getQuestionIdByAnswerId(answerId);
		return questionMapper.getQuestionNumByUserId(userId, questionId);
	}

	@Override
	public int updateBestAnswer(int answerId) {
		questionMapper.updateBestAnswer(answerId);
		int questionId = questionMapper.getQuestionIdByAnswerId(answerId);
		int isResolved = questionMapper.getIsResovled(questionId);
		if (isResolved == 1)
			throw new OuterException(Status.CONFLICT, OuterException.QUESTION_HAS_RESOLVED);
		return questionMapper.updateQuestionResovled(questionId);
	}

	@Override
	public int addAnswerAgreement(int userId, int answerId) {
		int exist = questionMapper.getAgreementExist(answerId, userId);
		if (exist == 1) {
			throw new OuterException(Status.CONFLICT, OuterException.HAS_AGREED);
		}
		return questionMapper.addAnswerAgreement(answerId, userId, new Date());
	}

	@Override
	public int deleteAnswerAgreement(int userId, int answerId) {
		return questionMapper.deleteAnswerAgreement(answerId, userId);
	}

	@Override
	public int addAnswer(Answer answer) {
		int maxFloor = questionMapper.getMaxAnswerFloorByQuestion(answer.getQuestionId());
		answer.setFloor(maxFloor + 1);
		return questionMapper.addAnswer(answer);
	}

	@Override
	public int addQuestion(Question question, String tags) {
		questionMapper.addQuestion(question);
		int questionId = question.getId();
		String[] tagsArray = tags.split(",");
		for (String tagName : tagsArray) {
			questionMapper.addTags(questionId, tagName);
		}
		return questionId;
	}

	@Override
	public List<Question> getPersonalResovledQuestions(int userId, int page) {
		int start = QUESTION_PAGESIZE * (page - 1);
		return questionMapper.getPersonalResovledQuestions(userId, start, QUESTION_PAGESIZE);
	}

	@Override
	public List<Question> getPersonalUnresolvedQuestions(int userId, int page) {
		int start = QUESTION_PAGESIZE * (page - 1);
		return questionMapper.getPersonalUnresolvedQuestions(userId, start, QUESTION_PAGESIZE);
	}
}
