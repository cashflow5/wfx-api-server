/*
 * 版本信息

 * 日期 2016-06-20 14:03:02

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.notice.api.front;

import java.util.List;

import com.yougou.wfx.notice.dto.input.NoticeInputDto;
import com.yougou.wfx.notice.dto.output.NoticeOutputDto;

/**
 * INoticeBackgroundApi
 * 
 * @author wfx
 * @Date 创建时间：2016-06-20 14:03:02
 */
public interface INoticeFrontApi {

	/**
	 * 获取生效中的公告
	 */
	public List<NoticeOutputDto> getActiveNotice();

	/**
	 * 通过id查询数据
	 */
	public NoticeOutputDto getById(String id);
}
