package com.bit.ss.controller;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bit.ss.model.Version;
import com.bit.ss.service.IVersionService;
import com.fasterxml.jackson.databind.JsonNode;

/**   
 * @Title: VersionController.java 
 * @Package com.bit.ss.controller 
 * @Description:  版本控制模块控制器
 * @author CCX
 * @date 2016年2月15日 下午5:58:39 
 * @version V1.0   
 */
@Controller
@Path("version")
@Produces(MediaType.APPLICATION_JSON)
public class VersionController {

	@Autowired
	private IVersionService versionService;

	/**
	 * 
	 * @Title: getLatestNumber 
	 * @Description: 获取最新的版本号
	 * @return String    返回类型 
	 * @throws
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("front/latestNumber")
	public String getLatestNumber() {
		return versionService.getLatestNumber();
	}

	/**
	 * 
	 * @Title: getVersionNum 
	 * @Description: 管理端，获取版本总数
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/versionNum")
	@Produces(MediaType.TEXT_PLAIN)
	public int getVersionNum() {
		return versionService.getVersionNum();
	}

	/**
	 * 
	 * @Title: getVersionList 
	 * @Description: 获取版本列表
	 * @return List<Version>    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/versionList")
	public List<Version> getVersionList(@QueryParam("page") int page) {
		return versionService.getVersionList(page);
	}

	/**
	 * 
	 * @Title: addVersion 
	 * @Description: 管理端，发行新版本
	 * @return Response    返回类型 
	 * @throws
	 */
	@PUT
	@Path("admin/version")
	public Response addVersion(JsonNode jsonNode) {
		Version version = new Version();
		version.setDescription(jsonNode.get("description").asText());
		version.setNumber(jsonNode.get("number").asText());
		version.setPublisher(jsonNode.get("publisher").asText());
		version.setPubTime(new Date());
		versionService.addVersion(version);
		return Response.status(Status.CREATED).build();
	}
}
