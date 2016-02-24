package com.bit.ss.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.stereotype.Controller;

import com.bit.ss.model.User;

/**   
 * @Title: ViewController.java 
 * @Package com.bit.ss.controller 
 * @Description:  视图控制器
 * @author CCX
 * @date 2016年2月19日 上午8:29:51 
 * @version V1.0   
 */
@Controller
@Path("view")
public class ViewController {

	/**
	 * 
	 * @Title: getIndex 
	 * @Description: 获取主页
	 * @return Viewable    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/index.html")
	public Viewable getIndex(@Context HttpServletRequest request) {

		// 当前用户信息
		User user = (User) request.getSession().getAttribute("userInfo");
		Map<String, Object> model = new HashMap<>();
		model.put("userInfo", user);
		return new Viewable("/index.ftl", model);
	}
}
