 /*
 * 版本信息
 
 * 日期 2016-03-24 16:17:44
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.member.dao.MemberProfileMapper;
import com.yougou.wfx.member.model.MemberProfileEntity;
import com.yougou.wfx.member.service.IMemberProfileService;

/**
 * IMemberProfileService实现
 * @author wuyang
 * @Date 创建时间：2016-03-24 16:17:44
 */
@Service
public class MemberProfileServiceImpl extends BaseService implements IMemberProfileService {
	
	@Resource
	private MemberProfileMapper memberProfileMapper;

	@Override
	public int findPageCount(MemberProfileEntity memberProfileEntity){
		return memberProfileMapper.findPageCount(memberProfileEntity);
	}

	@Override
	public List<MemberProfileEntity> findPage(MemberProfileEntity memberProfileEntity,RowBounds rowBounds){
		return memberProfileMapper.findPage(memberProfileEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(MemberProfileEntity memberProfileEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		memberProfileEntity.setId(strId);
		memberProfileMapper.insert(memberProfileEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(MemberProfileEntity memberProfileEntity){
		return  memberProfileMapper.update(memberProfileEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return memberProfileMapper.remove(id);
	}
	
	@Override
	public MemberProfileEntity getById(String id){
		return memberProfileMapper.getById(id);
	}

	@Override
	public MemberProfileEntity getMemberProfileByMemberId(String memberId) {
		return memberProfileMapper.getMemberProfileByMemberId(memberId);
	}

	@Override
	public int batchUpdateOrderAmountAndCount() {
		return memberProfileMapper.batchUpdateOrderAmountAndCount();
	} 
}