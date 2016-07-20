package com.yougou.wfx.system.api;


/**
 * 系统级服务，包括短信发送，获取系统配置项
 * @author zhang.f1
 *
 */
public interface IWFXSystemApi {
	
	/**
	 * 获取系统配置项值
	 * @param key 配置项key值
	 * @return configValue 系统配置项值
	 * @author zhang.f
	 */
	String getSystemConfigValue(String key);
	
	/**
	 * 实例我就不创建了，不知道扔到哪个目录下面
	 * 实现直接调用FtpServiceImpl.obtainImgBaseUrl
	 * @return
	 * @author zhang.hc
	 */
	public String obtainImgBaseUrl();
}
