package com.yougou.wfx.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yougou.wfx.basicset.service.ISysConfigService;
import com.yougou.wfx.framework.annotation.cache.CacheCable;
import com.yougou.wfx.framework.utils.WFXToolkit;
import com.yougou.wfx.shop.dao.ShopMapper;
import com.yougou.wfx.system.service.WFXSystemService;
import com.yougou.wfx.utils.CheckSensitiveWordsUtil;

@Service
public class WFXSystemServiceImpl implements WFXSystemService {
	
	@Resource
	ISysConfigService sysConfigService;
	
	@Resource
	ShopMapper shopMapper;
	
	@Override
	public String obtainImgBaseUrl() {
		return WFXToolkit.imgBaseUrl();
	}

	@Override
	public String checkSensitiveWord(String separator, String content) {
		return CheckSensitiveWordsUtil.checkSensitiveWord(separator, content);
	}

	@Override
	public String getSystemConfig(String key) {
		// TODO Auto-generated method stub
		return sysConfigService.getValueBykey(key);
	}

	@Override
	@CacheCable(key="'wfx:SensitiveWord'" ,value="value",expiration=24*60*60)
	public List<String> getAllSensitiveWord() {
		// TODO Auto-generated method stub
		return shopMapper.getAllSensitiveWord();

	}
}
