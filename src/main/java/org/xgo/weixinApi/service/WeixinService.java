package org.xgo.weixinApi.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.xgo.base.Service_;
import org.xgo.weixinApi.model.WeixinConfig;
import org.xgo.weixinApi.model.WeixinConfigStatic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/** 
 * 微信service基础服务
 * @author gaoxiang
 *
 */
public abstract class WeixinService extends Service_{
	
	/**
	 * 获取accessToken
	 * @return
	 * @throws Exception
	 */
	protected String getAccessToken() throws Exception{
		String getUrlStr = WeixinConfig.INSTANCE.get(WeixinConfigStatic.DOMAIN)+WeixinConfig.INSTANCE.get(WeixinConfigStatic.URL_GET_ACCESS_TOKEN);
		
		String getAccessTokenStr = httpGetRequest(getUrlStr);
		// 记录应答
		log.getLogger("weixin_s").info("getAccessToken.reponse : {}",getAccessTokenStr);
		
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<HashMap<String, String>> tyeRef = new TypeReference<HashMap<String,String>>() {};
		Map<String, String> map = mapper.readValue(getAccessTokenStr, tyeRef);
		if (StringUtils.isNotBlank(map.get("error"))) {
			throw new IllegalStateException(map.get("error"));
		}

		return map.get("object");
	}
	
	/**
	 * GET 请求封装
	 * 
	 * @param getUrl
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	protected String httpGetRequest(String getUrl) throws ClientProtocolException, IOException {
		String responesStr;

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(getUrl.toString());

		// 发送请求
		HttpResponse response = httpClient.execute(httpGet);
		// 解析应答
		HttpEntity entity = response.getEntity();

		responesStr = EntityUtils.toString(entity, "UTF-8");

		return responesStr;
	}
	
	/**
	 * POST 请求封装
	 * 
	 * @param getUrl
	 * @param requestBodyStr
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	protected String httpPostRequest(String getUrl, String requestBodyStr) throws ClientProtocolException, IOException {
		String responesStr;

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(getUrl.toString());
		httpPost.setHeader("Content-Type", "application/json; encoding=utf-8");
		httpPost.setEntity(new StringEntity(requestBodyStr.toString(), "utf-8"));

		// 发送请求
		HttpResponse response = httpClient.execute(httpPost);
		// 解析应答
		HttpEntity entity = response.getEntity();
		responesStr = EntityUtils.toString(entity, "UTF-8");

		return responesStr;
	}
}
