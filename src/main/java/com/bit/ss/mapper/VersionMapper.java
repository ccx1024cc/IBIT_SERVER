package com.bit.ss.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bit.ss.model.Version;

/**   
 * @Title: VersionMapper.java 
 * @Package com.bit.ss.mapper 
 * @Description:  版本控制模块DAO接口
 * @author CCX
 * @date 2016年2月15日 下午5:48:31 
 * @version V1.0   
 */
@Repository
public interface VersionMapper {

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
	 * @Description: 获取版本总数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getVersionNum();

	/**
	 * 
	 * @Title: getVersionList 
	 * @Description: 获取版本列表
	 * @return List<Version>    返回类型 
	 * @throws
	 */
	public List<Version> getVersionList(@Param("start") int start, @Param("num") int num);

	/**
	 * 
	 * @Title: addVersion 
	 * @Description: 添加新版本
	 * @return int    返回类型 
	 * @throws
	 */
	public int addVersion(Version version);
	
	/**
	 * 
	 * @Title: stopCurrentVersion 
	 * @Description: 终止当前版本
	 * @return int    返回类型 
	 * @throws
	 */
	public int stopCurrentVersion();
}
