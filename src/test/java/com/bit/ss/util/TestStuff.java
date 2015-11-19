package com.bit.ss.util;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Title: TestStuff.java
 * @Package com.bit.ss.util
 * @Description: 测试jdk问题
 * @author CCX
 * @date 2015年11月1日 下午12:58:02
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestStuff {

	@Test
	public void testClone() {
		Date a = new Date();
		System.out.println("orign : " + a);
		Date copy = (Date) a.clone();
		copy.setTime(a.getTime() + 3600 * 1000 * 2);
		System.out.println("copy : " + copy);
		System.out.println("a : " + a);
	}
}
