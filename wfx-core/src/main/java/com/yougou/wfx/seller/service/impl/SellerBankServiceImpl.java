 /*
 * 版本信息
 
 * 日期 2016-04-16 16:08:23
 
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
import com.yougou.wfx.seller.dao.SellerBankMapper;
import com.yougou.wfx.seller.model.SellerBankEntity;
import com.yougou.wfx.seller.service.ISellerBankService;

/**
 * ISellerBankService实现
 * @author luoq
 * @Date 创建时间：2016-04-16 16:08:24
 */
@Service
public class SellerBankServiceImpl extends BaseService implements ISellerBankService {
	
	@Resource
	private SellerBankMapper sellerBankMapper;

	@Override
	public int findPageCount(SellerBankEntity sellerBankEntity){
		return sellerBankMapper.findPageCount(sellerBankEntity);
	}

	@Override
	public List<SellerBankEntity> findPage(SellerBankEntity sellerBankEntity,RowBounds rowBounds){
		return sellerBankMapper.findPage(sellerBankEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(SellerBankEntity sellerBankEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		sellerBankEntity.setId(strId);
		sellerBankMapper.insert(sellerBankEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(SellerBankEntity sellerBankEntity){
		return  sellerBankMapper.update(sellerBankEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return sellerBankMapper.remove(id);
	}
	
	@Override
	public SellerBankEntity getById(String id){
		return sellerBankMapper.getById(id);
	}

	@Override
	public List<SellerBankEntity> getSellerBanks(String sellerId) {
		return sellerBankMapper.getSellerBanks(sellerId);
	} 
}