 /*
 * 版本信息
 
 * 日期 2016-03-25 17:36:56
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.member.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.member.model.MemberAddressEntity;

/**
 * MemberAddressMapper
 * @author wfx
 * @Date 创建时间：2016-03-25 17:36:56
 */
public interface MemberAddressMapper{
	
	int findPageCount(MemberAddressEntity memberAddressEntity);

	List<MemberAddressEntity> findPage(MemberAddressEntity memberAddressEntity,RowBounds rowBounds);

	List<MemberAddressEntity> queryList(MemberAddressEntity memberAddressEntity);
	
	int insert(MemberAddressEntity memberAddressEntity);
	
	int update(MemberAddressEntity memberAddressEntity);
	
	int remove(String id);
	
	MemberAddressEntity getById(String id);
	
	int updateAllMemberAddressNotDefault(String loginacccountId);
}
