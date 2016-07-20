 /*
 * 版本信息
 
 * 日期 2016-04-15 16:09:24
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.api.background.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.enums.MemberOptTypeEnum;
import com.yougou.wfx.finance.api.background.IFinSellerInfoBackgroundApi;
import com.yougou.wfx.finance.dto.output.FinSellerInfoOutputDto;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.member.model.MemberActionLogEntity;
import com.yougou.wfx.member.service.IMemberActionLogService;
import com.yougou.wfx.seller.api.background.ISellerAuthorizeBackgroundApi;
import com.yougou.wfx.seller.dto.input.SellerAuthorizeInputDto;
import com.yougou.wfx.seller.dto.output.SellerAuthorizeOutputDto;
import com.yougou.wfx.seller.model.SellerAuthorizeEntity;
import com.yougou.wfx.seller.service.ISellerAuthorizeService;

/**
 * ISellerAuthorizeBackgroundApi实现
 * @author zheng.qq
 * @Date 创建时间：2016-04-15 16:09:26
 */
@Service
public class SellerAuthorizeBackgroundApiImpl implements ISellerAuthorizeBackgroundApi{
	private final static Logger logger = LoggerFactory.getLogger(SellerAuthorizeBackgroundApiImpl.class);
	@Resource
	IFinSellerInfoBackgroundApi finSellerInfoBackgroundApi;
	@Resource
	ISellerAuthorizeService sellerAuthorizeService;
	@Resource
	IMemberActionLogService logService;
	
	@Override
	@LoggerProfile(methodNote="根据ID删除分销商资质数据")
	public int removeById(String id) {
		return sellerAuthorizeService.remove(id);
	}

	@Override
	@LoggerProfile(methodNote="保存分销商资质数据")
	public String insert(SellerAuthorizeInputDto sellerAuthorizeDto) {
		return sellerAuthorizeService.insert(this.dtoToEntity(sellerAuthorizeDto));
	}
	
	@Override
	@LoggerProfile(methodNote="更新修改分销商资质数据")
	public int update(@NotBlank.List(value={@NotBlank(target="id")})
			SellerAuthorizeInputDto sellerAuthorizeDto) {
		return sellerAuthorizeService.update(this.dtoToEntity(sellerAuthorizeDto));
	}

	@Override
	@LoggerProfile(methodNote="分页获取分销商资质数据")
	public PageModel<SellerAuthorizeOutputDto> findPage(SellerAuthorizeInputDto pageInputDto,PageModel pageModel) {
		SellerAuthorizeEntity sellerAuthorizeEntity = (SellerAuthorizeEntity) BeanUtil.convertBean(pageInputDto,SellerAuthorizeEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = sellerAuthorizeService.findPageCount(sellerAuthorizeEntity);
		List<SellerAuthorizeEntity> lstSellerAuthorizes = sellerAuthorizeService.findPage(sellerAuthorizeEntity, rowBounds);

		return new PageModel<SellerAuthorizeOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<SellerAuthorizeOutputDto>) BeanUtil.convertBeanList(lstSellerAuthorizes,SellerAuthorizeOutputDto.class));
	}

	@Override
	@LoggerProfile(methodNote="审核分销商资质")
	@Transactional
	public boolean updateSellerAuthorizeStatusById(@NotBlank String id, @NotBlank int status) {
		try{
			SellerAuthorizeEntity entity = new SellerAuthorizeEntity();
			entity.setId(id);
			entity.setStatus(status);
			entity.setUpdateTime(new Date());
			sellerAuthorizeService.update(entity);
			
			SellerAuthorizeInputDto in = new SellerAuthorizeInputDto();
			in.setId(id);
			List<SellerAuthorizeOutputDto> list = sellerAuthorizeService.findSellerAuthorizePage(in, new RowBounds());
			if(list!=null && list.size()>0){
				//记录审核通过和审核拒绝的日志
				MemberActionLogEntity memberActionLogEntity = new MemberActionLogEntity();

				memberActionLogEntity.setLoginaccountId(list.get(0).getSellerLoginAccessId());
				memberActionLogEntity.setLoginName(list.get(0).getSellerLoginName());

				memberActionLogEntity.setOptTime(new Date());
				memberActionLogEntity.setOptType( MemberOptTypeEnum.VERIFY_AUTHORIZE.getKey() );
				if(status==2){
					memberActionLogEntity.setRemark("分销商资质审核通过！");
				}else if(status==3){
					memberActionLogEntity.setRemark("分销商资质审核不通过！");
				}
				logService.insert(memberActionLogEntity);
			}else{
				logger.error("没有获取到分销商信息，无法记录审核通过和审核拒绝的日志！");
			}

		}catch(Exception e){
			logger.error("审核分销商资质程序发生错误：", e);
			throw new RuntimeException(e);
		}
		return true;
	}

	@Override
	@LoggerProfile(methodNote="根据ID获取分销商资质数据")
	public SellerAuthorizeOutputDto getById(String id) {
		SellerAuthorizeEntity sellerAuthorizeEntity = sellerAuthorizeService.getById(id);
		return this.entityToDto(sellerAuthorizeEntity);
	}
	
	@Override
	@LoggerProfile(methodNote="获取分页数据(关联分销商信息表查询)")
	public PageModel<SellerAuthorizeOutputDto> findSellerAuthorizePage(SellerAuthorizeInputDto sellerAuthorizeInputDto,PageModel pageModel){
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		int totalCount = sellerAuthorizeService.findSellerAuthorizeCount(sellerAuthorizeInputDto);
		List<SellerAuthorizeOutputDto> lstSellerAuthorizeOutputDto = sellerAuthorizeService.findSellerAuthorizePage(sellerAuthorizeInputDto, rowBounds);
		if(lstSellerAuthorizeOutputDto!=null){
			for(SellerAuthorizeOutputDto dto : lstSellerAuthorizeOutputDto){
				FinSellerInfoOutputDto fdto = finSellerInfoBackgroundApi.getById(dto.getSellerId());
				if(fdto !=null){
					dto.setCommissionTotalAmount(fdto.getCommissionTotalAmount());
				}
			}
		}
		return new PageModel<SellerAuthorizeOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,lstSellerAuthorizeOutputDto);
	}
	
	@Override
	@LoggerProfile(methodNote="通过sellerId查询分销商资质数据")
	public List<SellerAuthorizeOutputDto> getSellerAuthorizeBySellerId(String sellerId){
		RowBounds rowBounds = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
		SellerAuthorizeEntity sellerAuthorizeEntity = new SellerAuthorizeEntity();
		sellerAuthorizeEntity.setSellerId(sellerId);
		return (List<SellerAuthorizeOutputDto>) BeanUtil.convertBeanList(sellerAuthorizeService.findPage(sellerAuthorizeEntity, rowBounds),SellerAuthorizeOutputDto.class);
	}
	
	private SellerAuthorizeEntity dtoToEntity(Object obj){
		return (SellerAuthorizeEntity) BeanUtil.convertBean(obj,SellerAuthorizeEntity.class);
	}
	
	private SellerAuthorizeOutputDto entityToDto(Object obj){
		return (SellerAuthorizeOutputDto) BeanUtil.convertBean(obj,SellerAuthorizeOutputDto.class);
	}

}
