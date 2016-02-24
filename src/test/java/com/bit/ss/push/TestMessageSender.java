package com.bit.ss.push;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**   
 * @Title: TestMessageSender.java 
 * @Package com.bit.ss.push 
 * @Description:  
 * @author CCX
 * @date 2016年2月3日 下午12:42:42 
 * @version V1.0   
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMessageSender {

	@Test
	public void testSend(){
		MessageSender sender = new MessageSender();
		sender.send("13718927394", "验证码为123");
	}
}
