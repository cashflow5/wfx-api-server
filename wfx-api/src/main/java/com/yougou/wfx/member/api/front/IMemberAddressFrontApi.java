 /*
 * 版本信息
 
 * 日期 2016-03-25 17:36:56
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.api.front;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.member.dto.input.MemberAddressInputDto;
import com.yougou.wfx.member.dto.input.MemberAddressPageInputDto;
import com.yougou.wfx.member.dto.output.MemberAddressOutputDto;

/**
 * IMemberAddressFrontApi
 * @author li.j1
 * @Date 创建时间：2016-03-25 17:36:56
 */
public interface IMemberAddressFrontApi{
	/**
	 * 通过id删除记录
	 */
	public int removeMemberAddressById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insertMemberAddress(MemberAddressInputDto memberAddressDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<MemberAddressOutputDto> findMemberAddressPage(MemberAddressPageInputDto pageInputDto,PageModel<MemberAddressOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int updateMemberAddress(MemberAddressInputDto memberAddressDto);
	
	/**
	 * 通过id查询数据
	 */
	public MemberAddressOutputDto getMemberAddressById(String id);
}

