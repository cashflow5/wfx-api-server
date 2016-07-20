/*
 * 版本信息

 * 日期 2016-03-24 11:14:00

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.commodity.dao.CommodityCatb2cMapper;
import com.yougou.wfx.commodity.dto.output.CommodityCatb2cOutputDto;
import com.yougou.wfx.commodity.model.CommodityCatb2cEntity;
import com.yougou.wfx.commodity.service.ICommodityCatb2cService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.utils.NumGenUtil;

/**
 * ICommodityCatb2cService实现
 * 
 * @author wzf
 * @Date 创建时间：2016-03-24 11:14:01
 */
@Service
public class CommodityCatb2cServiceImpl extends BaseService implements ICommodityCatb2cService {

	@Resource
	private CommodityCatb2cMapper commodityCatb2cMapper;

	@Override
	public int findPageCount(CommodityCatb2cEntity commodityCatb2cEntity) {
		return commodityCatb2cMapper.findPageCount(commodityCatb2cEntity);
	}

	@Override
	public List<CommodityCatb2cEntity> findPage(CommodityCatb2cEntity commodityCatb2cEntity, RowBounds rowBounds) {
		return commodityCatb2cMapper.findPage(commodityCatb2cEntity, rowBounds);
	}

	@Override
	@Transactional
	public String insert(CommodityCatb2cEntity commodityCatb2cEntity) {
		String strId = UUIDGenerator.get32LowCaseUUID();
		commodityCatb2cEntity.setId(strId);
		if (StringUtils.isBlank(commodityCatb2cEntity.getNo())) {
			commodityCatb2cEntity.setNo(this.createCatNo());
		}
		commodityCatb2cMapper.insert(commodityCatb2cEntity);
		return strId;
	}

	@Override
	@Transactional
	public int update(CommodityCatb2cEntity commodityCatb2cEntity) {
		return commodityCatb2cMapper.update(commodityCatb2cEntity);
	}

	@Override
	@Transactional
	public int remove(String id) {
		return commodityCatb2cMapper.remove(id);
	}

	@Override
	public CommodityCatb2cEntity getById(String id) {
		return commodityCatb2cMapper.getById(id);
	}

	@Override
	public List<CommodityCatb2cEntity> queryList(CommodityCatb2cEntity commodityCatb2cEntity) {
		return commodityCatb2cMapper.queryList(commodityCatb2cEntity);
	}

	private String createCatNo() {
		String catNo = "";
		Boolean exist = true;
		int count = 0;// 生成类别编号的次数，超过3次抛出异常
		while (exist) {
			catNo = NumGenUtil.randomString(4);
			CommodityCatb2cEntity entity = new CommodityCatb2cEntity();
			entity.setNo(catNo);
			List<CommodityCatb2cEntity> entityList = commodityCatb2cMapper.queryList(entity);
			if (null != entityList && entityList.size() > 0) {
				count++;
				if (count > 3) {
					throw new IllegalArgumentException("随机生成分类编号超过三次");
				}
			} else {
				exist = false;
				break;
			}
		}
		return catNo;
	}

	@Override
	public List<CommodityCatb2cEntity> getNextLevelCats(CommodityCatb2cEntity commodityCatb2cEntity) {
		if (null == commodityCatb2cEntity) {
			return null;
		}
		Integer level = commodityCatb2cEntity.getLevel();
		if (null == level || level == 3) {
			return null;
		}
		CommodityCatb2cEntity entity = new CommodityCatb2cEntity();
		entity.setParentId(commodityCatb2cEntity.getId());
		return commodityCatb2cMapper.queryList(entity);
	}

	/**
	 * 查询某个分类下的所有三级分类
	 * 
	 * @param commodityCatb2cEntity
	 * @return
	 */
	@Override
	public List<CommodityCatb2cEntity> getThreeLevelCats(CommodityCatb2cEntity commodityCatb2cEntity) {
		List<CommodityCatb2cEntity> entityList = new ArrayList<CommodityCatb2cEntity>();
		if (null == commodityCatb2cEntity) {
			return null;
		}
		entityList.add(commodityCatb2cEntity);
		Integer level = commodityCatb2cEntity.getLevel();
		if (null == level || level == 3) {
			// 如果已经是三级了，就不用再查询下级了
		} else if (level == 2) {
			List<CommodityCatb2cEntity> levelList = this.getNextLevelCats(commodityCatb2cEntity);
			if (null != levelList && levelList.size() > 0) {
				entityList.addAll(levelList);
			}
		} else if (level == 1) {
			List<CommodityCatb2cEntity> levelList = this.getNextLevelCats(commodityCatb2cEntity);
			if (null != levelList && levelList.size() > 0) {
				entityList.addAll(levelList);
			}
			for (CommodityCatb2cEntity entityTemp : levelList) {
				List<CommodityCatb2cEntity> tempList = this.getNextLevelCats(entityTemp);
				if (null != tempList && tempList.size() > 0) {
					entityList.addAll(tempList);
				}
			}
		}
		return entityList;
	}

	@Override
	public int batchUpdateSkuNum() {
		// 统计三级分类的Sku数量
		int countLevel_3 = commodityCatb2cMapper.batchUpdateSkuNumLevel_3();
		// 统计二级分类的Sku数量
		int countLevel_2 = commodityCatb2cMapper.batchUpdateSkuNumLevel_2_1();
		// 统计一级分类的Sku数量
		int countLevel_1 = commodityCatb2cMapper.batchUpdateSkuNumLevel_2_1();
		return countLevel_3 + countLevel_2 + countLevel_1;
	}

	@Override
	public HashMap<String, String> getCatIds(String no) {
		return commodityCatb2cMapper.getCatIds(no);
	}

	@Override
	public List<CommodityCatb2cEntity> getCatList(String brandNo,
			String baseCatId, int level) {
		return commodityCatb2cMapper.getCatList(brandNo, baseCatId, level);
	}
}