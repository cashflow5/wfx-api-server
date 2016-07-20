 /*
 * 版本信息
 
 * 日期 2016-04-15 16:09:24
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.api.background;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.seller.dto.input.SellerAuthorizeInputDto;
import com.yougou.wfx.seller.dto.output.SellerAuthorizeOutputDto;

/**
 * ISellerAuthorizeBackgroundApi
 * @author zheng.qq
 * @Date 创建时间：2016-04-15 16:09:26
 */
public interface ISellerAuthorizeBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(SellerAuthorizeInputDto sellerAuthorizeDto);
	
	/**
	 * 更新分销商资质信息
	 */
	public int update(SellerAuthorizeInputDto sellerAuthorizeDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<SellerAuthorizeOutputDto> findPage(SellerAuthorizeInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 通过id查询数据
	 */
	public SellerAuthorizeOutputDto getById(String id);
	
	
	/**
	 * 获取分页数据(关联分销商信息表查询)
	 */
	public PageModel<SellerAuthorizeOutputDto> findSellerAuthorizePage(SellerAuthorizeInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 通过sellerId查询数据
	 */
	public List<SellerAuthorizeOutputDto> getSellerAuthorizeBySellerId(String sellerId);
	
	/**
	 * 审核分销商资质
	 */
	public boolean updateSellerAuthorizeStatusById(String id, int status);
	
}

