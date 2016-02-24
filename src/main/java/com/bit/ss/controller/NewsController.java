package com.bit.ss.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
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

import com.bit.ss.exception.OuterException;
import com.bit.ss.model.News;
import com.bit.ss.model.NewsComment;
import com.bit.ss.model.NewsConcern;
import com.bit.ss.model.User;
import com.bit.ss.service.INewsService;

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
	 * @Description: 翻页接口
	 * @return List<News>    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/newsByPage")
	public List<News> adminFindList(@QueryParam("page") int page, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer type = (Integer) session.getAttribute("newsType");
		type = type == null ? 0 : type;
		String keyword = (String) session.getAttribute("newsKeyword");
		List<News> list = null;
		try {
			list = newsService.findList(page, type, keyword);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @Title: searchList 
	 * @Description: 搜索接口
	 * @return List<News>    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/newsByKeyword")
	public List<News> adminSearchList(@QueryParam("page") int page, @QueryParam("keyword") String keyword,
			@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		if ("".equals(keyword) || keyword == null)
			session.removeAttribute("newsKeyword");
		else
			session.setAttribute("newsKeyword", keyword);
		Integer type = (Integer) session.getAttribute("newsType");
		type = type == null ? 0 : type;
		List<News> list = null;
		try {
			list = newsService.findList(page, type, keyword);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @Title: findNewsNumByKeyword 
	 * @Description: 搜索更新页码接口
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/numBySearch")
	@Produces(MediaType.TEXT_PLAIN)
	public int adminFindNewsNumByKeyword(@QueryParam("keyword") String keyword, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer type = (Integer) session.getAttribute("newsType");
		int num = 0;
		try {
			num = newsService.findCountByKeyword(type, keyword);
		} catch (Exception ex) {
			// TODO:例外
			ex.printStackTrace();
		}
		return num;
	}

	/**
	 * 
	 * @Title: findNewsListByType 
	 * @Description: 取出某一类新闻的某一页列表
	 * @return List<News>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/newsList/{type}")
	public List<News> findNewsListByType(@PathParam("type") @DefaultValue("0") Integer type,
			@QueryParam("page") Integer page) {
		return newsService.findNewsListByType(type, page);
	}

	/**
	 * 
	 * @Title: addNewsConcern 
	 * @Description: 添加关注某类新闻
	 * @return Response    返回类型 
	 * @throws
	 */
	@PUT
	@Path("front/news/concern/{type}")
	public Response addNewsConcern(@PathParam("type") Integer type, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		} else {
			newsService.addNewsConcern(userInfo.getId(), type);
		}
		return Response.status(Status.CREATED).build();
	}

	/**
	 * 
	 * @Title: deleteNewsConcern 
	 * @Description: 删除关注信息
	 * @return Response    返回类型 
	 * @throws
	 */
	@DELETE
	@Path("front/news/concern/{concernId}")
	public Response deleteNewsConcern(@PathParam("concernId") int concernId, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null)
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		else {
			newsService.deleteNewsConcern(concernId);
		}
		return Response.status(Status.OK).build();
	}

	/**
	 * 
	 * @Title: getConcernList 
	 * @Description: 获取关注新闻类型列表
	 * @return List<NewsConcern>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/news/concernList")
	public List<NewsConcern> getConcernList(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null)
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		else {
			return newsService.getConcernList(userInfo.getId());
		}
	}

	/**
	 * 
	 * @Title: addComment 
	 * @Description: 添加评论
	 * @return Response    返回类型 
	 * @throws
	 */
	@PUT
	@Path("front/newsComment")
	public Response addComment(@Context HttpServletRequest request, @FormParam("newsId") int newsId,
			@FormParam("content") String content) {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null)
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		else {
			NewsComment comment = new NewsComment();
			comment.setContent(content);
			comment.setNewsId(newsId);
			comment.setTime(new Date());
			comment.setUserId(userInfo.getId());
			newsService.addComment(comment);
			return Response.status(Status.CREATED).build();
		}
	}
	
	/**
	 * 
	 * @Title: getCommentList 
	 * @Description: 获取评论列表
	 * @return List<NewsComment>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/newsComment")
	public List<NewsComment> getCommentList(@QueryParam("newsId")int newsId,@QueryParam("page")int page){
		return newsService.getCommentList(newsId, page);
	}
}
