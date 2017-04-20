package org.xgo.weixinApi.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(WeixinConfig.INSTANCE.get(WeixinConfigStatic.DOMAIN)+WeixinConfig.INSTANCE.get(WeixinConfigStatic.URL_GET_ACCESS_TOKEN));
		// 发送请求
		HttpResponse response = httpClient.execute(httpGet);
		// 解析应答
		HttpEntity entity = response.getEntity();
		String getAccessTokenStr = EntityUtils.toString(entity);
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
	
}
