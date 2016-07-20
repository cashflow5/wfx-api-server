 /*
 * 版本信息
 
 * 日期 2016-03-24 18:30:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.UserContext;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.member.dto.input.MemberForWXInputDto;
import com.yougou.wfx.member.dto.output.MemberAccountOutputDto;
import com.yougou.wfx.member.model.MemberAccountEntity;

/**
 * IMemberAccountService接口
 * @author luoq
 * @Date 创建时间：2016-03-24 18:30:55
 */
public interface IMemberAccountService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(MemberAccountEntity memberAccountEntity);

	/**
	 * 获取分页数据
	 */
	public List<MemberAccountEntity> findPage(MemberAccountEntity memberAccountEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(MemberAccountEntity memberAccountEntity);
	
	/**
	 * 更新记录
	 */
	public int update(MemberAccountEntity memberAccountEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	MemberAccountEntity getById(String id);
	/**
	 * 通过id查询数据
	 */
	public MemberAccountEntity getMemberInfoById(String id); 
	
	/**
	 * 前端注册，手机号校验重复：根据手机号查询用户数量
	 * @param phone
	 * @return
	 */
	int getCountByPhone(String phone);
	
	/**
	 * 根据手机号获取用户信息
	 * @param phoneNubmer
	 * @return
	 */
	MemberAccountEntity getMemberAccountByPhoneNumber(String phoneNubmer);
	
	/**
	 * 用户注册
	 * @param memberAccountInputDto 用户信息
	 * @param code  短信验证码
	 * @param userContext  请求信息
	 * @return
	 * @throws Exception
	 */
	WFXResult<Boolean> registerMember(MemberAccountEntity memberAccountEntity,String code, UserContext userContext) throws Exception ;
	
	/**
	 * 用户登陆（普通用户 C端）
	 * @param loginName:注册账号,passWord:登陆密码
	 * @return MemberAccountEntity 用户信息（不含店铺，分销商信息）
	 * @author zhang.f
	 */
	MemberAccountEntity memberLogin(String loginName, String passWord, UserContext userContext);

	
	/**
	 * B端用户登陆 
	 * @param loginName:注册账号,passWord:登陆密码
	 * @return MemberAccountEntity 用户信息（含店铺，分销商信息）
	 * @author zhang.f
	 */
	MemberAccountEntity memberLoginForApp(String loginName, String passWord, UserContext userContext);
	
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
	 * 登陆后修改密码
	 * @param userId 用户id
	 * @param oldPassWord  旧密码
	 * @param newPassWord  新密码
	 * @return
	 */
	WFXResult<Boolean> updatePassWordAfterLogin(String userId,String oldPassWord, String newPassWord);
	
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
	
	WFXResult<MemberAccountEntity> wxMemberRegister(MemberForWXInputDto memberForWXInputDto, UserContext userContext);
	
	MemberAccountEntity wxMemberLogin(String openId, String h5OpenId, UserContext userContext);
	
	MemberAccountEntity wxMemberLoginForApp(String openId, UserContext userContext);

	WFXResult<MemberAccountEntity> wxSellerBindForApp(MemberForWXInputDto memberForWXInputDto,String type,UserContext userContext);

	WFXResult<MemberAccountEntity> phoneSellerBindForApp(MemberAccountEntity memberAccountEntity,String type, UserContext userContext);

	PageModel<MemberAccountOutputDto> queryFansList(String sellerId,PageModel pageModel);

	Integer queryFansListCount(String sellerId);

	String getParentMemberId(String memberId);
	
}