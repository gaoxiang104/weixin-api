package org.xgo.weixinApi.exception;

public class WeixinApiException extends RuntimeException {

	static final long serialVersionUID = -241488746881376883L;

	public WeixinApiException() {
		super();
	}

	public WeixinApiException(String message) {
		super(message);
	}
}
