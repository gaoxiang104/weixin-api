package org.xgo.weixinApi.model.user;

import java.util.List;

import org.xgo.weixinApi.model.WeiXinResponseError;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信用户信息
 * 
 * @author 00277768
 */
/**
 * @author gaoxiang
 *
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo extends WeiXinResponseError{

	/** 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 */
	private Integer subscribe;
	/** 用户的标识，对当前公众号唯一 */
	private String openid;
	/** 用户的昵称 */
	private String nickname;
	/** 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 */
	private Integer sex;
	/** 用户所在城市 */
	private String city;
	/** 用户所在国家 */
	private String country;
	/** 用户所在省份 */
	private String province;
	/** 用户的语言，简体中文为zh_CN */
	private String language;
	/** 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 */
	private String headimgurl;
	/** 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 */
	@JsonProperty(value = "subscribe_time")
	private String subscribeTime;
	/** 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。 */
	private String unionid;
	/** 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 */
	private String remark;
	/** 用户所在的分组ID（兼容旧的用户分组接口） */
	private String groupid;
	/** 用户被打上的标签ID列表 */
	@JsonProperty(value = "tagid_list")
	private List<String> tagidList;

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getGroupid() {
		return groupid;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public String getLanguage() {
		return language;
	}

	public String getNickname() {
		return nickname;
	}

	public String getOpenid() {
		return openid;
	}

	public String getProvince() {
		return province;
	}

	public String getRemark() {
		return remark;
	}

	public Integer getSex() {
		return sex;
	}

	public Integer getSubscribe() {
		return subscribe;
	}

	public String getSubscribeTime() {
		return subscribeTime;
	}

	public List<String> getTagidList() {
		return tagidList;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public void setTagidList(List<String> tagidList) {
		this.tagidList = tagidList;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserInfo [");
		if(getSubscribe() != null){
			builder.append("subscribe=");
			builder.append(subscribe);
			builder.append(",");
		}
		if(getOpenid() != null){
			builder.append("openid=");
			builder.append(openid);
			builder.append(",");
		}
		if(getNickname() != null){
			builder.append("nickname=");
			builder.append(nickname);
			builder.append(",");
		}
		if(getSex() != null){
			builder.append("sex=");
			builder.append(sex);
			builder.append(",");
		}
		if(getCity() != null){
			builder.append("city=");
			builder.append(city);
			builder.append(",");
		}
		if(getCountry() != null){
			builder.append("country=");
			builder.append(country);
			builder.append(",");
		}
		if(getProvince() != null){
			builder.append("province=");
			builder.append(province);
			builder.append(",");
		}
		if(getLanguage() != null){
			builder.append("language=");
			builder.append(language);
			builder.append(",");
		}
		if(getHeadimgurl() != null){
			builder.append("headimgurl=");
			builder.append(headimgurl);
			builder.append(",");
		}
		if(getSubscribeTime() != null){
			builder.append("subscribeTime=");
			builder.append(subscribeTime);
			builder.append(",");
		}
		if(getUnionid() != null){
			builder.append("unionid=");
			builder.append(unionid);
			builder.append(",");
		}
		if(getRemark() != null){
			builder.append("remark=");
			builder.append(remark);
			builder.append(",");
		}
		if(getGroupid() != null){
			builder.append("groupid=");
			builder.append(groupid);
			builder.append(",");
		}
		if(getTagidList() != null){
			builder.append("tagidList=");
			builder.append(tagidList);
			builder.append(",");
		}
		builder.append("]");
		return builder.toString();
	}
	
}
