package com.bit.ss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
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
import com.bit.ss.model.Good;
import com.bit.ss.model.GoodComment;
import com.bit.ss.model.GoodPic;
import com.bit.ss.model.Message;
import com.bit.ss.model.User;
import com.bit.ss.service.IGoodService;
import com.bit.ss.service.IMessageService;
import com.bit.ss.util.ImageUtil;
import com.fasterxml.jackson.databind.JsonNode;

/**   
 * @Title: GoodController.java 
 * @Package com.bit.ss.controller 
 * @Description:  二手市场模块控制器
 * @author CCX
 * @date 2016年2月15日 下午3:49:51 
 * @version V1.0   
 */
@Controller
@Path("good")
@Produces(MediaType.APPLICATION_JSON)
public class GoodController {

	@Autowired
	private IGoodService goodService;
	
	@Autowired
	private IMessageService messageService;

	/**
	 * @throws IOException 
	 * 
	 * @Title: addGood 
	 * @Description: 发布商品信息
	 * @return Response    返回类型 
	 * @throws
	 */
	@PUT
	@Path("front/good")
	public Response addGood(FormDataMultiPart form, @Context HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		Good good = new Good();
		good.setContent(form.getField("content").getValue());
		good.setKind(Integer.valueOf(form.getField("kind").getValue()));
		good.setOwner(userInfo);
		good.setPrice(Float.valueOf(form.getField("price").getValue()));
		good.setTitle(form.getField("title").getValue());

		List<GoodPic> icons = new ArrayList<>();

		FormDataBodyPart icon = form.getField("icon1");
		if (icon != null) {
			ImageUtil util = new ImageUtil();
			String iconPath = request.getSession().getServletContext().getRealPath("resources/img/good/icon");
			String completePath = util.saveImage(icon, iconPath);
			if (completePath != null) {
				String newCompletePath = completePath.replace("icon", "thumbnail");
				util.createThumbnailFixedWidth(completePath, newCompletePath, 120, 120);
				GoodPic icon1 = new GoodPic();
				icon1.setUrl(completePath.substring(completePath.indexOf("resources")));
				icons.add(icon1);
			}
		}

		icon = form.getField("icon2");
		if (icon != null) {
			ImageUtil util = new ImageUtil();
			String iconPath = request.getSession().getServletContext().getRealPath("resources/img/good/icon");
			String completePath = util.saveImage(icon, iconPath);
			if (completePath != null) {
				String newCompletePath = completePath.replace("icon", "thumbnail");
				util.createThumbnailFixedWidth(completePath, newCompletePath, 120, 120);
				GoodPic icon2 = new GoodPic();
				icon2.setUrl(completePath.substring(completePath.indexOf("resources")));
				icons.add(icon2);
			}
		}

		icon = form.getField("icon3");
		if (icon != null) {
			ImageUtil util = new ImageUtil();
			String iconPath = request.getSession().getServletContext().getRealPath("resources/img/good/icon");
			String completePath = util.saveImage(icon, iconPath);
			if (completePath != null) {
				String newCompletePath = completePath.replace("icon", "thumbnail");
				util.createThumbnailFixedWidth(completePath, newCompletePath, 120, 120);
				GoodPic icon3 = new GoodPic();
				icon3.setUrl(completePath.substring(completePath.indexOf("resources")));
				icons.add(icon3);
			}
		}

		goodService.addGood(good, icons);
		return Response.status(Status.ACCEPTED).build();
	}

	/**
	 * 
	 * @Title: updateGood 
	 * @Description: 更新商品信息
	 * @return Response    返回类型 
	 * @throws
	 */
	@POST
	@Path("front/good")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateGood(JsonNode node, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		int goodId = node.get("goodId").asInt();
		String title = null;
		JsonNode temp = node.get("title");
		if (temp != null && !temp.asText().equals(""))
			title = temp.asText();
		String content = null;
		temp = node.get("content");
		if (temp != null && !temp.asText().equals(""))
			content = temp.asText();
		Float price = null;
		temp = node.get("price");
		if (temp != null && !temp.asText().equals(""))
			price = Float.valueOf(temp.asText());

		// 判断用户是否发送过该商品信息
		int exist = goodService.getGoodExistByUser(goodId, userInfo.getId());
		if (exist == 0) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_ENOUGH_AUTH);
		}

		// 判断该商品信息是否已经下架
		exist = goodService.getGoodIsStale(goodId);
		if (exist == 0) {
			throw new OuterException(Status.EXPECTATION_FAILED, OuterException.GOOD_STALED);
		}

		goodService.updateGood(goodId, title, content, price);
		return Response.status(Status.ACCEPTED).build();
	}

	/**
	 * 
	 * @Title: addComment 
	 * @Description: 发表评论
	 * @return Response    返回类型 
	 * @throws
	 */
	@PUT
	@Path("front/commet")
	public Response addComment(JsonNode node, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		GoodComment comment = new GoodComment();
		comment.setContent(node.get("content").asText());
		comment.setGoodId(node.get("goodId").asInt());
		comment.setOwner(userInfo);
		comment.setTime(new Date());
		goodService.addComment(comment);

		// 产生消息
		Good good = goodService.getGoodById(comment.getGoodId());
		if (good != null) {
			Message message = new Message();
			message.setContent(userInfo.getName() + "评论了您的商品\"" + good.getTitle() + "\"");
			message.setOwner(good.getOwner());
			message.setModule(Message.MODULE_GOOD);
			message.setPubTime(new Date());
			message.setService("IGoodService.addComment");
			
			messageService.addMessage(message);
		}

		return Response.status(Status.ACCEPTED).build();
	}

	/**
	 * 
	 * @Title: updateStaleTime 
	 * @Description: 商品下架
	 * @return Response    返回类型 
	 * @throws
	 */
	@POST
	@Path("front/stale")
	public Response updateStaleTime(@FormParam("goodId") int goodId, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		// 判断用户是否发送过该商品信息
		int exist = goodService.getGoodExistByUser(goodId, userInfo.getId());
		if (exist == 0) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_ENOUGH_AUTH);
		}

		goodService.updateStaleTime(goodId);
		return Response.status(Status.ACCEPTED).build();
	}

	/**
	 * 
	 * @Title: getPersonalLatestGoodList 
	 * @Description: 获取当前用户未下架的商品列表
	 * @return List<Good>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/latestGoodList")
	public List<Good> getPersonalLatestGoodList(@QueryParam("page") int page, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		return goodService.getPersonalLatestGoodList(userInfo.getId(), page);
	}

	/**
	 * 
	 * @Title: getPersonalStaleGoodList 
	 * @Description: 获取当前用户已经下架的商品列表
	 * @return List<Good>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/staledGoodList")
	public List<Good> getPersonalStaleGoodList(@QueryParam("page") int page, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		return goodService.getPersonalStaleGoodList(userInfo.getId(), page);
	}

	/**
	 * 
	 * @Title: getLatestGoodsByType 
	 * @Description: 获取最近某类商品信息
	 * @return List<Good>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/latestGoods/{type}")
	public List<Good> getLatestGoodsByType(@PathParam("type") int type, @QueryParam("page") int page) {
		return goodService.getLatestGoodsByType(type, page);
	}

	/**
	 * 
	 * @Title: getHotGoodsByType 
	 * @Description: 获取热门商品信息
	 * @return List<Good>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/hotGoods/{type}")
	public List<Good> getHotGoodsByType(@PathParam("type") int type, @QueryParam("page") int page) {
		return goodService.getHotGoodsByType(type, page);
	}

	/**
	 * 
	 * @Title: getGoodById 
	 * @Description: 获取问题详情
	 * @return Good    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/goodById/{id}")
	public Good getGoodById(@PathParam("id") int id) {
		return goodService.getGoodById(id);
	}

	/**
	 * 
	 * @Title: getCommentListByGood 
	 * @Description: 获取某条商品的评论列表
	 * @return List<GoodComment>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/goodCommentList/{goodId}")
	public List<GoodComment> getCommentListByGood(@PathParam("goodId") int goodId, @QueryParam("page") int page) {
		return goodService.getCommentListByGood(goodId, page);
	}
}
