package com.bit.ss.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bit.ss.service.ISpiderService;

/**   
 * @Title: Spiders.java 
 * @Package com.bit.ss.domain 
 * @Description:  封装爬虫
 * @author CCX
 * @date 2015年11月27日 下午5:16:08 
 * @version V1.0   
 */
@Component
public class Spiders implements DefinedBean{

	@Autowired
	@Qualifier("campusNoticeSpider")
	private ISpiderService campusNoticeSpider;	//校方新闻爬虫
	
	@Autowired
	@Qualifier("dailyStuffSpider")
	private ISpiderService dailyStuffSpider;	//生活琐事爬虫

	@Autowired
	@Qualifier("educationNoticeSpider")
	private ISpiderService educationNoticeSpider;	//教育教学爬虫
	
	@Autowired
	@Qualifier("hMSpider")
	private ISpiderService hMSpider;	//人事公告爬虫
	
	@Autowired
	@Qualifier("indexNewsSpider")
	private ISpiderService indexNewsSpider;	//主页新闻爬虫
	
	@Autowired
	@Qualifier("internationComSpider")
	private ISpiderService internationComSpider;	//外事交流爬虫
	
	@Autowired
	@Qualifier("jobNoticeSpider")
	private ISpiderService jobNoticeSpider;	//就业信息爬虫
	
	@Autowired
	@Qualifier("jWCNoticeSpider")
	private ISpiderService jWCNoticeSpider;	//教务处爬虫
	
	@Autowired
	@Qualifier("lectureSpider")
	private ISpiderService lectureSpider;	//讲座预告爬虫
	
	@Autowired
	@Qualifier("networkNoticeSpider")
	private ISpiderService networkNoticeSpider;	//网络通告爬虫 
	
	@Autowired
	@Qualifier("officeWorkSpider")
	private ISpiderService officeWorkSpider;	//行政办公爬虫
	
	@Autowired
	@Qualifier("postGraduateEnrollSpider")
	private ISpiderService postGraduateEnrollSpider;	//研究生招生爬虫
	
	@Autowired
	@Qualifier("scienceStudyNoticeSpider")
	private ISpiderService scienceStudyNoticeSpider;	//学术研究爬虫
	
	@Autowired
	@Qualifier("studentStuffSpider")
	private ISpiderService studentStuffSpider;	//学工事物爬虫
	
	@Autowired
	@Qualifier("underGraduateEnrollSpider")
	private ISpiderService underGraduateEnrollSpider;	//本科生招生爬虫	
	
	
	/**
	 * 
	 * @Title: getSpiderList 
	 * @Description: 获取待执行的爬虫列表
	 * @return List<ISpiderService>    返回类型 
	 * @throws
	 */
	public List<ISpiderService> getSpiderList(){
		
		List<ISpiderService> list = new ArrayList<>();
		list.add(campusNoticeSpider);
		list.add(dailyStuffSpider);
		list.add(educationNoticeSpider);
		list.add(hMSpider);
		list.add(indexNewsSpider);
		list.add(internationComSpider);
		list.add(jWCNoticeSpider);
		list.add(jobNoticeSpider);
		list.add(lectureSpider);
		list.add(networkNoticeSpider);
		list.add(officeWorkSpider);
		list.add(postGraduateEnrollSpider);
		list.add(scienceStudyNoticeSpider);
		list.add(studentStuffSpider);
		list.add(underGraduateEnrollSpider);
		
		return list;
	}
}
