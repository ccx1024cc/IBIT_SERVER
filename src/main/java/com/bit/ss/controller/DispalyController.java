package com.bit.ss.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bit.ss.domain.News;
import com.bit.ss.service.INewsService;
import com.bit.ss.util.DateUtil;

/**   
 * @Title: BaseController.java 
 * @Package com.bit.ss.controller 
 * @Description:  
 * @author CCX
 * @date 2015年12月11日 上午9:08:00 
 * @version V1.0   
 */
@Controller
@Path("/display")
@Produces(MediaType.TEXT_HTML)
public class DispalyController {

	@Autowired
	private INewsService newsService;

	/**
	 * 
	 * @Title: getIndexPage 
	 * @Description: 直接访问主页
	 * @return Viewable    返回类型 
	 * @throws
	 */
	@GET
	@Path("/news.html")
	public Viewable getIndexPage(@QueryParam("newsType") @DefaultValue("0") int newsType,
			@QueryParam("newsTypeName") @DefaultValue("新闻快讯") String newsTypeName) {

		// 查询今日共有多少条新闻
		int newsNumToday = newsService.findCount(-1, new DateUtil().formatDateTime(new Date(), DateUtil.DATE_FORMAT),
				null);
		// 查询本类新闻共多少条
		int newsNumType = newsService.findCount(newsType, null, null);
		// 计算最大页数
		int pageNum = newsNumType % 30 == 0 ? newsNumType / 30 : newsNumType / 30 + 1;
		List<News> newsList = newsService.findList(1, newsType, null);
		Map<String, Object> model = new HashMap<>();
		model.put("theme", newsTypeName);
		model.put("newsList", newsList);
		model.put("newsNumToday", newsNumToday);
		model.put("maxPage", pageNum > 3 ? 3 : pageNum);
		model.put("pageNum", pageNum);

		return new Viewable("/news.ftl", model);
	}
}
