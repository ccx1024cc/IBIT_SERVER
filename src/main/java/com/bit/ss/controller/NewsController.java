package com.bit.ss.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bit.ss.domain.News;
import com.bit.ss.domain.NewsComment;
import com.bit.ss.service.INewsService;
import com.bit.ss.util.DateUtil;

/**   
 * @Title: NewsServiceController.java 
 * @Package com.bit.ss.controller 
 * @Description:  
 * @author CCX
 * @date 2015年11月30日 上午9:42:55 
 * @version V1.0   
 */
@Controller
@Path("news")
@Produces(MediaType.APPLICATION_JSON)
public class NewsController {

	@Autowired
	private INewsService newsService;

	/**
	 * 
	 * @Title: findList 
	 * @Description: 获取新闻列表
	 * @return List<News>    返回类型 
	 * @throws
	 */
	@GET
	@Path("news/newsList/{type}")
	public List<News> findList(@PathParam("type") int type, @QueryParam("page") int page,
			@QueryParam("keyword") String keyword) {
		List<News> list = null;
		try {
			list = newsService.findList(page, type, keyword);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	@GET
	@Path("news/num/{type}")
	@Produces(MediaType.TEXT_PLAIN)
	public int findNewsNum(@PathParam("type") int type, @QueryParam("pubTime") Date pubTime,
			@QueryParam("keyword") String keyword) {
		int num = 0;
		try {
			num = newsService.findCount(type, new DateUtil().formatDateTime(pubTime, DateUtil.DATE_FORMAT), keyword);
		} catch (Exception ex) {
			// TODO:例外
			ex.printStackTrace();
		}
		return num;
	}

	/**
	 * 
	 * @Title: findSingleNews 
	 * @Description: 获取新闻详情
	 * @return News    返回类型 
	 * @throws
	 */
	@GET
	@Path("news/news/singleNews/{newsID}")
	public News findSingleNews(@PathParam("newsID") int newsID) {
		News news = null;
		try {
			news = newsService.findNews(newsID);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return news;
	}

	/**
	 * 
	 * @Title: findComment 
	 * @Description: 获取单条新闻的评论列表
	 * @return List<NewsComment>    返回类型 
	 * @throws
	 */
	@GET
	@Path("comment/commentList/{news}")
	public List<NewsComment> findComment(@PathParam("newsID") int newsID, @QueryParam("num") int num,
			@QueryParam("start") int start) {
		List<NewsComment> list = null;
		try {
			newsService.findCommentList(start, num, newsID);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @Title: getCommentNum 
	 * @Description: 获取单条新闻的评论数量
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("comment/commentNum/{newsID}")
	@Produces(MediaType.TEXT_PLAIN)
	public int getCommentNum(@PathParam("newsID") int newsID) {
		int num = -1;
		try {
			num = newsService.getCommentNum(newsID);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * 
	 * @Title: addComment 
	 * @Description: 插入评论
	 * @return Response    返回类型 
	 * @throws
	 */
	@PUT
	@Path("comment/singleComment")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addComment(@FormParam("newsID") Integer newsID, @FormParam("content") String content,
			@Context HttpServletRequest request) {
		// if(newsID == null)
		// throw ...
		NewsComment comment = new NewsComment();
		comment.setContent(content);
		comment.setNewsId(newsID);
		comment.setTime(new Date());
		comment.setUserId((Integer) request.getSession().getAttribute("uid"));
		newsService.insertComment(comment);
		return Response.ok().type(MediaType.TEXT_PLAIN).status(Status.CREATED).build();
	}

}
