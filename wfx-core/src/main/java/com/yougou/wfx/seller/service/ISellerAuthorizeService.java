 /*
 * 版本信息
 
 * 日期 2016-04-15 16:09:24
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.seller.dto.input.SellerAuthorizeInputDto;
import com.yougou.wfx.seller.dto.output.SellerAuthorizeOutputDto;
import com.yougou.wfx.seller.model.SellerAuthorizeEntity;

/**
 * ISellerAuthorizeService接口
 * @author zheng.qq
 * @Date 创建时间：2016-04-15 16:09:26
 */
public interface ISellerAuthorizeService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(SellerAuthorizeEntity sellerAuthorizeEntity);

	/**
	 * 获取分页数据
	 */
	public List<SellerAuthorizeEntity> findPage(SellerAuthorizeEntity sellerAuthorizeEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(SellerAuthorizeEntity sellerAuthorizeEntity);
	
	/**
	 * 更新记录
	 */
	public int update(SellerAuthorizeEntity sellerAuthorizeEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public SellerAuthorizeEntity getById(String id); 
	
	/**
	 * 获取分销商资质总条数
	 */
	public int findSellerAuthorizeCount(SellerAuthorizeInputDto sellerAuthorizeInputDto);

	/**
	 * 获取分销商资质分页数据
	 */
	public List<SellerAuthorizeOutputDto> findSellerAuthorizePage(SellerAuthorizeInputDto sellerAuthorizeInputDto,RowBounds rowBounds);

	/**
	 * 根据分销商ID获取分销商资质信息
	 * @param sellerId
	 * @return
	 */
	public SellerAuthorizeEntity getBySellerId(String sellerId);

	/**
	 * 根据商家ID获取资质是否审核通过
	 */
	public Integer getSellerAuthorizeStatus(String sellerId);
	
}