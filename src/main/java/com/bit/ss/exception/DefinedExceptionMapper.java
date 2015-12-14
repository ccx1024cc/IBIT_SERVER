package com.bit.ss.exception;

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

import com.bit.ss.domain.ExceptionCode;

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
		String uri = req.getRequestURI();
		String code = null;
		String message = null;
		Object[] params = null;
		if (arg0 instanceof BaseException) {
			BaseException baseException = (BaseException) arg0;
			message = baseException.getMessage();
			params = baseException.getValues();
			code = baseException.getCode();
			if (code.equals(ExceptionCode.NOT_LOGIN)) {
				status = Status.UNAUTHORIZED;
			}
		} else if (arg0 instanceof NotFoundException) {
			status = Status.NOT_FOUND;
		}

		log.error("uri : {}\nerrorcode : {}\nmessage : {}\nparams[] : {}\n", uri, code, message, params);

		return Response.ok().type(MediaType.TEXT_PLAIN).status(status).build();
	}
}
