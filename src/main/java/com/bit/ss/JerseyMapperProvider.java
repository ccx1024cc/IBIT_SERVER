package com.bit.ss;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.bit.ss.domain.DefinedBean;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

/**   
 * @Title: JerseyMapperProvider.java 
 * @Package com.bit.ss 
 * @Description:  jersey数据传递格式过滤
 * @author CCX
 * @date 2015年11月30日 上午8:21:58 
 * @version V1.0   
 */
@Provider
public class JerseyMapperProvider implements ContextResolver<ObjectMapper> {

	final ObjectMapper defaultObjectMapper;
	final ObjectMapper definedObjectMapper;

	public JerseyMapperProvider() {
		defaultObjectMapper = createDefaultMapper();
		definedObjectMapper = createDefinedObjectMapper();
	}

	@Override
	public ObjectMapper getContext(final Class<?> type) {

		if (type == DefinedBean.class) {
			return definedObjectMapper;
		} else {
			return defaultObjectMapper;
		}
	}

	private static ObjectMapper createDefinedObjectMapper() {
		return new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, true)
				.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
				.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false)
				.setAnnotationIntrospector(createJaxbJacksonAnnotationIntrospector());
	}

	private static ObjectMapper createDefaultMapper() {
		final ObjectMapper result = new ObjectMapper();
		result.enable(SerializationFeature.INDENT_OUTPUT);

		return result;
	}

	private static AnnotationIntrospector createJaxbJacksonAnnotationIntrospector() {

		final AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
		final AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();

		return AnnotationIntrospector.pair(jacksonIntrospector, jaxbIntrospector);
	}
}
