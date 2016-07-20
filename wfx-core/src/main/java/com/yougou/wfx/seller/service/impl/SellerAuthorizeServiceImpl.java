 /*
 * 版本信息
 
 * 日期 2016-04-15 16:09:24
 
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
import com.yougou.wfx.seller.dao.SellerAuthorizeMapper;
import com.yougou.wfx.seller.dto.input.SellerAuthorizeInputDto;
import com.yougou.wfx.seller.dto.output.SellerAuthorizeOutputDto;
import com.yougou.wfx.seller.model.SellerAuthorizeEntity;
import com.yougou.wfx.seller.service.ISellerAuthorizeService;

/**
 * ISellerAuthorizeService实现
 * @author zheng.qq
 * @Date 创建时间：2016-04-15 16:09:26
 */
@Service
public class SellerAuthorizeServiceImpl extends BaseService implements ISellerAuthorizeService {
	
	@Resource
	private SellerAuthorizeMapper sellerAuthorizeMapper;

	@Override
	public int findPageCount(SellerAuthorizeEntity sellerAuthorizeEntity){
		return sellerAuthorizeMapper.findPageCount(sellerAuthorizeEntity);
	}

	@Override
	public List<SellerAuthorizeEntity> findPage(SellerAuthorizeEntity sellerAuthorizeEntity,RowBounds rowBounds){
		return sellerAuthorizeMapper.findPage(sellerAuthorizeEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(SellerAuthorizeEntity sellerAuthorizeEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		sellerAuthorizeEntity.setId(strId);
		sellerAuthorizeMapper.insert(sellerAuthorizeEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(SellerAuthorizeEntity sellerAuthorizeEntity){
		return  sellerAuthorizeMapper.update(sellerAuthorizeEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return sellerAuthorizeMapper.remove(id);
	}
	
	@Override
	public SellerAuthorizeEntity getById(String id){
		return sellerAuthorizeMapper.getById(id);
	}

	@Override
	public int findSellerAuthorizeCount(
			SellerAuthorizeInputDto sellerAuthorizeInputDto) {
		return sellerAuthorizeMapper.findSellerAuthorizeCount(sellerAuthorizeInputDto);
	}

	@Override
	public List<SellerAuthorizeOutputDto> findSellerAuthorizePage(
			SellerAuthorizeInputDto sellerAuthorizeInputDto, RowBounds rowBounds) {
		return sellerAuthorizeMapper.findSellerAuthorizePage(sellerAuthorizeInputDto,rowBounds);
	}

	@Override
	public SellerAuthorizeEntity getBySellerId(String sellerId) {
		return sellerAuthorizeMapper.getBySellerId(sellerId);
	}

	@Override
	public Integer getSellerAuthorizeStatus(String sellerId) {
		return sellerAuthorizeMapper.getSellerAuthorizeStatus(sellerId);
	}

}