package org.xgo.weixinApi.model.user.outh;

import org.xgo.weixinApi.model.WeiXinResponseError;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 网页授权换取AccessToken
 * <p>
 * 获取code后，请求以下链接获取access_token
 * </p>
 * 
 * @author gaoxiang
 *
 */
public class ResponseAccessToken extends WeiXinResponseError {
	/** access_token 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同 */
	@JsonProperty(value = "access_token")
	private String accessTonken;
	/** expires_in access_token接口调用凭证超时时间，单位（秒） */
	@JsonProperty(value = "expires_in")
	private Long expiresIn;
	/** openid 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID */
	private String openid;
	/** refresh_token 用户刷新access_token */
	@JsonProperty(value = "refresh_token")
	private String refreshToken;
	/** scope 用户授权的作用域，使用逗号（,）分隔 */
	private String scope;
	/** unionid 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制） */
	private String unionid;

	public String getAccessTonken() {
		return accessTonken;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public String getOpenid() {
		return openid;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public String getScope() {
		return scope;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setAccessTonken(String accessTonken) {
		this.accessTonken = accessTonken;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

}
