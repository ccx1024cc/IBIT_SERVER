package com.bit.ss.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.bit.ss.exception.OuterException;
import com.bit.ss.model.Authority;
import com.bit.ss.model.User;

/**   
 * @Title: AuthorityFilter.java 
 * @Package com.bit.ss.filter 
 * @Description:  权限控制过滤器
 * @author CCX
 * @date 2015年12月17日 下午4:47:21 
 * @version V1.0   
 */
@Provider
public class AuthorityFilter implements ContainerRequestFilter {

	@Context
	private HttpServletRequest request;

	@Override
	public void filter(ContainerRequestContext arg0) throws IOException {
		String uri = arg0.getUriInfo().getRequestUri().toString();
		//拦截管理端请求
		if (uri.contains("/admin/")) {
			//放行登陆请求
			if(uri.contains("/user/admin/login"))
				return ;
			HttpSession session = request.getSession();
			User userInfo = (User) session.getAttribute("userInfo");
			if (userInfo != null) {
				List<Authority> auths = userInfo.getAuths();
				if (auths != null) {
					for (Authority authority : auths) {
						if (authority.getName().equals("root") || authority.getName().equals("admin")) {
							return;
						}
					}
				}
			}
			//没有权限，则废弃当前请求
			arg0.abortWith(Response.status(Status.UNAUTHORIZED).entity(OuterException.NOT_ENOUGH_AUTH).build());
		}
	}
}
