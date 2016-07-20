 /*
 * 版本信息
 
 * 日期 2016-03-24 15:23:34
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.member.model.MemberAccountEntity;
import com.yougou.wfx.order.dto.output.AfterPayedCallBackDto;
import com.yougou.wfx.seller.dto.input.SellerInfoInputDto;
import com.yougou.wfx.seller.model.SellerInfoEntity;

/**
 * ISellerInfoService接口
 * @author zhangfeng
 * @Date 创建时间：2016-03-24 15:23:34
 */
public interface ISellerInfoService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(SellerInfoEntity sellerInfoEntity);

	/**
	 * 获取分页数据
	 */
	public List<SellerInfoEntity> findPage(SellerInfoEntity sellerInfoEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(SellerInfoEntity sellerInfoEntity);
	
	/**
	 * 更新记录
	 */
	public int update(SellerInfoEntity sellerInfoEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public SellerInfoEntity getById(String id); 
	
	/**
	 * 分销商审核列表
	 * @param sellerInfoEntity
	 * @param rowBounds
	 * @return
	 */
	List<SellerInfoEntity> findSellerAuditPage(SellerInfoEntity sellerInfoEntity,RowBounds rowBounds);
	
	/**
	 * 分销商审核列表总数
	 * @param sellerInfoEntity
	 * @return
	 */
	int findSellerAuditCount(SellerInfoEntity sellerInfoEntity);
	
	/**
	 * 获取分销商的二级(下级)分销商
	 * @param id
	 * @return
	 */
	List<SellerInfoEntity> getLevelTwoSeller(String id);
	
	/**
	 * 获取分销商的三级(下下级)分销商
	 * @param id
	 * @return
	 */
	List<SellerInfoEntity> getLevelThreeSeller(String id);
	
	/**
	 * 获取分销商的所有级（自己、二级、三级）分销商
	 * @param id
	 * @return
	 */
	List<SellerInfoEntity> getAllLevelSeller(String id);
	
	/**
	 * 根据分销商ID获取分销商详情
	 * @param sellerId
	 * @return
	 */
	SellerInfoEntity getSellerInfoById(String sellerId);
	
	/**
	 * 分销商列表
	 * @param sellerInfoEntity
	 * @param rowBounds
	 * @return
	 */
	List<SellerInfoEntity> findSellerPage(SellerInfoEntity sellerInfoEntity,RowBounds rowBounds);
	
	/**
	 * 后台分销商审核通过
	 * @param sellerInfoDto
	 * @param optUser
	 * @param type audit:审核通过，cooperate:开启合作
	 * @throws Exception
	 */
	void sellerAuditPass(SellerInfoInputDto sellerInfoDto,String optUser,String type) throws Exception;
	
	/**
	 * 后台分销商停止合作
	 * @param sellerInfoDto
	 * @param optUser
	 * @throws Exception
	 */
	void sellerStop(SellerInfoInputDto sellerInfoDto,String optUser) throws Exception;
	
	/**
	 * 分销商列表总数
	 * @param sellerInfoEntity
	 * @return
	 */
	int findSellerCount(SellerInfoEntity sellerInfoEntity);
	
	/**
	 * 不分页分销商列表
	 * @param sellerInfoEntity
	 * @return
	 */
	List<SellerInfoEntity> queryList(SellerInfoEntity sellerInfoEntity);
	
	/**
	 * 申请成为分销商，自动审核
	 * @param memberId 用户ID
	 * @param loginName 登陆账号
	 * @param sellerId 分销商ID
	 * @param shopId 店铺ID
	 * @return WFXResult{ result:(true:审核通过 false:审核不通过),
	 * 					  resultCode:(1：开关关闭，审核不通过；2：没有成功交易订单，审核不通过；3：审核通过;4:系统异常，审核不通过)}
	 */
	//WFXResult<Boolean > sellerAutoAudit(String memberId,String loginName,String sellerId,String shopId) throws Exception;
	
	/**
	 * 根据用户ID获取分销商和店铺状态
	 * @param memberId
	 * @return
	 */
	Map<String,Object> getSellerAndShopStatusByMemberId(String memberId);
	
	/**
	 * 根据用户ID获取分销商信息
	 * @param memberId
	 * @return
	 */
	SellerInfoEntity getSellerByMemberId(String memberId);
	
	/**
	 * 申请成为分销商
	 * @param sellerInfoEntity
	 * @return resultCode:1:提交成功，待审核；2：提交并审核通过
	 * @throws Exception
	 */
	WFXResult<Boolean> applyToSeller(SellerInfoEntity sellerInfoEntity) throws Exception;
	
	/**
	 * 获取下级分销商列表
	 * @param sellerId
	 * @param rowBounds
	 * @return
	 */
	List<SellerInfoEntity>  getSubSellerList(String sellerId,RowBounds rowBounds);
	
	/**
	 * 获取下级分销商总数
	 * @param sellerId
	 * @return
	 */
	int getSubSellerCount(String sellerId);
	
	/**
     * 后台job，分销商自动审核，定时扫描满足条件的成为分销商申请，审核通过
     * @return
     */
    int sellerAutoAuditAllApply()  throws Exception;
    
    /**
     * 根据店铺ID 获取分销商及店铺状态
     * @param shopId
     * @return
     */
    Map<String, Object> getSellerAndShopStatusByShopId(String shopId);

    /* 支付成功回调接口，自动升级优粉 */
	void autoBecomeSeller(WFXResult<AfterPayedCallBackDto> result,
			MemberAccountEntity member, String wfxOrderNo);
//
//	 /**
//     * 分销商代理所有上架商品
//     * @return
//     */
//	public int sellerAutoProxyAll();

	/**
	 * 后台升级成为分销商
	 * @param sellerInfoEntity
	 * @return
	 * @throws Exception
	 */
	WFXResult<Boolean> applyToSellerBackgound(SellerInfoEntity sellerInfoEntity)
			throws Exception;
	/**
	 * 获取分销商的一级分销商数目
	 * @param id
	 * @return
	 */
	public Integer getLevelTwoSellerNum(String sellerId);
	/**
	 * 获取分销商的二级分销商数目
	 * @param id
	 * @return
	 */
	public Integer getLevelThreeSellerNum(String sellerId);
	/**
	 * 一级分销商列表接口(包括下级分销商给我带来佣金总额)
	 * @param sellerId 分销商ID，pageModel 分页信息
	 * @return 下级分销商列表（包括下级分销商详情，可以得到下级分销商带给上级的收益）
	 */
	public List<SellerInfoEntity> getLevelTwoSellerListById(
			String sellerId, RowBounds rowBounds);
	/**
	 * 二级分销商列表接口(包括下级分销商给我带来佣金总额)
	 * @param sellerId 分销商ID，pageModel 分页信息
	 * @return 下级分销商列表（包括下级分销商详情，可以得到下级分销商带给上级的收益）
	 */
	public List<SellerInfoEntity> getLevelThreeSellerListById(
			String sellerId, RowBounds rowBounds);

	public Map<String, String> queryIfAutoTransfer(String memberId,
			String wfxOrderNo);
	/* 查粉丝数目 */
	public Integer getFansCountBySellerId(String sellerId);

	public List<SellerInfoEntity> getSubSellerListSimple(String sellerId,
			RowBounds rowBounds);
	
}