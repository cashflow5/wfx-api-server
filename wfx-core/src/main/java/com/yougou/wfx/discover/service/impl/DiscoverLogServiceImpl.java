 /*
 * 版本信息
 
 * 日期 2016-06-02 20:18:25
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.discover.dao.DiscoverLogMapper;
import com.yougou.wfx.discover.model.DiscoverLogEntity;
import com.yougou.wfx.discover.service.IDiscoverLogService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * IDiscoverLogService实现
 * @author zhang.wj
 * @Date 创建时间：2016-06-02 20:18:26
 */
@Service
public class DiscoverLogServiceImpl extends BaseService implements IDiscoverLogService {
	
	@Resource
	private DiscoverLogMapper discoverLogMapper;

	@Override
	public int findPageCount(DiscoverLogEntity discoverLogEntity){
		return discoverLogMapper.findPageCount(discoverLogEntity);
	}

	@Override
	public List<DiscoverLogEntity> findPage(DiscoverLogEntity discoverLogEntity,RowBounds rowBounds){
		return discoverLogMapper.findPage(discoverLogEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(DiscoverLogEntity discoverLogEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		discoverLogEntity.setId(strId);
		discoverLogMapper.insert(discoverLogEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(DiscoverLogEntity discoverLogEntity){
		return  discoverLogMapper.update(discoverLogEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return discoverLogMapper.remove(id);
	}
	
	@Override
	public DiscoverLogEntity getById(String id){
		return discoverLogMapper.getById(id);
	} 
}