/*
 * 版本信息

 * 日期 2016-04-07 18:29:33

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.basicset.api.front.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yougou.wfx.basicset.api.front.IVersionManageFrontApi;
import com.yougou.wfx.basicset.dto.output.AppVersionOutputDto;
import com.yougou.wfx.basicset.model.AppVersionEntity;
import com.yougou.wfx.basicset.service.IAppVersionService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.utils.PicPathUtil;

/**
 * ISystemmgAreaFrontApi实现
 * 
 * @author wfx
 * @Date 创建时间：2016-04-07 18:29:33
 */
@Service
public class VersionManageFrontApiImpl implements IVersionManageFrontApi {
	@Resource
	IAppVersionService appVersionService;

	@Override
	public AppVersionOutputDto getAndroidNewestVersion() {
		AppVersionEntity entity = appVersionService.getAndroidNewestVersion();
		AppVersionOutputDto dto = null;
		if (entity != null) {
			dto = (AppVersionOutputDto) BeanUtil.convertBean(entity, AppVersionOutputDto.class);
			dto.setVersionUrl(PicPathUtil.getImageAbsUrl(dto.getVersionUrl()));
		}
		return dto;
	}
}
