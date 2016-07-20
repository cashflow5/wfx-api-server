 /*
 * 版本信息
 
 * 日期 2016-03-29 14:08:45
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.dto.input.FinSellerInfoDetailInputDto;
import com.yougou.wfx.finance.dto.input.FinSellerInfoDetailPageInputDto;
import com.yougou.wfx.finance.dto.output.FinSellerInfoDetailOutputDto;

/**
 * IFinSellerInfoDetailBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-03-29 14:08:45
 */
public interface IFinSellerInfoDetailBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(FinSellerInfoDetailInputDto finSellerInfoDetailDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<FinSellerInfoDetailOutputDto> findPage(FinSellerInfoDetailPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(FinSellerInfoDetailInputDto finSellerInfoDetailDto);
	
	/**
	 * 通过id查询数据
	 */
	public FinSellerInfoDetailOutputDto getById(String id);
	
	/**
	 * 查询分销商交易明细列表
	 * @param finSellerInfoDetailEntity
	 * @return
	 */
	List<FinSellerInfoDetailOutputDto> querySellerDetailList(FinSellerInfoDetailInputDto finSellerInfoDetailDto);
	
	/**
	 * 查询分销商交易列表-总记录数
	 * @param finSellerInfoDetailEntity
	 * @return
	 */
	int querySellerDetailListCount(FinSellerInfoDetailInputDto finSellerInfoDetailDto);
	
}

