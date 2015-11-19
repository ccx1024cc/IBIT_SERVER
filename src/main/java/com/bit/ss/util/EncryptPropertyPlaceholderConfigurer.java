package com.bit.ss.util;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @Title: EncryptPropertyPlaceholderConfigurer.java
 * @Package com.bit.ss.util
 * @Description: 属性加密类
 * @author CCX
 * @date 2015年10月11日 下午3:24:18
 * @version V1.0
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		String pwd = props.getProperty("jdbc.password.read");
		if (pwd != null)
			props.setProperty("jdbc.password.read", EncryptHelper.desDecode("woshitiancai", pwd));
		super.processProperties(beanFactoryToProcess, props);
	}
}
