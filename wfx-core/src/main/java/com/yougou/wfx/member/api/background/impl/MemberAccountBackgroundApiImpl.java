 /*
 * 版本信息
 
 * 日期 2016-03-24 18:30:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.member.api.background.IMemberAccountBackgroundApi;
import com.yougou.wfx.member.dto.input.MemberAccountInputDto;
import com.yougou.wfx.member.dto.input.MemberAccountPageInputDto;
import com.yougou.wfx.member.dto.output.MemberAccountOutputDto;
import com.yougou.wfx.member.model.MemberAccountEntity;
import com.yougou.wfx.member.service.IMemberAccountService;

/**
 * IMemberAccountBackgroundApi实现
 * @author luoq
 * @Date 创建时间：2016-03-24 18:30:55
 */
@Service
public class MemberAccountBackgroundApiImpl implements IMemberAccountBackgroundApi{
	
	@Resource
	IMemberAccountService memberAccountService;
	
	@Override
	public int removeById(String id) {
		return memberAccountService.remove(id);
	}

	@Override
	public String insert(MemberAccountInputDto memberAccountDto) {
		return memberAccountService.insert(this.dtoToEntity(memberAccountDto));
	}

	@Override
	public PageModel<MemberAccountOutputDto> findPage(MemberAccountPageInputDto pageInputDto,PageModel<MemberAccountOutputDto> pageModel) {
		MemberAccountEntity memberAccountEntity = (MemberAccountEntity) BeanUtil.convertBean(pageInputDto,MemberAccountEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = memberAccountService.findPageCount(memberAccountEntity);
		List<MemberAccountEntity> lstMemberAccounts = memberAccountService.findPage(memberAccountEntity, rowBounds);

		return new PageModel<MemberAccountOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<MemberAccountOutputDto>) BeanUtil.convertBeanList(lstMemberAccounts,MemberAccountOutputDto.class));
	}

	@Override
	public int update(MemberAccountInputDto memberAccountDto) {
		return memberAccountService.update(this.dtoToEntity(memberAccountDto));
	}

	@Override
	public MemberAccountOutputDto getMemberInfoById(String id) {
		MemberAccountEntity memberAccountEntity = memberAccountService.getMemberInfoById(id);
		return this.entityToDto(memberAccountEntity);
	}
	
	private MemberAccountEntity dtoToEntity(Object obj){
		return (MemberAccountEntity) BeanUtil.convertBean(obj,MemberAccountEntity.class);
	}
	
	private MemberAccountOutputDto entityToDto(Object obj){
		return (MemberAccountOutputDto) BeanUtil.convertBean(obj,MemberAccountOutputDto.class);
	}
}
