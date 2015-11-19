package com.bit.ss.jackson;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @Title: JerseyMapper.java
 * @Package com.bit.ss.jackson
 * @Description:
 * @author CCX
 * @date 2015年11月1日 下午3:01:02
 * @version V1.0
 */
@Provider
public class JerseyMapper implements ContextResolver<ObjectMapper> {

	final ObjectMapper defaultMapper;

	public JerseyMapper() {
		// TODO Auto-generated constructor stub
		defaultMapper = createDefaultMapper();
	}

	public static ObjectMapper createDefaultMapper() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(new BooleanSerializer());
		return new ObjectMapper().registerModule(module).setSerializationInclusion(Include.NON_NULL)
				.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
	}

	@Override
	public ObjectMapper getContext(Class<?> arg0) {
		// TODO Auto-generated method stub
		return this.defaultMapper;
	}
}
