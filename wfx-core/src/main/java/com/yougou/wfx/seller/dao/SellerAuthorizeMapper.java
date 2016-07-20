 /*
 * 版本信息
 
 * 日期 2016-04-15 16:09:24
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.seller.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.seller.dto.input.SellerAuthorizeInputDto;
import com.yougou.wfx.seller.dto.output.SellerAuthorizeOutputDto;
import com.yougou.wfx.seller.model.SellerAuthorizeEntity;

/**
 * SellerAuthorizeMapper
 * @author zheng.qq
 * @Date 创建时间：2016-04-15 16:09:26
 */
public interface SellerAuthorizeMapper{
	
	int findPageCount(SellerAuthorizeEntity sellerAuthorizeEntity);

	List<SellerAuthorizeEntity> findPage(SellerAuthorizeEntity sellerAuthorizeEntity,RowBounds rowBounds);
	
	int insert(SellerAuthorizeEntity sellerAuthorizeEntity);
	
	int update(SellerAuthorizeEntity sellerAuthorizeEntity);
	
	int remove(String id);
	
	SellerAuthorizeEntity getById(String id);
	
	/**
	 * 获取分销商资质总条数
	 */
	int findSellerAuthorizeCount(SellerAuthorizeInputDto sellerAuthorizeInputDto);

	/**
	 * 获取分销商资质分页数据(包含分销商基本信息)
	 */
	List<SellerAuthorizeOutputDto> findSellerAuthorizePage(SellerAuthorizeInputDto sellerAuthorizeInputDto,RowBounds rowBounds);

	SellerAuthorizeEntity getBySellerId(String sellerId);
	/* 根据商家ID获取资质是否审核通过 */
	Integer getSellerAuthorizeStatus(String sellerId);

}
