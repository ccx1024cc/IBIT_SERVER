package com.bit.ss.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Title: TestJob.java
 * @Package com.bit.ss.service
 * @Description: 测试定时任务调度
 * @author CCX
 * @date 2015年10月29日 上午11:21:56
 * @version V1.0
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Service()
public class TestJob {

	@Test
	@Scheduled(fixedRate = 5000)
	public void doJob1() {
		System.out.println("Now time is : " + new Date());
	}

}
