 /*
 * 版本信息
 
 * 日期 2016-04-18 16:48:04
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.api.front;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.member.dto.input.MemberActionLogInputDto;
import com.yougou.wfx.member.dto.input.MemberActionLogPageInputDto;
import com.yougou.wfx.member.dto.output.MemberActionLogOutputDto;

/**
 * IMemberActionLogFrontApi
 * @author zheng.qq
 * @Date 创建时间：2016-04-18 16:48:05
 */
public interface IMemberActionLogFrontApi{
	
	/**
	 * 保存单条记录
	 */
	public String insert(MemberActionLogInputDto memberActionLogDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<MemberActionLogOutputDto> findPage(MemberActionLogPageInputDto pageInputDto,PageModel pageModel);
}

