package com.bit.ss;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.monitoring.MonitoringFeature;

/**
 * @Title: ResourceFeature.java
 * @Package com.bit.ss
 * @Description: jersey特性配置
 * @author CCX
 * @date 2015年10月11日 下午3:10:59
 * @version V1.0
 */

public class ResourceFeature extends ResourceConfig {
	public ResourceFeature() {
		packages(new String[] { "com.bit.ss" });
		register(JacksonFeature.class);
		register(MultiPartFeature.class);
		register(MonitoringFeature.class);
		property("jersey.config.servlet.filter.staticContentRegex", "/resources/.*");
	}
}