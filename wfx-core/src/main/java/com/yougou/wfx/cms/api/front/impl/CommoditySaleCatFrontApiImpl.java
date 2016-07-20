 /*
 * 版本信息
 
 * 日期 2016-03-24 17:49:13
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.cms.api.front.ICommoditySaleCatFrontApi;
import com.yougou.wfx.cms.dto.input.CommoditySaleCatPageInputDto;
import com.yougou.wfx.cms.dto.output.CommoditySaleCatOutputDto;
import com.yougou.wfx.cms.model.CommoditySaleCatEntity;
import com.yougou.wfx.cms.service.ICommoditySaleCatService;
import com.yougou.wfx.commodity.dto.output.CommodityBrandOutputDto;
import com.yougou.wfx.commodity.model.CommodityBrandEntity;
import com.yougou.wfx.commodity.service.ICommodityBrandService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.cache.CacheCable;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ICommoditySaleCatFrontApi实现
 * @author li.j1
 * @Date 创建时间：2016-03-24 17:49:14
 */
@Service
public class CommoditySaleCatFrontApiImpl implements ICommoditySaleCatFrontApi{
	
	@Resource
	ICommoditySaleCatService commoditySaleCatService;
	
	@Resource
	ICommodityBrandService commodityBrandService;
	
	@Override
	@LoggerProfile(methodNote = "查询所有一级销售分类列表")
	public List<CommoditySaleCatOutputDto> queryCommoditySaleCatLevelOneList() {
		CommoditySaleCatEntity commoditySaleCatEntity = new CommoditySaleCatEntity();
		commoditySaleCatEntity.setLevel(1);
		commoditySaleCatEntity.setDeleteFlag(2);
		RowBounds rowBounds = new RowBounds();
		List<CommoditySaleCatEntity> lstCommoditySaleCats = commoditySaleCatService.findPage(commoditySaleCatEntity, rowBounds);
		return (List<CommoditySaleCatOutputDto>) BeanUtil.convertBeanList(lstCommoditySaleCats,CommoditySaleCatOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote = "根据一级分类ID查询所有二级销售分类列表")
	public List<CommoditySaleCatOutputDto> queryCommoditySaleCatLevelTwoList(
			@NotBlank String parentCatId) {
		CommoditySaleCatEntity commoditySaleCatEntity = new CommoditySaleCatEntity();
		commoditySaleCatEntity.setLevel(2);
		commoditySaleCatEntity.setParentId(parentCatId);
		commoditySaleCatEntity.setDeleteFlag(2);
		RowBounds rowBounds = new RowBounds();
		List<CommoditySaleCatEntity> lstCommoditySaleCats = commoditySaleCatService.findPage(commoditySaleCatEntity, rowBounds);
		return (List<CommoditySaleCatOutputDto>) BeanUtil.convertBeanList(lstCommoditySaleCats,CommoditySaleCatOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote = "查询一级销售分类对应的品牌列表")
	@CacheCable(key="'wfx_commodity_brandcat'+#catId",value="wfx_commodity" ,expiration=24*60*60)
	public List<CommodityBrandOutputDto> queryCommodityBrandByCatId(
			@NotBlank String catId) {
		List<CommodityBrandEntity> lstCommodityBrand = commodityBrandService.queryCommodityBrandByCatId(catId);
		return (List<CommodityBrandOutputDto>) BeanUtil.convertBeanList(lstCommodityBrand,CommodityBrandOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote = "查询所有销售分类列表")
	public PageModel<CommoditySaleCatOutputDto> queryCommoditySaleCat(
			@NotNull CommoditySaleCatPageInputDto pageInputDto, @NotNull PageModel pageModel) {
		
		CommoditySaleCatEntity commoditySaleCatEntity = (CommoditySaleCatEntity) BeanUtil.convertBean(pageInputDto,CommoditySaleCatEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = commoditySaleCatService.findPageCount(commoditySaleCatEntity);
		List<CommoditySaleCatEntity> lstCommoditySaleCats = commoditySaleCatService.findPage(commoditySaleCatEntity, rowBounds);

		return new PageModel<CommoditySaleCatOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommoditySaleCatOutputDto>) BeanUtil.convertBeanList(lstCommoditySaleCats,CommoditySaleCatOutputDto.class));
	}
}
