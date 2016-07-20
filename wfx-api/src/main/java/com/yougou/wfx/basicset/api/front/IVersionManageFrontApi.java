 /*
 * 版本信息
 
 * 日期 2016-04-07 18:29:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.basicset.api.front;

import com.yougou.wfx.basicset.dto.output.AppVersionOutputDto;


/**
 * IVersionManageFrontApi
 * @author li.j1
 * @Date 创建时间：2016-04-07 18:29:33
 */
public interface IVersionManageFrontApi{
	
	AppVersionOutputDto getAndroidNewestVersion();
}

