 /*
 * 版本信息
 
 * 日期 2016-03-31 19:13:58
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.commodity.api.background.ICommodityBrandCatb2cBackgroundApi;
import com.yougou.wfx.commodity.dto.input.CommodityBrandCatb2cInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityBrandCatb2cPageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityBrandCatb2cOutputDto;
import com.yougou.wfx.commodity.model.CommodityBrandCatb2cEntity;
import com.yougou.wfx.commodity.service.ICommodityBrandCatb2cService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * ICommodityBrandCatb2cBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-03-31 19:13:58
 */
@Service
public class CommodityBrandCatb2cBackgroundApiImpl implements ICommodityBrandCatb2cBackgroundApi{
	
	@Resource
	ICommodityBrandCatb2cService commodityBrandCatb2cService;
	
	@Override
	@LoggerProfile(methodNote="根据ID删除品牌分类关系")
	public int removeById(String id) {
		return commodityBrandCatb2cService.remove(id);
	}

	@Override
	@LoggerProfile(methodNote="新增品牌分类关系")
	public String insert(CommodityBrandCatb2cInputDto commodityBrandCatb2cDto) {
		return commodityBrandCatb2cService.insert(this.dtoToEntity(commodityBrandCatb2cDto));
	}
	
	@Override
	@LoggerProfile(methodNote="批量新增品牌分类关系")
	public void batchInsert(String brandId,String[] catIds) {
		List<CommodityBrandCatb2cEntity> commodityBrandDtoList = new ArrayList<CommodityBrandCatb2cEntity>(catIds.length);
		commodityBrandCatb2cService.deleteByBrandId(brandId);
		CommodityBrandCatb2cEntity entity = null;
		for(String catId : catIds){
			entity = new CommodityBrandCatb2cEntity();
			entity.setBrandId(brandId);
			entity.setCatb2cId(catId);
			entity.setId(UUIDGenerator.get32LowCaseUUID());
			commodityBrandDtoList.add(entity);
		}
		commodityBrandCatb2cService.batchInsert(commodityBrandDtoList);
	}

	@Override
	@LoggerProfile(methodNote="分页查询品牌分类关系列表")
	public PageModel<CommodityBrandCatb2cOutputDto> findPage(CommodityBrandCatb2cPageInputDto pageInputDto,PageModel pageModel) {
		CommodityBrandCatb2cEntity commodityBrandCatb2cEntity = (CommodityBrandCatb2cEntity) BeanUtil.convertBean(pageInputDto,CommodityBrandCatb2cEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = commodityBrandCatb2cService.findPageCount(commodityBrandCatb2cEntity);
		List<CommodityBrandCatb2cEntity> lstCommodityBrandCatb2cs = commodityBrandCatb2cService.findPage(commodityBrandCatb2cEntity, rowBounds);

		return new PageModel<CommodityBrandCatb2cOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommodityBrandCatb2cOutputDto>) BeanUtil.convertBeanList(lstCommodityBrandCatb2cs,CommodityBrandCatb2cOutputDto.class));
	}

	@Override
	@LoggerProfile(methodNote="修改品牌分类关系")
	public int update(CommodityBrandCatb2cInputDto commodityBrandCatb2cDto) {
		return commodityBrandCatb2cService.update(this.dtoToEntity(commodityBrandCatb2cDto));
	}

	@Override
	@LoggerProfile(methodNote="根据ID查询品牌分类关系")
	public CommodityBrandCatb2cOutputDto getById(String id) {
		CommodityBrandCatb2cEntity commodityBrandCatb2cEntity = commodityBrandCatb2cService.getById(id);
		return this.entityToDto(commodityBrandCatb2cEntity);
	}
	
	@Override
	@LoggerProfile(methodNote="根据品牌ID查询分类ID列表")
	public List<String> queryCatb2cIdsByBrandId(String brandId) {
		CommodityBrandCatb2cEntity commodityBrandCatb2cEntity = new CommodityBrandCatb2cEntity();
		commodityBrandCatb2cEntity.setBrandId(brandId);
		RowBounds rowBounds = new RowBounds(0, Integer.MAX_VALUE);
		List<CommodityBrandCatb2cEntity> lstCommodityBrandCatb2cs = commodityBrandCatb2cService.findPage(commodityBrandCatb2cEntity, rowBounds);
		
		List<String> catIdList = new ArrayList<String>(lstCommodityBrandCatb2cs.size());
		for(CommodityBrandCatb2cEntity entity : lstCommodityBrandCatb2cs){
			catIdList.add(entity.getCatb2cId());
		}
		return catIdList;
	}
	
	
	private CommodityBrandCatb2cEntity dtoToEntity(Object obj){
		return (CommodityBrandCatb2cEntity) BeanUtil.convertBean(obj,CommodityBrandCatb2cEntity.class);
	}
	
	private CommodityBrandCatb2cOutputDto entityToDto(Object obj){
		return (CommodityBrandCatb2cOutputDto) BeanUtil.convertBean(obj,CommodityBrandCatb2cOutputDto.class);
	}
}
