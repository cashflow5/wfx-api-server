/**
 * 创建于: 2016年4月15日 上午10:04:15<br>
 * 所属项目:优购时尚商城运营财务组
 * 文件名称:FinSellerInfoFrontApiImpl.java
 * 作者:zhang.jj
 * 版权信息: 版权所有 © 2015-2016 优购
 */
package com.yougou.wfx.finance.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.finance.api.front.IFinSellerInfoFrontApi;
import com.yougou.wfx.finance.dto.input.FinSellerInfoInputDto;
import com.yougou.wfx.finance.dto.input.FinSellerInfoPageInputDto;
import com.yougou.wfx.finance.dto.output.FinSellerInfoOutputDto;
import com.yougou.wfx.finance.dto.output.MessageOutputDto;
import com.yougou.wfx.finance.model.FinSellerInfoEntity;
import com.yougou.wfx.finance.service.IFinSellerInfoDetailService;
import com.yougou.wfx.finance.service.IFinSellerInfoService;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * 类功能描述：
 * FinSellerInfoFrontApiImpl.java
 * @author zhang.jj
 * @version 0.1
 * @history 2016年4月15日 zhang.jj 创建FinSellerInfoFrontApiImpl.java
 */
@Service
public class FinSellerInfoFrontApiImpl implements IFinSellerInfoFrontApi {
	private static Logger LOG = LoggerFactory.getLogger(FinSellerInfoFrontApiImpl.class.getName());

	@Resource
	private IFinSellerInfoService finSellerInfoService;
	
	@Resource
	private IFinSellerInfoDetailService finSellerInfoDetailService;
	
	/**
	 * @see com.yougou.wfx.finance.api.front.IFinSellerInfoFrontApi#findPage(com.yougou.wfx.finance.dto.input.FinSellerInfoPageInputDto, com.yougou.wfx.dto.base.PageModel)
	 */
	@Override
	public PageModel<FinSellerInfoOutputDto> findPage(final FinSellerInfoPageInputDto pageInputDto, PageModel pageModel) {
		FinSellerInfoEntity finSellerInfoEntity = (FinSellerInfoEntity) BeanUtil.convertBean(pageInputDto,FinSellerInfoEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = finSellerInfoService.findPageCount(finSellerInfoEntity);
		List<FinSellerInfoEntity> lstFinSellerInfos = finSellerInfoService.findPage(finSellerInfoEntity, rowBounds);

		return new PageModel<FinSellerInfoOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<FinSellerInfoOutputDto>) BeanUtil.convertBeanList(lstFinSellerInfos,FinSellerInfoOutputDto.class));
	}

	/**
	 * @see com.yougou.wfx.finance.api.front.IFinSellerInfoFrontApi#getById(java.lang.String)
	 */
	@Override
	public FinSellerInfoOutputDto getById(String id) {
		FinSellerInfoEntity finSellerInfoEntity = finSellerInfoService.getById(id);
		return this.entityToDto(finSellerInfoEntity);
	}
	
	/**
	 * 查询下级分销商详情(包括下级分销商的收益情况)(接口:查询下级分销商带来收益接口)
	 * @see com.yougou.wfx.finance.api.front.IFinSellerInfoFrontApi#querySubSellers(java.util.List)
	 */
	@Override
	public List<FinSellerInfoOutputDto> querySubSellers(final List<String> subSellers) {
		List<FinSellerInfoEntity>  subSellerList = finSellerInfoService.querySubSellers(subSellers);
		return (List<FinSellerInfoOutputDto>)BeanUtil.convertBeanList(subSellerList,FinSellerInfoOutputDto.class);
	}
	
	
	private FinSellerInfoEntity dtoToEntity(Object obj){
		return (FinSellerInfoEntity) BeanUtil.convertBean(obj,FinSellerInfoEntity.class);
	}
	
	private FinSellerInfoOutputDto entityToDto(Object obj){
		return (FinSellerInfoOutputDto) BeanUtil.convertBean(obj,FinSellerInfoOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote = "取下级分销商给上级分销商带来的佣金收益")
	public Double getCommissionTotalAmountById(@NotBlank String sellerId, @NotBlank String sellerNextId) {
		return this.finSellerInfoDetailService.getCommissionTotalAmountById(sellerId, sellerNextId);
	}

	@Override
	@LoggerProfile(methodNote = "创建分销商")
	public MessageOutputDto createSeller(@NotBlank.List(value={@NotBlank(target="id"), @NotBlank(target="shopName")}) FinSellerInfoInputDto finSellerInfoDto) {
		LOG.info("【FMSDubbo】前端创建分销商：" + finSellerInfoDto);
		return this.finSellerInfoService.createSeller(this.dtoToEntity(finSellerInfoDto));
	}

	@Override
	@LoggerProfile(methodNote = "更新分销商信息")
	public WFXResult<Boolean> updateSeller(@NotBlank.List(value={@NotBlank(target="id")}) FinSellerInfoInputDto finSellerInfoDto) {
		LOG.info("【FMSDubbo】前端更新分销商：" + finSellerInfoDto);
		WFXResult<Boolean> rs = new WFXResult<Boolean>();
		int num = this.finSellerInfoService.updateSeller(this.dtoToEntity(finSellerInfoDto));
		if (num > 0) {
			rs.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			rs.setResult(true);
			rs.setResultMsg("更新成功");
		} else {
			rs.setResultCode(ResultCodeEnum.FAILURE.getKey());
			rs.setResult(false);
			rs.setResultMsg("更新失败");
		}
		return rs;
	}

	@Override
	@LoggerProfile(methodNote = "查询分销商累计收益总额")
	public Double getTotalCommissionAmount(@NotBlank String sellerId) {
		return this.finSellerInfoService.getTotalCommissionAmount(sellerId);
	}

	@Override
	@LoggerProfile(methodNote = "查询分销商今日收益总和")
	public Double getTodayCommissionAmount(@NotBlank String sellerId) {
		return this.finSellerInfoService.getTodayCommissionAmount(sellerId);
	}

}
