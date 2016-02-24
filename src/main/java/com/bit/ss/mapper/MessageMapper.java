package com.bit.ss.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bit.ss.model.Message;

/**   
 * @Title: MessageMapper.java 
 * @Package com.bit.ss.mapper 
 * @Description:  用户消息模块DAO接口
 * @author CCX
 * @date 2016年2月17日 下午6:03:23 
 * @version V1.0   
 */
@Repository
public interface MessageMapper {

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
	 * @Title: updateIdRead 
	 * @Description: 更新已经阅读过的消息的状态：未阅读过》已经阅读过
	 * @return int    返回类型 
	 * @throws
	 */
	public int updateIdRead(List<Message> messages);

	/**
	 * 
	 * @Title: getPersonalMessagesByModule 
	 * @Description: 按照模块获取某人的消息
	 * @return List<Message>    返回类型 
	 * @throws
	 */
	public List<Message> getPersonalMessagesByModule(@Param("module") String module, @Param("userId") int userId,
			@Param("start") int start, @Param("num") int num);

	/**
	 * 
	 * @Title: getPersonalMessageNumByModule 
	 * @Description: 获取某人在某一模块中产生的消息数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int getPersonalMessageNumByModule(@Param("userId") int userId, @Param("module") String module);
}
