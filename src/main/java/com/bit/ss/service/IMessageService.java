package com.bit.ss.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bit.ss.model.Message;

/**   
 * @Title: IMessageService.java 
 * @Package com.bit.ss.service 
 * @Description:  用户消息模块服务接口
 * @author CCX
 * @date 2016年2月17日 下午6:44:40 
 * @version V1.0   
 */
public interface IMessageService {

	public static final int PAGESIZE = 7;// 一页一次显示七条消息

	/**
	 * 
	 * @Title: addMessage 
	 * @Description: 添加新的消息
	 * @return int    返回类型 
	 * @throws
	 */
	public int addMessage(Message message);

	/**
	 * 
	 * @Title: getPersonalMessagesByModule 
	 * @Description: 按照模块获取某人的消息
	 * @return List<Message>    返回类型 
	 * @throws
	 */
	public List<Message> getPersonalMessagesByModule(String module, int userId, int page);

	/**
	 * 
	 * @Title: getPersonalMessageNumByModule 
	 * @Description: 获取某人在某一模块中产生的消息数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getPersonalMessageNumByModule(@Param("userId") int userId, @Param("module") String module);
}
