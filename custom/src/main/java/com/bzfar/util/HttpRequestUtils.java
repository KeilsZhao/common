package com.bzfar.util;

import com.bzfar.exception.DataException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;

public class HttpRequestUtils {

	/**
     * 超时时间30s
	 */
	private static final int TIMEOUTTIME = 30000;

	/**
	 * 编码格式
	 */
	private static final String CHARSET = "utf-8";


	public static String httpPost(String url, String json) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-type", "application/json; charset=utf-8");
		RequestConfig requestConfig =  RequestConfig.custom().setSocketTimeout(TIMEOUTTIME).setConnectTimeout(TIMEOUTTIME).build();
		httpPost.setConfig(requestConfig);
		try {
			if (StringUtils.isNotBlank(json)) {
				// 构建消息实体 Map转json字符串
				StringEntity entity = new StringEntity(json,CHARSET);
				entity.setContentEncoding(CHARSET);
				// 发送Json格式的数据请求
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			return result;
		} catch (IOException e) {
			throw new DataException(e.getMessage());
		} finally {
			httpPost.releaseConnection();
		}
	}
}