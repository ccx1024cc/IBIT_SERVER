package com.bit.ss.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bit.ss.mapper.MessageMapper;
import com.bit.ss.model.Message;

/**   
 * @Title: MessageService.java 
 * @Package com.bit.ss.service 
 * @Description:  
 * @author CCX
 * @date 2016年2月18日 上午7:29:31 
 * @version V1.0   
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageService {

	@Autowired
	private MessageMapper messageMapper;

	@Test
	public void testUpdateIsRead() {
		List<Message> messages = new ArrayList<>();
		Message message = new Message();
		message.setId(1);
		messages.add(message);
		message = new Message();
		message.setId(2);
		messages.add(message);
		messageMapper.updateIdRead(messages);
	}
}
