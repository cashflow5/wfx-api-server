 /*
 * 版本信息
 
 * 日期 2016-05-19 09:43:26
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.basicset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.basicset.dao.AppVersionMapper;
import com.yougou.wfx.basicset.model.AppVersionEntity;
import com.yougou.wfx.basicset.service.IAppVersionService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * IAppVersionService实现
 * @author wfx
 * @Date 创建时间：2016-05-19 09:43:27
 */
@Service
public class AppVersionServiceImpl extends BaseService implements IAppVersionService {
	
	@Resource
	private AppVersionMapper appVersionMapper;

	@Override
	public int findPageCount(AppVersionEntity appVersionEntity){
		return appVersionMapper.findPageCount(appVersionEntity);
	}

	@Override
	public List<AppVersionEntity> findPage(AppVersionEntity appVersionEntity,RowBounds rowBounds){
		return appVersionMapper.findPage(appVersionEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(AppVersionEntity appVersionEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		appVersionEntity.setId(strId);
		appVersionMapper.insert(appVersionEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(AppVersionEntity appVersionEntity){
		return  appVersionMapper.update(appVersionEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return appVersionMapper.remove(id);
	}
	
	@Override
	public AppVersionEntity getById(String id){
		return appVersionMapper.getById(id);
	}

	@Override
	public AppVersionEntity getAndroidNewestVersion() {
		return appVersionMapper.getAndroidNewestVersion();
	} 
}