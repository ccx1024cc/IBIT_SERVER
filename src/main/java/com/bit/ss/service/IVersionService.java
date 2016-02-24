package com.bit.ss.service;

import java.util.List;

import com.bit.ss.model.Version;

/**   
 * @Title: IVersionService.java 
 * @Package com.bit.ss.service 
 * @Description:  版本控制模块服务接口
 * @author CCX
 * @date 2016年2月15日 下午5:56:02 
 * @version V1.0   
 */
public interface IVersionService {

	public static final int PC_PAGE_SIZE = 20;//每页版本数量

	/**
	 * 
	 * @Title: getLatestNumber 
	 * @Description: 获取最新版本号
	 * @return String    返回类型 
	 * @throws
	 */
	public String getLatestNumber();

	/**
	 * 
	 * @Title: getVersionNum 
	 * @Description: 后台服务，获取版本总数
	 * @return int    返回类型 
	 * @throws
	 */
	public int getVersionNum();

	/**
	 * 
	 * @Title: getVersionList 
	 * @Description: 后台服务，获取版本列表
	 * @return List<Version>    返回类型 
	 * @throws
	 */
	public List<Version> getVersionList(int page);

	/**
	 * 
	 * @Title: addVersion 
	 * @Description: 后台服务，发行新版本
	 * @return int    返回类型 
	 * @throws
	 */
	public int addVersion(Version version);
}
