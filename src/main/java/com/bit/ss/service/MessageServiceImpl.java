package com.bit.ss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.ss.mapper.MessageMapper;
import com.bit.ss.model.Message;

/**   
 * @Title: MessageServiceImpl.java 
 * @Package com.bit.ss.service 
 * @Description:  消息服务实现类
 * @author CCX
 * @date 2016年2月17日 下午6:48:34 
 * @version V1.0   
 */
@Service
public class MessageServiceImpl implements IMessageService {

	@Autowired
	private MessageMapper messageMapper;

	@Override
	public int addMessage(Message message) {
		return messageMapper.addMessage(message);
	}

	@Override
	public List<Message> getPersonalMessagesByModule(String module, int userId, int page) {
		int start = (page - 1) * PAGESIZE;
		List<Message> messages = messageMapper.getPersonalMessagesByModule(module, userId, start, PAGESIZE);
		messageMapper.updateIdRead(messages);
		return messages;
	}

	@Override
	public int getPersonalMessageNumByModule(int userId, String module) {
		return messageMapper.getPersonalMessageNumByModule(userId, module);
	}
}
