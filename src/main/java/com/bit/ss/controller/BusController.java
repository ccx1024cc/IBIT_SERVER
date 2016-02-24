package com.bit.ss.controller;

import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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

import com.bit.ss.model.Bus;
import com.bit.ss.model.News;
import com.bit.ss.service.IBusService;
import com.bit.ss.util.DateUtil;
import com.fasterxml.jackson.databind.JsonNode;

/**   
 * @Title: BusController.java 
 * @Package com.bit.ss.controller 
 * @Description: 班车服务控制器 
 * @author CCX
 * @date 2016年2月8日 下午4:51:18 
 * @version V1.0   
 */
@Controller
@Path("bus")
@Produces(MediaType.APPLICATION_JSON)
public class BusController {

	@Autowired
	private IBusService busService;

	/**
	 * 
	 * @Title: getTempBusByStartDate 
	 * @Description: 获取某一天出发的所有临时班车
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	@Path("front/tempBusListByStartDate")
	@GET
	public List<Bus> getTempBusByStartDate(@QueryParam("startDate") String startDate) {
		return busService.getTempBusByStartDate(startDate);
	}

	/**
	 * 
	 * @Title: getLatestBusList 
	 * @Description: 获取最新的定时班车表
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	@Path("front/LatestBusList")
	@GET
	public List<Bus> getLatestBusList() {
		return busService.getLatestBusList();
	}

	/**
	 * 
	 * @Title: getTempBusByPoints 
	 * @Description: 获取某两点间运行的临时班车表
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	@GET
	@Path("front/TempBusListByPoints")
	public List<Bus> getTempBusByPoints(@QueryParam("startPoint") String startPoint,
			@QueryParam("aimPoint") String aimPoint) {
		return busService.getTempBusByPoints(startPoint, aimPoint);
	}

	/**
	 * 
	 * @Title: getBusNewsList 
	 * @Description: 管理端，获取最新的校车新闻列表
	 * @return List<BusNews>    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/busNewsList")
	public List<News> getBusNewsList(@QueryParam("page") int page) {
		return busService.getBusNewsList(page);
	}

	/**
	 * 
	 * @Title: getBusNewsNum 
	 * @Description: 管理端，获取校车新闻数量
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/busNewsNum")
	@Produces(MediaType.TEXT_PLAIN)
	public int getBusNewsNum() {
		return busService.getBusNewsNum();
	}

	/**
	 * 
	 * @Title: getTempBusNum 
	 * @Description:管理端， 获取临时班车数量
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/tempBusNum")
	@Produces(MediaType.TEXT_PLAIN)
	public int getTempBusNum() {
		return busService.getTempBusNum();
	}

	/**
	 * 
	 * @Title: getTempBusList 
	 * @Description: 管理端，获取临时班车列表
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/tempBusList")
	public List<Bus> getTempBusList(@QueryParam("page") int page) {
		return busService.getTempBusList(page);
	}

	/**
	 * 
	 * @Title: deleteBus 
	 * @Description: 管理端，删除班车信息
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	@DELETE
	@Path("admin/bus")
	public Response deleteBus(@FormParam("busId") int busId) {
		busService.deleteBus(busId);
		return Response.ok().build();
	}

	/**
	 * 
	 * @Title: getBusList 
	 * @Description: 管理端,获取定时班车列表
	 * @return List<Bus>    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/busList")
	public List<Bus> getBusList(@QueryParam("page") int page) {
		return busService.getBusList(page);
	}

	/**
	 * 
	 * @Title: getBusNum 
	 * @Description: 管理端，获取定时班车数量
	 * @return int    返回类型 
	 * @throws
	 */
	@GET
	@Path("admin/busNum")
	@Produces(MediaType.TEXT_PLAIN)
	public int getBusNum() {
		return busService.getBusNum();
	}

	/**
	 * 
	 * @Title: addBus 
	 * @Description: 管理端，添加校车信息
	 * @return Response    返回类型 
	 * @throws
	 */
	@PUT
	@Path("admin/bus")
	public Response addBus(JsonNode node) {
		Bus bus = new Bus();
		bus.setAimPoint(node.get("aimPoint").asText());
		bus.setCharger(node.get("charger").asText());
		bus.setMidStation(node.get("midStation").asText());
		bus.setModifyTime(new Date());
		bus.setSeatMeasage(node.get("seatMeasage").asInt());
		bus.setStartPoint(node.get("startPoint").asText());
		bus.setStartTime(new DateUtil().parse(node.get("startTime").asText(), DateUtil.DATE_TIME_FORMAT));
		bus.setType(node.get("type").asInt());
		busService.addBus(bus);
		return Response.status(Status.CREATED).build();
	}
}
