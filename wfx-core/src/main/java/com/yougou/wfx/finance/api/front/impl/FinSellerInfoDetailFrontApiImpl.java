/**
 * 创建于: 2016年4月15日 下午1:18:34<br>
 * 所属项目:优购时尚商城运营财务组
 * 文件名称:FinSellerInfoDetailFrontApiImpl.java
 * 作者:zhang.jj
 * 版权信息: 版权所有 © 2015-2016 优购
 */
package com.yougou.wfx.finance.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.api.front.IFinSellerInfoDetailFrontApi;
import com.yougou.wfx.finance.dto.input.FinSellerInfoDetailPageInputDto;
import com.yougou.wfx.finance.dto.output.FinSellerInfoDetailOutputDto;
import com.yougou.wfx.finance.model.FinSellerInfoDetailEntity;
import com.yougou.wfx.finance.service.IFinSellerInfoDetailService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * 类功能描述：
 * FinSellerInfoDetailFrontApiImpl.java
 * @author zhang.jj
 * @version 0.1
 * @history 2016年4月15日 zhang.jj 创建FinSellerInfoDetailFrontApiImpl.java
 */
@Service
public class FinSellerInfoDetailFrontApiImpl implements IFinSellerInfoDetailFrontApi {

	@Resource
	IFinSellerInfoDetailService finSellerInfoDetailService;
	
	/**
	 * 
	 * @see com.yougou.wfx.finance.api.front.IFinSellerInfoDetailFrontApi#findPage(com.yougou.wfx.finance.dto.input.FinSellerInfoDetailPageInputDto, com.yougou.wfx.dto.base.PageModel)
	 */
	@Override
	public PageModel<FinSellerInfoDetailOutputDto> querySellerDetails(final FinSellerInfoDetailPageInputDto pageInputDto, PageModel pageModel) {
		FinSellerInfoDetailEntity finSellerInfoDetailEntity = (FinSellerInfoDetailEntity) BeanUtil.convertBean(pageInputDto,FinSellerInfoDetailEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = finSellerInfoDetailService.findPageCount(finSellerInfoDetailEntity);
		List<FinSellerInfoDetailEntity> lstFinSellerInfoDetails = finSellerInfoDetailService.findPage(finSellerInfoDetailEntity, rowBounds);

		return new PageModel<FinSellerInfoDetailOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<FinSellerInfoDetailOutputDto>) BeanUtil.convertBeanList(lstFinSellerInfoDetails,FinSellerInfoDetailOutputDto.class));
	}

	/**
	 * 
	 * @see com.yougou.wfx.finance.api.front.IFinSellerInfoDetailFrontApi#getById(java.lang.String)
	 */
	@Override
	public FinSellerInfoDetailOutputDto getById(final String id) {
		FinSellerInfoDetailEntity finSellerInfoDetailEntity = finSellerInfoDetailService.getById(id);
		return this.entityToDto(finSellerInfoDetailEntity);
	}
	
	private FinSellerInfoDetailEntity dtoToEntity(Object obj){
		return (FinSellerInfoDetailEntity) BeanUtil.convertBean(obj,FinSellerInfoDetailEntity.class);
	}
	
	private FinSellerInfoDetailOutputDto entityToDto(Object obj){
		return (FinSellerInfoDetailOutputDto) BeanUtil.convertBean(obj,FinSellerInfoDetailOutputDto.class);
	}

}
