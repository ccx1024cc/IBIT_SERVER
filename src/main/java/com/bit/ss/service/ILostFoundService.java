package com.bit.ss.service;

import java.util.List;

import com.bit.ss.model.LostFound;

/**   
* @Title: ILostFoundService.java 
* @Package com.bit.ss.service 
* @Description:  失物招领模块服务接口
* @author CCX
* @date 2016年2月14日 下午2:58:37 
* @version V1.0   
*/
public interface ILostFoundService {

	public static final int LOSTFOUND_PAGESIZE = 20;// 每一页数量
	public static final int DAYS_SPACE = 7;// 显示的时间跨度为一周（7天)

	/**
	 * 
	 * @Title: getLatestStuffList 
	 * @Description: 获取最近的失物信息
	 * @return List<LostFound>    返回类型 
	 * @throws
	 */
	public List<LostFound> getLatestStuffList(int page);

	/**
	 * 
	 * @Title: getOldStuffList 
	 * @Description: 获取时间较长的失物信息
	 * @return List<LostFound>    返回类型 
	 * @throws
	 */
	public List<LostFound> getOldStuffList(int page);

	/**
	 * 
	 * @Title: addLostFound 
	 * @Description: 发表失物信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int addLostFound(LostFound lostFound);
}
