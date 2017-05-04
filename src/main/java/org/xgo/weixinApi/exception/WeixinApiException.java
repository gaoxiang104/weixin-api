package org.xgo.weixinApi.exception;

public abstract class WeixinApiException extends Exception {

	static final long serialVersionUID = -241488746881376883L;

	public WeixinApiException() {
		super();
	}

	public WeixinApiException(String message) {
		super(message);
	}
}
