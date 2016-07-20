 /*
 * 版本信息
 
 * 日期 2016-03-24 16:17:44
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.api.background;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.member.dto.input.MemberProfileInputDto;
import com.yougou.wfx.member.dto.input.MemberProfilePageInputDto;
import com.yougou.wfx.member.dto.output.MemberProfileOutputDto;

/**
 * IMemberProfileBackgroundApi
 * @author luo.q
 * @Date 创建时间：2016-03-24 16:17:44
 */
public interface IMemberProfileBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(MemberProfileInputDto memberProfileDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<MemberProfileOutputDto> findPage(MemberProfilePageInputDto pageInputDto,PageModel<MemberProfileOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(MemberProfileInputDto memberProfileDto);
	
	/**
	 * 通过id查询数据
	 */
	public MemberProfileOutputDto getById(String id);
	
	/**
	 * 批量更新订单数和金额字段
	 * @return
	 */
	public int batchUpdateOrderAmountAndCount() ;
}

