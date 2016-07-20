 /*
 * 版本信息
 
 * 日期 2016-04-14 14:39:16
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.seller.dao.SellerCommodityCatRelaMapper;
import com.yougou.wfx.seller.model.SellerCommodityCatRelaEntity;
import com.yougou.wfx.seller.service.ISellerCommodityCatRelaService;

/**
 * ISellerCommodityCatRelaService实现
 * @author zheng.qq
 * @Date 创建时间：2016-04-14 14:39:16
 */
@Service
public class SellerCommodityCatRelaServiceImpl extends BaseService implements ISellerCommodityCatRelaService {
	
	@Resource
	private SellerCommodityCatRelaMapper sellerCommodityCatRelaMapper;

	@Override
	public int findPageCount(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity){
		return sellerCommodityCatRelaMapper.findPageCount(sellerCommodityCatRelaEntity);
	}

	@Override
	public List<SellerCommodityCatRelaEntity> findPage(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity,RowBounds rowBounds){
		return sellerCommodityCatRelaMapper.findPage(sellerCommodityCatRelaEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		sellerCommodityCatRelaEntity.setId(strId);
		sellerCommodityCatRelaMapper.insert(sellerCommodityCatRelaEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity){
		return  sellerCommodityCatRelaMapper.update(sellerCommodityCatRelaEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return sellerCommodityCatRelaMapper.remove(id);
	}
	
	@Override
	public SellerCommodityCatRelaEntity getById(String id){
		return sellerCommodityCatRelaMapper.getById(id);
	} 
	
	@Override
	public List<SellerCommodityCatRelaEntity> queryList(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity){
		return sellerCommodityCatRelaMapper.queryList(sellerCommodityCatRelaEntity);
	}
	
	@Override
	@Transactional
	public int removeByCatId(String catId){
		return sellerCommodityCatRelaMapper.removeByCatId(catId);
	}
}