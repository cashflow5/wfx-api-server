 /*
 * 版本信息
 
 * 日期 2016-03-24 18:30:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.api.front;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.UserContext;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.member.dto.input.MemberAccountInputDto;
import com.yougou.wfx.member.dto.input.MemberForWXInputDto;
import com.yougou.wfx.member.dto.input.MemberProfileInputDto;
import com.yougou.wfx.member.dto.output.MemberAccountOutputDto;
import com.yougou.wfx.member.dto.output.MemberProfileOutputDto;

/**
 * IMemberAccountFrontApi
 * @author luoq
 * @Date 创建时间：2016-03-24 18:30:55
 */
public interface IMemberAccountFrontApi{
	
	/**
	 * 根据用户ID 获取用户信息（含基本信息，店铺信息，分销商信息，在MemberAccountOutputDto店铺、基本信息、分销商信息DTO属性内）
	 * @param memberId 用户ID
	 * @return MemberAccountOutputDto 用户信息
	 * @author zhang.f
	 */
	public MemberAccountOutputDto getMemberAccountById(String memberId);
	

	/**
	 * C端校验手机号是否已经注册
	 * @param phone 手机号
	 * @param userContext 请求信息
	 * @return true:已注册 false:未注册
	 * @author zhang.f
	 */
	WFXResult<Boolean> checkPhoneRegiter(String phone, UserContext userContext);
	
	/**
	 * 发送短信验证码
	 * @param phone 手机号
	 * @param context 请求信息
	 * @return true:发送成功，false:发送失败
	 */
	WFXResult<Boolean> sendPhoneCode(String phone,UserContext context);
	
	/**
	 * 校验短信验证码
	 * @param phone 手机号
	 * @param context 请求信息
	 * @return true:校验通过，false:校验失败
	 */
	WFXResult<Boolean> checkPhoneCode(String phone,String code,UserContext context);
	
	
	/**
	 * C端用户注册
	 * @param memberAccountInputDto 用户信息：登陆账号（必输）、密码 （必输）、潜在上级分销商ID（必输）
	 * @param code 短信验证码 （必输）
	 * @param userContext 请求信息  IP地址 （必输）、sessionId（必输）
	 * @return true:成功，false:失败 
	 * @throws Exception
	 */
	WFXResult<Boolean> registerMember(MemberAccountInputDto memberAccountInputDto,String code, UserContext userContext);
	
	/**
	 * 用户登陆（普通用户 C端）
	 * @param loginName:注册账号,passWord:登陆密码
	 * @return MemberAccountOutputDto 用户信息
	 * @author zhang.f
	 */
	MemberAccountOutputDto memberLogin(String loginName, String passWord, UserContext userContext);
	
	/**
	 * 用户修改密码（找回密码时重置密码）
	 * @param userId 用户ID
	 * @param newPassWord  要设置的新密码
	 * @param phone  手机号：登录账号
	 * @param code  短信验证码
	 * @param userContext 请求信息
	 * @return true:成功 false:失败
	 * @author zhang.f
	 */
	WFXResult<Boolean> updatePassWord(String userId,String newPassWord,String phone, String code, UserContext userContext);
	
	/**
	 * 用户登陆后修改密码（登陆后修改密码）
	 * @param userId 用户ID
	 * @param oldPassWord  旧密码
	 * @param newPassWord  要设置的新密码
	 * @param userContext 请求信息
	 * @return true:成功 false:失败
	 * @author zhang.f
	 */
	WFXResult<Boolean> updatePassWordAfterLogin(String userId,String oldPassWord,String newPassWord, UserContext userContext);
	
	/**
	 * 用户登陆（分销商用户 B端）
	 * @param loginName:注册账号,passWord:登陆密码
	 * @return MemberAccountOutputDto 用户信息（含分销商，店铺）
	 * @author zhang.f
	 */
	MemberAccountOutputDto memberLoginForApp(String loginName, String passWord, UserContext userContext);
	

	/**
	 * 修改用户账户基本信息
	 * @param memberProfileDto 账户信息
	 * @return true:成功 false:失败
	 * @author zhang.f
	 */
	WFXResult<Boolean> updateMemberProfile(MemberProfileInputDto memberProfileDto);
	
	/**
	 * 根据用户id查询用户账户基本信息
	 * @param memberId 用户ID
	 * @return MemberProfileOutputDto 用户账户基本信息
	 * @author zhang.f
	 */
	MemberProfileOutputDto getMemberProfileByMemberId(String memberId);
	
	/**
	 * 根据手机号获取用户信息(不含基本信息，店铺信息，分销商信息)
	 * @param nubmer    手机号
	 * @return  
	 */ 
	MemberAccountOutputDto getMemberAccountByPhoneNumber(String nubmer);

	/**
	 * 个人中心上传用户头像
	 * @param memberId   用户id
	 * @param fileName   文件名
	 * @param in        输入流
	 * @return  WFXResult<Boolean> true：成功 false:失败
	 * 与C端同事协商后此接口不用提供，于2016.4.18 屏蔽
	 */ 
	//WFXResult<Boolean> updateUserHeadImg(String memberId, String fileName,InputStream in);

	/**
	 * 微信登录H5接口
	 * @param openId，h5OpenId 必须
	 * @param userContext
	 * @return
	 */
	MemberAccountOutputDto wxMemberLogin(MemberForWXInputDto memberForWXInputDto, UserContext userContext);
	
	/**
	 * 微信用户登录接口, MemberForWXInputDto openId,nickname,sex,headimgurl,parentSellerId 必须
	 * @param memberForWXInputDto
	 * @param userContext
	 * @return
	 */
	WFXResult<MemberAccountOutputDto> wxMemberRegister(MemberForWXInputDto memberForWXInputDto, UserContext userContext);
	
	/**
	 * 微信分销商登录接口,如果返回null，表示无微信登录数据，需要先在H5登录并申请开店
	 * @param memberAccountInputDto
	 * @param userContext
	 * @return
	 */
	MemberAccountOutputDto wxMemberLoginForApp(String openId, UserContext userContext);
	
	/**
	 * 手机号登录绑定微信
	 * @param memberForWXInputDto openId,nickname,sex,headimgurl,memberId 必须
	 * @param type  app/h5
	 * @return
	 */
	WFXResult<MemberAccountOutputDto> wxSellerBindForApp(MemberForWXInputDto memberForWXInputDto,String type,	UserContext userContext);
	
	/**
	 * 微信绑定手机号 
	 * @param memberAccountInputDto  loginName,loginPassword,openId,validateCode 必须
	 * @return
	 */
	WFXResult<MemberAccountOutputDto> phoneSellerBindForApp(MemberAccountInputDto memberAccountInputDto,String type, UserContext userContext);

	/**
	 *  查询粉丝列表 
	 */
	PageModel<MemberAccountOutputDto> queryFansList(String sellerId,PageModel pageModel);


	/**
	 *  查询粉丝数目 
	 */
	Integer queryFansListCount(String sellerId);


	String getParentMemberId(String memberId);
}

