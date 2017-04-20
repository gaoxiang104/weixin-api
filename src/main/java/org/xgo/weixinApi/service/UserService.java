package org.xgo.weixinApi.service;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.xgo.weixinApi.model.WeixinConfig;
import org.xgo.weixinApi.model.WeixinConfigStatic;
import org.xgo.weixinApi.model.user.UserInfo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService extends WeixinService {

	/**
	 * 通过openId获取用户信息
	 * 
	 * @param openIds
	 * @return UserInfo | List&lt;UserInfo&gt;
	 * @throws Exception 
	 */
	public Object userInfo(List<String> openIds) throws Exception {
		Object result = null;
		if (1 == openIds.size()) {
			result = getUserInfo(openIds.get(0));
		} else {
			result = batchAccessUserInfo(openIds);
		}
		return result;
	}

	/**
	 * 批量获取用户信息
	 * 
	 * @param openIds
	 * @return List&lt;UserInfo&gt;
	 * @throws Exception 
	 */
	private List<UserInfo> batchAccessUserInfo(List<String> openIds) throws Exception {
		List<UserInfo> result = null;
		
		// 组装URL
		StringBuffer getUrl = new StringBuffer(WeixinConfig.INSTANCE.get(WeixinConfigStatic.GZ_URL_USER_INFO_BATCHGET));
		getUrl.append("?access_token=");
		getUrl.append(super.getAccessToken());
		// 组装requestBody
		StringBuffer requestBodyStr = new StringBuffer("{\"user_list\": [");
		for (int i = 0; i < openIds.size(); i++) {
			requestBodyStr.append("{\"openid\":\"");
			requestBodyStr.append(openIds.get(i));
			requestBodyStr.append("\",\"lang\": \"zh-CN\"}");
			if(i < openIds.size()-1){
				requestBodyStr.append(",");
			}
		}
		requestBodyStr.append("]}");
		
		log.getLogger("weixin_user_s").info("batchAccessUserInfo.request : {}",requestBodyStr);
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(getUrl.toString());
		httpPost.setHeader("Content-Type", "application/json; encoding=utf-8");
		httpPost.setEntity(new StringEntity(requestBodyStr.toString(), "utf-8"));
		
		// 发送请求
		HttpResponse response = httpClient.execute(httpPost);
		// 解析应答
		HttpEntity entity = response.getEntity();
		String batchAccessUserInfoStr = EntityUtils.toString(entity, "UTF-8");
		// 记录应答
		log.getLogger("weixin_user_s").info("batchAccessUserInfo.reponse : {}",batchAccessUserInfoStr);
		// 获取失败
		if(batchAccessUserInfoStr.indexOf("errcode") != -1){
			throw new IllegalStateException(batchAccessUserInfoStr);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<UserInfo>> tyeRef = new TypeReference<List<UserInfo>>() {
		};
		// 处理json字符串： {"user_info_list":[...]} -> [...]
		batchAccessUserInfoStr = batchAccessUserInfoStr.substring(batchAccessUserInfoStr.indexOf("["),
				batchAccessUserInfoStr.length() - 1);
		result = mapper.readValue(batchAccessUserInfoStr, tyeRef);

		return result;
	}

	/**
	 * 获取一个用户信息
	 * 
	 * @param openId
	 * @return UserInfo
	 * @throws Exception 
	 */
	private UserInfo getUserInfo(String openId) throws Exception {
		UserInfo result = null;
		
		// 组装URL
		StringBuffer getUrl = new StringBuffer(WeixinConfig.INSTANCE.get(WeixinConfigStatic.GZ_URL_USER_INFO));
		getUrl.append("?access_token=");
		getUrl.append(super.getAccessToken());
		getUrl.append("&openid=");
		getUrl.append(openId);
		getUrl.append("&lang=zh_CN");
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(getUrl.toString());
		
		// 发送请求
		HttpResponse response = httpClient.execute(httpGet);
		// 解析应答
		HttpEntity entity = response.getEntity();
		String getUserInfoStr =  EntityUtils.toString(entity, "UTF-8");
		// 记录应答
		log.getLogger("weixin_user_s").info("getUserInfo.reponse : {}",getUserInfoStr);
		ObjectMapper mapper = new ObjectMapper();
		result = mapper.readValue(getUserInfoStr, UserInfo.class);
		
		if(null != result.getErrcode()){
			throw new IllegalStateException(result.getErrcode() + ": " + result.getErrmsg());
		}
		
		return result;
	}
}
