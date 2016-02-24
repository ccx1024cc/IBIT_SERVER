package com.bit.ss.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bit.ss.exception.OuterException;
import com.bit.ss.model.Feedback;
import com.bit.ss.model.User;
import com.bit.ss.service.IFeedbackService;
import com.fasterxml.jackson.databind.JsonNode;

/**   
 * @Title: FeedbackController.java 
 * @Package com.bit.ss.controller 
 * @Description:  反馈模块控制器
 * @author CCX
 * @date 2016年2月15日 下午5:36:06 
 * @version V1.0   
 */
@Controller
@Path("feedback")
@Produces(MediaType.APPLICATION_JSON)
public class FeedbackController {

	@Autowired
	private IFeedbackService feedbackService;

	/**
	 * 
	 * @Title: addFeedback 
	 * @Description: 添加反馈信息
	 * @return Response    返回类型 
	 * @throws
	 */
	@PUT
	@Path("front/feedback")
	public Response addFeedback(JsonNode node, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		Feedback feedback = new Feedback();
		feedback.setContent(node.get("content").asText());
		feedback.setPlatForm(node.get("platForm").asInt());
		feedback.setPublisher(userInfo);
		feedback.setPubTime(new Date());

		feedbackService.addFeedback(feedback);
		return Response.status(Status.CREATED).build();
	}

	/**
	 * '
	 * @Title: getFeedbackTotalNum 
	 * @Description: 管理端，获取反馈总数
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/feedbackTotalNum")
	@Produces(MediaType.TEXT_PLAIN)
	public int adminGetFeedbackTotalNum() {
		return feedbackService.getFeedbackNumByStatus(null);
	}

	/**
	 * 
	 * @Title: adminGetClosedFeedbackNum 
	 * @Description: 管理端，获取已经关闭的反馈数量
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/closedFeedbackNum")
	@Produces(MediaType.TEXT_PLAIN)
	public int adminGetClosedFeedbackNum() {
		return feedbackService.getFeedbackNumByStatus(1) + feedbackService.getFeedbackNumByStatus(2);
	}

	/**
	 * 
	 * @Title: adminGetOpenedFeedbackNum 
	 * @Description: 管理端，获取尚未解决的反馈数量
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/openedFeedbackNum")
	@Produces(MediaType.TEXT_PLAIN)
	public int adminGetOpenedFeedbackNum() {
		return feedbackService.getFeedbackNumByStatus(0);
	}

	/**
	 * 
	 * @Title: adminGetLatestFeedback 
	 * @Description: 管理端，获取最新的反馈列表
	 * @return List<Feedback>    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/latestFeedback")
	public List<Feedback> adminGetLatestFeedback(@QueryParam("page") int page) {
		return feedbackService.getLatestFeedback(page);
	}

	/**
	 * 
	 * @Title: adminGetOpenedFeedbackList 
	 * @Description: 管理端，获取尚未解决的反馈列表
	 * @return List<Feedback>    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/openedFeedbackList")
	public List<Feedback> adminGetOpenedFeedbackList(@QueryParam("page") int page) {
		return feedbackService.getOpenFeedbackList(page);
	}

	/**
	 * 
	 * @Title: adminGetClosedFeedbackList 
	 * @Description: 管理端，获取已经关闭的反馈列表
	 * @return List<Feedback>    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/closedFeedbackList")
	public List<Feedback> adminGetClosedFeedbackList(@QueryParam("page") int page) {
		return feedbackService.getClosedFeedbackList(page);
	}

	/**
	 * 
	 * @Title: adminCloseFeedback 
	 * @Description: 管理端，关闭尚未解决的反馈
	 * @return Response    返回类型 
	 * @throws
	 */
	@POST
	@Path("admin/ClosingFeedback")
	public Response adminCloseFeedback(@FormParam("feedbackId") int feedbackId, @FormParam("status") int status,
			@Context HttpServletRequest request) {
		User userInfo = (User) request.getSession().getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_ENOUGH_AUTH);
		}

		feedbackService.updateOpenedFeedbackList(feedbackId, userInfo.getName(), status);
		return Response.ok().build();
	}
}
