 /*
 * 版本信息
 
 * 日期 2016-03-25 17:36:56
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.member.dao.MemberAddressMapper;
import com.yougou.wfx.member.model.MemberAddressEntity;
import com.yougou.wfx.member.service.IMemberAddressService;

/**
 * IMemberAddressService实现
 * @author wfx
 * @Date 创建时间：2016-03-25 17:36:56
 */
@Service
public class MemberAddressServiceImpl extends BaseService implements IMemberAddressService {
	
	@Resource
	private MemberAddressMapper memberAddressMapper;

	@Override
	public int findPageCount(MemberAddressEntity memberAddressEntity){
		return memberAddressMapper.findPageCount(memberAddressEntity);
	}

	@Override
	public List<MemberAddressEntity> findPage(MemberAddressEntity memberAddressEntity,RowBounds rowBounds){
		return memberAddressMapper.findPage(memberAddressEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(MemberAddressEntity memberAddressEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		memberAddressEntity.setId(strId);
		int count = memberAddressMapper.findPageCount(new MemberAddressEntity());
		if(count == 0){
			memberAddressEntity.setIsDefaultAddress(1);
		}else if(memberAddressEntity.getIsDefaultAddress() == 1){
			memberAddressMapper.updateAllMemberAddressNotDefault(memberAddressEntity.getLoginacccountId());
		}
		memberAddressEntity.setCreateTime(new Date(System.currentTimeMillis()));
		memberAddressEntity.setLastUpdateTime(new Date(System.currentTimeMillis()));
		memberAddressMapper.insert(memberAddressEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(MemberAddressEntity memberAddressEntity){
		if(memberAddressEntity.getIsDefaultAddress() == 1){
			memberAddressMapper.updateAllMemberAddressNotDefault(memberAddressEntity.getLoginacccountId());
		}
		memberAddressEntity.setLastUpdateTime(new Date(System.currentTimeMillis()));
		return  memberAddressMapper.update(memberAddressEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return memberAddressMapper.remove(id);
	}
	
	@Override
	public MemberAddressEntity getById(String id){
		return memberAddressMapper.getById(id);
	}

	@Override
	public List<MemberAddressEntity> queryList(
			MemberAddressEntity memberAddressEntity) {
		return memberAddressMapper.queryList(memberAddressEntity);
	} 
}