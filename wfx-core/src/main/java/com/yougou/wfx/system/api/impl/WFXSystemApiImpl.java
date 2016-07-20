package com.yougou.wfx.system.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.system.api.IWFXSystemApi;
import com.yougou.wfx.system.service.WFXSystemService;

/**
 * 系统级别API
 * @author zhang.hc
 *
 */
@Service("wfxSystemApi")
public class WFXSystemApiImpl implements IWFXSystemApi {
	@Autowired
	private WFXSystemService wfxSystemService;
	
	@LoggerProfile(methodNote = "获取系统配置项接口")
	@Override
	public String getSystemConfigValue(String key) {
		return wfxSystemService.getSystemConfig(key);
	}
	
	@LoggerProfile(methodNote = "获取图片基本路径接口")
	@Override
	public String obtainImgBaseUrl() {
		return wfxSystemService.obtainImgBaseUrl();
	}

}
