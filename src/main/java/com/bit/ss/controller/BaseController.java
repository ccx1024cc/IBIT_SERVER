package com.bit.ss.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

/**   
 * @Title: BaseController.java 
 * @Package com.bit.ss.controller 
 * @Description:  
 * @author CCX
 * @date 2015年11月30日 上午9:38:25 
 * @version V1.0   
 */
@Controller
@Produces(MediaType.APPLICATION_JSON)
public class BaseController {

	@Inject
	protected HttpSession session;
}
