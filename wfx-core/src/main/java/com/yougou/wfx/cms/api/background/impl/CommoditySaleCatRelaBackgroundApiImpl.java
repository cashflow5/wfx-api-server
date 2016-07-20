 /*
 * 版本信息
 
 * 日期 2016-04-01 18:28:14
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.cms.api.background.ICommoditySaleCatRelaBackgroundApi;
import com.yougou.wfx.cms.dto.input.CommoditySaleCatRelaInputDto;
import com.yougou.wfx.cms.dto.input.CommoditySaleCatRelaPageInputDto;
import com.yougou.wfx.cms.dto.output.CommoditySaleCatRelaOutputDto;
import com.yougou.wfx.cms.model.CommoditySaleCatRelaEntity;
import com.yougou.wfx.cms.service.ICommoditySaleCatRelaService;
import com.yougou.wfx.commodity.model.CommodityCatb2cEntity;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
import com.yougou.wfx.commodity.service.ICommodityCatb2cService;
import com.yougou.wfx.commodity.service.ICommodityStyleService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ICommoditySaleCatRelaBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-04-01 18:28:15
 */
@Service
public class CommoditySaleCatRelaBackgroundApiImpl implements ICommoditySaleCatRelaBackgroundApi{
	
	@Resource
	ICommoditySaleCatRelaService commoditySaleCatRelaService;
	@Resource
	ICommodityStyleService commodityStyleService;
	@Resource
	ICommodityCatb2cService commodityCatb2cService;
	
	@Override
	@LoggerProfile(methodNote="通过销售分类与基础分类关系表id删除数据")
	public int removeById(String id) {
		return commoditySaleCatRelaService.remove(id);
	}

	@Override
	@LoggerProfile(methodNote="插入销售分类与基础分类关系表")
	public String insert(CommoditySaleCatRelaInputDto commoditySaleCatRelaDto) {
		return commoditySaleCatRelaService.insert(this.dtoToEntity(commoditySaleCatRelaDto));
	}

	@Override
	@LoggerProfile(methodNote="分页查询销售分类对应基础分类关系")
	public PageModel<CommoditySaleCatRelaOutputDto> findPage(CommoditySaleCatRelaPageInputDto pageInputDto,PageModel pageModel) {
		CommoditySaleCatRelaEntity commoditySaleCatRelaEntity = (CommoditySaleCatRelaEntity) BeanUtil.convertBean(pageInputDto,CommoditySaleCatRelaEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		int totalCount = commoditySaleCatRelaService.findPageCount(commoditySaleCatRelaEntity);
		List<CommoditySaleCatRelaEntity> lstCommoditySaleCatRelas = commoditySaleCatRelaService.findPage(commoditySaleCatRelaEntity, rowBounds);
		return new PageModel<CommoditySaleCatRelaOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommoditySaleCatRelaOutputDto>) BeanUtil.convertBeanList(lstCommoditySaleCatRelas,CommoditySaleCatRelaOutputDto.class));
	}

	@Override
	@LoggerProfile(methodNote="更新销售分类与基础分类关系表")
	public int update(CommoditySaleCatRelaInputDto commoditySaleCatRelaDto) {
		return commoditySaleCatRelaService.update(this.dtoToEntity(commoditySaleCatRelaDto));
	}

	@Override
	@LoggerProfile(methodNote="统计销售分类与基础分类关系表数量")
	public int finaPageCount(CommoditySaleCatRelaInputDto commoditySaleCatRelaDto) {
		CommoditySaleCatRelaEntity entity = (CommoditySaleCatRelaEntity)BeanUtil.convertBean(commoditySaleCatRelaDto, CommoditySaleCatRelaEntity.class);
		return commoditySaleCatRelaService.findPageCount(entity);
	}

	@Override
	@LoggerProfile(methodNote="通过id查询统计销售分类与基础分类关系表")
	public CommoditySaleCatRelaOutputDto getById(String id) {
		CommoditySaleCatRelaEntity commoditySaleCatRelaEntity = commoditySaleCatRelaService.getById(id);
		return this.entityToDto(commoditySaleCatRelaEntity);
	}

	@Override
	@LoggerProfile(methodNote="查询销售分类与基础分类关系列表")
	public List<CommoditySaleCatRelaOutputDto> queryList(CommoditySaleCatRelaInputDto commoditySaleCatRelaDto) {
		CommoditySaleCatRelaEntity entity = (CommoditySaleCatRelaEntity)BeanUtil.convertBean(commoditySaleCatRelaDto, CommoditySaleCatRelaEntity.class);
		List<CommoditySaleCatRelaOutputDto> a;
		List<CommoditySaleCatRelaEntity> entityList = commoditySaleCatRelaService.queryList(entity);
		return (List<CommoditySaleCatRelaOutputDto>)BeanUtil.convertBeanList(entityList, CommoditySaleCatRelaOutputDto.class);
	}
	
	/**
	 * 查询出三级分类的一、二、三级分类的名称并填充进列表对象
	 */
	@Override
	@LoggerProfile(methodNote="查询出三级分类的一、二、三级分类的名称并填充进列表对象")
	public List<CommoditySaleCatRelaOutputDto> fillCatNames(List<CommoditySaleCatRelaOutputDto> relaList) {
		if(relaList != null && relaList.size() > 0){
			for(CommoditySaleCatRelaOutputDto relaDto:relaList){
				String basicCatId = relaDto.getBaseCatId();
				if(StringUtils.isNotBlank(basicCatId)){
					CommodityCatb2cEntity entityDto = commodityCatb2cService.getById(basicCatId);
					relaDto.setLevelThreeName(entityDto.getCatName());
					entityDto = commodityCatb2cService.getById(entityDto.getParentId());
					relaDto.setLevelTwoName(entityDto.getCatName());
					entityDto = commodityCatb2cService.getById(entityDto.getParentId());
					relaDto.setLevelOneName(entityDto.getCatName());
				}
			}
		}
		return relaList;
	}

	@Override
	@LoggerProfile(methodNote="查询出销信分类的商品数量")
	public Integer queryStyleNum(List<CommoditySaleCatRelaOutputDto> relaList) {
		Integer comNum = 0;
		if(null != relaList && relaList.size() > 0){
			for(CommoditySaleCatRelaOutputDto saleCatTemp:relaList){
				CommodityStyleEntity etity = new CommodityStyleEntity();
				etity.setCatNo(saleCatTemp.getBaseCatNo());
				comNum += commodityStyleService.findPageCount(etity);
			}
		}
		return comNum;
	}

	@Override
	@LoggerProfile(methodNote="删除销售分类与基础分类关系表")
	public int multiRemove(CommoditySaleCatRelaInputDto commoditySaleCatRelaDto) {
		CommoditySaleCatRelaEntity entity = (CommoditySaleCatRelaEntity)BeanUtil.convertBean(commoditySaleCatRelaDto, CommoditySaleCatRelaEntity.class);
		return commoditySaleCatRelaService.multiRemove(entity);
	}
	
	private CommoditySaleCatRelaEntity dtoToEntity(Object obj){
		return (CommoditySaleCatRelaEntity) BeanUtil.convertBean(obj,CommoditySaleCatRelaEntity.class);
	}
	
	private CommoditySaleCatRelaOutputDto entityToDto(Object obj){
		return (CommoditySaleCatRelaOutputDto) BeanUtil.convertBean(obj,CommoditySaleCatRelaOutputDto.class);
	}
}
