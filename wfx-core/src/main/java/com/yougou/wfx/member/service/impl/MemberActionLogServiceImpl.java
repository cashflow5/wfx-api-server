 /*
 * 版本信息
 
 * 日期 2016-04-18 16:48:04
 
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
import com.yougou.wfx.member.dao.MemberActionLogMapper;
import com.yougou.wfx.member.model.MemberActionLogEntity;
import com.yougou.wfx.member.service.IMemberActionLogService;

/**
 * IMemberActionLogService实现
 * @author zheng.qq
 * @Date 创建时间：2016-04-18 16:48:05
 */
@Service
public class MemberActionLogServiceImpl extends BaseService implements IMemberActionLogService {
	
	@Resource
	private MemberActionLogMapper memberActionLogMapper;

	@Override
	public int findPageCount(MemberActionLogEntity memberActionLogEntity){
		return memberActionLogMapper.findPageCount(memberActionLogEntity);
	}

	@Override
	public List<MemberActionLogEntity> findPage(MemberActionLogEntity memberActionLogEntity,RowBounds rowBounds){
		return memberActionLogMapper.findPage(memberActionLogEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(MemberActionLogEntity memberActionLogEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		memberActionLogEntity.setId(strId);
		memberActionLogMapper.insert(memberActionLogEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(MemberActionLogEntity memberActionLogEntity){
		return  memberActionLogMapper.update(memberActionLogEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return memberActionLogMapper.remove(id);
	}
	
	@Override
	public MemberActionLogEntity getById(String id){
		return memberActionLogMapper.getById(id);
	} 
}