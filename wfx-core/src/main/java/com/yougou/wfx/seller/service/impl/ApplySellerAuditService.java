package com.yougou.wfx.seller.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.common.constant.Constant;
import com.yougou.wfx.common.constant.MessageConstant;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.MemberTypeEnum;
import com.yougou.wfx.enums.SellerStateEnum;
import com.yougou.wfx.enums.ShopStatusEnum;
import com.yougou.wfx.finance.api.front.IFinSellerInfoFrontApi;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.member.dao.MemberAccountMapper;
import com.yougou.wfx.member.dao.MemberProfileMapper;
import com.yougou.wfx.member.model.MemberAccountEntity;
import com.yougou.wfx.order.service.IOrderService;
import com.yougou.wfx.proxy.messenger.SmsProxyApi;
import com.yougou.wfx.seller.dao.SellerInfoMapper;
import com.yougou.wfx.seller.model.SellerInfoEntity;
import com.yougou.wfx.shop.model.ShopEntity;
import com.yougou.wfx.shop.service.IShopService;
import com.yougou.wfx.system.service.WFXSystemService;

/**
 * 申请分销商后自动审核
 * @author zhang.f1
 *
 */
@Service
public class ApplySellerAuditService extends BaseService {
	
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
	private SellerInfoMapper sellerInfoMapper;
	
	/**
	 * 申请成为分销商，自动审核
	 * @param memberId 用户ID
	 * @param loginName 登陆账号
	 * @param sellerId 分销商ID
	 * @param shopId 店铺ID
	 * @return WFXResult{ result:(true:审核通过 false:审核不通过),
	 * 					  resultCode:(1：开关关闭，审核不通过；2：没有成功交易订单，审核不通过；3：审核通过;4:系统异常，审核不通过)}
	 */
	@Transactional(propagation=Propagation.NESTED,rollbackFor=Exception.class)
	public WFXResult<Boolean> sellerAutoAudit(String memberId,String loginName,String sellerId,String shopId)throws Exception {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result = new WFXResult<Boolean>();		
		try{
			//获取成为分销商自动审核开关
			String autoAudit = wfxSystemService.getSystemConfig(Constant.APPLY_TO_SELLER_AUDIT_SWITCH);
			//开关开启进入自动审核流程
			if(StringUtils.isNotBlank(autoAudit) && Constant.WFX_APPLY_TO_SELLER_SWITCH_OPEN.equalsIgnoreCase(autoAudit)){
				int receivedCount = orderService.queryReceivedCount(memberId);
				// 获取成为分销商系统配置项设置的成功交易订单数量
				String sysOrderCountStr =  wfxSystemService.getSystemConfig(Constant.WFX_APPLY_TO_SELLER_LIMIT_KEY);
				int sysOrderCount = 0;
				try{
					 sysOrderCount = Integer.parseInt(sysOrderCountStr);
				}catch(Exception e){
					// 默认为成功交易一笔
					sysOrderCount = 1;
					logger.error("分销商申请开店自动审核功能，转换系统配置项：成为分销商需成功交易定数异常，获取到的配置项值为：{}", sysOrderCountStr);
				}
				// 用户成功交易订单数量 >= 系统设置的配置项值
				if(receivedCount >= sysOrderCount){
					Date curDay = new Date();
					//分销商状态：合作中
					SellerInfoEntity sellerInfoEntity = new SellerInfoEntity();
					sellerInfoEntity.setId(sellerId);
					sellerInfoEntity.setState(SellerStateEnum.IN_COOPERATING.getState());
					sellerInfoEntity.setPassDate(curDay);
					sellerInfoEntity.setPassSysuser("system");
					sellerInfoEntity.setLastActiveDate(curDay);
					sellerInfoEntity.setLastActiveOptuser("system");
					sellerInfoMapper.update(sellerInfoEntity);
					
					//店铺状态：开启
					ShopEntity shopEntity = new ShopEntity();
					shopEntity.setId(shopId);
					shopEntity.setUpdateTime(curDay);
					shopEntity.setUpdateUser("system");
					shopEntity.setStatus(ShopStatusEnum.OPEN_SHOP.getStatus());
					shopService.update(shopEntity);
					
					//会员：成为分销商
					MemberAccountEntity memberAccountEntity = new MemberAccountEntity();
					memberAccountEntity.setId(memberId);
					memberAccountEntity.setMemberType(MemberTypeEnum.SELLER_MEMBER.getKey());
					memberAccountMapper.update(memberAccountEntity);
					
					// 立即发送短信提醒
					SmsProxyApi.sendNow(new String[] { loginName }, MessageConstant.APPLY_TO_SELLER_MESSAGE, "apply_to_seller_success");
					
					result.setResult(true);
					result.setResultCode(3);
					result.setResultMsg("自动审核通过，成为分销商，店铺开启");
					return result;
				}else{
					logger.info("用户ID为:{},申请成为分销商，没有成功交易订单，自动审核不通过",memberId);
					result.setResult(false);
					result.setResultCode(2);
					result.setResultMsg("没有成功交易订单，审核不通过");
					return result;
				}
			}else{
				logger.info("申请成为分销商，自动审核开关状态为关闭:{}",autoAudit);
				result.setResult(false);
				result.setResultCode(1);
				result.setResultMsg("自动审核开关关闭，审核不通过");
				return result;
			}
		}catch(Exception e){
			logger.error("申请成为分销商，自动审核系统异常:",e);
		/*	result.setResult(false);
			result.setResultCode(4);
			result.setResultMsg("自动审核系统异常，审核不通过");*/
			throw e;
			///return result;
		}
		
	} 
}
