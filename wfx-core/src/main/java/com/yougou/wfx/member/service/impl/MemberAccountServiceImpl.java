 /*
 * 版本信息
 
 * 日期 2016-03-24 18:30:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.service.impl;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.common.constant.Constant;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.UserContext;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.MemberApplySellerTypeEnum;
import com.yougou.wfx.enums.MemberTypeEnum;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.finance.api.front.IFinSellerInfoFrontApi;
import com.yougou.wfx.finance.dto.input.FinSellerInfoInputDto;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.DESEncrypt;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.member.dao.MemberAccountMapper;
import com.yougou.wfx.member.dto.input.MemberForWXInputDto;
import com.yougou.wfx.member.dto.output.MemberAccountOutputDto;
import com.yougou.wfx.member.dto.output.MemberProfileOutputDto;
import com.yougou.wfx.member.model.MemberAccountEntity;
import com.yougou.wfx.member.model.MemberProfileEntity;
import com.yougou.wfx.member.service.IMemberAccountService;
import com.yougou.wfx.member.service.IMemberProfileService;
import com.yougou.wfx.messenger.enums.SmsCodeDeleteState;
import com.yougou.wfx.messenger.enums.SmsSecurityCheckState;
import com.yougou.wfx.messenger.enums.SmsSecuritySendState;
import com.yougou.wfx.proxy.messenger.SmsUtil;
import com.yougou.wfx.seller.dto.output.SellerInfoOutputDto;
import com.yougou.wfx.seller.model.SellerInfoEntity;
import com.yougou.wfx.seller.service.ISellerInfoService;
import com.yougou.wfx.shop.dto.output.ShopOutputDto;
import com.yougou.wfx.shop.model.ShopEntity;
import com.yougou.wfx.shop.service.IShopService;
import com.yougou.wfx.util.ApiConstant;
import com.yougou.wfx.utils.PicPathUtil;
import com.yougou.wfx.visitor.model.VisitorEntity;

/**
 * IMemberAccountService实现
 * @author luoq
 * @Date 创建时间：2016-03-24 18:30:55
 */
@Service
public class MemberAccountServiceImpl extends BaseService implements IMemberAccountService {
	
	@Resource
	private MemberAccountMapper memberAccountMapper;
	
	@Resource
	private IMemberProfileService memberProfileService;
	
	@Resource
	private ISellerInfoService  sellerInfoService;
	
	@Resource
	private IShopService shopService;
	
	@Resource
	IFinSellerInfoFrontApi  finSellerInfoFrontApi;
	
	private Logger logger = LoggerFactory.getLogger(MemberAccountServiceImpl.class);

	@Override
	public int findPageCount(MemberAccountEntity memberAccountEntity){
		return memberAccountMapper.findPageCount(memberAccountEntity);
	}

	@Override
	public List<MemberAccountEntity> findPage(MemberAccountEntity memberAccountEntity,RowBounds rowBounds){
		return memberAccountMapper.findPage(memberAccountEntity,rowBounds);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String insert(MemberAccountEntity memberAccountEntity){
		String strId = memberAccountEntity.getId();
		if(null == memberAccountEntity.getId()){
			strId = UUIDGenerator.get32LowCaseUUID();
			memberAccountEntity.setId(strId);
		}
		memberAccountMapper.insert(memberAccountEntity);
		return strId;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int update(MemberAccountEntity memberAccountEntity){
		return  memberAccountMapper.update(memberAccountEntity);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int remove(String id){
		return memberAccountMapper.remove(id);
	}
	
	@Override
	public MemberAccountEntity getMemberInfoById(String id){
		//return memberAccountMapper.getMemberInfoById(id);
		MemberAccountEntity memberAccountEntity = memberAccountMapper.getById(id);
		if(memberAccountEntity != null){
			//获取用户基本信息
			MemberProfileEntity memberProfileEntity =memberProfileService.getMemberProfileByMemberId(memberAccountEntity.getId());
			
			MemberProfileOutputDto profileOutputDto = (MemberProfileOutputDto) BeanUtil.convertBean(memberProfileEntity,MemberProfileOutputDto.class);
			memberAccountEntity.setProfileInfo(profileOutputDto);
			//获取分销商信息
			SellerInfoEntity sellerEntity = sellerInfoService.getSellerByMemberId(memberAccountEntity.getId());		
			if(null != sellerEntity){
				SellerInfoOutputDto sellerOutputDto = (SellerInfoOutputDto) BeanUtil.convertBean(sellerEntity,SellerInfoOutputDto.class);
				memberAccountEntity.setSellerInfo(sellerOutputDto);
				//获取店铺信息
				ShopEntity shopEntity = shopService.getShopBySeller(sellerEntity.getId());
				ShopOutputDto shopOutputDto = (ShopOutputDto) BeanUtil.convertBean(shopEntity,ShopOutputDto.class);
				memberAccountEntity.setShopInfo(shopOutputDto);
			}
		}
		return memberAccountEntity;
	}

	@Override
	public int getCountByPhone(String phone) {
		// TODO Auto-generated method stub
		return memberAccountMapper.getCountByPhone(phone);
	}

	@Override
	public MemberAccountEntity getMemberAccountByPhoneNumber(String phoneNubmer) {
		// TODO Auto-generated method stub
		return memberAccountMapper.getMemberAccountByPhoneNumber(phoneNubmer);
	} 
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public WFXResult<Boolean> registerMember(MemberAccountEntity memberAccountEntity,String code, UserContext userContext) throws Exception {
		
		WFXResult<Boolean> result = new  WFXResult<Boolean>();
		
		SmsSecurityCheckState checkState = SmsUtil.checkPhoneCode(memberAccountEntity.getLoginName(), code, userContext,SmsCodeDeleteState.DELETE_CODE.getCode());
		//短信验证码校验不成功
		if(checkState.getCode() != SmsSecurityCheckState.CHECK_SUCCESS.getCode()){
			result.setResult(false);
			result.setResultMsg(checkState.getMsg());
			return result;
		}
		//保存用户账户信息
		String password = memberAccountEntity.getLoginPassword();
		String memberId =  UUIDGenerator.get32LowCaseUUID();
		//对密码加密
		String secPwd = DESEncrypt.encryptPBKDF2(password, memberId);
		memberAccountEntity.setId(memberId);
		memberAccountEntity.setLoginPassword(secPwd);
		memberAccountEntity.setRegisterDate(new Date());
		memberAccountEntity.setRegisterCheckMobileTime(new Date());
		//默认绑定手机号为注册时的手机号，即：登陆账号
		if(StringUtils.isBlank(memberAccountEntity.getRegisterCheckMobile())){
			memberAccountEntity.setRegisterCheckMobile(memberAccountEntity.getLoginName());
		}
		//默认注册IP为请求信息内clientIp
		if(StringUtils.isBlank(memberAccountEntity.getRegisterIp())){
			memberAccountEntity.setRegisterIp(userContext.getClientIp());
		}
		//默认用户类型为普通用户
		if(memberAccountEntity.getMemberType() == null || memberAccountEntity.getMemberType() == 0 ){
			memberAccountEntity.setMemberType(MemberTypeEnum.NORMAL_MEMBER.getKey());
		}
		String loginaccountId = insert(memberAccountEntity);
		//保存用户基本信息
		MemberProfileEntity profileEntity = new MemberProfileEntity();
		profileEntity.setLoginaccountId(loginaccountId);
		profileEntity.setLoginName(memberAccountEntity.getLoginName());
		profileEntity.setApplySeller(MemberApplySellerTypeEnum.NOT_APPLY.getKey()); //未申请成为分销商
		profileEntity.setOrderAmount((double) 0);
		profileEntity.setOrderCount(0);
		profileEntity.setRegisterDate(new Date());
		memberProfileService.insert(profileEntity);
		
		result.setResult(true);
		result.setResultMsg("注册成功");
		return result;
	}
	

	@Override
	public MemberAccountEntity memberLogin(String loginName, String passWord, UserContext userContext) {
		//根据账号获取用户信息
		MemberAccountEntity memberAccount=memberAccountMapper.getMemberAccountByPhoneNumber(loginName);
		if(memberAccount != null){
			String memberId = memberAccount.getId();
			//对密码加密
			passWord = DESEncrypt.encryptPBKDF2(passWord, memberId);
			MemberAccountEntity memberAccountEntity = memberAccountMapper.memberLogin(loginName, passWord);
			getProfileAndUpdateLoginInfo(MemberTypeEnum.NORMAL_MEMBER, userContext, memberAccountEntity);
			if(memberAccountEntity != null){
				getSellerAndShopInfo(memberAccountEntity);
			}		
			return memberAccountEntity;
		}
		return null;
	}
	
	
	/**
	 * 获取用户基本信息并更新登陆相关信息
	 * @param memberType
	 * @param userContext
	 * @param memberAccountEntity
	 */
	private void getProfileAndUpdateLoginInfo(MemberTypeEnum memberType, UserContext userContext,
			MemberAccountEntity memberAccountEntity) {
		if(memberAccountEntity != null){
			//获取用户基本信息
			MemberProfileEntity memberProfileEntity =memberProfileService.getMemberProfileByMemberId(memberAccountEntity.getId());
			MemberProfileOutputDto profileOutputDto = (MemberProfileOutputDto) BeanUtil.convertBean(memberProfileEntity,MemberProfileOutputDto.class);
			if(profileOutputDto != null && StringUtils.isNoneBlank(profileOutputDto.getHeadShowImg())
					&& profileOutputDto.getHeadShowImg().startsWith(Constant.WX_IMG_PREFIX)){
				profileOutputDto.setHeadShowImg(profileOutputDto.getHeadShowImg().replace(Constant.WX_IMG_PREFIX, ""));
			}
			
			memberAccountEntity.setProfileInfo(profileOutputDto);
			
			//更新用户登录相关信息
			MemberAccountEntity memberAccountEntityUpdate = new MemberAccountEntity();
			memberAccountEntityUpdate.setLastLoginIp(userContext.getClientIp());
			memberAccountEntityUpdate.setLastLoginTime(new Date());
			Integer loginCount = (memberAccountEntity.getLoginCount() == null ? 0 : memberAccountEntity.getLoginCount() );
			memberAccountEntityUpdate.setLoginCount(loginCount + 1);
			memberAccountEntityUpdate.setId(memberAccountEntity.getId());
			String lastLoginSite = String.valueOf(memberType.getKey()); //普通会员登录/ 会员登录
			memberAccountEntityUpdate.setLastLoginSite(lastLoginSite);
			this.update(memberAccountEntityUpdate);
		}
	}

	@Override
	public MemberAccountEntity memberLoginForApp(String loginName, String passWord, UserContext userContext) {
		//根据账号获取用户信息
		MemberAccountEntity memberAccount=memberAccountMapper.getMemberAccountByPhoneNumber(loginName);
		if(memberAccount != null){
			String memberId = memberAccount.getId();
			//对密码加密
			passWord = DESEncrypt.encryptPBKDF2(passWord, memberId);			
			MemberAccountEntity memberAccountEntity = memberAccountMapper.memberLoginForApp(loginName, passWord);
			getProfileAndUpdateLoginInfo(MemberTypeEnum.SELLER_MEMBER, userContext, memberAccountEntity);
			if(memberAccountEntity != null){
				getSellerAndShopInfo(memberAccountEntity);
			}		
			return memberAccountEntity;
		}
		return null;
	}

	@Override
	public WFXResult<Boolean> updatePassWord(String userId, String newPassWord, String phone, String code,
			UserContext userContext) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result = new WFXResult<Boolean>();
		
		//对密码加密
		newPassWord = DESEncrypt.encryptPBKDF2(newPassWord, userId);	
		int checkCount = memberAccountMapper.checkMemberPassWord(userId, newPassWord);
		if(checkCount > 0){
			result.setResult(false);
			result.setResultMsg("新密码与旧密码一致，修改密码失败");
			logger.error("用户ID：{}新密码与旧密码一致，修改密码失败", userId);
			return result;
		}
		SmsSecurityCheckState checkState = SmsUtil.checkPhoneCode(phone, code, userContext,SmsCodeDeleteState.DELETE_CODE.getCode());
		//短信验证码校验不成功
		if(checkState.getCode() != SmsSecurityCheckState.CHECK_SUCCESS.getCode()){
			result.setResult(false);
			result.setResultMsg(checkState.getMsg());
			logger.error("用户ID：{}重置密码失败，验证码校验不通过", userId);
			return result;
		}
		int count = memberAccountMapper.updatePassword(userId, newPassWord);
		if(count >0){
			result.setResult(true);
			result.setResultMsg("修改密码成功");
		}else{
			result.setResult(false);
			result.setResultMsg("修改密码失败，用户不存在");
		}
		return result;
	}

	@Override
	public WFXResult<Boolean> sendPhoneCode(String phone, UserContext context) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result = new  WFXResult<Boolean>();
		SmsSecuritySendState sendState = SmsUtil.sendPhoneCode(phone, context);
		if(SmsSecuritySendState.SEND_SUCCESS.getCode() != sendState.getCode()){
			result.setResult(false);
			result.setResultMsg(sendState.getMsg());
			return result;
		}
		result.setResult(true);
		result.setResultMsg(sendState.getMsg());
		return result;
	}

	@Override
	public WFXResult<Boolean> checkPhoneCode(String phone, String code, UserContext context) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result = new  WFXResult<Boolean>();
		SmsSecurityCheckState checkState = SmsUtil.checkPhoneCode(phone, code, context,SmsCodeDeleteState.NOT_DELETE_CODE.getCode());
		if(checkState.getCode() != SmsSecurityCheckState.CHECK_SUCCESS.getCode()){
			logger.warn("{},校验短信短信验证码失败：{}",phone,checkState.getMsg());
			result.setResult(false);
			result.setResultMsg(checkState.getMsg());
			return result;
		}
		result.setResult(true);
		result.setResultMsg(checkState.getMsg());
		return result;
	}

	@Override
	public MemberAccountEntity getById(String id) {
		return memberAccountMapper.getById(id);
	}

	@Override
	public WFXResult<Boolean> updatePassWordAfterLogin(String memberId, String oldPassWord, String newPassWord) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result = new WFXResult<Boolean>();
		//对旧密码加密
		oldPassWord = DESEncrypt.encryptPBKDF2(oldPassWord, memberId);
		int count = memberAccountMapper.checkMemberPassWord(memberId, oldPassWord);
		if(count < 1){
			result.setResult(false);
			result.setResultMsg("旧密码填写错误，修改密码失败");
			logger.error("用户ID：{}旧密码填写错误，修改密码失败", memberId);
			return result;
		}
		//对新密码加密
		newPassWord = DESEncrypt.encryptPBKDF2(newPassWord, memberId);
		int checkCount = memberAccountMapper.checkMemberPassWord(memberId, newPassWord);
		if(checkCount > 0){
			result.setResult(false);
			result.setResultMsg("新密码与旧密码一致，修改密码失败");
			logger.error("用户ID：{}新密码与旧密码一致，修改密码失败", memberId);
			return result;
		}
		int count2 = memberAccountMapper.updatePassword(memberId, newPassWord);
		if(count2 >0){
			result.setResult(true);
			result.setResultMsg("修改密码成功");
		}else{
			result.setResult(false);
			result.setResultMsg("修改密码失败，用户不存在");
		}
		return result;
	}
	
	@Override
	public MemberAccountEntity wxMemberLogin(String openId,String h5OpenId, UserContext userContext) {
		//根据账号获取用户信息
		MemberAccountEntity memberAccount=memberAccountMapper.getMemberAccountByOpenId(openId);
		if(memberAccount != null){
			//如果已经有h5_open_id，不处理，否则维护进去
			if(StringUtils.isNotBlank(h5OpenId) && StringUtils.isBlank(memberAccount.getH5OpenId())){
				MemberAccountEntity memberAccountUp = new MemberAccountEntity();
				memberAccountUp.setId(memberAccount.getId());
				memberAccountUp.setH5OpenId(h5OpenId);
				memberAccountMapper.update(memberAccountUp);
			}
			getSellerAndShopInfo(memberAccount);
			getProfileAndUpdateLoginInfo(MemberTypeEnum.NORMAL_MEMBER, userContext, memberAccount);
			//增加绑定H5OpenId
		}
		return memberAccount;
	}
	
	@Override
	public WFXResult<MemberAccountEntity> wxMemberRegister(MemberForWXInputDto memberForWXInputDto, UserContext userContext) {
		WFXResult<MemberAccountEntity> result = new WFXResult<MemberAccountEntity>();
		//根据账号获取用户信息
		MemberAccountEntity memberAccount=memberAccountMapper.getMemberAccountByOpenId(memberForWXInputDto.getOpenId());
		if(memberAccount == null){ //第一次登录,新增用户信息
			try {
				this.registerMemberForWX(memberForWXInputDto, userContext);
				memberAccount=memberAccountMapper.getMemberAccountByOpenId(memberForWXInputDto.getOpenId());
			} catch (Exception e) {
				logger.error("微信登录，保存用户信息失败！{}", memberForWXInputDto,e);
				result.setResult(null);
				result.setResultMsg("系统异常，请重试！");
				result.setResultCode(ResultCodeEnum.FAILURE.getKey());
				return result;
			}
		}else{
			result.setResult(memberAccount);
			result.setResultMsg("已经使用微信注册过！");
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			return result;
		}
		if(memberAccount != null){
			getSellerAndShopInfo(memberAccount);
			getProfileAndUpdateLoginInfo(MemberTypeEnum.NORMAL_MEMBER, userContext, memberAccount);
		}
		result.setResult(memberAccount);
		result.setResultMsg(null);
		result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
		return result;
	}

	//获取分销商信息和店铺信息
	private void getSellerAndShopInfo(MemberAccountEntity memberAccount) {
		//获取分销商新信息
		SellerInfoEntity sellerEntity = sellerInfoService.getSellerByMemberId(memberAccount.getId());			
		SellerInfoOutputDto sellerOutputDto = (SellerInfoOutputDto) BeanUtil.convertBean(sellerEntity,SellerInfoOutputDto.class);
		memberAccount.setSellerInfo(sellerOutputDto);
		//获取店铺信息
		if(null != sellerEntity){
			ShopEntity shopEntity = shopService.getShopBySeller(sellerEntity.getId());
			ShopOutputDto shopOutputDto = (ShopOutputDto) BeanUtil.convertBean(shopEntity,ShopOutputDto.class);
			memberAccount.setShopInfo(shopOutputDto);
		}
	}
	
	@Override
	public MemberAccountEntity wxMemberLoginForApp(String openId, UserContext userContext) {
		//根据账号获取用户信息
		MemberAccountEntity memberAccount=memberAccountMapper.getMemberAccountByOpenId(openId);
		if(memberAccount != null){
			getProfileAndUpdateLoginInfo(MemberTypeEnum.SELLER_MEMBER, userContext, memberAccount);
			getSellerAndShopInfo(memberAccount);
		}
		return memberAccount;
	}
	
	@Transactional(rollbackFor=Exception.class)
	private void registerMemberForWX(MemberForWXInputDto memberForWXInputDto, UserContext userContext) throws Exception {
		MemberAccountEntity memberAccountEntity = new MemberAccountEntity();
		//保存用户账户信息
		String memberId =  UUIDGenerator.get32LowCaseUUID();
		memberAccountEntity.setId(memberId);
//		String password = memberAccountEntity.getLoginPassword();
		//对密码加密
//		String secPwd = DESEncrypt.encryptPBKDF2(password, memberId);
//		memberAccountEntity.setLoginPassword(secPwd);
		memberAccountEntity.setRegisterDate(new Date());
		memberAccountEntity.setRegisterCheckMobileTime(new Date());
		//默认绑定手机号为注册时的手机号，即：登陆账号
//		if(StringUtils.isBlank(memberAccountEntity.getRegisterCheckMobile())){
//			memberAccountEntity.setRegisterCheckMobile(memberAccountEntity.getLoginName());
//		}
		if(memberForWXInputDto.getParentSellerId().equals("-1")){
			memberAccountEntity.setParentSellerId( ApiConstant.ORIGINAL_SELLER_ID_DEFAULT );
		}else{
			memberAccountEntity.setParentSellerId(memberForWXInputDto.getParentSellerId());
		}
		//默认注册IP为请求信息内clientIp
		memberAccountEntity.setRegisterIp(userContext.getClientIp());
		memberAccountEntity.setPlatformUsername(filterOffUtf8Mb4(memberForWXInputDto.getNickname()));
		
		memberAccountEntity.setOpenId(memberForWXInputDto.getOpenId());
		memberAccountEntity.setH5OpenId(memberForWXInputDto.getH5OpenId());
		//默认用户类型为普通用户
		memberAccountEntity.setMemberType(MemberTypeEnum.NORMAL_MEMBER.getKey());
		memberAccountEntity.setPlatform("wechat");
		String loginaccountId = insert(memberAccountEntity);
		//保存用户基本信息
		MemberProfileEntity profileEntity = new MemberProfileEntity();
		profileEntity.setLoginaccountId(loginaccountId);
		profileEntity.setHeadShowImg(Constant.WX_IMG_PREFIX+memberForWXInputDto.getHeadimgurl());
		profileEntity.setMemberSex(memberForWXInputDto.getSex());
//		profileEntity.setLoginName(memberAccountEntity.getLoginName());
		profileEntity.setApplySeller(MemberApplySellerTypeEnum.NOT_APPLY.getKey()); //未申请成为分销商
		profileEntity.setOrderAmount((double) 0);
		profileEntity.setOrderCount(0);
		profileEntity.setRegisterDate(new Date());
		memberProfileService.insert(profileEntity);
	}

	@Override
	public WFXResult<MemberAccountEntity> wxSellerBindForApp(MemberForWXInputDto memberForWXInputDto,String type,UserContext userContext) {
		
		WFXResult<MemberAccountEntity> result = new WFXResult<MemberAccountEntity>();
		//根据账号获取用户信息
		MemberAccountEntity memberAccount = memberAccountMapper.getMemberAccountByOpenId(memberForWXInputDto.getOpenId());
		if(memberAccount == null){
			//微信没有绑定
			MemberAccountEntity member = new  MemberAccountEntity();
			member.setOpenId(memberForWXInputDto.getOpenId());
			member.setId(memberForWXInputDto.getMemberId());
			member.setPlatformUsername(filterOffUtf8Mb4(memberForWXInputDto.getNickname()));
			if("h5".equals(type)){
			member.setH5OpenId(memberForWXInputDto.getH5OpenId());
		    member.setH5OpenId(memberForWXInputDto.getH5OpenId());
			}
			memberAccountMapper.update(member);
			
			//保存用户基本信息
			MemberProfileEntity profileEntity = memberProfileService.getMemberProfileByMemberId(memberForWXInputDto.getMemberId());
			MemberProfileEntity profileEntityForUpdate = new MemberProfileEntity();
			boolean needUpdate = false;
			profileEntityForUpdate.setId(profileEntity.getId());
			if(StringUtils.isBlank(profileEntity.getHeadShowImg()) || profileEntity.getHeadShowImg().endsWith("default_logo.png")){
				profileEntityForUpdate.setHeadShowImg(Constant.WX_IMG_PREFIX+memberForWXInputDto.getHeadimgurl());
				needUpdate = true;
			}
			if(profileEntity.getMemberSex() ==null || (profileEntity.getMemberSex() != 1 && profileEntity.getMemberSex() != 2)){
				profileEntityForUpdate.setMemberSex(memberForWXInputDto.getSex());
				if(memberForWXInputDto.getSex() >0){
					needUpdate = true;
				}
			}
			if(needUpdate){
				memberProfileService.update(profileEntityForUpdate);
			}
			
			if("h5".equals(type)){
				memberAccount = wxMemberLogin(memberForWXInputDto.getOpenId(),"" , userContext);
			}else{
				memberAccount = wxMemberLoginForApp(memberForWXInputDto.getOpenId(), userContext);
			}
			
			result.setResult(memberAccount);
			result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			result.setResultMsg("绑定成功！");
		}else{
			result.setResult(null);
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResultMsg("绑定失败，微信号已经被绑定！");
		}
		return result;
	}

	@Override
	public WFXResult<MemberAccountEntity> phoneSellerBindForApp(MemberAccountEntity memberAccountEntity,String type, UserContext userContext) {
		WFXResult<MemberAccountEntity> result = new WFXResult<MemberAccountEntity>();
		SmsSecurityCheckState checkState = SmsUtil.checkPhoneCode(memberAccountEntity.getLoginName(), memberAccountEntity.getValidateCode(), userContext,SmsCodeDeleteState.DELETE_CODE.getCode());
		//短信验证码校验不成功
		if(checkState.getCode() != SmsSecurityCheckState.CHECK_SUCCESS.getCode()){
			result.setResult(null);
			result.setResultMsg(checkState.getMsg());
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			return result;
		}
		MemberAccountEntity memberAccountExists=memberAccountMapper.getMemberAccountByPhoneNumber(memberAccountEntity.getLoginName());
		if(memberAccountExists != null){
			result.setResult(null);
			result.setResultMsg("账号已经被使用！");
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			return result;
		}
		//修改账号信息
		MemberAccountEntity memberAccount = memberAccountMapper.getMemberAccountByOpenId(memberAccountEntity.getOpenId());
		memberAccount.setLoginName(memberAccountEntity.getLoginName());
		String memberId = memberAccount.getId();
		//对密码加密
		String passWord = DESEncrypt.encryptPBKDF2(memberAccountEntity.getLoginPassword(), memberId);
		memberAccount.setLoginPassword(passWord);
		memberAccountMapper.update(memberAccount);
		//修改分销商信息
		SellerInfoEntity sellerInfo = sellerInfoService.getSellerByMemberId(memberId);
		if(sellerInfo != null){
			sellerInfo.setLoginName(memberAccountEntity.getLoginName());
			sellerInfoService.update(sellerInfo);
			
			//保存分销商财务账户信息
			FinSellerInfoInputDto finSellerInfoDto = new FinSellerInfoInputDto();			
			finSellerInfoDto.setSellerAccount(memberAccountEntity.getLoginName());						
			finSellerInfoDto.setLatelyTransactionTime(new Date(System.currentTimeMillis()));	
			finSellerInfoDto.setId(sellerInfo.getId());
			finSellerInfoFrontApi.updateSeller(finSellerInfoDto);
		}
		//修改店铺信息
		ShopEntity shopEntity = shopService.getShopByMemberId(memberId);
		if(shopEntity != null){
			shopEntity.setLoginName(memberAccountEntity.getLoginName());
			shopService.update(shopEntity);
		}
		
		
		if("h5".equals(type)){
			memberAccount = wxMemberLogin(memberAccountEntity.getOpenId(),"", userContext);
		}else{
			memberAccount = wxMemberLoginForApp(memberAccountEntity.getOpenId(), userContext);
		}
		result.setResult(memberAccount);
		result.setResultMsg("绑定成功！");
		result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
		return result;
	}
	
	/**
	 * 过滤掉超过3个字节的UTF8字符
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String filterOffUtf8Mb4(String text) {
		byte[] bytes;
		try {
			bytes = text.getBytes("utf-8");
			ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
			int i = 0;
			while (i < bytes.length) {
				short b = bytes[i];
				if (b > 0) {
					buffer.put(bytes[i++]);
					continue;
				}

				b += 256; // 去掉符号位

				if (((b >> 5) ^ 0x6) == 0) {
					buffer.put(bytes, i, 2);
					i += 2;
				} else if (((b >> 4) ^ 0xE) == 0) {
					buffer.put(bytes, i, 3);
					i += 3;
				} else if (((b >> 3) ^ 0x1E) == 0) {
					i += 4;
				} else if (((b >> 2) ^ 0x3E) == 0) {
					i += 5;
				} else if (((b >> 1) ^ 0x7E) == 0) {
					i += 6;
				} else {
					buffer.put(bytes[i++]);
				}
			}
			buffer.flip();
			return new String(buffer.array(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("过滤非UTF8字符出现异常",e);
		}
		return "";
	}
	
	@Override
	public PageModel<MemberAccountOutputDto> queryFansList(String sellerId,
			PageModel pageModel) {
		int totalCount = memberAccountMapper.queryFansListCount(sellerId);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		List<MemberAccountEntity> list = memberAccountMapper.queryFansList(sellerId,rowBounds);
		//处理头像图片链接
		for(MemberAccountEntity dto : list){
			String headShowImg = dto.getHeadShowImg();
			if(StringUtils.isEmpty(headShowImg)){
				dto.setHeadShowImg( PicPathUtil.getImageAbsUrl(Constant.WFX_MEMBER_LOGO_DEFAULT_PIC_URL) ); 
			}else if( headShowImg.startsWith( Constant.WX_IMG_PREFIX ) ){
				dto.setHeadShowImg( headShowImg.replace(Constant.WX_IMG_PREFIX, "") );
			}else{
				dto.setHeadShowImg(PicPathUtil.getImageAbsUrl(headShowImg));
			}
		}
		return new PageModel<MemberAccountOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<MemberAccountOutputDto>) BeanUtil.convertBeanList(list,MemberAccountOutputDto.class));
		
	}

	@Override
	public Integer queryFansListCount(String sellerId) {
		// TODO Auto-generated method stub
		return memberAccountMapper.queryFansListCount(sellerId);
	}

	@Override
	public String getParentMemberId(String memberId) {
		return memberAccountMapper.getParentMemberIdByMemberId(memberId);
	}
}