 /*
 * 版本信息
 
 * 日期 2016-03-24 17:49:13
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.cms.api.background.ICommoditySaleCatBackgroundApi;
import com.yougou.wfx.cms.dto.input.CommoditySaleCatInputDto;
import com.yougou.wfx.cms.dto.input.CommoditySaleCatPageInputDto;
import com.yougou.wfx.cms.dto.output.CommoditySaleCatOutputDto;
import com.yougou.wfx.cms.model.CommoditySaleCatEntity;
import com.yougou.wfx.cms.model.CommoditySaleCatRelaEntity;
import com.yougou.wfx.cms.service.ICommoditySaleCatRelaService;
import com.yougou.wfx.cms.service.ICommoditySaleCatService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ICommoditySaleCatBackgroundApi实现
 * @author wzf
 * @Date 创建时间：2016-03-24 17:49:14
 */
@Service
public class CommoditySaleCatBackgroundApiImpl implements ICommoditySaleCatBackgroundApi{
	
	@Resource
	ICommoditySaleCatService commoditySaleCatService;
	@Resource
	ICommoditySaleCatRelaService commoditySaleCatRelaService;
	
	@Override
	@LoggerProfile(methodNote="通过id删除销售分类")
	public int removeById(String id) {
		return commoditySaleCatService.remove(id);
	}

	@Override
	@LoggerProfile(methodNote="插入销售分类")
	public String insert(CommoditySaleCatInputDto commoditySaleCatDto) {
		return commoditySaleCatService.insert(this.dtoToEntity(commoditySaleCatDto));
	}

	@Override
	@LoggerProfile(methodNote="分页查询销售分类")
	public PageModel<CommoditySaleCatOutputDto> findPage(CommoditySaleCatPageInputDto pageInputDto,PageModel<CommoditySaleCatOutputDto> pageModel) {
		CommoditySaleCatEntity commoditySaleCatEntity = (CommoditySaleCatEntity) BeanUtil.convertBean(pageInputDto,CommoditySaleCatEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = commoditySaleCatService.findPageCount(commoditySaleCatEntity);
		List<CommoditySaleCatEntity> lstCommoditySaleCats = commoditySaleCatService.findPage(commoditySaleCatEntity, rowBounds);

		return new PageModel<CommoditySaleCatOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommoditySaleCatOutputDto>) BeanUtil.convertBeanList(lstCommoditySaleCats,CommoditySaleCatOutputDto.class));
	}
	
	@Override
	@LoggerProfile(methodNote="查询销售分类列表")
	public List<CommoditySaleCatOutputDto> queryList(CommoditySaleCatInputDto inputDto){
		CommoditySaleCatEntity commoditySaleCatEntity = (CommoditySaleCatEntity)BeanUtil.convertBean(inputDto, CommoditySaleCatEntity.class);
		List<CommoditySaleCatEntity> saleCatList = commoditySaleCatService.queryList(commoditySaleCatEntity);
		return (List<CommoditySaleCatOutputDto>)BeanUtil.convertBeanList(saleCatList, CommoditySaleCatOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="更新销售分类")
	public int update(CommoditySaleCatInputDto commoditySaleCatDto) {
		return commoditySaleCatService.update(this.dtoToEntity(commoditySaleCatDto));
	}
	
	@Override
	@LoggerProfile(methodNote="根据交分类id更新销售分类")
	public int updateChildren(CommoditySaleCatInputDto commoditySaleCatDto) {
		return commoditySaleCatService.updateChildren(this.dtoToEntity(commoditySaleCatDto));
	}

	@Override
	@LoggerProfile(methodNote="统计销售分类数量")
	public int findPageCount(CommoditySaleCatInputDto inputDto) {
		CommoditySaleCatEntity commoditySaleCatEntity = (CommoditySaleCatEntity) BeanUtil.convertBean(inputDto,CommoditySaleCatEntity.class);
		return commoditySaleCatService.findPageCount(commoditySaleCatEntity);
	}

	@Override
	@LoggerProfile(methodNote="通过id查询销售分类")
	public CommoditySaleCatOutputDto getById(String id) {
		CommoditySaleCatEntity commoditySaleCatEntity = commoditySaleCatService.getById(id);
		return this.entityToDto(commoditySaleCatEntity);
	}

	@Override
	@LoggerProfile(methodNote="查询销售分类是否有绑定的基础分类")
	public boolean hasBindCat(CommoditySaleCatInputDto inputDto) {
		boolean flag = false;
		CommoditySaleCatEntity entity = (CommoditySaleCatEntity)BeanUtil.convertBean(inputDto, CommoditySaleCatEntity.class);
		List<CommoditySaleCatEntity> entityList = commoditySaleCatService.getChildren(entity);
		if(null != entityList && entityList.size() > 0){
			for(CommoditySaleCatEntity tempEntity:entityList){
				CommoditySaleCatRelaEntity commoditySaleCatRelaEntity = new CommoditySaleCatRelaEntity();
				commoditySaleCatRelaEntity.setSaleCatId(tempEntity.getId());
				int catNum = commoditySaleCatRelaService.findPageCount(commoditySaleCatRelaEntity);
				if(catNum > 0){
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
	private CommoditySaleCatEntity dtoToEntity(Object obj){
		return (CommoditySaleCatEntity) BeanUtil.convertBean(obj,CommoditySaleCatEntity.class);
	}
	
	private CommoditySaleCatOutputDto entityToDto(Object obj){
		return (CommoditySaleCatOutputDto) BeanUtil.convertBean(obj,CommoditySaleCatOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="分页查询热门分类")
	public PageModel<CommoditySaleCatOutputDto> queryHotCatPage(
			CommoditySaleCatPageInputDto pageInputDto,PageModel<CommoditySaleCatOutputDto> pageModel) {
		CommoditySaleCatEntity commoditySaleCatEntity = (CommoditySaleCatEntity) BeanUtil.convertBean(pageInputDto,CommoditySaleCatEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		int totalCount = commoditySaleCatService.getHotCatCount(commoditySaleCatEntity);
		List<CommoditySaleCatEntity> lstCommoditySaleCats = commoditySaleCatService.queryHotCatList(commoditySaleCatEntity, rowBounds);
		return new PageModel<CommoditySaleCatOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommoditySaleCatOutputDto>) BeanUtil.convertBeanList(lstCommoditySaleCats,CommoditySaleCatOutputDto.class));
	}

	@Override
	@LoggerProfile(methodNote="查询所有热门分类")
	public List<CommoditySaleCatOutputDto> queryHotCatList(CommoditySaleCatInputDto inputDto) {
		CommoditySaleCatEntity entity = (CommoditySaleCatEntity)BeanUtil.convertBean(inputDto, CommoditySaleCatEntity.class);
		List<CommoditySaleCatEntity> hotCatList = commoditySaleCatService.queryHotCatList(entity);
		return (List<CommoditySaleCatOutputDto>)BeanUtil.convertBeanList(hotCatList, CommoditySaleCatOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="查询热门分类数量")
	public int getHotCatCount(CommoditySaleCatInputDto commoditySaleCatInputDto) {
		CommoditySaleCatEntity entity = (CommoditySaleCatEntity)BeanUtil.convertBean(commoditySaleCatInputDto, CommoditySaleCatEntity.class);
		return commoditySaleCatService.getHotCatCount(entity);
	}

	@Override
	@LoggerProfile(methodNote="查询销售分类的最大排序号")
	public int getMaxHotCatSn() {
		return commoditySaleCatService.getMaxHotCatSn();
	}

	@Override
	@LoggerProfile(methodNote="批量更新销售分类")
	public int batchUpdate(List<CommoditySaleCatInputDto> inputDtoList) {
		List<CommoditySaleCatEntity> entityList = (List<CommoditySaleCatEntity>)BeanUtil.convertBeanList(inputDtoList, CommoditySaleCatEntity.class);
		return commoditySaleCatService.batchUpdate(entityList);
	}

	@Override
	@LoggerProfile(methodNote="销售分类数量统计job")
	public void saleCatSkuNumJob() {
		// TODO Auto-generated method stub
		commoditySaleCatService.saleCatSkuNumJob();
	}
}
