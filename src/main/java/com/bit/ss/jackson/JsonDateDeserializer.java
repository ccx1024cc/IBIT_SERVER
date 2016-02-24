package com.bit.ss.jackson;

import java.io.IOException;
import java.util.Date;

import com.bit.ss.util.DateUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**   
 * @Title: JsonDateDeserializer.java 
 * @Package com.bit.ss.jackson 
 * @Description:  
 * @author CCX
 * @date 2016年2月8日 下午4:30:54 
 * @version V1.0   
 */
public class JsonDateDeserializer extends JsonDeserializer<Date> {

	private final DateUtil dateUtil = new DateUtil();

	@Override
	public Date deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException {
		String date = arg0.getValueAsString();
		return dateUtil.parse(date, DateUtil.DATE_TIME_FORMAT);
	}
}
