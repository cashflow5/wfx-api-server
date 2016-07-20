/*
 * 版本信息

 * 日期 2016-03-25 14:05:39

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.api.front;

import java.util.List;
import java.util.Map;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.seller.dto.input.SellerAuthorizeInputDto;
import com.yougou.wfx.seller.dto.input.SellerBankInputDto;
import com.yougou.wfx.seller.dto.input.SellerInfoInputDto;
import com.yougou.wfx.seller.dto.output.SellerAuthorizeOutputDto;
import com.yougou.wfx.seller.dto.output.SellerBankOutputDto;
import com.yougou.wfx.seller.dto.output.SellerInfoOutputDto;

/**
 * ISellerInfoFrontApi
 * 
 * @author luoq
 * @Date 创建时间：2016-03-25 14:05:40
 */
public interface ISellerInfoFrontApi {

	/**
	 * 通过id查询数据
	 */
	SellerInfoOutputDto getSellerInfoById(String id);

	/**
	 * 今日访客数量统计、7日订单数量统计接口 (B端)
	 * 
	 * @param sellerId
	 *            分销商ID
	 * @return key:"todayvistorSum" 今日访客数量;key:"7DaysOrderSum"7日订单数量
	 */
	Map<String, Integer> getTwoItemSum(String sellerId);

	/**
	 * 银行卡账号管理-所有银行信息(B端)
	 * 
	 * @return key:银行序号，value：银行名称
	 */
	Map<Integer, String> getAllBankList();

	/**
	 * 银行卡账户管理接口-----------------------------------------
	 */
	/**
	 * 银行卡账户、资质信息保存
	 * 
	 * @param 银行账户信息对象
	 *            ;sellerId 必输
	 * @return 银行账户ID
	 */
	WFXResult<String> insertSellerBank(SellerBankInputDto sellerBankDto,
			SellerAuthorizeInputDto sellerAuthorizeInputDto);

	/**
	 * 银行卡账户、资质信息更新
	 * 
	 * @param 银行账户信息对象
	 *            :sellerId 必输；
	 * @return 更新是否成功
	 */
	WFXResult<Boolean> updateSellerBank(SellerBankInputDto sellerBankDto,
			SellerAuthorizeInputDto sellerAuthorizeInputDto);

	/**
	 * 通过分销商id查询银行卡账户、资质信息数据
	 * 
	 * @param 分销商ID
	 * @return 银行账户信息（包括资质信息）
	 */
	SellerBankOutputDto getSellerBank(String sellerId);

	/**
	 * 下级分销商列表接口(包括下级分销商给我带来佣金总额)
	 * 
	 * @param sellerId
	 *            分销商ID，pageModel 分页信息
	 * @return 下级分销商列表（包括下级分销商详情，可以得到下级分销商带给上级的收益）
	 */
	PageModel<SellerInfoOutputDto> getSubSellerListById(String sellerId,
			PageModel pageModel);

	/**
	 * 申请成为分销商：新增分销商，新增店铺
	 * 
	 * @param sellerInfo
	 *            分销商信息（含店铺）
	 * @return WFXResult{ result:(true:提交成功 false:提交失败),
	 *         resultCode:(0：重复申请,提交失败；
	 *         1：开店申请已成功提交等待审核；2：开店申请已提交成功并审核通过；3：提交失败，系统异常), resultMsg:提示信息}
	 * @throws Exception
	 * @author zhang.f
	 */
	WFXResult<Boolean> applyToSeller(SellerInfoInputDto sellerInfo);

	/**
	 * 根据分销商ID ，获取分销商状态（B端使用）
	 * 
	 * @param memberId
	 *            用户ID
	 * @return result:1 停止合作 ，2：店铺关闭 ，3：正常
	 */
	WFXResult<Integer> getSellerStatusByMemberId(String memberId);

	/**
	 * 根据店铺ID ，获取分销商状态（C端使用）
	 * 
	 * @param shopId
	 *            店铺ID
	 * @return result:1 停止合作 ，2：店铺关闭 ，3：正常
	 */
	WFXResult<Integer> getSellerStatusByShopId(String shopId);

	/**
	 * 根据商家ID获取资质是否审核通过接口
	 * 
	 * @param sellerId
	 *            商家ID
	 * @return WFXResult{ resultCode - (200,"成功"),(500,"失败")}
	 */
	WFXResult<Boolean> isSellerAuthorizePass(String sellerId);

	/**
	 * 根据用户ID获取经销商信息（例如状态） 状态枚举类：SellerStateEnum
	 */
	SellerInfoOutputDto getSellerByMemberId(String memberId);

	/**
	 * 获取分销商的一级分销商数目
	 * 
	 * @param id
	 * @return
	 */
	Integer getLevelTwoSellerNum(String id);

	/**
	 * 获取分销商的二级分销商数目
	 * 
	 * @param id
	 * @return
	 */
	Integer getLevelThreeSellerNum(String id);

	/**
	 * 一级分销商列表接口(包括下级分销商给我带来佣金总额)
	 * 
	 * @param sellerId
	 *            分销商ID，pageModel 分页信息
	 * @return 下级分销商列表（包括下级分销商详情，可以得到下级分销商带给上级的收益）
	 */
	PageModel<SellerInfoOutputDto> getLevelTwoSellerListById(String sellerId,
			PageModel pageModel);

	/**
	 * 二级分销商列表接口(包括下级分销商给我带来佣金总额)
	 * 
	 * @param sellerId
	 *            分销商ID，pageModel 分页信息
	 * @return 下级分销商列表（包括下级分销商详情，可以得到下级分销商带给上级的收益）
	 */
	PageModel<SellerInfoOutputDto> getLevelThreeSellerListById(String sellerId,
			PageModel pageModel);

	Integer getFansCountBySellerId(String sellerId);

	/* 根据分销商ID获取下级分销商信息(不包括佣金)接口 */
	PageModel<SellerInfoOutputDto> getSubSellerListSimple(String sellerId,
			PageModel pageModel);

	/**
	 * 根据sellerID查询资质审核记录状态
	 * 
	 * @param sellerId
	 * @return
	 */
	Integer getSellerAuthorizeStatus(String sellerId);
}
