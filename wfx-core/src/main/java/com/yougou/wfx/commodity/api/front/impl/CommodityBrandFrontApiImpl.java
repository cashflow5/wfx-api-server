 /*
 * 版本信息
 
 * 日期 2016-03-23 18:39:33
 
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

import com.yougou.wfx.commodity.api.front.ICommodityBrandFrontApi;
import com.yougou.wfx.commodity.dto.input.CommodityBrandPageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityBrandOutputDto;
import com.yougou.wfx.commodity.model.CommodityBrandEntity;
import com.yougou.wfx.commodity.service.ICommodityBrandService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ICommodityBrandFrontApi实现
 * @author wuyang
 * @Date 创建时间：2016-03-23 18:39:34
 */
@Service
public class CommodityBrandFrontApiImpl implements ICommodityBrandFrontApi{
	
	@Resource
	ICommodityBrandService commodityBrandService;
	
	@Override
	@LoggerProfile(methodNote="分页查询商品品牌")
	public PageModel<CommodityBrandOutputDto> findPage(@NotNull CommodityBrandPageInputDto pageInputDto,@NotNull PageModel pageModel) {
		CommodityBrandEntity commodityBrandEntity = (CommodityBrandEntity) BeanUtil.convertBean(pageInputDto,CommodityBrandEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = commodityBrandService.findPageCount(commodityBrandEntity);
		List<CommodityBrandEntity> lstCommodityBrands = commodityBrandService.findPage(commodityBrandEntity, rowBounds);

		return new PageModel<CommodityBrandOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommodityBrandOutputDto>) BeanUtil.convertBeanList(lstCommodityBrands,CommodityBrandOutputDto.class));
	}
	
}
