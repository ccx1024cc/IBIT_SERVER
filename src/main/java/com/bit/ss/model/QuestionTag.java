package com.bit.ss.model;

import javax.xml.bind.annotation.XmlRootElement;

/**   
 * @Title: QuestionTag.java 
 * @Package com.bit.ss.model 
 * @Description:  问题标签
 * @author CCX
 * @date 2016年2月15日 上午10:57:30 
 * @version V1.0   
 */
@XmlRootElement
public class QuestionTag {

	private Integer id;
	private Integer questionId;
	private String tagName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
