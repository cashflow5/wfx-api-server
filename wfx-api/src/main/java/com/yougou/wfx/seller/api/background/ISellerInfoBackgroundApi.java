 /*
 * 版本信息
 
 * 日期 2016-03-25 14:05:39
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.api.background;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.seller.dto.input.SellerInfoInputDto;
import com.yougou.wfx.seller.dto.input.SellerInfoPageInputDto;
import com.yougou.wfx.seller.dto.output.SellerInfoOutputDto;

/**
 * ISellerInfoBackgroundApi
 * @author luoq
 * @Date 创建时间：2016-03-25 14:05:40
 */
public interface ISellerInfoBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(SellerInfoInputDto sellerInfoDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<SellerInfoOutputDto> findPage(SellerInfoPageInputDto pageInputDto,PageModel<SellerInfoOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(SellerInfoInputDto sellerInfoDto);
	
	/**
	 * 通过id查询数据
	 */
	public SellerInfoOutputDto getById(String id);
	
	/**
	 * 分销商审核列表
	 * @param sellerInfoEntity
	 * @param rowBounds
	 * @return
	 */
	PageModel<SellerInfoOutputDto> findSellerAuditPage(SellerInfoPageInputDto pageInputDto,PageModel<SellerInfoOutputDto> pageModel);
	
	/**
	 * 根据分销商ID获取分销商详情
	 * @param sellerId
	 */
	SellerInfoOutputDto getSellerInfoById(String sellerId); 
	
	/**
	 * 分销商列表
	 * @param sellerInfoEntity
	 * @param rowBounds
	 * @return
	 */
	PageModel<SellerInfoOutputDto> findSellerPage(SellerInfoPageInputDto pageInputDto,PageModel<SellerInfoOutputDto> pageModel);
	
	/**
	 * 后台分销商审核通过
	 * @param sellerInfoDto 分销商信息
	 * @param optUser 操作人
	 * @param type audit:审核通过，cooperate:开启合作
	 * @return true：操作成功 false：操作失败
	 */
    WFXResult<Boolean>	sellerAuditPass( SellerInfoInputDto sellerInfoDto,String optUser,String type);
    
    /**
     * 后台分销商停止合作
     * @param sellerInfoDto 分销商信息
     * @param optUser 操作人
     * @return true：操作成功 false：操作失败
     */
    WFXResult<Boolean> sellerStop ( SellerInfoInputDto sellerInfoDto,String optUser);
    
    /**
     * 后台job，分销商自动审核，定时扫描满足条件的成为分销商申请，审核通过
     * @return
     */
    int sellerAutoAuditAllApply();

    /**
     * 定时扫描分销商自动代理所有上架商品
     * @return
     */
	public int sellerAutoProxyAll();

	/**
	 * 申请成为分销商
	 * @param sellerInfo
	 * @return
	 */
	WFXResult<Boolean> applyToSeller(SellerInfoInputDto sellerInfo);

	SellerInfoOutputDto getSellerByMemberId(String id);

}

