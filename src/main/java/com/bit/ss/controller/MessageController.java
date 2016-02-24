package com.bit.ss.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bit.ss.exception.OuterException;
import com.bit.ss.model.Message;
import com.bit.ss.model.User;
import com.bit.ss.service.IMessageService;

/**   
 * @Title: MessageController.java 
 * @Package com.bit.ss.controller 
 * @Description:  消息模块控制器
 * @author CCX
 * @date 2016年2月17日 下午7:35:43 
 * @version V1.0   
 */
@Controller
@Path("message")
@Produces(MediaType.APPLICATION_JSON)
public class MessageController {

	@Autowired
	private IMessageService messageService;

	/**
	 * 
	 * @Title: getPersonalMessagesByModule 
	 * @Description: 按照模块获取某人的消息
	 * @return List<Message>    返回类型 
	 * @throws
	 */
	@Path("front/personalMessages/{module}")
	@GET
	public List<Message> getPersonalMessagesByModule(@QueryParam("page") int page, @PathParam("module") String module,
			@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		return messageService.getPersonalMessagesByModule(module, userInfo.getId(), page);
	}

	/**
	 * 
	 * @Title: getPersonalMessageNumByModule 
	 * @Description: 获取某人在某个模块中的消息数量
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/personalMessageNum/{module}")
	@Produces(MediaType.TEXT_PLAIN)
	public int getPersonalMessageNumByModule(@PathParam("module") String module, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		return messageService.getPersonalMessageNumByModule(userInfo.getId(), module);
	}
}
