package com.yougou.wfx.system.service;

import java.util.List;

public interface WFXSystemService {
	/**
	 * 获取图片服务器访问地址
	 * @return
	 */
	public String obtainImgBaseUrl();
	
	/**
	 * 敏感词过滤
	 * @param separator 分隔符，默认英文逗号
	 * @param content 需要过滤的内容
	 * @return 无敏感词：空字符，有：返回敏感词，以separator间隔
	 */
	String checkSensitiveWord(String separator, String content);
	
	/**
	 * 获取系统配置项值
	 * @param key
	 * @return
	 */
	String getSystemConfig(String key);
	
	/**
	 * 获取所有微分销敏感词
	 * @return
	 */
	List<String> getAllSensitiveWord();
}
