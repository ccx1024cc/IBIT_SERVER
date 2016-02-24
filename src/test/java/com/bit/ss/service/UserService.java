package com.bit.ss.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bit.ss.mapper.UserMapper;
import com.bit.ss.model.Authority;
import com.bit.ss.model.User;

/**   
 * @Title: UserService.java 
 * @Package com.bit.ss.service 
 * @Description:  用户服务测试模块
 * @author CCX
 * @date 2016年2月20日 下午3:34:04 
 * @version V1.0   
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testSearch() {
		User condition = new User();
		condition.setName("ccccx");
		List<Authority> auths = new ArrayList<>();
		Authority authority = new Authority();
		authority.setName("admin");
		auths.add(authority);
		condition.setAuths(auths);
//		List<User> list = userMapper.findUsersByCondition(null, null, "13718927394", null, "1261138729@qq.com", auths,
//				0, 30);
		List<User> list = userMapper.findUsersByCondition(condition, 0, 30);
		System.out.println(list);
	}
	
	@Test
	public void testFindAuths(){
		List<Authority> auths = userMapper.getPersonalAuth(8);
		System.out.println(auths);
	}
}
