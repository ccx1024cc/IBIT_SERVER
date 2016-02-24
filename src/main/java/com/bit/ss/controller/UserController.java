package com.bit.ss.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
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

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.bit.ss.exception.OuterException;
import com.bit.ss.model.Authority;
import com.bit.ss.model.Message;
import com.bit.ss.model.User;
import com.bit.ss.service.IMessageService;
import com.bit.ss.service.IUserService;
import com.bit.ss.util.ImageUtil;
import com.fasterxml.jackson.databind.JsonNode;

/**   
 * @Title: UserController.java 
 * @Package com.bit.ss.controller 
 * @Description:  用户管理控制类
 * @author CCX
 * @date 2015年12月18日 下午1:52:32 
 * @version V1.0   
 */
@Controller
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IMessageService messageService;

	@Autowired
	@Qualifier("root")
	private User root;

	/**
	 * 
	 * @Title: frontGetCheckCode 
	 * @Description: 获取验证码
	 * @return Response    返回类型 
	 * @throws
	 */
	@PUT
	@Path("front/userCheckCode")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCheckCode(@FormParam("phone") String phone, @Context HttpServletRequest request) {
		int num = userService.findUserNumByPhone(phone);
		if (num != 0)
			throw new OuterException(Status.CONFLICT, OuterException.PHONE_REGISTED);
		HttpSession session = request.getSession();
		String checkCode = userService.sendCheckCode(phone);
		session.setAttribute("checkCode", checkCode);
		session.setAttribute("phone", phone);
		return Response.status(Status.ACCEPTED).entity(checkCode).build();
	}

	/**
	 * 
	 * @Title: frontCompareCheckCode 
	 * @Description: 校验验证码
	 * @return Response    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/userCheckCode")
	public Response compareCheckCode(@QueryParam("checkCode") String checkCode, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (checkCode != null && session.getAttribute("checkCode") != null
				&& checkCode.equals(session.getAttribute("checkCode").toString())) {
			session.removeAttribute("checkCode");
			session.setAttribute("checked", true);
			return Response.accepted().build();
		} else {
			throw new OuterException(Status.NOT_ACCEPTABLE, OuterException.CHECKCODE_WRONG);
		}
	}

	/**
	 * 
	 * @Title: frontAddUser 
	 * @Description: 注册账号
	 * @return Response    返回类型 
	 * @throws
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("front/user")
	public Response addUser(JsonNode node, @Context HttpServletRequest request) {

		HttpSession session = request.getSession();
		// 判断是否验证过
		if (session.getAttribute("checked") != null && (Boolean) session.getAttribute("checked").equals(true)) {
			User user = new User();
			user.setName(node.get("name").asText());
			user.setEmail(node.get("email").asText());
			user.setPassword(node.get("password").asText());
			user.setPhone(session.getAttribute("phone").toString());
			user.setDate(new Date());
			user.setIconUrl("/resources/img/user/icon/default.jpg");

			userService.addUser(user);

			// 产生消息
			Message message = new Message();
			message.setContent("请及早更新个人资料");
			message.setModule(Message.MODULE_USER);
			message.setOwner(user);
			message.setPubTime(new Date());
			message.setService("IUserService.addUser");
			messageService.addMessage(message);

			return Response.ok().type(MediaType.TEXT_PLAIN).status(Status.CREATED).build();
		} else {
			throw new OuterException(Status.NOT_ACCEPTABLE, OuterException.CHECKCODE_NOT_CHECKED);
		}
	}

	/**
	 * 
	 * @Title: frontLogin 
	 * @Description: 客户端登陆
	 * @return Response    返回类型 
	 * @throws
	 */
	@POST
	@Path("front/login")
	public Response login(@FormParam("email") String email, @FormParam("password") String password,
			@Context HttpServletRequest request) throws URISyntaxException {
		HttpSession session = request.getSession();
		User user = userService.findUserByAccount(email, password);

		if (user == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.LOGIN_PWDERROR);
		} else {
			session.setAttribute("userInfo", user);
			return Response.status(Status.ACCEPTED).build();
		}
	}

	/**
	 * 
	 * @Title: frontLogOut 
	 * @Description: 注销
	 * @return Response    返回类型 
	 * @throws
	 */
	@DELETE
	@Path("front/login")
	public Response logOut(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		return Response.status(Status.OK).build();
	}

	/**
	 * @throws Exception 
	 * 
	 * @Title: frontUpdateUser 
	 * @Description: 修改用户信息
	 * @return Response    返回类型 
	 * @throws
	 */
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("front/user")
	public Response updateUser(FormDataMultiPart form, @Context HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("userInfo");

		if (user == null)
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		FormDataBodyPart temp = form.getField("name");
		if (temp != null)
			user.setName(temp.getValue());
		temp = form.getField("password");
		if (temp != null)
			user.setPassword(temp.getValue());
		temp = form.getField("gender");
		if (temp != null)
			user.setGender(Integer.valueOf(temp.getValue()));
		temp = form.getField("autograph");
		if (temp != null)
			user.setAutograph(temp.getValue());

		FormDataBodyPart icon = form.getField("icon");
		ImageUtil util = new ImageUtil();
		String iconPath = request.getSession().getServletContext().getRealPath("resources/img/user/icon");
		String completePath = util.saveImage(icon, iconPath);
		String newCompletePath = completePath.replace("icon", "thumbnail");
		util.createThumbnailFixedWidth(completePath, newCompletePath, 120, 120);
		user.setIconUrl(completePath.substring(completePath.indexOf("resources")));

		userService.updateUser(user);
		return Response.status(Status.CREATED).build();
	}

	/**
	 * 
	 * @Title: frontUserExistByEmail 
	 * @Description: 查询是否存在拥有该邮箱的账户
	 * @return Response    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/userExistByEmail")
	@Produces(MediaType.TEXT_PLAIN)
	public Response userExistByEmail(@QueryParam("email") String email) {
		int num = userService.findUserNumByEmail(email);
		if (num != 0)
			return Response.status(Status.FOUND).build();
		else
			return Response.status(Status.OK).build();
	}

	/**
	 * 
	 * @Title: getUserOnlineInfo 
	 * @Description: 查询当前用户基本信息
	 * @return User    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/userOnlineBasicInfo")
	public User getUserOnlineBasicInfo(@Context HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userInfo");

		if (user == null) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_LOGIN);
		} else {
			return user;
		}
	}

	/**
	 * 
	 * @Title: adminLogin 
	 * @Description: 管理端登陆接口
	 * @return Response    返回类型 
	 * @throws
	 */
	@POST
	@Path("admin/login")
	public Response adminLogin(@FormParam("email") String email, @FormParam("password") String password,
			@Context HttpServletRequest request) {

		HttpSession session = request.getSession();

		// root用户登录
		if (root.getEmail().equals(email) && root.getPassword().equals(password)) {
			Authority authority = new Authority();
			authority.setName("root");
			List<Authority> auths = new ArrayList<>();
			auths.add(authority);
			root.setAuths(auths);
			Calendar date = Calendar.getInstance();
			date.set(2016, 2, 19, 00, 00, 00);
			root.setDate(date.getTime());
			session.setAttribute("userInfo", root);
			return Response.status(Status.ACCEPTED).build();
		} else {
			User user = userService.findUserByAccount(email, password);
			if (user != null && user.getAuths() != null) {
				List<Authority> auths = user.getAuths();
				for (Authority each : auths) {
					if (each.getName().equals("admin")) {
						session.setAttribute("userInfo", user);
						return Response.status(Status.ACCEPTED).build();
					}
				}
			}
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_ENOUGH_AUTH);
		}
	}

	/**
	 * 
	 * @Title: adminLogOut 
	 * @Description: 管理端登出
	 * @return Response    返回类型 
	 * @throws
	 */
	@DELETE
	@Path("admin/login")
	public Response adminLogOut(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		return Response.ok().build();
	}

	/**
	 * 
	 * @Title: adminGetUsers 
	 * @Description: 管理端获取用户信息列表
	 * @return List<User>    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/userList")
	public List<User> adminGetUsers(@QueryParam("page") int page) {
		return userService.findUserListByCondition(new User(), 1);
	}

	/**
	 * 
	 * @Title: adminGetUserNum 
	 * @Description: 管理端获取用户数量
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/userNum")
	@Produces(MediaType.TEXT_PLAIN)
	public int adminGetUserNum() {
		return userService.findUserNumByCondition(new User());
	}

	/**
	 * 
	 * @Title: adminSearchUsers 
	 * @Description: 管理端搜索用户
	 * @return List<User>    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/search")
	public List<User> adminSearchUsers(@QueryParam("userId") Integer userId, @QueryParam("name") String name,
			@QueryParam("phone") String phone, @QueryParam("gender") Integer gender, @QueryParam("email") String email,
			@QueryParam("authorty") String authority, @QueryParam("page") int page) {
		User user = new User();
		if (authority != null) {
			List<Authority> authorities = new ArrayList<>();
			Authority temp = new Authority();
			temp.setName(authority);
			authorities.add(temp);
			user.setAuths(authorities);
		}

		user.setEmail(email);
		user.setGender(gender);
		user.setId(userId);
		user.setName(name);
		user.setPhone(phone);

		return userService.findUserListByCondition(user, page);
	}

	/**
	 * 
	 * @Title: adminSearchUserNum 
	 * @Description: 管理端搜索用户数量
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/searchNum")
	@Produces(MediaType.TEXT_PLAIN)
	public int adminSearchUserNum(@QueryParam("userId") Integer userId, @QueryParam("name") String name,
			@QueryParam("phone") String phone, @QueryParam("gender") Integer gender, @QueryParam("email") String email,
			@QueryParam("authority") String authority) {
		User user = new User();
		List<Authority> authorities = new ArrayList<>();
		Authority temp = new Authority();
		temp.setName(authority);
		authorities.add(temp);
		user.setAuths(authorities);

		user.setEmail(email);
		user.setGender(gender);
		user.setId(userId);
		user.setName(name);
		user.setPhone(phone);
		return userService.findUserNumByCondition(user);
	}

	/**
	 * 
	 * @Title: Response 
	 * @Description: 管理端添加授权
	 * @return void    返回类型 
	 * @throws
	 */
	@PUT
	@Path("admin/authority")
	public Response adminAddAuthority(@FormParam("userId") int userId, @FormParam("authority") String authority,
			@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		boolean isRoot = false;
		List<Authority> auths = user.getAuths();
		for (Authority auth : auths) {
			if (auth.getName().equals("root")) {
				isRoot = true;
			}
		}
		if (!isRoot) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_ENOUGH_AUTH);
		}

		//保证root用户只有一个
		if (authority.equals("root")) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_ENOUGH_AUTH);
		}

		Authority temp = new Authority();
		temp.setGrandTime(new Date());
		temp.setGrandUser(user.getName());
		temp.setName(authority);
		temp.setUserId(userId);

		userService.addAuthority(temp);
		return Response.status(Status.CREATED).build();
	}

	/**
	 * 
	 * @Title: deleteAuthority 
	 * @Description: 管理端删除权限
	 * @return Response    返回类型 
	 * @throws
	 */
	@DELETE
	@Path("admin/authority")
	public Response adminDeleteAuthority(@FormParam("authId") int authId, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		boolean isRoot = false;
		List<Authority> auths = user.getAuths();
		for (Authority auth : auths) {
			if (auth.getName().equals("root")) {
				isRoot = true;
			}
		}
		if (!isRoot) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_ENOUGH_AUTH);
		}

		userService.deleteAuthority(authId);
		return Response.status(Status.ACCEPTED).build();
	}

	/**
	 * 
	 * @Title: adminGetAuthority 
	 * @Description: 管理端获取某用户权限
	 * @return List<Authority>    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/personalAuths")
	public List<Authority> adminGetAuthority(@Context HttpServletRequest request,
			@QueryParam("userId") @DefaultValue("-1") int userId) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		boolean isRoot = false;
		List<Authority> auths = user.getAuths();
		for (Authority auth : auths) {
			if (auth.getName().equals("root")) {
				isRoot = true;
			}
		}
		if (!isRoot) {
			throw new OuterException(Status.UNAUTHORIZED, OuterException.NOT_ENOUGH_AUTH);
		}

		return userService.getPersonalAuths(userId);
	}

	/**
	 * 
	 * @Title: getPsersonalUsername 
	 * @Description: 管理端获取用户名称
	 * @return String    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/username")
	@Produces(MediaType.TEXT_PLAIN)
	public String adminGetPersonalUsername(@QueryParam("userId") @DefaultValue("-1") int userId,
			@Context HttpServletRequest request) {
		return userService.getPersonalUsername(userId);
	}

	/**
	 * 
	 * @Title: adminGetUserDetail 
	 * @Description: 管理端获取用户详细信息
	 * @return User    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/userDetail")
	public User adminGetUserDetail(@QueryParam("userId") @DefaultValue("-1") int userId) {
		return userService.getUserDetail(userId);
	}

	/**
	 * 
	 * @Title: adminUpdateUser 
	 * @Description: 管理端更新用户信息
	 * @return Response    返回类型 
	 * @throws
	 */
	@POST
	@Path("admin/userDetail")
	public Response adminUpdateUser(@FormParam("userId") Integer userId, @FormParam("name") String name,
			@FormParam("password") String password, @FormParam("phone") String phone,
			@FormParam("gender") Integer gender, @FormParam("email") String email,
			@FormParam("autograph") String autograph) {
		User detail = new User();
		if (userId != null)
			detail.setId(userId);
		if (name != null)
			detail.setName(name);
		if (password != null)
			detail.setPassword(password);
		if (email != null)
			detail.setEmail(email);
		if (phone != null)
			detail.setPhone(phone);
		if (gender != null)
			detail.setGender(gender);
		if (autograph != null)
			detail.setAutograph(autograph);
		userService.updateUserDetail(detail);
		return Response.status(Status.ACCEPTED).build();
	}

	/**
	 * 
	 * @Title: adminGetExistByEmail 
	 * @Description: 管理端通过邮箱判断用户是否存在
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/userExistByEmail")
	@Produces(MediaType.TEXT_PLAIN)
	public int adminGetExistByEmail(@QueryParam("email") String email, @QueryParam("userId") int userId) {
		return userService.findUserNumByEmail(email, userId);
	}

	/**
	 * 
	 * @Title: admintGetExistByPhone 
	 * @Description: 管理端通过手机号判断用户是否存在
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/userExistByPhone")
	@Produces(MediaType.TEXT_PLAIN)
	public int admintGetExistByPhone(@QueryParam("phone") String phone, @QueryParam("userId") int userId) {
		return userService.findUserNumByPhone(phone, userId);
	}

	/**
	 * @throws IOException 
	 * 
	 * @Title: adminAddUser 
	 * @Description: 管理端添加用户
	 * @return int    返回类型 
	 * @throws
	 */
	@PUT
	@Path("admin/addUser")
	@Produces(MediaType.TEXT_PLAIN)
	public int adminAddUser(FormDataMultiPart form, @Context HttpServletRequest request) throws IOException {
		User user = new User();
		user.setAutograph(form.getField("autograph").getValue());
		user.setDate(new Date());
		user.setEmail(form.getField("email").getValue());
		user.setGender(Integer.valueOf(form.getField("gender").getValue()));
		user.setIconUrl("/resources/img/user/icon/default.jpg");
		user.setName(form.getField("name").getValue());
		user.setPassword(form.getField("password").getValue());
		user.setPhone(form.getField("phone").getValue());

		FormDataBodyPart icon = form.getField("icon");
		ImageUtil util = new ImageUtil();
		String iconPath = request.getSession().getServletContext().getRealPath("resources/img/user/icon");
		String completePath = util.saveImage(icon, iconPath);
		String newCompletePath = completePath.replace("icon", "thumbnail");
		util.createThumbnailFixedWidth(completePath, newCompletePath, 120, 120);
		user.setIconUrl(completePath.substring(completePath.indexOf("resources")));

		userService.addUserByAdmin(user);
		return user.getId();
	}

	/**
	 * 
	 * @Title: adminGetUserExistByPhone 
	 * @Description: 管理端获取拥有某个电话号码的用户数量
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/userNumByPhone")
	@Produces(MediaType.TEXT_PLAIN)
	public int adminGetUserExistByPhone(@QueryParam("phone") String phone) {
		return userService.findUserNumByPhone(phone);
	}

	/**
	 * 
	 * @Title: adminGetUserExistByEmail 
	 * @Description: 管理端获取拥有某个邮箱的用户数量
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/userNumByEmail")
	@Produces(MediaType.TEXT_PLAIN)
	public int adminGetUserExistByEmail(@QueryParam("email") String email) {
		return userService.findUserNumByEmail(email);
	}
}
