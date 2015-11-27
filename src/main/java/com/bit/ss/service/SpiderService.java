package com.bit.ss.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bit.ss.domain.Spiders;

/**   
 * @Title: Spiders.java 
 * @Package com.bit.ss.service 
 * @Description:  爬虫服务
 * @author CCX
 * @date 2015年11月27日 上午11:56:16 
 * @version V1.0   
 */
@Service
public class SpiderService {
	
	//private final static Logger logger = LoggerFactory.getLogger(SpiderService.class);	//记录爬虫一场信息
	
	@Autowired 
	private Spiders spiders;	//待执行的爬虫列表
	
	/**
	 * 
	 * @Title: crawl 
	 * @Description: 每隔一个小时抓取一次
	 * @return void    返回类型 
	 * @throws
	 */
	@Scheduled(fixedRate = 3600000)
	public void crawl() {
		List<ISpiderService> list = spiders.getSpiderList();
		for(ISpiderService spider :list){
			try {
				spider.crawl();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
}
