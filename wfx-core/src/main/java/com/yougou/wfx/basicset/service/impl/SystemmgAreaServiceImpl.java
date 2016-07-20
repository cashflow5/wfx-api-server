 /*
 * 版本信息
 
 * 日期 2016-04-07 18:29:33
 
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

import com.yougou.wfx.basicset.dao.SystemmgAreaMapper;
import com.yougou.wfx.basicset.model.SystemmgAreaEntity;
import com.yougou.wfx.basicset.service.ISystemmgAreaService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * ISystemmgAreaService实现
 * @author wfx
 * @Date 创建时间：2016-04-07 18:29:33
 */
@Service
public class SystemmgAreaServiceImpl extends BaseService implements ISystemmgAreaService {
	
	@Resource
	private SystemmgAreaMapper systemmgAreaMapper;

	@Override
	public int findPageCount(SystemmgAreaEntity systemmgAreaEntity){
		return systemmgAreaMapper.findPageCount(systemmgAreaEntity);
	}

	@Override
	public List<SystemmgAreaEntity> findPage(SystemmgAreaEntity systemmgAreaEntity,RowBounds rowBounds){
		return systemmgAreaMapper.findPage(systemmgAreaEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(SystemmgAreaEntity systemmgAreaEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		systemmgAreaEntity.setId(strId);
		systemmgAreaMapper.insert(systemmgAreaEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(SystemmgAreaEntity systemmgAreaEntity){
		return  systemmgAreaMapper.update(systemmgAreaEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return systemmgAreaMapper.remove(id);
	}
	
	@Override
	public SystemmgAreaEntity getById(String id){
		return systemmgAreaMapper.getById(id);
	} 
	
	@Override
	public List<SystemmgAreaEntity> queryList(SystemmgAreaEntity systemmgAreaEntity){
		RowBounds rowBounds = new RowBounds();
		return systemmgAreaMapper.findPage(systemmgAreaEntity, rowBounds);
	}
	
	@Override
	@Transactional
	public int removeCurrentAndSubArea(String id, String no){
		systemmgAreaMapper.removeSubAreaByNo(no+"-");
		return systemmgAreaMapper.remove(id);
	}
}