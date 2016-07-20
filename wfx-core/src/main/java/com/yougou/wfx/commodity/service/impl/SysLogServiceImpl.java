 /*
 * 版本信息
 
 * 日期 2016-03-30 10:51:22
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.commodity.dao.SysLogMapper;
import com.yougou.wfx.commodity.model.SysLogEntity;
import com.yougou.wfx.commodity.service.ISysLogService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * ISysLogService实现
 * @author luoq
 * @Date 创建时间：2016-03-30 10:51:23
 */
@Service
public class SysLogServiceImpl extends BaseService implements ISysLogService {
	
	@Resource
	private SysLogMapper sysLogMapper;

	@Override
	public int findPageCount(SysLogEntity sysLogEntity){
		return sysLogMapper.findPageCount(sysLogEntity);
	}

	@Override
	public List<SysLogEntity> findPage(SysLogEntity sysLogEntity,RowBounds rowBounds){
		return sysLogMapper.findPage(sysLogEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(SysLogEntity sysLogEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		sysLogEntity.setId(strId);
		sysLogMapper.insert(sysLogEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(SysLogEntity sysLogEntity){
		return  sysLogMapper.update(sysLogEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return sysLogMapper.remove(id);
	}
	
	@Override
	public SysLogEntity getById(String id){
		return sysLogMapper.getById(id);
	} 
}