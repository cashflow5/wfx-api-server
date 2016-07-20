package com.yougou.wfx.framework.jmx;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * JVM级配置管理
 * @author wuyang
 *
 */
public class ConfigMBean {
	
	private Map<String, String> configers;
	
	private static ConfigMBean configMBean;
	
	private ConfigMBean(){};
	
	public static ConfigMBean getInstance(){
		if(configMBean == null){
			configMBean = new ConfigMBean();
		}
		return configMBean;
	}

	public void setConfigers(Map<String, String> configers) {
		getInstance().configers = configers;
	}

	/**
	 * 设置值项
	 * @param key
	 * @param object
	 */
	public void setConfigerByKey(String key ,String value) {
		if(StringUtils.isBlank(key) || value == null) return;
		if(getInstance().configers == null){
			getInstance().configers = new LinkedHashMap<String, String>();
		}
		getInstance().configers.put(key, value);
	}
	
	/**
	 * 为空情况，返回默认值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getConfigValueByKey(String key,String defaultValue){
		if(StringUtils.isBlank(key) || getInstance().configers == null){
			return defaultValue;
		}
		String value = String.valueOf(getInstance().configers.get(key));
		return StringUtils.isBlank(value) ? defaultValue : value;
	}
	
	/**
	 * 获取Integer型配置值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Integer getConfigInt(String key,Integer defaultValue){
		String value = getConfigValueByKey(key,String.valueOf(defaultValue));
		Integer result = defaultValue;
		try{
			result = Integer.parseInt(value);
		}catch(Exception e){}
		return result;
	}
	
	/**
	 * 获取Boolean型配置值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public boolean getConfigBoolean(String key, boolean defaultValue){
		String value = getConfigValueByKey(key,String.valueOf(defaultValue));
		return Boolean.valueOf(value);
	}

}
