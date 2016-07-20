package com.yougou.wfx.dto.base;


/**
 * 用户客户端环境上下文
 * @author wuyang
 */
public class UserContext extends InputDto {

	private static final long serialVersionUID = -7403047122486669258L;

	/** 客户端ip(必填) **/
	private String clientIp;

	/** User-Agent **/
	private String userAgent;
	
	/** 请求方法(必填) **/
	private String requestMethod;

	/** sessionId(必填) **/
	private String sessionId;

	/** 来路 **/
	private String referer;

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public String toString() {
		return "UserClientContext [clientIp=" + clientIp + ", userAgent=" + userAgent + ", referer=" + referer
				+ ", requestMethod=" + requestMethod + ", sessionId=" + sessionId +"]";
	}
}
