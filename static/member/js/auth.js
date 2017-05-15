/**
 * 授权测试js
 */

/** appid */
let appid = 'wxde5937b7f8acd726';

/** 获取授权url 
 * redirect_uri：授权后重定向的回调链接地址
 * type：授权方式;值[‘snsapi_base’|‘snsapi_userinfo’]
 * ?state：重定向后会带上state参数
 * */
function get_snsapi_userinfo_url(redirect_uri, type, state) {

	if (redirect_uri == null) {
		throw new Error('redirect_uri是空的');
	}
	if (type == null) {
		throw new Error('type是空的');
	} else if (!('snsapi_base' == type || 'snsapi_userinfo' == type)) {
		throw new Error('type必须是“snsapi_base”或者“snsapi_userinfo”');
	} else if (state != null && state.length > 128) {
		throw new Error('state长度不能超过128');
	} else {
		var url = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=';
		url += appid;
		url += '&redirect_uri=';
		url += redirect_uri;
		url += '&response_type=code&scope=';
		url += type;
		if (state) {
			url += '&state=' + state;
		}
		url += '';
		url += '#wechat_redirect';

		return url;
	}

}

$(function() {
	/** 点击“用户确认授权”按钮 */
	$("#btn_snsapi_userinfo").click(function() {
		window.location.href = get_snsapi_userinfo_url('http://gaoxiang104.vicp.io/weixin-api/member/auth_snsapi_userinfo.html','snsapi_userinfo');
	});

	/** 点击“静默方式授权”按钮 */
	$("#btn_snsapi_base").click(function() {
		window.location.href = get_snsapi_userinfo_url('http://gaoxiang104.vicp.io/weixin-api/member/auth_snsapi_base.html','snsapi_base');
	});
});