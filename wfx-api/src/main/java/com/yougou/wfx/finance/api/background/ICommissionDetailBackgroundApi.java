 /*
 * 版本信息
 
 * 日期 2016-03-29 10:46:57
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.exception.WFXException;
import com.yougou.wfx.finance.dto.input.CommissionDetailInputDto;
import com.yougou.wfx.finance.dto.input.CommissionDetailPageInputDto;
import com.yougou.wfx.finance.dto.output.CommissionDetailOutputDto;

/**
 * ICommissionDetailBackgroundApi
 * @author langqiwei
 * @Date 创建时间：2016-03-29 10:46:58
 */
public interface ICommissionDetailBackgroundApi{
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommissionDetailInputDto commissionDetailDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<CommissionDetailOutputDto> findPage(CommissionDetailPageInputDto pageInputDto,PageModel<CommissionDetailOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(CommissionDetailInputDto commissionDetailDto);
	
	/**
	 * 通过id查询数据
	 */
	public CommissionDetailOutputDto getById(String id);
	/**
     *佣金明细数据导出
     */
    List<CommissionDetailOutputDto> exportCommissionDetail(CommissionDetailPageInputDto pageInputDto);
    /**
     *佣金明细调度，进行结算
     */
    public void dispatchCreateAccountBalance() throws Exception;
}

