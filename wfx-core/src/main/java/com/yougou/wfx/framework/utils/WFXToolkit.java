 /*
 * 版本信息
 
 * 日期 2016-03-24 16:17:44
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.framework.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

import com.yougou.tools.common.utils.SpringContextHolder;

/**
 * 微分销工具箱
 * @author wuyang
 * @Date 创建时间：2016-03-24 16:17:44
 */
public class WFXToolkit {
	/**
	 * 是否是开发模式
	 * @return
	 */
	public static boolean isDev(){
		Environment env = SpringContextHolder.getBean(Environment.class);
		if(env.acceptsProfiles("development") || env.acceptsProfiles("test")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取图片服务器域名
	 * @return
	 */
	public static String imgBaseUrl() {
		String baseUrl = SpringContextHolder.getBean("imageBaseUrl");
		if(StringUtils.isNoneBlank(baseUrl)) {
			return baseUrl;
		}
		return "";
	}
}
