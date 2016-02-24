package com.bit.ss.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bit.ss.model.News;

/**   
 * @Title: NewsService.java 
 * @Package com.bit.ss.service 
 * @Description:  
 * @author CCX
 * @date 2015年12月14日 上午8:53:50 
 * @version V1.0   
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class NewsService {

	@Autowired
	private INewsService newsService;

	@Test
	public void testFindList() {
		List<News> newsList = newsService.findList(1, 3, "");
		System.out.println(newsList.size());
		newsList = newsService.findList(2, 3, "");
		System.out.println(newsList.size());
	}
}
