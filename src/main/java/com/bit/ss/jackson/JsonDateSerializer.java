package com.bit.ss.jackson;

import java.io.IOException;
import java.util.Date;

import com.bit.ss.util.DateUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**   
 * @Title: JsonDateSerializer.java 
 * @Package com.bit.ss.jackson 
 * @Description:  日期序列化类
 * @author CCX
 * @date 2015年12月12日 上午9:12:21 
 * @version V1.0   
 */
public class JsonDateSerializer extends JsonSerializer<Date>{

	private final static DateUtil dateUtil = new DateUtil();
	
	@Override
	public void serialize(Date arg0, JsonGenerator arg1, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		arg1.writeString(dateUtil.formatDateTime(arg0, DateUtil.DATE_TIME_FORMAT));
	}
}
