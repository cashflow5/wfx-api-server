 /*
 * 版本信息
 
 * 日期 2016-03-24 11:14:00
 
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

import com.yougou.wfx.commodity.api.background.ICommodityCatb2cBackgroundApi;
import com.yougou.wfx.commodity.api.background.ICommodityStyleBackgroundApi;
import com.yougou.wfx.commodity.dto.input.CommodityCatb2cInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityCatb2cPageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityCatb2cOutputDto;
import com.yougou.wfx.commodity.model.CommodityCatb2cEntity;
import com.yougou.wfx.commodity.service.ICommodityCatb2cService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ICommodityCatb2cBackgroundApi实现
 * @author wzf
 * @Date 创建时间：2016-03-24 11:14:01
 */
@Service
public class CommodityCatb2cBackgroundApiImpl implements ICommodityCatb2cBackgroundApi{
	
	@Resource
	ICommodityCatb2cService commodityCatb2cService;
	@Resource
	ICommodityStyleBackgroundApi commodityStyleBackgroundApi;
	
	@Override
	@LoggerProfile(methodNote="根据基础分类id删除基础分类")
	public int removeById(String id) {
		return commodityCatb2cService.remove(id);
	}

	@Override
	@LoggerProfile(methodNote="插入基础分类")
	public String insert(CommodityCatb2cInputDto commodityCatb2cDto) {
		return commodityCatb2cService.insert(this.dtoToEntity(commodityCatb2cDto));
	}

	@Override
	@LoggerProfile(methodNote="分页查询基础分类")
	public PageModel<CommodityCatb2cOutputDto> findPage(CommodityCatb2cPageInputDto pageInputDto,PageModel<CommodityCatb2cOutputDto> pageModel) {
		CommodityCatb2cEntity commodityCatb2cEntity = (CommodityCatb2cEntity) BeanUtil.convertBean(pageInputDto,CommodityCatb2cEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = commodityCatb2cService.findPageCount(commodityCatb2cEntity);
		List<CommodityCatb2cEntity> lstCommodityCatb2cs = commodityCatb2cService.findPage(commodityCatb2cEntity, rowBounds);

		return new PageModel<CommodityCatb2cOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommodityCatb2cOutputDto>) BeanUtil.convertBeanList(lstCommodityCatb2cs,CommodityCatb2cOutputDto.class));
	}
	
	@Override
	@LoggerProfile(methodNote="查询基础分类数量")
	public int findPageCount(CommodityCatb2cInputDto inputDto){
		CommodityCatb2cEntity commodityCatb2cEntity = (CommodityCatb2cEntity)BeanUtil.convertBean(inputDto, CommodityCatb2cEntity.class);
		return commodityCatb2cService.findPageCount(commodityCatb2cEntity);
	}

	@Override
	@LoggerProfile(methodNote="更新基础分类")
	public int update(CommodityCatb2cInputDto commodityCatb2cDto) {
		return commodityCatb2cService.update(this.dtoToEntity(commodityCatb2cDto));
	}

	@Override
	@LoggerProfile(methodNote="根据基础分类id查询基础分类")
	public CommodityCatb2cOutputDto getById(String id) {
		CommodityCatb2cEntity commodityCatb2cEntity = commodityCatb2cService.getById(id);
		return this.entityToDto(commodityCatb2cEntity);
	}

	@Override
	@LoggerProfile(methodNote="查询满足条件的基础分类列表")
	public List<CommodityCatb2cOutputDto> queryList(CommodityCatb2cInputDto commodityCatb2cDto) {
		List<CommodityCatb2cEntity> catList = commodityCatb2cService.queryList(this.dtoToEntity(commodityCatb2cDto));
		return (List<CommodityCatb2cOutputDto>) BeanUtil.convertBeanList(catList,CommodityCatb2cOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="查询某基础分类下是否有商品")
	public boolean hasCommodityOnCat(CommodityCatb2cInputDto commodityCatb2cDto) {
		boolean flag = false;
		CommodityCatb2cEntity commodityCatb2cEntity = (CommodityCatb2cEntity)BeanUtil.convertBean(commodityCatb2cDto, CommodityCatb2cEntity.class);
		List<CommodityCatb2cEntity> entityList = commodityCatb2cService.getThreeLevelCats(commodityCatb2cEntity);
		if(null != entityList && entityList.size() > 0){
			for(CommodityCatb2cEntity entity:entityList){
				boolean hasComm = commodityStyleBackgroundApi.hasCommodityOnSaleByCat(entity.getNo());
				if(hasComm){
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
	private CommodityCatb2cEntity dtoToEntity(Object obj){
		return (CommodityCatb2cEntity) BeanUtil.convertBean(obj,CommodityCatb2cEntity.class);
	}
	
	private CommodityCatb2cOutputDto entityToDto(Object obj){
		return (CommodityCatb2cOutputDto) BeanUtil.convertBean(obj,CommodityCatb2cOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="批量更新基础分类数量")
	public int batchUpdateSkuNum() {
		return commodityCatb2cService.batchUpdateSkuNum();
	}
	
	@Override
	@LoggerProfile(methodNote="根据品牌，分类查询分类信息")
	public List<CommodityCatb2cOutputDto> getCatList(String brandNo, String baseCatId, int level){
		List<CommodityCatb2cEntity> lstCommodityCatb2cs = commodityCatb2cService.getCatList(brandNo, baseCatId, level);
		return (List<CommodityCatb2cOutputDto>) BeanUtil.convertBeanList(lstCommodityCatb2cs,CommodityCatb2cOutputDto.class);
	}
}
