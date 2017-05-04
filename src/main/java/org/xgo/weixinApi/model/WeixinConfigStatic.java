package org.xgo.weixinApi.model;

/**
 * 微信静态参数名称
 * 
 * @author gaoxiang
 */
public class WeixinConfigStatic {

	/** 服务域名 */
	public static final String DOMAIN = "DOMAIN";
	/** 公众平台_账号appid */
	public static final String GZ_APPID = "GZ_APPID";
	/** 公众平台_app_secret */
	public static final String GZ_APPSECRET = "GZ_APPSECRET";
	/** 公众平台_通过code换取网页授权access_token_地址 */
	public static final String GZ_URL_OAUTH2_ACCESS_TOKEN = "GZ_URL_OAUTH2_ACCESS_TOKEN";
	/** 公众平台_网页授权_拉取用户信息(需scope为 snsapi_userinfo)_地址 */
	public static final String GZ_URL_OAUTH2_USERINFO = "GZ_URL_OAUTH2_USERINFO";
	/** 公众平台_获取用户基本信息_地址 */
	public static final String GZ_URL_USER_INFO = "GZ_URL_USER_INFO";
	/** 公众平台_批量获取用户基本信息_地址*/
	public static final String GZ_URL_USER_INFO_BATCHGET = "GZ_URL_USER_INFO_BATCHGET";
	/** 获取access_token地址 */
	public static final String URL_GET_ACCESS_TOKEN = "URL_GET_ACCESS_TOKEN";
}