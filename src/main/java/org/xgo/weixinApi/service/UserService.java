package org.xgo.weixinApi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.xgo.weixinApi.exception.IllegalStateWeixinApiException;
import org.xgo.weixinApi.model.WeixinConfig;
import org.xgo.weixinApi.model.WeixinConfigStatic;
import org.xgo.weixinApi.model.user.UserInfo;
import org.xgo.weixinApi.model.user.outh.ResponseAccessToken;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService extends WeixinService {

	/**
	 * 授权获取openId
	 * 
	 * @param code 授权后微信给的code
	 * @return
	 */
	public ResponseAccessToken authGetOpenid(String code) throws Exception {
		ResponseAccessToken result = null;
		// 组装URL
		StringBuffer getUrl = new StringBuffer(WeixinConfig.INSTANCE.get(WeixinConfigStatic.GZ_URL_OAUTH2_ACCESS_TOKEN));
		getUrl.append("?appid=");
		getUrl.append(WeixinConfig.INSTANCE.get(WeixinConfigStatic.GZ_APPID));
		getUrl.append("&secret=");
		getUrl.append(WeixinConfig.INSTANCE.get(WeixinConfigStatic.GZ_APPSECRET));
		getUrl.append("&code=");
		getUrl.append(code);
		getUrl.append("&grant_type=authorization_code");
		// 发送请求，获取响应
		String outhRespsAccessTokenStr = httpGetRequest(getUrl.toString());
		// 记录应答
		log.getLogger("weixin_user_s").info("authGetOpenid.reponse : {}", outhRespsAccessTokenStr);

		ObjectMapper mapper = new ObjectMapper();
		ResponseAccessToken responseAccessToken = mapper.readValue(outhRespsAccessTokenStr, ResponseAccessToken.class);

		if (null != responseAccessToken.getErrcode()) {
			throw new IllegalStateWeixinApiException(responseAccessToken.getErrcode() + ": " + responseAccessToken.getErrmsg());
		}

		result = responseAccessToken;

		return result;
	}
	
	/**
	 * 授权获取微信用户信息
	 *
	 * @param code 授权后微信给的code
	 * @return
	 */
	public UserInfo authGetUserInfo(String code) throws Exception{
		UserInfo result = null;
		
		ResponseAccessToken accessToken = authGetOpenid(code);
		// 组装URL
		StringBuffer getUrl = new StringBuffer(WeixinConfig.INSTANCE.get(WeixinConfigStatic.GZ_URL_OAUTH2_USERINFO));
		getUrl.append("?access_token=");
		getUrl.append(accessToken.getAccessTonken());
		getUrl.append("&openid=");
		getUrl.append(accessToken.getOpenid());
		getUrl.append("&lang=zh_CN");
		// 发送请求，获取响应
		String authUserInfoStr = httpGetRequest(getUrl.toString());
		
		// 记录应答
		log.getLogger("weixin_user_s").info("authGetUserInfo.reponse : {}", authUserInfoStr);

		ObjectMapper mapper = new ObjectMapper();
		result = mapper.readValue(authUserInfoStr, UserInfo.class);
		
		if(null != result.getErrcode()){
			throw new IllegalStateWeixinApiException(result.getErrcode() + ": " + result.getErrmsg());
		}
		
		return result;
	}
	
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
		
//		log.getLogger("weixin_user_s").info("batchAccessUserInfo.request : {}",requestBodyStr);
		// 发送请求，获取响应
		String batchAccessUserInfoStr = httpPostRequest(getUrl.toString(), requestBodyStr.toString());
		// 记录应答
		log.getLogger("weixin_user_s").info("batchAccessUserInfo.reponse : {}",batchAccessUserInfoStr);
		// 获取失败
		if(batchAccessUserInfoStr.indexOf("errcode") != -1){
			throw new IllegalStateWeixinApiException(batchAccessUserInfoStr);
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
		
		// 发送请求，获取响应
		String getUserInfoStr =  httpGetRequest(getUrl.toString());
		// 记录应答
		log.getLogger("weixin_user_s").info("getUserInfo.reponse : {}",getUserInfoStr);
		ObjectMapper mapper = new ObjectMapper();
		result = mapper.readValue(getUserInfoStr, UserInfo.class);
		
		if(null != result.getErrcode()){
			throw new IllegalStateWeixinApiException(result.getErrcode() + ": " + result.getErrmsg());
		}
		
		return result;
	}
}
