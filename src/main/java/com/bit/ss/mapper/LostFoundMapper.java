package com.bit.ss.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bit.ss.model.LostFound;

/**   
 * @Title: LostFoundMapper.java 
 * @Package com.bit.ss.mapper 
 * @Description:  失物招领模块DAO
 * @author CCX
 * @date 2016年2月14日 下午2:11:05 
 * @version V1.0   
 */
@Repository
public interface LostFoundMapper {

	/**
	 * 
	 * @Title: getLatestStuffList 
	 * @Description: 获取最近的失物信息
	 * @return List<LostFound>    返回类型 
	 * @throws
	 */
	public List<LostFound> getLatestStuffList(@Param("start") int start, @Param("num") int num,
			@Param("threshold") Date dateThreshold);

	/**
	 * 
	 * @Title: getOldStuffList 
	 * @Description: 获取时间较长的失物信息
	 * @return List<LostFound>    返回类型 
	 * @throws
	 */
	public List<LostFound> getOldStuffList(@Param("start") int start, @Param("num") int num,
			@Param("threshold") Date dateThreshold);
	
	/**
	 * 
	 * @Title: addLostFound 
	 * @Description: 发表失物信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int addLostFound(LostFound lostFound);
}
