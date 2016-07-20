 /*
 * 版本信息
 
 * 日期 2016-03-24 18:30:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.member.model.MemberAccountEntity;

/**
 * MemberAccountMapper
 * @author luoq
 * @Date 创建时间：2016-03-24 18:30:55
 */
public interface MemberAccountMapper{
	
	int findPageCount(MemberAccountEntity memberAccountEntity);

	List<MemberAccountEntity> findPage(MemberAccountEntity memberAccountEntity,RowBounds rowBounds);
	
	int insert(MemberAccountEntity memberAccountEntity);
	
	int update(MemberAccountEntity memberAccountEntity);
	
	int remove(String id);
	
	MemberAccountEntity getById(String id);
	
	MemberAccountEntity getMemberInfoById(String id);
	
	/**
	 * 前端注册，手机号校验重复：根据手机号查询用户数量
	 * @param phone
	 * @return
	 */
	int getCountByPhone(String phone);
	
	/**
	 * 用户登录
	 * @param loginName
	 * @param loginPassword
	 * @return
	 */
	MemberAccountEntity memberLogin(@Param("loginName") String loginName, @Param("loginPassword") String loginPassword);
	
	/**
	 * B端用户登录
	 * @param loginName
	 * @param loginPassword
	 * @return
	 */
	MemberAccountEntity memberLoginForApp(@Param("loginName") String loginName, @Param("loginPassword") String loginPassword);
	
	/**
	 * 修改用户登录密码
	 * @param memberId
	 * @param newPassword
	 * @return
	 */
	int updatePassword(@Param("memberId") String memberId, @Param("newPassword") String newPassword);
	
	/**
	 * 根据手机号获取用户信息
	 * @param phoneNubmer
	 * @return
	 */
	MemberAccountEntity getMemberAccountByPhoneNumber(String phoneNubmer);
	
	/**
	 * 校验用户密码是否正确
	 * @param memberId
	 * @param passWord
	 * @return
	 */
	int checkMemberPassWord(@Param("memberId") String memberId, @Param("oldPassword") String oldPassword);
	
	/**
	 * 后台分销商自动审核job,审核通过批量修改用户身份为分销商
	 * @param list
	 * @return
	 */
	int batchUpdateMemberType(List<MemberAccountEntity> list);
	
	/**
	 * 根据openId用户信息
	 * @param phoneNubmer
	 * @return
	 */
	MemberAccountEntity getMemberAccountByOpenId(String openId);
	/*查粉丝数目*/
	int queryFansListCount(@Param("sellerId")String sellerId);
	/*查粉丝列表*/
	List<MemberAccountEntity> queryFansList(String sellerId, RowBounds rowBounds);
	
	String getParentMemberIdByMemberId(String memberId);
}
