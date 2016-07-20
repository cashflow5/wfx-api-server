/**
 * 创建于: 2016年4月6日 下午2:43:00<br>
 * 所属项目:优购时尚商城运营财务组
 * 文件名称:FinSellerAccountWithdrawFrontApiImpl.java
 * 作者:zhang.jj
 * 版权信息: 版权所有 © 2015-2016 优购
 */
package com.yougou.wfx.finance.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.api.front.IFinSellerAccountWithdrawFrontApi;
import com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawInputDto;
import com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawPageInputDto;
import com.yougou.wfx.finance.dto.output.FinSellerAccountWithdrawOutputDto;
import com.yougou.wfx.finance.dto.output.FinSellerAccountWithdrawVo;
import com.yougou.wfx.finance.dto.output.MessageOutputDto;
import com.yougou.wfx.finance.enums.FinSellerAccountWithdrawalStatusEnum;
import com.yougou.wfx.finance.model.FinSellerAccountWithdrawEntity;
import com.yougou.wfx.finance.service.IFinSellerAccountWithdrawService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * 类功能描述：提现申请单提供给C端的接口
 * FinSellerAccountWithdrawFrontApiImpl.java
 * @author zhang.jj
 * @version 0.1
 * @history 2016年4月6日 zhang.jj 创建FinSellerAccountWithdrawFrontApiImpl.java
 */
@Service
public class FinSellerAccountWithdrawFrontApiImpl implements IFinSellerAccountWithdrawFrontApi {
	private static Logger LOG = LoggerFactory.getLogger(FinSellerAccountWithdrawFrontApiImpl.class.getName());
	
	@Resource
	IFinSellerAccountWithdrawService finSellerAccountWithdrawService;
	
	/**
	 * 创建提现申请单.
	 *@see com.yougou.wfx.finance.api.front.IFinSellerAccountWithdrawFrontApi#createApplyBill(com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawInputDto)
	 */
	@Override
	public MessageOutputDto createApplyBill(@NotBlank.List(value={@NotBlank(target="sellerId"), @NotBlank(target="sellerAccount"), @NotBlank(target="shopName"), 
			@NotBlank(target="withdrawAmount"), @NotBlank(target="accountName"), @NotBlank(target="accountNo"), @NotBlank(target="accountBankNo"), 
			@NotBlank(target="applyTime"), @NotBlank(target="accountBalance")}) FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto) {
		LOG.info("【FMSDubbo】前端创建提现申请，申请单：" + finSellerAccountWithdrawDto);
		return finSellerAccountWithdrawService.createApplyBill(this.dtoToEntity(finSellerAccountWithdrawDto));
	}
	
	/**
	 *  查询提现申请单详情(接口调用)
	 * @see com.yougou.wfx.finance.api.front.IFinSellerAccountWithdrawFrontApi#queryDetailsWithdraw(com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawInputDto)
	 */
	@Override
	public FinSellerAccountWithdrawOutputDto queryDetailsWithdraw(FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto) {
		LOG.info("【FMSDubbo】前端查询提现申请单详情，参数："+finSellerAccountWithdrawDto);
		FinSellerAccountWithdrawEntity entity =  finSellerAccountWithdrawService.queryDetailsWithdraw(this.dtoToEntity(finSellerAccountWithdrawDto));
		return this.entityToDto(entity);
	}
	
	/*
	 * 查询提现详情，前端H5调用
	 * @see com.yougou.wfx.finance.api.front.IFinSellerAccountWithdrawFrontApi#queryDetailById(java.lang.String)
	 */
	@Override
	public FinSellerAccountWithdrawOutputDto queryDetailById(String id) {
		LOG.info("【FMSDubbo】前端H5查询提现申请单详情，参数："+id);
		FinSellerAccountWithdrawEntity entity =  finSellerAccountWithdrawService.getById(id);
		if (StringUtils.isNotBlank(entity.getBillStatus()) && !StringUtils.equals(FinSellerAccountWithdrawalStatusEnum.ACCOUNT_WITHDRAWAL_REFUSED.getCode(), entity.getBillStatus())) {
			entity.setRemark("无");
		}
		return this.entityToDto(entity);
	}

	private FinSellerAccountWithdrawEntity dtoToEntity(Object obj){
		return (FinSellerAccountWithdrawEntity) BeanUtil.convertBean(obj,FinSellerAccountWithdrawEntity.class);
	}
	
	private FinSellerAccountWithdrawOutputDto entityToDto(Object obj){
		return (FinSellerAccountWithdrawOutputDto) BeanUtil.convertBean(obj,FinSellerAccountWithdrawOutputDto.class);
	}

	/**
	 * @see com.yougou.wfx.finance.api.front.IFinSellerAccountWithdrawFrontApi#findPage(com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawPageInputDto, com.yougou.wfx.dto.base.PageModel)
	 */
	@Override
	public FinSellerAccountWithdrawVo queryPageList(FinSellerAccountWithdrawPageInputDto pageInputDto, PageModel pageModel) {
		FinSellerAccountWithdrawVo returnVo = new FinSellerAccountWithdrawVo();
		FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity = (FinSellerAccountWithdrawEntity) BeanUtil.convertBean(pageInputDto,FinSellerAccountWithdrawEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = finSellerAccountWithdrawService.queryPageListCount(finSellerAccountWithdrawEntity);
		List<FinSellerAccountWithdrawEntity> lstFinSellerAccountWithdraws = finSellerAccountWithdrawService.queryPageList(finSellerAccountWithdrawEntity, rowBounds);
		
		PageModel<FinSellerAccountWithdrawOutputDto> pageModelList = new PageModel<FinSellerAccountWithdrawOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<FinSellerAccountWithdrawOutputDto>) BeanUtil.convertBeanList(lstFinSellerAccountWithdraws,FinSellerAccountWithdrawOutputDto.class));
		returnVo.setAmount(this.finSellerAccountWithdrawService.queryTotalAmountByStatus(finSellerAccountWithdrawEntity));
		returnVo.setPageModel(pageModelList);
		return returnVo;
	}

	/*
	 * 申请提现验证
	 * @see com.yougou.wfx.finance.api.front.IFinSellerAccountWithdrawFrontApi#applyBillValidation(com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawInputDto)
	 */
	@Override
	public MessageOutputDto applyBillValidation(@NotBlank String sellerId) {
		LOG.info("【FMSDubbo】前端申请提现验证接口，参数：" + sellerId);
		return this.finSellerAccountWithdrawService.applyBillValidation(sellerId);
	}

}
