 /*
 * 版本信息
 
 * 日期 2016-03-24 17:49:13
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.cms.dao.CommoditySaleCatMapper;
import com.yougou.wfx.cms.model.CommoditySaleCatEntity;
import com.yougou.wfx.cms.service.ICommoditySaleCatService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.utils.NumGenUtil;

/**
 * ICommoditySaleCatService实现
 * @author wzf
 * @Date 创建时间：2016-03-24 17:49:14
 */
@Service
public class CommoditySaleCatServiceImpl extends BaseService implements ICommoditySaleCatService {
	
	@Resource
	private CommoditySaleCatMapper commoditySaleCatMapper;

	@Override
	public int findPageCount(CommoditySaleCatEntity commoditySaleCatEntity){
		return commoditySaleCatMapper.findPageCount(commoditySaleCatEntity);
	}

	@Override
	public List<CommoditySaleCatEntity> findPage(CommoditySaleCatEntity commoditySaleCatEntity,RowBounds rowBounds){
		return commoditySaleCatMapper.findPage(commoditySaleCatEntity,rowBounds);
	}
	
	@Override
	public List<CommoditySaleCatEntity> queryList(CommoditySaleCatEntity commoditySaleCatEntity){
		return commoditySaleCatMapper.queryList(commoditySaleCatEntity);
	}
	
	@Override
	@Transactional
	public String insert(CommoditySaleCatEntity commoditySaleCatEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		commoditySaleCatEntity.setId(strId);
		if(StringUtils.isBlank(commoditySaleCatEntity.getCatNo())){
			commoditySaleCatEntity.setCatNo(this.createCatNo());
		}
		commoditySaleCatMapper.insert(commoditySaleCatEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(CommoditySaleCatEntity commoditySaleCatEntity){
		return  commoditySaleCatMapper.update(commoditySaleCatEntity);
	}

	@Override
	@Transactional
	public int updateChildren(CommoditySaleCatEntity commoditySaleCatEntity) {
		return  commoditySaleCatMapper.updateChildren(commoditySaleCatEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return commoditySaleCatMapper.remove(id);
	}
	
	@Override
	public CommoditySaleCatEntity getById(String id){
		return commoditySaleCatMapper.getById(id);
	}

	@Override
	public List<CommoditySaleCatEntity> getChildren(CommoditySaleCatEntity commoditySaleCatEntity) {
		if(null == commoditySaleCatEntity){
			return null;
		}
		Integer level = commoditySaleCatEntity.getLevel();
		if(null == level || level == 2){
			List<CommoditySaleCatEntity> saleCatList = new ArrayList<CommoditySaleCatEntity>();
			saleCatList.add(commoditySaleCatEntity);
			return saleCatList;
		}else{
			CommoditySaleCatEntity querySaleCatEntity = new CommoditySaleCatEntity();
			querySaleCatEntity.setParentId(commoditySaleCatEntity.getId());
			return commoditySaleCatMapper.queryList(querySaleCatEntity);
		}
	}
	
	/**
	 * 随机生成销售分类的类别编号，最多重复生成三次
	 * @return
	 */
	private String createCatNo(){
		String catNo = "";
		Boolean exist = true;
		int count = 0;//生成类别编号的次数，超过3次抛出异常
		while(exist){
			catNo = NumGenUtil.randomString(4);
			CommoditySaleCatEntity entity = new CommoditySaleCatEntity();
			entity.setCatNo(catNo);
			List<CommoditySaleCatEntity> entityList = commoditySaleCatMapper.queryList(entity);
			if(null != entityList && entityList.size() > 0){
				count++;
				if(count > 3){
					throw new IllegalArgumentException("随机生成分类编号超过三次");
				}
			}else{
				exist = false;
				break;
			}
		}
		return catNo;
	}

	@Override
	public List<CommoditySaleCatEntity> queryHotCatList(
			CommoditySaleCatEntity commoditySaleCatEntity, RowBounds rowBounds) {
		return commoditySaleCatMapper.queryHotCatList(commoditySaleCatEntity, rowBounds);
	}

	@Override
	public List<CommoditySaleCatEntity> queryHotCatList(
			CommoditySaleCatEntity commoditySaleCatEntity) {
		return commoditySaleCatMapper.queryHotCatList(commoditySaleCatEntity);
	}

	@Override
	public int getHotCatCount(CommoditySaleCatEntity commoditySaleCatEntity) {
		return commoditySaleCatMapper.getHotCatCount(commoditySaleCatEntity);
	}

	@Override
	public int getMaxHotCatSn() {
		Integer num = commoditySaleCatMapper.getMaxHotCatSn();
		if(null == num){
			num = 0;
		}
		return num;
	}

	@Override
	public int batchUpdate(List<CommoditySaleCatEntity> entityList) {
		return commoditySaleCatMapper.batchUpdate(entityList);
	}

	@Override
	@Transactional
	public int saleCatSkuNumJob() {
		// TODO Auto-generated method stub
		return commoditySaleCatMapper.computeSaleCatSkuNum();
	}
}