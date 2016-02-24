package com.bit.ss.push;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**   
 * @Title: MessageSender.java 
 * @Package com.bit.ss.push 
 * @Description:  通过网建平台下发短信
 * @author CCX
 * @date 2016年2月3日 下午12:08:22 
 * @version V1.0   
 */
@Component
public class MessageSender {

	final private String host = "ccccx";
	final private String key = "c024f75d6b45736c3a25";

	public static Logger logger = LoggerFactory.getLogger(MessageSender.class);

	public void send(String phone, String message) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
				//.custom().setProxy(new HttpHost("127.0.0.1", 8888)).build();
		HttpPost post = new HttpPost("http://utf8.sms.webchinese.cn/");
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("Uid", host));
		params.add(new BasicNameValuePair("Key", key));
		params.add(new BasicNameValuePair("smsMob", phone));
		params.add(new BasicNameValuePair("smsText", message));
		try {
			post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			CloseableHttpResponse response = httpClient.execute(post);
			response.close();
			httpClient.close();
		} catch (Exception e) {
			logger.error("验证码推送异常");
		}
	}
}
