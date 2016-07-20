 /*
 * 版本信息
 
 * 日期 2016-03-24 18:30:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.api.front.impl;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yougou.wfx.common.constant.Constant;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.UserContext;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.annotation.reqfilter.ReqFilter;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.member.api.front.IMemberAccountFrontApi;
import com.yougou.wfx.member.dto.input.MemberAccountInputDto;
import com.yougou.wfx.member.dto.input.MemberForWXInputDto;
import com.yougou.wfx.member.dto.input.MemberProfileInputDto;
import com.yougou.wfx.member.dto.output.MemberAccountOutputDto;
import com.yougou.wfx.member.dto.output.MemberProfileOutputDto;
import com.yougou.wfx.member.model.MemberAccountEntity;
import com.yougou.wfx.member.model.MemberProfileEntity;
import com.yougou.wfx.member.service.IMemberAccountService;
import com.yougou.wfx.member.service.IMemberProfileService;
import com.yougou.wfx.seller.service.ISellerInfoService;
import com.yougou.wfx.shop.service.IShopService;
import com.yougou.wfx.util.ApiConstant;
import com.yougou.wfx.utils.PicPathUtil;

/**
 * IMemberAccountFrontApi实现
 * @author luoq
 * @Date 创建时间：2016-03-24 18:30:55
 */
@Service
public class MemberAccountFrontApiImpl implements IMemberAccountFrontApi{
	
	@Resource
	private IMemberAccountService memberAccountService;
	
	@Resource
	private IMemberProfileService memberProfileService;
	
	@Resource
	private IShopService shopService;
	
	@Resource
	private ISellerInfoService  sellerInfoService;
	
	private Logger logger = LoggerFactory.getLogger(MemberAccountFrontApiImpl.class);
	
	private MemberAccountEntity dtoToEntity(Object obj){
		return (MemberAccountEntity) BeanUtil.convertBean(obj,MemberAccountEntity.class);
	}
	
	private MemberAccountOutputDto entityToDto(MemberAccountEntity obj){
		if( obj!=null ){
			MemberAccountOutputDto result = (MemberAccountOutputDto) BeanUtil.convertBean(obj,MemberAccountOutputDto.class);
			MemberProfileOutputDto profile = result.getProfileInfo();
			if(profile!=null){
				MemberProfileEntity profileEntity = (MemberProfileEntity) BeanUtil.convertBean(profile,MemberProfileEntity.class);
				result.setProfileInfo( profileEntityToDto(profileEntity));
			}
			return result;
		}else{
			return null;
		}
	}
	
	//@ReqFilter(keySpel="'getMemberAccountById_'+#id" ,limit=10 ,time=60000)
	@LoggerProfile(methodNote = "根据用户ID查询用户信息接口")
	@Override
	public MemberAccountOutputDto getMemberAccountById(@NotBlank String id) {
		// TODO Auto-generated method stub
		MemberAccountEntity memberAccountEntity = memberAccountService.getMemberInfoById(id);
		return this.entityToDto(memberAccountEntity);
	}
	
	@ReqFilter(keySpel="'checkPhoneRegiter_'+#phone" ,limit=5 ,time=60000)
	@LoggerProfile(methodNote = "校验手机号能否注册接口")
	@Override
	public WFXResult<Boolean> checkPhoneRegiter(@NotBlank String phone, 
			@NotBlank.List(value={@NotBlank(target="clientIp"),@NotBlank(target="sessionId")}) UserContext userContext) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result = new WFXResult<Boolean>();
		result.setResult(false);
		int count = memberAccountService.getCountByPhone(phone);		
		if(count > 0){
			result.setResult(true);
		}
		return result;
	}
	
	@LoggerProfile(methodNote = "用户注册接口")
	@Override
	public WFXResult<Boolean> registerMember(@NotBlank.List(value={@NotBlank(target="loginPassword"),
			@NotBlank(target="loginName"),@NotBlank(target="parentSellerId")})MemberAccountInputDto memberAccountInputDto,
			@NotBlank String code,@NotBlank.List(value={@NotBlank(target="clientIp"),@NotBlank(target="sessionId")}) UserContext userContext)  {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result;
		try {
			MemberAccountEntity memberAccountEntity = this.dtoToEntity(memberAccountInputDto);
			if(memberAccountEntity!=null && memberAccountEntity.getParentSellerId().equals("-1")){//与前端约定 -1 代表 总经销商
				memberAccountEntity.setParentSellerId( ApiConstant.ORIGINAL_SELLER_ID_DEFAULT );
			}
			result = memberAccountService.registerMember(memberAccountEntity, code, userContext);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("注册用户失败：", e);
			result = new WFXResult<Boolean>();
			result.setResult(false);
			result.setResultMsg("注册时系统异常");
			return result;
		}
		return result;
	}
	
	//@ReqFilter(keySpel="'memberLogin_'+#loginName" ,limit=10 ,time=60000)
	@LoggerProfile(methodNote = "C端用户登录接口")
	@Override
	public MemberAccountOutputDto memberLogin(@NotBlank String loginName, @NotBlank String passWord,
			@NotBlank.List(value={@NotBlank(target="clientIp"),@NotBlank(target="sessionId")}) UserContext userContext) {
		MemberAccountEntity memberAccountEntity = memberAccountService.memberLogin(loginName, passWord, userContext);
		MemberAccountOutputDto memberAccountOutputDto = this.entityToDto(memberAccountEntity);
		return memberAccountOutputDto;
	}
	
	
	@LoggerProfile(methodNote = "修改登录密码接口")
	@Override
	public WFXResult<Boolean> updatePassWord(@NotBlank String userId, @NotBlank String newPassword,@NotBlank String phone, @NotBlank String code,
			@NotBlank.List(value={@NotBlank(target="clientIp"),@NotBlank(target="sessionId")}) UserContext userContext) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result = memberAccountService.updatePassWord(userId, newPassword, phone, code, userContext);
		return result;
	}
	
	//@ReqFilter(keySpel="'memberLoginForApp_'+#loginName" ,limit=10 ,time=60000)
	@LoggerProfile(methodNote = "B端登录接口")
	@Override
	public MemberAccountOutputDto memberLoginForApp(@NotBlank String loginName, @NotBlank String passWord,
			@NotBlank.List(value={@NotBlank(target="clientIp"),@NotBlank(target="sessionId")}) UserContext userContext) {
		// TODO Auto-generated method stub
		MemberAccountEntity memberAccountEntity = memberAccountService.memberLoginForApp(loginName, passWord, userContext);
		MemberAccountOutputDto memberOutputDto =  this.entityToDto(memberAccountEntity);
		return memberOutputDto;
	}
	
	@LoggerProfile(methodNote = "修改用户基本信息接口")
	@Override
	public WFXResult<Boolean> updateMemberProfile(@NotBlank.List(value={@NotBlank(target="id")})MemberProfileInputDto memberProfileDto) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result = new WFXResult<Boolean>();
		MemberProfileEntity profileEntity = (MemberProfileEntity) BeanUtil.convertBean(memberProfileDto,MemberProfileEntity.class);
		if(StringUtils.isBlank(profileEntity.getHeadShowImg())){
			profileEntity.setHeadShowImg(Constant.WFX_MEMBER_LOGO_DEFAULT_PIC_URL);
		}
		int count = memberProfileService.update(profileEntity);
		if(count > 0){
			result.setResult(true);
			result.setResultMsg("修改基本信息成功");
		}else{
			result.setResult(false);
			result.setResultMsg("用户不存在，修改失败");
		}
		return result;
	}
	
	@LoggerProfile(methodNote = "根据用户ID获取基本信息接口")
	@Override
	public MemberProfileOutputDto getMemberProfileByMemberId(@NotBlank String memberId) {
		// TODO Auto-generated method stub
		MemberProfileEntity profileEntity = memberProfileService.getMemberProfileByMemberId(memberId);
		return profileEntityToDto(profileEntity);
	}
	
	@LoggerProfile(methodNote = "根据手机号获取账户信息接口")
	@Override
	public MemberAccountOutputDto getMemberAccountByPhoneNumber(@NotBlank String nubmer) {
		// TODO Auto-generated method stub
		MemberAccountEntity memberAccountEntity = memberAccountService.getMemberAccountByPhoneNumber(nubmer);
		return entityToDto(memberAccountEntity);
	}

	/*@Override
	public WFXResult<Boolean> updateUserHeadImg(String memberId,
			String fileName, InputStream in) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	@ReqFilter(keySpel="'sendPhoneCode_'+#phone" ,limit=5 ,time=60000)
	@LoggerProfile(methodNote = "发送短信验证码接口")
	@Override
	public WFXResult<Boolean> sendPhoneCode(@NotBlank @NotNull String phone,@net.sf.oval.constraint.NotBlank.List(
			value={@NotBlank(target="clientIp"),@NotBlank(target="sessionId")}) UserContext context) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result = memberAccountService.sendPhoneCode(phone, context);
		return result;
	}
	
	@ReqFilter(keySpel="'checkPhoneCode_'+#phone" ,limit=5 ,time=60000)
	@LoggerProfile(methodNote = "校验短信验证码接口")
	@Override
	public WFXResult<Boolean> checkPhoneCode(@NotBlank @NotNull String phone, @NotBlank @NotNull String code,
			@net.sf.oval.constraint.NotBlank.List(value={@NotBlank(target="clientIp"),@NotBlank(target="sessionId")})  UserContext context) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result = memberAccountService.checkPhoneCode(phone, code, context);
		return result;
	}
	
	@LoggerProfile(methodNote = "用户登录后修改密码接口")
	@Override
	public WFXResult<Boolean> updatePassWordAfterLogin(@NotBlank @NotNull String userId,@NotBlank @NotNull String oldPassWord,
			@NotBlank @NotNull String newPassWord,
			@NotBlank.List(value={@NotBlank(target="clientIp"),@NotBlank(target="sessionId")}) UserContext userContext) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result = memberAccountService.updatePassWordAfterLogin(userId, oldPassWord, newPassWord);
		return result;
	}

	@Override
	@LoggerProfile(methodNote = "微信用户注册接口")
	public WFXResult<MemberAccountOutputDto> wxMemberRegister(MemberForWXInputDto memberForWXInputDto, UserContext userContext) {
		WFXResult<MemberAccountEntity> result = memberAccountService.wxMemberRegister(memberForWXInputDto, userContext);
		WFXResult<MemberAccountOutputDto> outputResult = new WFXResult<MemberAccountOutputDto>();
		outputResult.setResultCode(result.getResultCode());
		outputResult.setResultMsg(result.getResultMsg());
		if(result.getResult() != null){
			MemberAccountOutputDto memberAccountOutputDto = this.entityToDto(result.getResult());
			outputResult.setResult(memberAccountOutputDto);
		}
		return outputResult;
	}
	
	@Override
	@LoggerProfile(methodNote = "微信分销商登录接口")
	public MemberAccountOutputDto wxMemberLoginForApp(String openId, UserContext userContext) {
		MemberAccountEntity memberAccountEntity = memberAccountService.wxMemberLoginForApp(openId, userContext);
		MemberAccountOutputDto memberAccountOutputDto = this.entityToDto(memberAccountEntity);
		return memberAccountOutputDto;
	}

	@Override
	@LoggerProfile(methodNote = "微信普通用户登录接口")
	public MemberAccountOutputDto wxMemberLogin(@NotBlank.List(value={@NotBlank(target="openId")}) MemberForWXInputDto memberForWXInputDto,	UserContext userContext) {
		MemberAccountEntity memberAccountEntity = memberAccountService.wxMemberLogin(memberForWXInputDto.getOpenId(), memberForWXInputDto.getH5OpenId(), userContext);
		MemberAccountOutputDto memberAccountOutputDto = this.entityToDto(memberAccountEntity);
		return memberAccountOutputDto;
	}

	@Override
	@LoggerProfile(methodNote = "绑定微信账号接口")
	public WFXResult<MemberAccountOutputDto> wxSellerBindForApp(MemberForWXInputDto memberForWXInputDto,String type,UserContext userContext) {
		WFXResult<MemberAccountEntity> result = memberAccountService.wxSellerBindForApp(memberForWXInputDto,type,userContext);
		WFXResult<MemberAccountOutputDto> outputResult = new WFXResult<MemberAccountOutputDto>();
		outputResult.setResultCode(result.getResultCode());
		outputResult.setResultMsg(result.getResultMsg());
		if(result.getResult() != null){
			MemberAccountOutputDto memberAccountOutputDto = this.entityToDto(result.getResult());
			outputResult.setResult(memberAccountOutputDto);
		}
		return outputResult;
	}

	@Override
	@LoggerProfile(methodNote = "微信绑定手机号接口")
	public WFXResult<MemberAccountOutputDto> phoneSellerBindForApp(
			MemberAccountInputDto memberAccountInputDto,String type, UserContext userContext) {
		MemberAccountEntity memberAccountEntity = dtoToEntity(memberAccountInputDto);
		WFXResult<MemberAccountEntity> result = memberAccountService.phoneSellerBindForApp(memberAccountEntity,type,userContext);
		WFXResult<MemberAccountOutputDto> outputResult = new WFXResult<MemberAccountOutputDto>();
		outputResult.setResultCode(result.getResultCode());
		outputResult.setResultMsg(result.getResultMsg());
		if(result.getResult() != null){
			MemberAccountOutputDto memberAccountOutputDto = this.entityToDto(result.getResult());
			outputResult.setResult(memberAccountOutputDto);
		}
		return outputResult;
	}
	
	private MemberProfileOutputDto profileEntityToDto(MemberProfileEntity obj){
		if(obj!=null){
			String headShowImg = obj.getHeadShowImg();
			if(StringUtils.isEmpty(headShowImg)){
				obj.setHeadShowImg( PicPathUtil.getImageAbsUrl(Constant.WFX_MEMBER_LOGO_DEFAULT_PIC_URL) ); 
			}else if( headShowImg.startsWith( Constant.WX_IMG_PREFIX ) ){
				obj.setHeadShowImg( headShowImg.replace(Constant.WX_IMG_PREFIX, "") );
			}else{
				//obj.setHeadShowImg(PicPathUtil.getImageAbsUrl(headShowImg));
				obj.setHeadShowImg(headShowImg);
			}
			return (MemberProfileOutputDto) BeanUtil.convertBean(obj,MemberProfileOutputDto.class);
		}
		return null;
	}

	@LoggerProfile(methodNote = "根据分销商ID获取粉丝列表接口")
	@Override
	public PageModel<MemberAccountOutputDto> queryFansList(String sellerId,PageModel pageModel) {
		return memberAccountService.queryFansList(sellerId,pageModel);
	}
	
	@LoggerProfile(methodNote = "根据分销商ID获取粉丝数目接口")
	@Override
	public Integer queryFansListCount(@NotBlank String sellerId){
		return memberAccountService.queryFansListCount(sellerId);
	}
	
	@LoggerProfile(methodNote = "根据用户ID获取父用户ID")
	@Override
	public String getParentMemberId(String memberId){
		return memberAccountService.getParentMemberId(memberId);
	}
}
