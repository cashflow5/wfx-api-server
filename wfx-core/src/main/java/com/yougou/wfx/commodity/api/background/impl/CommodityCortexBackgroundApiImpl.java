 /*
 * 版本信息
 
 * 日期 2016-06-21 17:57:54
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.commodity.api.background.ICommodityCortexBackgroundApi;
import com.yougou.wfx.commodity.dto.input.CommodityCortexInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityCortexPageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityCortexOutputDto;
import com.yougou.wfx.commodity.model.CommodityCortexEntity;
import com.yougou.wfx.commodity.service.ICommodityCortexService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ICommodityCortexBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-06-21 17:57:55
 */
@Service
public class CommodityCortexBackgroundApiImpl implements ICommodityCortexBackgroundApi{
	
	@Resource
	ICommodityCortexService commodityCortexService;
	
	@Override
	public int removeById(String id) {
		return commodityCortexService.remove(id);
	}

	@Override
	public String insert(CommodityCortexInputDto commodityCortexDto) {
		return commodityCortexService.insert(this.dtoToEntity(commodityCortexDto));
	}

	@Override
	public PageModel<CommodityCortexOutputDto> findPage(CommodityCortexPageInputDto pageInputDto,PageModel pageModel) {
		CommodityCortexEntity commodityCortexEntity = (CommodityCortexEntity) BeanUtil.convertBean(pageInputDto,CommodityCortexEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = commodityCortexService.findPageCount(commodityCortexEntity);
		List<CommodityCortexEntity> lstCommodityCortexs = commodityCortexService.findPage(commodityCortexEntity, rowBounds);

		return new PageModel<CommodityCortexOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommodityCortexOutputDto>) BeanUtil.convertBeanList(lstCommodityCortexs,CommodityCortexOutputDto.class));
	}

	@Override
	public int update(CommodityCortexInputDto commodityCortexDto) {
		return commodityCortexService.update(this.dtoToEntity(commodityCortexDto));
	}

	@Override
	public CommodityCortexOutputDto getById(String id) {
		CommodityCortexEntity commodityCortexEntity = commodityCortexService.getById(id);
		return this.entityToDto(commodityCortexEntity);
	}
	
	private CommodityCortexEntity dtoToEntity(Object obj){
		return (CommodityCortexEntity) BeanUtil.convertBean(obj,CommodityCortexEntity.class);
	}
	
	private CommodityCortexOutputDto entityToDto(Object obj){
		return (CommodityCortexOutputDto) BeanUtil.convertBean(obj,CommodityCortexOutputDto.class);
	}

	@Override
	public int updateCommodityCortex() {
		return commodityCortexService.updateCommodityCortex();
	}
}
