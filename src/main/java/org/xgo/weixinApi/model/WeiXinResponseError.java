package org.xgo.weixinApi.model;
/**
 * 微信返回的错误信息
 * @author gaoxiang
 *
 */
public abstract class WeiXinResponseError {
	/** 错误编号 */
	private Integer errcode;

	/** 错误信息 */
	private String errmsg;

	public Integer getErrcode() {
		return errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
