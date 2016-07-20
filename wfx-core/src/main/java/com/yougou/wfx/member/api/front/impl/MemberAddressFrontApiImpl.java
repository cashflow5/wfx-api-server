 /*
 * 版本信息
 
 * 日期 2016-03-25 17:36:56
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.member.api.front.IMemberAddressFrontApi;
import com.yougou.wfx.member.dto.input.MemberAddressInputDto;
import com.yougou.wfx.member.dto.input.MemberAddressPageInputDto;
import com.yougou.wfx.member.dto.output.MemberAddressOutputDto;
import com.yougou.wfx.member.model.MemberAddressEntity;
import com.yougou.wfx.member.service.IMemberAddressService;

/**
 * IMemberAddressFrontApi实现
 * @author wfx
 * @Date 创建时间：2016-03-25 17:36:56
 */
@Service
public class MemberAddressFrontApiImpl implements IMemberAddressFrontApi{
	
	@Resource
	IMemberAddressService memberAddressService;
	
	@Override
	@LoggerProfile(methodNote="删除会员地址")
	public int removeMemberAddressById(@NotBlank String id) {
		return memberAddressService.remove(id);
	}

	@Override
	@LoggerProfile(methodNote="新增会员地址")
	public String insertMemberAddress(@NotNull @NotBlank.List(value={@NotBlank(target="loginacccountId")
	,@NotBlank(target="receivingName"),@NotBlank(target="receivingProvince"),@NotBlank(target="receivingCity"),
	@NotBlank(target="receivingDistrict"),@NotBlank(target="receivingAddress"),@NotBlank(target="receivingMobilePhone")}) MemberAddressInputDto memberAddressDto) {
		return memberAddressService.insert(this.dtoToEntity(memberAddressDto));
	}

	@Override
	@LoggerProfile(methodNote="分页查询会员地址")
	public PageModel<MemberAddressOutputDto> findMemberAddressPage(@NotNull MemberAddressPageInputDto pageInputDto,@NotNull PageModel<MemberAddressOutputDto> pageModel) {
		MemberAddressEntity memberAddressEntity = (MemberAddressEntity) BeanUtil.convertBean(pageInputDto,MemberAddressEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = memberAddressService.findPageCount(memberAddressEntity);
		List<MemberAddressEntity> lstMemberAddresss = memberAddressService.findPage(memberAddressEntity, rowBounds);

		return new PageModel<MemberAddressOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<MemberAddressOutputDto>) BeanUtil.convertBeanList(lstMemberAddresss,MemberAddressOutputDto.class));
	}

	@Override
	@LoggerProfile(methodNote="更新会员地址")
	public int updateMemberAddress(@NotNull @NotBlank.List(value={@NotBlank(target="loginacccountId"),@NotBlank(target="id")}) MemberAddressInputDto memberAddressDto) {
		return memberAddressService.update(this.dtoToEntity(memberAddressDto));
	}

	@Override
	@LoggerProfile(methodNote="查询会员地址")
	public MemberAddressOutputDto getMemberAddressById(@NotBlank String id) {
		MemberAddressEntity memberAddressEntity = memberAddressService.getById(id);
		return this.entityToDto(memberAddressEntity);
	}
	
	private MemberAddressEntity dtoToEntity(Object obj){
		return (MemberAddressEntity) BeanUtil.convertBean(obj,MemberAddressEntity.class);
	}
	
	private MemberAddressOutputDto entityToDto(Object obj){
		return (MemberAddressOutputDto) BeanUtil.convertBean(obj,MemberAddressOutputDto.class);
	}
}
