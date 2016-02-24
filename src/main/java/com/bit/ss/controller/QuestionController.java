package com.bit.ss.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bit.ss.exception.OuterException;
import com.bit.ss.model.Answer;
import com.bit.ss.model.Message;
import com.bit.ss.model.Question;
import com.bit.ss.model.User;
import com.bit.ss.service.IMessageService;
import com.bit.ss.service.IQuestionService;
import com.bit.ss.util.ImageUtil;
import com.fasterxml.jackson.databind.JsonNode;

/**   
 * @Title: QuestionController.java 
 * @Package com.bit.ss.controller 
 * @Description:  问答模块控制器
 * @author CCX
 * @date 2016年2月11日 上午11:00:58 
 * @version V1.0   
 */
@Path("question")
@Controller
@Produces(MediaType.APPLICATION_JSON)
public class QuestionController {

	@Autowired
	private IQuestionService questionService;

	@Autowired
	private IMessageService messageService;

	/**
	 * 
	 * @Title: getHotResolvedQuestionList 
	 * @Description: 查询热门的已经解决的问题
	 * @return List<Question>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/resolvedQuestions")
	public List<Question> getHotResolvedQuestionList(@QueryParam("page") int page) {
		return questionService.getHotResolvedQuestionList(page);
	}

	/**
	 * 
	 * @Title: getUnresolvedQuestionList 
	 * @Description: 获取最新的未解决问题列表
	 * @return List<Question>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/unresolvedQuestions")
	public List<Question> getUnresolvedQuestionList(@QueryParam("page") int page) {
		return questionService.getUnresolvedQuestionList(page);
	}

	/**
	 * 
	 * @Title: getQuestionById 
	 * @Description: 查询问题详情
	 * @return Question    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/questionById/{id}")
	public Question getQuestionById(@PathParam("id") int id) {
		return questionService.getQuestionById(id);
	}

	/**
	 * 
	 * @Title: getAnswerListByQuestionId 
	 * @Description: 查询某一问题的回答列表
	 * @return List<Answer>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/answerListByQuestion/{id}")
	public List<Answer> getAnswerListByQuestionId(@PathParam("id") int id, @QueryParam("page") int page,
			@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		Integer userId = null;
		if (userInfo != null) {
			userId = userInfo.getId();
		}
		return questionService.getAnswerListByQuestionId(id, page, userId);
	}

	/**
	 * 
	 * @Title: adpotAnswer 
	 * @Description: 采纳答案
	 * @return Response    返回类型 
	 * @throws
	 */
	@POST
	@Path("front/bestAnswer")
	public Response adpotAnswer(@FormParam("answerId") int answerId, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		// 判断是否登陆
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		// 判断当前用户是否为问题的发布者
		int exist = questionService.getQuestionNumByUserId(userInfo.getId(), answerId);
		if (exist == 0) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_ENOUGH_AUTH);
		}

		questionService.updateBestAnswer(answerId);
		return Response.status(Status.CREATED).build();
	}

	/**
	 * 
	 * @Title: addAnswerAgreement 
	 * @Description: 添加赞
	 * @return Response    返回类型 
	 * @throws
	 */
	@PUT
	@Path("front/answerAgreement")
	public Response addAnswerAgreement(@FormParam("answerId") int answerId, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		int userId = userInfo.getId();
		questionService.addAnswerAgreement(userId, answerId);
		return Response.status(Status.CREATED).build();
	}

	/**
	 * 
	 * @Title: deleteAnswerAgreement 
	 * @Description: 撤销赞
	 * @return Response    返回类型 
	 * @throws
	 */
	@DELETE
	@Path("front/answerAgreement")
	public Response deleteAnswerAgreement(@FormParam("answerId") int answerId, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		int userId = userInfo.getId();
		questionService.deleteAnswerAgreement(userId, answerId);
		return Response.status(Status.CREATED).build();
	}

	/**
	 * 
	 * @Title: addAnswer 
	 * @Description: 添加回答
	 * @return Response    返回类型 
	 * @throws
	 */
	@PUT
	@Path("front/answer")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAnswer(JsonNode node, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		Answer answer = new Answer();
		answer.setOwner(userInfo);
		answer.setTime(new Date());
		answer.setContent(node.get("content").asText());
		answer.setQuestionId(node.get("questionId").asInt());

		questionService.addAnswer(answer);

		// 生成消息
		Question question = questionService.getQuestionById(answer.getQuestionId());
		if (question != null) {
			Message message = new Message();
			message.setContent(userInfo.getName() + "回答了您的问题\"" + question.getTitle() + "\"");
			message.setModule(Message.MODULE_QUESTION);
			message.setOwner(question.getOwner());
			message.setPubTime(new Date());
			message.setService("IQuestionService.addAnswer");
			messageService.addMessage(message);
		}

		return Response.status(Status.CREATED).build();
	}

	/**
	 * @throws IOException 
	 * 
	 * @Title: addQuestion 
	 * @Description: 添加问题
	 * @return Response    返回类型 
	 * @throws
	 */
	@PUT
	@Path("front/question")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response addQuestion(FormDataMultiPart form, @Context HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		Question question = new Question();
		question.setContent(form.getField("content").getValue());
		question.setDate(new Date());
		question.setOwner(userInfo);
		question.setTitle(form.getField("title").getValue());

		FormDataBodyPart icon = form.getField("icon");
		ImageUtil util = new ImageUtil();
		String iconPath = request.getSession().getServletContext().getRealPath("resources/img/question/icon");
		String completePath = util.saveImage(icon, iconPath);
		if (completePath != null) {
			String newCompletePath = completePath.replace("icon", "thumbnail");
			util.createThumbnailFixedWidth(completePath, newCompletePath, 120, 120);
			question.setIconUrl(completePath.substring(completePath.indexOf("resources")));
		}

		String tags = form.getField("tags").getValue();
		questionService.addQuestion(question, tags);
		return Response.status(Status.CREATED).build();
	}

	/**
	 * 
	 * @Title: getPersonalResovledQuestions 
	 * @Description: 获取某人已经解决的问题列表
	 * @return List<Question>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/personalQuestions/resolved")
	public List<Question> getPersonalResovledQuestions(@QueryParam("page") int page,
			@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		return questionService.getPersonalResovledQuestions(userInfo.getId(), page);
	}

	/**
	 * 
	 * @Title: getPersonalUnResovledQuestions 
	 * @Description: 获取某人未解决的问题列表
	 * @return List<Question>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/personalQuestions/unresolved")
	public List<Question> getPersonalUnResovledQuestions(@QueryParam("page") int page,
			@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		return questionService.getPersonalUnresolvedQuestions(userInfo.getId(), page);
	}
}
