/*
 * 版本信息

 * 日期 2016-06-21 17:57:54

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

import com.yougou.wfx.commodity.dao.CommodityCortexMapper;
import com.yougou.wfx.commodity.dto.output.CommodityCortexOutputDto;
import com.yougou.wfx.commodity.model.CommodityCortexEntity;
import com.yougou.wfx.commodity.service.ICommodityCortexService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * ICommodityCortexService实现
 * 
 * @author wfx
 * @Date 创建时间：2016-06-21 17:57:55
 */
@Service
public class CommodityCortexServiceImpl extends BaseService implements ICommodityCortexService {

	@Resource
	private CommodityCortexMapper commodityCortexMapper;

	@Override
	public int findPageCount(CommodityCortexEntity commodityCortexEntity) {
		return commodityCortexMapper.findPageCount(commodityCortexEntity);
	}

	@Override
	public List<CommodityCortexEntity> findPage(CommodityCortexEntity commodityCortexEntity, RowBounds rowBounds) {
		return commodityCortexMapper.findPage(commodityCortexEntity, rowBounds);
	}

	@Override
	@Transactional
	public String insert(CommodityCortexEntity commodityCortexEntity) {
		String strId = UUIDGenerator.get32LowCaseUUID();
		commodityCortexEntity.setId(strId);
		commodityCortexMapper.insert(commodityCortexEntity);
		return strId;
	}

	@Override
	@Transactional
	public int update(CommodityCortexEntity commodityCortexEntity) {
		return commodityCortexMapper.update(commodityCortexEntity);
	}

	@Override
	@Transactional
	public int remove(String id) {
		return commodityCortexMapper.remove(id);
	}

	@Override
	public CommodityCortexEntity getById(String id) {
		return commodityCortexMapper.getById(id);
	}

	@Override
	public CommodityCortexOutputDto getByName(String cortexName) {
		CommodityCortexOutputDto dto = new CommodityCortexOutputDto();
		CommodityCortexEntity entity = new CommodityCortexEntity();
		entity.setName(cortexName);
		entity.setIsNotDescription(100);
		List<CommodityCortexEntity> list = commodityCortexMapper.queryList(entity);
		if (list != null && !list.isEmpty()) {
			dto =(CommodityCortexOutputDto) BeanUtil.convertBean(list.get(0), CommodityCortexOutputDto.class) ;
		}
		return dto;
	}

	@Override
	public CommodityCortexOutputDto getByNo(String no) {
		CommodityCortexOutputDto dto = new CommodityCortexOutputDto();
		CommodityCortexEntity entity = new CommodityCortexEntity();
		entity.setNo(no);
		entity.setIsNotDescription(100);
		List<CommodityCortexEntity> list = commodityCortexMapper.queryList(entity);
		if (list != null && !list.isEmpty()) {
			dto = (CommodityCortexOutputDto) BeanUtil.convertBean(list.get(0), CommodityCortexOutputDto.class) ;
		}
		return dto;
	}

	@Override
	public int updateCommodityCortex() {
		return commodityCortexMapper.updateCommodityCortex();
	}
}