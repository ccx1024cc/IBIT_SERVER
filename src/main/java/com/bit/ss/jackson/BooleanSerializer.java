package com.bit.ss.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @Title: BooleanSerializer.java
 * @Package com.bit.ss.jackson
 * @Description: 布尔类型值序列化
 * @author CCX
 * @date 2015年11月1日 下午3:29:05
 * @version V1.0
 */
public class BooleanSerializer extends JsonSerializer<Boolean> {

	@Override
	public void serialize(Boolean arg0, JsonGenerator arg1, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		arg1.writeNumber(arg0 == true ? 0 : 1);
	}
}
