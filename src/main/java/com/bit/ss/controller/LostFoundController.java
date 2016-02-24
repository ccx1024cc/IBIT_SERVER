package com.bit.ss.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
import com.bit.ss.model.LostFound;
import com.bit.ss.model.User;
import com.bit.ss.service.ILostFoundService;
import com.bit.ss.util.ImageUtil;

/**   
 * @Title: LostFoundController.java 
 * @Package com.bit.ss.controller 
 * @Description:  失物招领模块控制器
 * @author CCX
 * @date 2016年2月14日 下午3:12:54 
 * @version V1.0   
 */
@Controller
@Path("lostFound")
@Produces(MediaType.APPLICATION_JSON)
public class LostFoundController {
	@Autowired
	private ILostFoundService lostFoundService;

	/**
	 * 
	 * @Title: getLatestStuffList 
	 * @Description: 获取最近的失物信息
	 * @return List<LostFound>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/LatestStuff")
	public List<LostFound> getLatestStuffList(@QueryParam("page") int page) {
		return lostFoundService.getLatestStuffList(page);
	}

	/**
	 * 
	 * @Title: getOldStuffList 
	 * @Description: 获取时间较长的失物信息
	 * @return List<LostFound>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/oldStuff")
	public List<LostFound> getOldStuffList(@QueryParam("page") int page) {
		return lostFoundService.getOldStuffList(page);
	}

	/**
	 * 
	 * @Title: addLostFound 
	 * @Description: 发表失物信息
	 * @return Response    返回类型 
	 * @throws
	 */
	@PUT
	@Path("front/lostFound")
	public Response addLostFound(FormDataMultiPart form, @Context HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("userInfo");
		if (userInfo == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		}

		LostFound lostFound = new LostFound();
		lostFound.setContent(form.getField("content").getValue());
		lostFound.setLocation(form.getField("location").getValue());
		lostFound.setPubTime(new Date());
		lostFound.setPublisher(userInfo);

		FormDataBodyPart icon = form.getField("icon");
		ImageUtil util = new ImageUtil();
		String iconPath = request.getSession().getServletContext().getRealPath("resources/img/lostFound/icon");
		String completePath = util.saveImage(icon, iconPath);
		if (completePath != null) {
			String newCompletePath = completePath.replace("icon", "thumbnail");
			util.createThumbnailFixedWidth(completePath, newCompletePath, 120, 120);
			lostFound.setIconUrl(completePath.substring(completePath.indexOf("resources")));
		}

		lostFoundService.addLostFound(lostFound);
		return Response.status(Status.CREATED).build();
	}
}
