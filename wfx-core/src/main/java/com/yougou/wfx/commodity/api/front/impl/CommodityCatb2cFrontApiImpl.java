 /*
 * 版本信息
 
 * 日期 2016-03-24 11:14:00
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotNull;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.commodity.api.front.ICommodityCatb2cFrontApi;
import com.yougou.wfx.commodity.dto.input.CommodityCatb2cPageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityCatb2cOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityExtendPropItemOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityExtendPropValueOutputDto;
import com.yougou.wfx.commodity.model.CommodityCatb2cEntity;
import com.yougou.wfx.commodity.service.ICommodityCatb2cService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ICommodityCatb2cFrontApi实现
 * @author wzf
 * @Date 创建时间：2016-03-24 11:14:01
 */
@Service
public class CommodityCatb2cFrontApiImpl implements ICommodityCatb2cFrontApi{
	
	@Resource
	ICommodityCatb2cService commodityCatb2cService;
	
	
	@Override
	@LoggerProfile(methodNote="分页查询基础分类")
	public PageModel<CommodityCatb2cOutputDto> findCommodityCatb2cPage(@NotNull CommodityCatb2cPageInputDto pageInputDto,@NotNull PageModel pageModel) {
		CommodityCatb2cEntity commodityCatb2cEntity = (CommodityCatb2cEntity) BeanUtil.convertBean(pageInputDto,CommodityCatb2cEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = commodityCatb2cService.findPageCount(commodityCatb2cEntity);
		List<CommodityCatb2cEntity> lstCommodityCatb2cs = commodityCatb2cService.findPage(commodityCatb2cEntity, rowBounds);

		return new PageModel<CommodityCatb2cOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommodityCatb2cOutputDto>) BeanUtil.convertBeanList(lstCommodityCatb2cs,CommodityCatb2cOutputDto.class));
	}

	@Override
	@LoggerProfile(methodNote="分页查询商品属性")
	public PageModel<CommodityExtendPropItemOutputDto> findCommodityExtendPropItemPage(
			@NotNull PageModel pageModel) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@LoggerProfile(methodNote="分页查询商品属性值")
	public PageModel<CommodityExtendPropValueOutputDto> findCommodityExtendPropValuePage(
			@NotNull PageModel pageModel) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
