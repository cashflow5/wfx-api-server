 /*
 * 版本信息
 
 * 日期 2016-03-24 15:23:34
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.commodity.service.ISellerCommodityService;
import com.yougou.wfx.common.constant.Constant;
import com.yougou.wfx.common.constant.MessageConstant;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.MemberApplySellerTypeEnum;
import com.yougou.wfx.enums.MemberTypeEnum;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.enums.SellerStateEnum;
import com.yougou.wfx.enums.ShopStatusEnum;
import com.yougou.wfx.exception.WFXParamsCheckException;
import com.yougou.wfx.finance.api.front.IFinSellerInfoFrontApi;
import com.yougou.wfx.finance.dto.input.FinSellerInfoInputDto;
import com.yougou.wfx.framework.annotation.cache.CacheCable;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.cache.redis.WFXRedisUtil;
import com.yougou.wfx.framework.cache.redis.WFXRedisUtil.WFXModuleType;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.member.dao.MemberAccountMapper;
import com.yougou.wfx.member.dao.MemberProfileMapper;
import com.yougou.wfx.member.model.MemberAccountEntity;
import com.yougou.wfx.member.model.MemberProfileEntity;
import com.yougou.wfx.order.dto.output.AfterPayedCallBackDto;
import com.yougou.wfx.order.service.IOrderService;
import com.yougou.wfx.proxy.messenger.SmsProxyApi;
import com.yougou.wfx.seller.dao.SellerInfoMapper;
import com.yougou.wfx.seller.dto.input.SellerInfoInputDto;
import com.yougou.wfx.seller.model.SellerInfoEntity;
import com.yougou.wfx.seller.service.ISellerInfoService;
import com.yougou.wfx.shop.dto.input.ShopInputDto;
import com.yougou.wfx.shop.model.ShopEntity;
import com.yougou.wfx.shop.service.IShopService;
import com.yougou.wfx.system.service.WFXSystemService;
import com.yougou.wfx.util.ApiConstant;
import com.yougou.wfx.utils.BizNoGen;

/**
 * ISellerInfoService实现
 * @author zhangfeng
 * @Date 创建时间：2016-03-24 15:23:34
 */
@Service
public class SellerInfoServiceImpl extends BaseService implements ISellerInfoService {
	
	@Resource
	private SellerInfoMapper sellerInfoMapper;
	@Resource
	WFXSystemService wfxSystemService;
	@Resource
	IOrderService orderService;
	@Resource
	IShopService shopService;
	@Resource
	MemberAccountMapper memberAccountMapper;
	
	@Resource
	IFinSellerInfoFrontApi  finSellerInfoFrontApi;
	@Resource
	MemberProfileMapper memberProfileMapper;
	@Resource
	ApplySellerAuditService applySellerAuditService;
	@Resource
	ISellerCommodityService sellerCommodityService;
	
	private Logger logger = LoggerFactory.getLogger(SellerInfoServiceImpl.class);
	

	@Override
	public int findPageCount(SellerInfoEntity sellerInfoEntity){
		return sellerInfoMapper.findPageCount(sellerInfoEntity);
	}

	@Override
	public List<SellerInfoEntity> findPage(SellerInfoEntity sellerInfoEntity,RowBounds rowBounds){
		return sellerInfoMapper.findPage(sellerInfoEntity,rowBounds);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String insert(SellerInfoEntity sellerInfoEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		sellerInfoEntity.setId(strId);
		sellerInfoMapper.insert(sellerInfoEntity);
		return strId;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int update(SellerInfoEntity sellerInfoEntity){
		return  sellerInfoMapper.update(sellerInfoEntity);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int remove(String id){
		return sellerInfoMapper.remove(id);
	}
	
	@Override
	public SellerInfoEntity getById(String id){
		SellerInfoEntity seller = sellerInfoMapper.getById(id);
		if(null != seller){
			seller.setSellerLevel(1);
		}
		return seller;
	}

	@Override
	public List<SellerInfoEntity> findSellerAuditPage(SellerInfoEntity sellerInfoEntity, RowBounds rowBounds) {
		// TODO Auto-generated method stub
		return sellerInfoMapper.findSellerAuditPage(sellerInfoEntity,rowBounds);
	}

	@Override
	public int findSellerAuditCount(SellerInfoEntity sellerInfoEntity) {
		// TODO Auto-generated method stub
		return sellerInfoMapper.findSellerAuditCount(sellerInfoEntity);
	}

	@Override
	public SellerInfoEntity getSellerInfoById(String sellerId) {
		// TODO Auto-generated method stub
		return sellerInfoMapper.getSellerInfoById(sellerId);
	}

	@Override
	public List<SellerInfoEntity> findSellerPage(SellerInfoEntity sellerInfoEntity,RowBounds rowBounds) {
		// TODO Auto-generated method stub
		return sellerInfoMapper.findSellerPage(sellerInfoEntity, rowBounds);
	}

	@Override
	public int findSellerCount(SellerInfoEntity sellerInfoEntity) {
		// TODO Auto-generated method stub
		return sellerInfoMapper.findSellerCount(sellerInfoEntity);
	}

	@Override
	public List<SellerInfoEntity> queryList(SellerInfoEntity sellerInfoEntity) {
		return sellerInfoMapper.queryList(sellerInfoEntity);
	}

	

	@CacheCable(key="'wfx:sellerAndShopStatusByMember:'+#memberId" ,value="value",expiration=24*60*60)
	@Override
	public Map<String, Object> getSellerAndShopStatusByMemberId(String memberId) {
		// TODO Auto-generated method stub
		return sellerInfoMapper.getSellerAndShopStatusByMemberId(memberId);
	}

	@Override
	public SellerInfoEntity getSellerByMemberId(String memberId) {
		// TODO Auto-generated method stub
		return sellerInfoMapper.getSellerByMemberId(memberId);
	}
	
	/* 申请开店调用-旧逻辑*/
	@Transactional(rollbackFor=Exception.class)
	@Override
	public WFXResult<Boolean> applyToSeller(SellerInfoEntity sellerInfoEntity) throws Exception {
		// TODO Auto-generated method stub
		WFXResult<Boolean> applyResult = new WFXResult<Boolean>();
		
		String sellerId = null;
		String shopId = null;
		try{
			if(sellerInfoEntity == null){
				throw new WFXParamsCheckException("申请开店时分销商信息为空");
			}
			ShopInputDto shopDto = sellerInfoEntity.getShopInputDto();
			if(shopDto == null){
				throw new WFXParamsCheckException(sellerInfoEntity.getLoginName()+"申请开店时店铺信息为空");
			}
			
			Map<String,Object> map = this.getSellerAndShopStatusByMemberId(sellerInfoEntity.getLoginaccountId());
			if(null != map && map.size() >0 ){
				Integer sellerStatus = (Integer) map.get("sellerStatus");
				//已经成为分销商
				if(null != sellerStatus && !String.valueOf(sellerStatus).equals(SellerStateEnum.AUDIT_NOT_PASS.getState()) ){
					applyResult.setResult(false);
					applyResult.setResultCode(0);
					applyResult.setResultMsg("已经申请成为分销商，不能重复申请");
					return applyResult;
				}
			}
			
			Date curDay = new Date();
			
			//保存分销商信息
			//SellerInfoEntity sellerInfoEntity = dtoToEntity(sellerInfo);			
			sellerInfoEntity.setState(SellerStateEnum.AUDITING.getState());//待审核
			
			//保存店铺信息
			ShopEntity shopEntity = (ShopEntity)BeanUtil.convertBean(shopDto,ShopEntity.class);			
			shopEntity.setUpdateTime(curDay);			
			shopEntity.setStatus(ShopStatusEnum.CLOSE_SHOP.getStatus()); //关闭
			shopEntity.setUpdateUser(shopEntity.getLoginName());
			// 设置默认店铺LOGO
			if(StringUtils.isBlank(shopEntity.getLogoUrl())){
				shopEntity.setLogoUrl(Constant.WFX_SHOP_LOGO_DEFAULT_PIC_URL);
			}
			//  设置默认店招
			if(StringUtils.isBlank(shopEntity.getSignUrl())){
				shopEntity.setSignUrl(Constant.WFX_SHOP_SIGN_DEFAULT_PIC_URL);
			}
			
			//保存分销商财务账户信息
			FinSellerInfoInputDto finSellerInfoDto = new FinSellerInfoInputDto();			
			finSellerInfoDto.setSellerAccount(sellerInfoEntity.getLoginName());						
			finSellerInfoDto.setLatelyTransactionTime(curDay);			
			
			//信息不存在，新增
			if(null == map){
				sellerInfoEntity.setCreateTime(curDay);
				sellerId = this.insert(sellerInfoEntity);	
				
				String shopCode = BizNoGen.getShopCode();
				shopEntity.setShopCode(shopCode);
				shopEntity.setName(shopCode); //默认存店铺编码
				shopEntity.setSellerId(sellerId);
				shopEntity.setCreateTime(curDay);
				shopId = shopService.insert(shopEntity);
				
				finSellerInfoDto.setId(sellerId);
				finSellerInfoDto.setShopName(shopCode);
				finSellerInfoDto.setShopCode(shopCode);
				finSellerInfoDto.setRegisterTime(curDay);
				finSellerInfoFrontApi.createSeller(finSellerInfoDto);
				
			//已经申请过，修改	
			}else{
				sellerId = (String) map.get("sellerId");
				sellerInfoEntity.setId(sellerId);
				this.update(sellerInfoEntity);
			
				shopId = (String) map.get("shopId");
				shopEntity.setId(shopId);
				shopEntity.setSellerId(sellerId);
				shopService.update(shopEntity);
				finSellerInfoDto.setId(sellerId);
				finSellerInfoFrontApi.updateSeller(finSellerInfoDto);
			}
			//修改用户基本信息表是否申请分销商状态
			MemberProfileEntity profile = new MemberProfileEntity();
			profile.setApplySeller(MemberApplySellerTypeEnum.ALREADY_APPLY.getKey());
			profile.setApplySellerTime(curDay);
			profile.setLoginaccountId(sellerInfoEntity.getLoginaccountId());
			memberProfileMapper.update(profile);

		}catch(Exception e){
			//logger.error("{}申请成为分销商,提交失败：{}",sellerInfoEntity.getLoginName(), e);
			logger.error(sellerInfoEntity.getLoginName()+"申请成为分销商,提交失败：", e);
			throw new Exception("申请开店信息保存失败");
		}
		
		try{
			//自动审核是否成为分销商
			WFXResult<Boolean> result = applySellerAuditService.sellerAutoAudit(sellerInfoEntity.getLoginaccountId(),
					sellerInfoEntity.getLoginName(),sellerId, shopId);
			logger.info("{}申请成为分销商，自动审核结果：{}", sellerInfoEntity.getLoginName(),result);
			Boolean auditResult = result.getResult();
			if(auditResult){
				applyResult.setResult(true);
				applyResult.setResultCode(2);
				applyResult.setResultMsg("开店申请已提交成功并审核通过");				
			}else{
				applyResult.setResult(true);
				applyResult.setResultCode(1);
				applyResult.setResultMsg("开店申请已成功提交等待审核");
			}
			return applyResult;
		}catch(Exception e){
			logger.error("申请开店后自动审核异常：", e);
			applyResult.setResult(true);
			applyResult.setResultCode(1);
			applyResult.setResultMsg("开店申请已成功提交等待审核");
		}
		return applyResult;
		
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public WFXResult<Boolean> applyToSellerBackgound(SellerInfoEntity sellerInfoEntity) throws Exception {
		WFXResult<Boolean> applyResult = new WFXResult<Boolean>();
		
		String sellerId = null;
		String shopId = null;
		try{
			ShopInputDto shopDto = sellerInfoEntity.getShopInputDto();
			
			Map<String,Object> map = this.getSellerAndShopStatusByMemberId(sellerInfoEntity.getLoginaccountId());
			if(null != map && map.size() >0 ){
				Integer sellerStatus = (Integer) map.get("sellerStatus");
				//已经成为分销商
				if(null != sellerStatus && !String.valueOf(sellerStatus).equals(SellerStateEnum.AUDIT_NOT_PASS.getState()) ){
					applyResult.setResult(false);
					applyResult.setResultCode(0);
					applyResult.setResultMsg("已经申请成为分销商，不能重复申请");
					return applyResult;
				}
			}
			
			Date curDay = new Date();
			
			//保存分销商信息
			sellerInfoEntity.setState(SellerStateEnum.IN_COOPERATING.getState());
			sellerInfoEntity.setPassDate(curDay);
			sellerInfoEntity.setPassSysuser(sellerInfoEntity.getLastActiveOptuser());
			sellerInfoEntity.setLastActiveDate(curDay);
			sellerInfoEntity.setLastActiveOptuser(sellerInfoEntity.getLastActiveOptuser());
			
			//保存店铺信息
			ShopEntity shopEntity = (ShopEntity)BeanUtil.convertBean(shopDto,ShopEntity.class);			
			shopEntity.setUpdateTime(curDay);			
			shopEntity.setUpdateUser(sellerInfoEntity.getLastActiveOptuser());
			shopEntity.setStatus(ShopStatusEnum.OPEN_SHOP.getStatus());
			// 设置默认店铺LOGO
			if(StringUtils.isBlank(shopEntity.getLogoUrl())){
				shopEntity.setLogoUrl(Constant.WFX_SHOP_LOGO_DEFAULT_PIC_URL);
			}
			//  设置默认店招
			if(StringUtils.isBlank(shopEntity.getSignUrl())){
				shopEntity.setSignUrl(Constant.WFX_SHOP_SIGN_DEFAULT_PIC_URL);
			}
			
			//保存分销商财务账户信息
			FinSellerInfoInputDto finSellerInfoDto = new FinSellerInfoInputDto();			
			finSellerInfoDto.setSellerAccount(sellerInfoEntity.getLoginName());						
			finSellerInfoDto.setLatelyTransactionTime(curDay);			
			//设置上级分销商Id
			MemberAccountEntity memberAccount = memberAccountMapper.getById(sellerInfoEntity.getLoginaccountId());
			if(memberAccount!=null && StringUtils.isNotBlank(memberAccount.getParentSellerId())){
				sellerInfoEntity.setParentId(memberAccount.getParentSellerId());
			}else{
				sellerInfoEntity.setParentId(ApiConstant.ORIGINAL_SELLER_ID_DEFAULT);
			}
			//信息不存在，新增
			if(null == map){
				sellerInfoEntity.setCreateTime(curDay);
				sellerId = this.insert(sellerInfoEntity);	
				
				String shopCode = BizNoGen.getShopCode();
				shopEntity.setShopCode(shopCode);
				shopEntity.setName(shopCode); //默认存店铺编码
				shopEntity.setSellerId(sellerId);
				shopEntity.setCreateTime(curDay);
				shopId = shopService.insert(shopEntity);
				
				finSellerInfoDto.setId(sellerId);
				finSellerInfoDto.setShopName(shopCode);
				finSellerInfoDto.setShopCode(shopCode);
				finSellerInfoDto.setRegisterTime(curDay);
				finSellerInfoFrontApi.createSeller(finSellerInfoDto);
				
				//已经申请过，修改	
			}else{
				sellerId = (String) map.get("sellerId");
				sellerInfoEntity.setId(sellerId);
				this.update(sellerInfoEntity);
				
				shopId = (String) map.get("shopId");
				shopEntity.setId(shopId);
				shopEntity.setSellerId(sellerId);
				shopService.update(shopEntity);
				finSellerInfoDto.setId(sellerId);
				finSellerInfoFrontApi.updateSeller(finSellerInfoDto);
			}
			//修改用户基本信息表是否申请分销商状态
			MemberProfileEntity profile = new MemberProfileEntity();
			profile.setApplySeller(MemberApplySellerTypeEnum.ALREADY_APPLY.getKey());
			profile.setApplySellerTime(curDay);
			profile.setLoginaccountId(sellerInfoEntity.getLoginaccountId());
			memberProfileMapper.update(profile);
			
			//会员：成为分销商
			MemberAccountEntity memberAccountEntity = new MemberAccountEntity();
			memberAccountEntity.setId(sellerInfoEntity.getLoginaccountId());
			memberAccountEntity.setMemberType(MemberTypeEnum.SELLER_MEMBER.getKey());
			memberAccountMapper.update(memberAccountEntity);
			
			// 立即发送短信提醒
			try{////
				SmsProxyApi.sendNow(new String[] { sellerInfoEntity.getLoginName() }, MessageConstant.APPLY_TO_SELLER_MESSAGE, "apply_to_seller_success");
			}catch(Exception e){
				logger.error("会员：{}发送升级为优粉的短信出错 {}",sellerInfoEntity.getId(),e);
			}
			// TODO 记录到日志
			
//			// 自动代理所有上架商品
//			final SellerInfoEntity seller = sellerInfoEntity;
//			final 
//			Runnable run = new Runnable(){
//				@Override
//				public void run() {
//					logger.warn("线程{},开始为优粉{}自动代理所有上架商品...", Thread.currentThread().getName(),seller.getLoginName());
//					
//					int count = sellerCommodityService.proxyAll(seller, ApiConstant.ORIGINAL_SELLER_ID_DEFAULT);////
//					logger.info("{}自动代理所有上架商品{}条。", seller.getLoginName(),count);
//					
//					logger.warn("线程{},为优粉{}自动代理所有上架商品......", Thread.currentThread().getName(),seller.getLoginName());
//				}
//	    	};	
//	    	Thread thread = new Thread(run);
//			thread.setName("proxyAll-自动代理所有上架商品");
//			thread.start();
			
		}catch(Exception e){
			logger.error(sellerInfoEntity.getLoginName()+"后台升级成为分销商,提交失败：", e);
			throw new Exception("后台升级成为分销商失败");
		}
		
			
		applyResult.setResult(true);
		applyResult.setResultCode(1);
		applyResult.setResultMsg("开店申请已成功提交等待审核");
		return applyResult;
		
	}
	
	/* 并且：默认该用户代理平台目前的所有已上架的商品 */
	@Transactional(   rollbackFor=Exception.class)
	@Override
	public void autoBecomeSeller(WFXResult<AfterPayedCallBackDto> result,MemberAccountEntity member, String wfxOrderNo)  {
			SellerInfoEntity sellerInfoEntity = null;
			String sellerId = null;
			String shopId = null;
			ShopEntity shopEntity = null;
	
			Date curDay = new Date();
			Map<String,Object> map = getSellerAndShopStatusByMemberId( member.getId() );
			if(null != map && map.size() >0 && map.get("sellerId")!=null){//已经申请过
				Integer sellerStatus = (Integer) map.get("sellerStatus");
				sellerId  = (String)map.get("sellerId");
				shopId = (String)map.get("shopId");
				
				if( StringUtils.isNotEmpty(sellerId) && ( null == sellerStatus || sellerStatus<1 ||
						 String.valueOf(sellerStatus).equals(SellerStateEnum.AUDITING.getState())  )
				  ) {// 更新审核状态，升级为优粉
					shopEntity = shopService.getShopBySeller(sellerId);
					sellerInfoEntity = sellerInfoMapper.getById(sellerId);
				}else{// 已经是分销商或者取消合作、审核不通过的分销商 ，忽略返回。
					return ;
				}
			}else{// 初始化 ，分销商和店铺数据
				shopEntity = new ShopEntity ();
				sellerInfoEntity = new SellerInfoEntity();
			}
			
			//保存分销商信息
			sellerInfoEntity.setState(SellerStateEnum.IN_COOPERATING.getState());//合作中
			sellerInfoEntity.setLastActiveDate(curDay);
			sellerInfoEntity.setLastActiveOptuser("system");
			sellerInfoEntity.setPassDate(curDay);
			sellerInfoEntity.setPassSysuser("system");
			//保存店铺信息
			shopEntity.setUpdateTime(curDay);	
			shopEntity.setLoginName( member.getLoginName() );
			shopEntity.setStatus(ShopStatusEnum.OPEN_SHOP.getStatus()); //开启
			shopEntity.setUpdateUser(member.getLoginName());
			// 设置默认店铺LOGO
			if(StringUtils.isBlank(shopEntity.getLogoUrl())){
				shopEntity.setLogoUrl(Constant.WFX_SHOP_LOGO_DEFAULT_PIC_URL);
			}
			//  设置默认店招
			if(StringUtils.isBlank(shopEntity.getSignUrl())){
				shopEntity.setSignUrl(Constant.WFX_SHOP_SIGN_DEFAULT_PIC_URL);
			}
			
			//保存分销商财务账户信息
			FinSellerInfoInputDto finSellerInfoDto = new FinSellerInfoInputDto();			
			finSellerInfoDto.setSellerAccount(member.getLoginName());						
			finSellerInfoDto.setLatelyTransactionTime(curDay);			
			
			AfterPayedCallBackDto dto = result.getResult();
			//信息不存在，新增
			if( null != map && map.size() >0 && map.get("sellerId")!=null ){//已经申请过，修改	
				
				this.update(sellerInfoEntity);
				
				shopEntity.setSellerId(sellerId);
				shopService.update(shopEntity);
				
				finSellerInfoDto.setId(sellerId);
				finSellerInfoFrontApi.updateSeller(finSellerInfoDto);
				if( dto!= null ){
					dto.setShopCode( shopEntity.getShopCode() );
					dto.setShopId( shopEntity.getId() );
				}
				try{
					//删除分销商状态缓存
					WFXRedisUtil.delete(WFXModuleType.SELLER_STATUS_BY_MEMBER, member.getId());
					WFXRedisUtil.delete(WFXModuleType.SELLER_STATUS_BY_SHOP, shopEntity.getId());
				}catch(Exception e){
					logger.error("删除缓存异常",e);
				}
			}else{
				sellerInfoEntity.setCreateTime(curDay);
				sellerInfoEntity.setParentId(
						StringUtils.isEmpty(member.getParentSellerId())?ApiConstant.ORIGINAL_SELLER_ID_DEFAULT: member.getParentSellerId()
											);
				sellerInfoEntity.setLoginName( member.getLoginName() );
				sellerInfoEntity.setLoginaccountId( member.getId() );
				sellerId = this.insert(sellerInfoEntity);////	
				sellerInfoEntity.setId(sellerId);
				
				String shopCode = "";
				try {
					shopCode = BizNoGen.getShopCode();
				} catch (Exception e) {
					logger.error("会员：{}发送升级为优粉时生成店铺编码出错： {}",member.getLoginName(),e);
				}
				shopEntity.setShopCode(shopCode);
				shopEntity.setName(shopCode); //默认存店铺编码
				shopEntity.setSellerId(sellerId);
				shopEntity.setCreateTime( curDay );
				shopId = shopService.insert(shopEntity);////
				
				finSellerInfoDto.setId(sellerId);
				finSellerInfoDto.setShopName(shopCode);
				finSellerInfoDto.setShopCode(shopCode);
				finSellerInfoDto.setRegisterTime(curDay);
				finSellerInfoFrontApi.createSeller(finSellerInfoDto);////
				if( dto!= null ){////
					dto.setShopCode( shopCode );
					dto.setShopId( shopId );
				}
				try{
					//删除分销商状态缓存
					WFXRedisUtil.delete(WFXModuleType.SELLER_STATUS_BY_MEMBER, member.getId());
					WFXRedisUtil.delete(WFXModuleType.SELLER_STATUS_BY_SHOP, shopId);
				}catch(Exception e){
					logger.error("删除缓存异常",e);
				}
			}
			//修改用户基本信息表是否申请分销商状态
			MemberProfileEntity profile = new MemberProfileEntity();
			profile.setApplySeller(MemberApplySellerTypeEnum.ALREADY_APPLY.getKey());
			profile.setApplySellerTime(curDay);
			profile.setLoginaccountId(member.getId());
			memberProfileMapper.update(profile);////
			
			//会员：成为分销商
			MemberAccountEntity memberAccountEntity = new MemberAccountEntity();
			memberAccountEntity.setId(member.getId());
			memberAccountEntity.setMemberType(MemberTypeEnum.SELLER_MEMBER.getKey());
			memberAccountMapper.update(memberAccountEntity);////
			
			// 存入缓存
			String value = member.getId();
			WFXRedisUtil.set(WFXModuleType.FIRSTPAY, wfxOrderNo, value, 2, TimeUnit.DAYS);
			logger.info("会员：{}首单支付成为优粉信息已存入缓存，订单号 {}",member.getLoginName(),wfxOrderNo);
			
			// 发短信
			try{////
				SmsProxyApi.sendNow(new String[] { member.getLoginName() }, MessageConstant.APPLY_TO_SELLER_MESSAGE, "apply_to_seller_success");
			}catch(Exception e){
				logger.error("会员：{}发送升级为优粉的短信出错 {}",member.getLoginName(),e);
			}
			// TODO 记录到日志
			
//			// 自动代理所有上架商品
//			final SellerInfoEntity seller = sellerInfoEntity;
//			final 
//			Runnable run = new Runnable(){
//				@Override
//				public void run() {
//					logger.warn("线程{},开始为优粉{}自动代理所有上架商品...", Thread.currentThread().getName(),seller.getLoginName());
//					
//					int count = sellerCommodityService.proxyAll(seller, ApiConstant.ORIGINAL_SELLER_ID_DEFAULT);////
//					logger.info("{}自动代理所有上架商品{}条。", seller.getLoginName(),count);
//					
//					logger.warn("线程{},为优粉{}自动代理所有上架商品......", Thread.currentThread().getName(),seller.getLoginName());
//				}
//	    	};	
//	    	Thread thread = new Thread(run);
//			thread.setName("proxyAll-自动代理所有上架商品");
//			thread.start();
			
			result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			result.setResultMsg("支付完成并且自动升级成为优粉！");
			logger.info("{}自动升级为优粉。", member.getLoginName());
		
	}

	@Override
	public List<SellerInfoEntity> getSubSellerList(String sellerId, RowBounds rowBounds) {
		// TODO Auto-generated method stub
		List<SellerInfoEntity> subSellerList = sellerInfoMapper.getSubSellerList(sellerId, rowBounds);
		if(null != subSellerList && subSellerList.size() >0){
			for(SellerInfoEntity sellerInfo : subSellerList){
				Double commissionTotalAmountForParent = finSellerInfoFrontApi.getCommissionTotalAmountById(sellerId,sellerInfo.getId());
				sellerInfo.setCommissionTotalAmountForParent(commissionTotalAmountForParent);
			}			
		}
		return subSellerList;
	}
	
	@Override
	public List<SellerInfoEntity> getSubSellerListSimple(String sellerId, RowBounds rowBounds) {
		// TODO Auto-generated method stub
		List<SellerInfoEntity> subSellerList = sellerInfoMapper.getSubSellerList(sellerId, rowBounds);
		return subSellerList;
	}

	@Override
	public int getSubSellerCount(String sellerId) {
		// TODO Auto-generated method stub
		return sellerInfoMapper.getSubSellerCount(sellerId);
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void sellerAuditPass(SellerInfoInputDto sellerInfoDto, String optUser,String type) throws Exception {
		// TODO Auto-generated method stub
		MemberAccountEntity memberAccountEntity= new MemberAccountEntity();
		memberAccountEntity.setId(sellerInfoDto.getLoginaccountId());
		memberAccountEntity.setMemberType(MemberTypeEnum.SELLER_MEMBER.getKey()); //会员身份变为分销商
		memberAccountMapper.update(memberAccountEntity);
		
		Date curDay = new Date();
		sellerInfoDto.setState(SellerStateEnum.IN_COOPERATING.getState()); //分销商状态变为 合作中
		//如果是审核通过，更新审核通过时间，审核人，最后开启时间，最后开启人
		if(StringUtils.isNotBlank(type) && Constant.WFX_SELLER_AUDIT_TYPE.equals(type)){
			sellerInfoDto.setPassDate(curDay);
			sellerInfoDto.setPassSysuser(optUser);
			sellerInfoDto.setLastActiveDate(curDay);
			sellerInfoDto.setLastActiveOptuser(optUser);
		// 如果是开启合作，只更新最后开启时间，最后开启人	
		}else if(StringUtils.isNotBlank(type) && Constant.WFX_SELLER_COOPERATE_TYPE.equals(type)){
			sellerInfoDto.setLastActiveDate(curDay);
			sellerInfoDto.setLastActiveOptuser(optUser);
			//开启合作清除店铺和分销商状态缓存
			WFXRedisUtil.delete(WFXModuleType.SELLER_STATUS_BY_MEMBER, sellerInfoDto.getLoginaccountId());
			ShopEntity shop = shopService.getShopBySeller(sellerInfoDto.getId());
			WFXRedisUtil.delete(WFXModuleType.SELLER_STATUS_BY_SHOP, shop.getId());
		}
		SellerInfoEntity sellerInfoEntity = (SellerInfoEntity) BeanUtil.convertBean(sellerInfoDto,SellerInfoEntity.class);
		this.update(sellerInfoEntity);
		
		ShopEntity shopEntity = new ShopEntity();
		shopEntity.setSellerId(sellerInfoDto.getId());
		shopEntity.setStatus(ShopStatusEnum.OPEN_SHOP.getStatus()); //店铺状态开启
		shopEntity.setUpdateTime(curDay);
		shopEntity.setUpdateUser(optUser);
		shopService.update(shopEntity);
		// 如果是审核通过，发送短信提醒
		if(StringUtils.isNotBlank(type) && Constant.WFX_SELLER_AUDIT_TYPE.equals(type)){
			try{
				//实时发送短信提醒审核通过
				SmsProxyApi.sendNow(new String[]{sellerInfoDto.getLoginName()},MessageConstant.APPLY_TO_SELLER_MESSAGE, "apply_to_seller_success");
			}catch(Exception e){
				logger.error("后台分销商审核通过，发送短信失败：", e);
			}
//			//自动代理所有商品
//			String originalSellerId = ApiConstant.ORIGINAL_SELLER_ID_DEFAULT;
//			sellerInfoEntity = sellerInfoMapper.getById( sellerInfoDto.getId() );
//			int num = sellerCommodityService.proxyAll(sellerInfoEntity, originalSellerId);
//			logger.info("分销商审核通过自动代理商品{}条",num);
		}
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void sellerStop(SellerInfoInputDto sellerInfoDto, String optUser) throws Exception {
		// TODO Auto-generated method stub
	/*	MemberAccountEntity memberAccountEntity= new MemberAccountEntity();
		memberAccountEntity.setId(sellerInfoDto.getLoginaccountId());
		memberAccountEntity.setMemberType(MemberTypeEnum.NORMAL_MEMBER.getKey()); //会员身份变为普通用户
		memberAccountMapper.update(memberAccountEntity);*/
		
		sellerInfoDto.setState(SellerStateEnum.CANCEL_COOPERATE.getState()); //分销商状态变为 取消合作
		sellerInfoDto.setLastUnactiveDate(new Date());
		sellerInfoDto.setLastUnactiveOptuser(optUser);
		SellerInfoEntity sellerInfoEntity = (SellerInfoEntity) BeanUtil.convertBean(sellerInfoDto,SellerInfoEntity.class);
		this.update(sellerInfoEntity);
		
		ShopEntity shopEntity = new ShopEntity();
		shopEntity.setSellerId(sellerInfoDto.getId());
		shopEntity.setStatus(ShopStatusEnum.CLOSE_SHOP.getStatus()); //店铺状态关闭
		shopEntity.setUpdateTime(new Date());
		shopEntity.setUpdateUser(optUser);		
		shopService.update(shopEntity);
		
		//开启合作清除店铺和分销商状态缓存
		WFXRedisUtil.delete(WFXModuleType.SELLER_STATUS_BY_MEMBER, sellerInfoDto.getLoginaccountId());
		ShopEntity shop = shopService.getShopBySeller(sellerInfoDto.getId());
		WFXRedisUtil.delete(WFXModuleType.SELLER_STATUS_BY_SHOP, shop.getId());
	}
	
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int sellerAutoAuditAllApply() throws Exception {
		// TODO Auto-generated method stub
		//获取成为分销商自动审核开关
		String autoAudit = wfxSystemService.getSystemConfig(Constant.APPLY_TO_SELLER_AUDIT_SWITCH);
		//开关开启进入自动审核流程
		if(StringUtils.isNotBlank(autoAudit) && Constant.WFX_APPLY_TO_SELLER_SWITCH_OPEN.equalsIgnoreCase(autoAudit)){
			int pageSize = 200;
			int page = 1;
			int totalPage = 0;		
			int count = sellerInfoMapper.queryAllApplyToSellerCount();
			if(count % pageSize > 0){
				totalPage = count / pageSize + 1;
			}else{
				totalPage = count / pageSize ;
			}
			
			//记录修改的总数
			int updateCount = 0;
			for(int i =0 ;i<totalPage;i++){	
				RowBounds rowBounds = new RowBounds((page -1)*pageSize, pageSize);
				//分页查询
				List<Map<String,String>> allApplyList = sellerInfoMapper.queryAllApplyToSeller(rowBounds);
				if(allApplyList!=null && allApplyList.size() >0){
					//需修改的分销商集合
					List<SellerInfoEntity> sellerList = new ArrayList<SellerInfoEntity>();
					//需修改的店铺集合
					List<ShopEntity> shopList = new ArrayList<ShopEntity>();
					//需修改的用户集合
					List<MemberAccountEntity> memberList = new ArrayList<MemberAccountEntity>();
					//需要发送短信的用户集合
					List<String> loginNameList = new ArrayList<String>();
					for(Map<String,String> applyMap : allApplyList){
						setAuditParam(sellerList, shopList, memberList, loginNameList, applyMap);
						updateCount ++;
					}					
				
					sellerInfoMapper.batchUpdateSellerStatus(sellerList);
					shopService.batchUpdateShopStatus(shopList);
					memberAccountMapper.batchUpdateMemberType(memberList);
					//延时短信提示
					String[] phones = new String[loginNameList.size()]; 
					loginNameList.toArray(phones);
					SmsProxyApi.sendDelay(phones, MessageConstant.APPLY_TO_SELLER_MESSAGE, "apply_to_seller_success");
				}
			}
			
			return updateCount;
		}else{
			logger.error("分销商自动审核job配置项开关为关闭："+autoAudit);
		}
		return 0;
	}
	
	/**
	 * 封装批量审核通过的店铺，分销商，用户参数 到相应集合对象
	 * @param sellerList
	 * @param shopList
	 * @param memberList
	 * @param loginNameList
	 * @param applyMap
	 */
	private void setAuditParam(List<SellerInfoEntity> sellerList, List<ShopEntity> shopList,
			List<MemberAccountEntity> memberList, List<String> loginNameList, Map<String, String> applyMap) {
		String sellerId = applyMap.get("sellerId"); // 分销商ID
		String shopId = applyMap.get("shopId"); // 店铺ID
		String memberId = applyMap.get("memberId"); // 用户ID
		String loginName = applyMap.get("loginName"); // 用户账号
		loginNameList.add(loginName);
		
		Date curDay = new Date();
		//分销商状态：合作中
		SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
		sellerInfoEntity.setId(sellerId);
		sellerInfoEntity.setState(SellerStateEnum.IN_COOPERATING.getState());
		sellerInfoEntity.setPassDate(curDay);
		sellerInfoEntity.setPassSysuser("system");
		sellerInfoEntity.setLastActiveDate(curDay);
		sellerInfoEntity.setLastActiveOptuser("system");
		sellerList.add(sellerInfoEntity);
		
		//店铺状态：开启
		ShopEntity shopEntity = new ShopEntity();
		shopEntity.setId(shopId);
		shopEntity.setUpdateTime(curDay);
		shopEntity.setUpdateUser("system");
		shopEntity.setStatus(ShopStatusEnum.OPEN_SHOP.getStatus());
		shopList.add(shopEntity);
		
		//会员：成为分销商
		MemberAccountEntity memberAccountEntity = new MemberAccountEntity();
		memberAccountEntity.setId(memberId);
		memberAccountEntity.setMemberType(MemberTypeEnum.SELLER_MEMBER.getKey());
		memberList.add(memberAccountEntity);
	}
	
	
	@CacheCable(key="'wfx:sellerAndShopStatusByShop:'+#shopId" ,value="value",expiration=24*60*60)
	@Override
	public Map<String, Object> getSellerAndShopStatusByShopId(String shopId) {
		// TODO Auto-generated method stub
		return sellerInfoMapper.getSellerAndShopStatusByShopId(shopId);
	}
	
//	/**
//     * 分销商代理所有上架商品
//     * @return
//     */
//	@Override
//	public int sellerAutoProxyAll() {
//		String originalSellerId = ApiConstant.ORIGINAL_SELLER_ID_DEFAULT;
//		int totalCount = 0;
//		SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
//		sellerInfoEntity.setState( "3" );//合作中
//		List<SellerInfoEntity> sellerlist = sellerInfoMapper.queryList(sellerInfoEntity);
//		if(sellerlist!=null&& sellerlist.size()>0){
//			for(SellerInfoEntity sellerInfo:sellerlist){
//				if( ! originalSellerId.equals(  sellerInfo.getId() ) ){
//					totalCount = totalCount + sellerCommodityService.proxyAll(sellerInfo, originalSellerId);
//				}
//			}
//		}
//		return totalCount;
//	}

	@Override
	public List<SellerInfoEntity> getLevelTwoSeller(String id) {
		List<SellerInfoEntity> sellerList = sellerInfoMapper.getLevelTwoSeller(id);
		if(null != sellerList && sellerList.size() > 0){
			for(SellerInfoEntity seller:sellerList){
				seller.setSellerLevel(2);
			}
		}
		return sellerList;
	}

	@Override
	public List<SellerInfoEntity> getLevelThreeSeller(String id) {
		List<SellerInfoEntity> sellerList = sellerInfoMapper.getLevelThreeSeller(id);
		if(null != sellerList && sellerList.size() > 0){
			for(SellerInfoEntity seller:sellerList){
				seller.setSellerLevel(3);
			}
		}
		return sellerInfoMapper.getLevelThreeSeller(id);
	}

	@Override
	public List<SellerInfoEntity> getAllLevelSeller(String id) {
		List<SellerInfoEntity> sellerList = new ArrayList<SellerInfoEntity>();
		SellerInfoEntity seller = sellerInfoMapper.getById(id);
		if(null != seller){
			seller.setSellerLevel(1);
			sellerList.add(seller);
		}
		List<SellerInfoEntity> seller2List = sellerInfoMapper.getLevelTwoSeller(id);
		if(null != seller2List && seller2List.size() > 0){
			for(SellerInfoEntity tempSeller:seller2List){
				tempSeller.setSellerLevel(2);
			}
			sellerList.addAll(seller2List);
			List<SellerInfoEntity> seller3List = sellerInfoMapper.getLevelThreeSeller(id);
			if(null != seller3List && seller3List.size() > 0){
				for(SellerInfoEntity tempSeller:seller3List){
					tempSeller.setSellerLevel(3);
				}
				sellerList.addAll(seller3List);
			}
		}
		return sellerList;
	}

	@Override
	public Integer getLevelTwoSellerNum(String sellerId) {
		return sellerInfoMapper.getSubSellerCount(sellerId);
	}

	@Override
	public Integer getLevelThreeSellerNum(String sellerId) {
		return sellerInfoMapper.getLevelThreeSellerNum(sellerId);
	}

	@Override
	public List<SellerInfoEntity> getLevelTwoSellerListById(String sellerId,
			RowBounds rowBounds) {
				List<SellerInfoEntity> subSellerList = sellerInfoMapper.getSubSellerList(sellerId, rowBounds);
				if(null != subSellerList && subSellerList.size() >0){
					for(SellerInfoEntity sellerInfo : subSellerList){
						Double commissionTotalAmountForParent = finSellerInfoFrontApi.getCommissionTotalAmountById(sellerId,sellerInfo.getId());
						sellerInfo.setCommissionTotalAmountForParent(commissionTotalAmountForParent);
					}			
				}
				return subSellerList;
	}

	@Override
	public List<SellerInfoEntity> getLevelThreeSellerListById(String sellerId,
			RowBounds rowBounds) {
				List<SellerInfoEntity> subSellerList = sellerInfoMapper.getLevelThreeSellerListById(sellerId, rowBounds);
				if(null != subSellerList && subSellerList.size() >0){
					for(SellerInfoEntity sellerInfo : subSellerList){
						Double commissionTotalAmountForParent = finSellerInfoFrontApi.getCommissionTotalAmountById(sellerId,sellerInfo.getId());
						sellerInfo.setCommissionTotalAmountForParent(commissionTotalAmountForParent);
					}			
				}
				return subSellerList;
	}
	@Override
	public Map<String, String> queryIfAutoTransfer(String memberId,
			String wfxOrderNo) {
		Map<String, String> result = null;
		String value = WFXRedisUtil.getString(WFXModuleType.FIRSTPAY, wfxOrderNo);
		if( StringUtils.isNotEmpty( value ) && value.equals(memberId) ){
			ShopEntity shop = shopService.getShopByMemberId(memberId);
			if(shop!=null){
				result = new HashMap<String, String>();
				result.put("shopCode", shop.getShopCode());
				result.put("shopId", shop.getId());
			}
		}
		return result;
	}

	/* 查粉丝数目 */
	@Override
	public Integer getFansCountBySellerId(String sellerId) {
		return sellerInfoMapper.getFansCountBySellerId(sellerId);
	}
	
}
