package com.bit.ss.exception;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: ExceptionMapper.java
 * @Package com.bit.ss.exception
 * @Description: 异常处理器
 * @author CCX
 * @date 2015年10月29日 上午10:06:12
 * @version V1.0
 */
@Provider
public class DefinedExceptionMapper implements ExceptionMapper<Exception> {

	private static Logger log = LoggerFactory.getLogger(DefinedExceptionMapper.class);

	@Context
	private HttpServletRequest req;

	@Override
	public Response toResponse(Exception arg0) {
		Status status = Status.INTERNAL_SERVER_ERROR;
		String message = "unhandled exception ： " + arg0.toString();
		// 封锁在系统内部的异常
		if (arg0 instanceof InnerException) {
			log.error("uri : {}\nmessage : {}\n", req.getRequestURL(), arg0.toString());
		}
		// 可向外部传播的异常
		else if (arg0 instanceof OuterException) {
			OuterException exception = (OuterException) arg0;
			message = exception.getShortcut();
			status = exception.getStatus();
			log.info("uri : {}\nmessage : {}\n", req.getRequestURL(), message);
		} else if (arg0 instanceof NotFoundException) {
			try {
				// 欢迎页面定位登录页面
				if (req.getRequestURI().toString().equals("/IBIT/")) {
					return Response.temporaryRedirect(new URI(req.getContextPath() + "/resources/pages/login.html"))
							.build();
				} else {
					return Response.status(Status.NOT_FOUND).build();
				}
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		// 未处理的异常种类
		else {
			log.error("uri : {}\nmessage : {}\n", req.getRequestURL(), message);
		}

		// arg0.printStackTrace();
		return Response.ok().type(MediaType.TEXT_PLAIN).status(status).entity(message).build();
	}
}
