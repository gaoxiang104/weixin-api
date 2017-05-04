package org.xgo.weixinApi.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xgo.base.Controller_;
import org.xgo.weixinApi.exception.IllegalStateWeixinApiException;
import org.xgo.weixinApi.service.UserService;

/** 微信用户信息相关 */
@RestController
@RequestMapping(value = "/user", consumes = "application/json", produces = "application/json")
public class UserController extends Controller_ {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 通过openId获取用户信息
	 * @param params openId列表
	 * @return UserInfo | List&lt;UserInfo&gt;
	 */
	@RequestMapping(value = "/userInfo.ctrl")
	public Map<String, Object> userInfo(@RequestBody List<String> params) {
		String error = null;
		Object object = null;

		if (null == params) {
			error = "params是空的";
		} else {
			try {
				object = userService.userInfo(params);
			} catch (IllegalStateWeixinApiException i) {
				error = i.getMessage();
				log.getLogger("weixin_user_c").warn("userInfo({}) fail: {}", params, error);
			} catch (Exception e) {
				error = e.getMessage();
				log.getLogger("weixin_user_c").error("userInfo({}): ", params);
				log.getLogger("weixin_user_c").error("StackTrace: ", e);
			}
		}

		return result(error, object);
	}
	
	/**
	 * 授权获取openId
	 * 
	 * @param code 授权后微信给的code
	 * @return
	 */
	@RequestMapping("authGetOpenid")
	public Map<String, Object> authGetOpenid(@RequestBody String code) {
		String error = null;
		Object object = null;
		if(StringUtils.isBlank(code)){
			error = "授权后微信给的code是空的";
		}
		else{
			try {
				object = userService.authGetOpenid(code).getOpenid();
			} 
			catch (IllegalStateWeixinApiException i) {
				error = i.getMessage();
				log.getLogger("weixin_user_c").warn("authGetOpenid({}) fail: {}", code, error);
			}
			catch (Exception e) {
				error = e.getMessage();
				log.getLogger("weixin_user_c").error("authGetOpenid({}): ", code);
				log.getLogger("weixin_user_c").error("StackTrace: ", e);
			}
		}
		return result(error, object);
	}

	/**
	 * 授权获取微信用户信息
	 *
	 * @param code 授权后微信给的code
	 * @return
	 */
	@RequestMapping("authGetUserInfo")
	public Map<String, Object> authGetUserInfo(@RequestBody String code) {
		String error = null;
		Object object = null;
		if(StringUtils.isBlank(code)){
			error = "授权后微信给的code是空的";
		}
		else{
			try {
				object = userService.authGetUserInfo(code);
			} 
			catch (IllegalStateWeixinApiException i) {
				error = i.getMessage();
				log.getLogger("weixin_user_c").warn("authGetUserInfo({}) fail: {}", code, error);
			}
			catch (Exception e) {
				error = e.getMessage();
				log.getLogger("weixin_user_c").error("authGetUserInfo({}): ", code);
				log.getLogger("weixin_user_c").error("StackTrace: ", e);
			}
		}
		return result(error, object);
	}
}
