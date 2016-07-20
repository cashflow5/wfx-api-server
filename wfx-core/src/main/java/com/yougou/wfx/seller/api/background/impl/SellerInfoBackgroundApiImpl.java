/*
 * 版本信息

 * 日期 2016-03-24 15:23:34

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.SellerStateEnum;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.member.service.IMemberAccountService;
import com.yougou.wfx.seller.api.background.ISellerInfoBackgroundApi;
import com.yougou.wfx.seller.dto.input.SellerInfoInputDto;
import com.yougou.wfx.seller.dto.input.SellerInfoPageInputDto;
import com.yougou.wfx.seller.dto.output.SellerInfoOutputDto;
import com.yougou.wfx.seller.model.SellerInfoEntity;
import com.yougou.wfx.seller.service.ISellerInfoService;
import com.yougou.wfx.shop.service.IShopService;

/**
 * ISellerInfoBackgroundApi实现
 * 
 * @author zhang.f
 * @Date 创建时间：2016-03-24 15:23:34
 */
@Service
public class SellerInfoBackgroundApiImpl implements ISellerInfoBackgroundApi {

	@Resource
	ISellerInfoService sellerInfoService;

	@Resource
	IMemberAccountService memberAccountService;

	@Resource
	IShopService shopService;

	private static Logger logger = LoggerFactory.getLogger(SellerInfoBackgroundApiImpl.class);

	@Override
	public int removeById(String id) {
		return sellerInfoService.remove(id);
	}

	@Override
	public String insert(SellerInfoInputDto sellerInfoDto) {
		return sellerInfoService.insert(this.dtoToEntity(sellerInfoDto));
	}

	@Override
	public PageModel<SellerInfoOutputDto> findPage(SellerInfoPageInputDto pageInputDto, PageModel<SellerInfoOutputDto> pageModel) {
		/*
		 * SellerInfoEntity sellerInfoEntity = (SellerInfoEntity)
		 * BeanUtil.convertBean(pageInputDto,SellerInfoEntity.class); RowBounds
		 * rowBounds = RowBoundUtils.rowBounds(pageModel);
		 * 
		 * int totalCount = sellerInfoService.findPageCount(sellerInfoEntity);
		 * List<SellerInfoEntity> lstSellerInfos =
		 * sellerInfoService.findPage(sellerInfoEntity, rowBounds);
		 * 
		 * return new
		 * PageModel<SellerInfoOutputDto>(pageModel.getPage(),pageModel
		 * .getLimit(), totalCount,(List<SellerInfoOutputDto>)
		 * BeanUtil.convertBeanList(lstSellerInfos,SellerInfoOutputDto.class));
		 */

		return null;
	}

	@LoggerProfile(methodNote = "后台修改分销商信息接口")
	@Override
	public int update(SellerInfoInputDto sellerInfoDto) {
		return sellerInfoService.update(this.dtoToEntity(sellerInfoDto));
	}

	@LoggerProfile(methodNote = "后台根据分销商ID获取分销商信息接口")
	@Override
	public SellerInfoOutputDto getById(String id) {
		SellerInfoEntity sellerInfoEntity = sellerInfoService.getById(id);
		return this.entityToDto(sellerInfoEntity);
	}

	private SellerInfoEntity dtoToEntity(Object obj) {
		return (SellerInfoEntity) BeanUtil.convertBean(obj, SellerInfoEntity.class);
	}

	private SellerInfoOutputDto entityToDto(Object obj) {
		return (SellerInfoOutputDto) BeanUtil.convertBean(obj, SellerInfoOutputDto.class);
	}

	@LoggerProfile(methodNote = "后台分销商审核列表查询接口")
	@Override
	public PageModel<SellerInfoOutputDto> findSellerAuditPage(SellerInfoPageInputDto pageInputDto, PageModel<SellerInfoOutputDto> pageModel) {
		SellerInfoEntity sellerInfoEntity = (SellerInfoEntity) BeanUtil.convertBean(pageInputDto, SellerInfoEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = sellerInfoService.findSellerAuditCount(sellerInfoEntity);
		List<SellerInfoEntity> lstSellerInfos = sellerInfoService.findSellerAuditPage(sellerInfoEntity, rowBounds);

		return new PageModel<SellerInfoOutputDto>(pageModel.getPage(), pageModel.getLimit(), totalCount, (List<SellerInfoOutputDto>) BeanUtil.convertBeanList(lstSellerInfos, SellerInfoOutputDto.class));
	}

	@LoggerProfile(methodNote = "后台根据分销商ID分销商信息接口")
	@Override
	public SellerInfoOutputDto getSellerInfoById(String sellerId) {
		// TODO Auto-generated method stub
		SellerInfoEntity sellerInfoEntity = sellerInfoService.getSellerInfoById(sellerId);
		SellerInfoOutputDto sellerInfoOutputDto = (SellerInfoOutputDto) BeanUtil.convertBean(sellerInfoEntity, SellerInfoOutputDto.class);

		return sellerInfoOutputDto;
	}

	@LoggerProfile(methodNote = "后台分销商列表查询接口")
	@Override
	public PageModel<SellerInfoOutputDto> findSellerPage(SellerInfoPageInputDto pageInputDto, PageModel<SellerInfoOutputDto> pageModel) {
		SellerInfoEntity sellerInfoEntity = (SellerInfoEntity) BeanUtil.convertBean(pageInputDto, SellerInfoEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = sellerInfoService.findSellerCount(sellerInfoEntity);
		List<SellerInfoEntity> lstSellerInfos = sellerInfoService.findSellerPage(sellerInfoEntity, rowBounds);

		return new PageModel<SellerInfoOutputDto>(pageModel.getPage(), pageModel.getLimit(), totalCount, (List<SellerInfoOutputDto>) BeanUtil.convertBeanList(lstSellerInfos, SellerInfoOutputDto.class));
	}

	@LoggerProfile(methodNote = "后台分销商审核通过接口")
	@Override
	public WFXResult<Boolean> sellerAuditPass(SellerInfoInputDto sellerInfoDto, String optUser, String type) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result = new WFXResult<Boolean>();
		try {
			SellerInfoEntity entity = new SellerInfoEntity();
			entity.setState(SellerStateEnum.IN_COOPERATING.getState());// 合作中
			entity.setId(sellerInfoDto.getId());
			int count = sellerInfoService.findPageCount(entity);
			if (count > 0) {
				logger.info("分销商账号:" + sellerInfoDto.getLoginName() + " ,分销商已审核通过，无需再次审核");
			} else {
				sellerInfoService.sellerAuditPass(sellerInfoDto, optUser, type);
			}
			result.setResult(true);
			result.setResultMsg("分销商审核通过，操作成功");
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("sellerAuditPass,操作人：{}，分销商账号：{}，后台分销商审核通过系统异常：", optUser, sellerInfoDto.getLoginName(), e);
		}
		result.setResult(false);
		result.setResultMsg("分销商审核通过，操作失败");
		return result;
	}

	@LoggerProfile(methodNote = "后台分销商停止合作接口")
	@Transactional
	@Override
	public WFXResult<Boolean> sellerStop(SellerInfoInputDto sellerInfoDto, String optUser) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result = new WFXResult<Boolean>();
		try {
			sellerInfoService.sellerStop(sellerInfoDto, optUser);
			result.setResult(true);
			result.setResultMsg("分销商停止合作，操作成功");
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("sellerStop,操作人：{}，分销商账号：{}，后台分销商审核通过系统异常：", optUser, sellerInfoDto.getLoginName(), e);
		}
		result.setResult(false);
		result.setResultMsg("分销商停止合作，操作失败");
		return result;

	}

	@LoggerProfile(methodNote = "后台分销商自动审核job接口")
	@Override
	public int sellerAutoAuditAllApply() {
		// TODO Auto-generated method stub
		int updateCount = 0;
		try {
			updateCount = sellerInfoService.sellerAutoAuditAllApply();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("sellerAutoAuditAllApply后台分销商自动审核job执行异常：", e);
		}
		return updateCount;
	}

	@LoggerProfile(methodNote = "后台定时扫描分销商自动代理所有上架商品job接口")
	@Override
	public int sellerAutoProxyAll() {
		int updateCount = 0;
		try {
//			updateCount = sellerInfoService.sellerAutoProxyAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("sellerAutoProxyAll定时扫描分销商自动代理所有上架商品job执行异常：", e);
		}
		return updateCount;
	}
	
	@LoggerProfile(methodNote = "后台升级成为分销商")
	@Override
	public WFXResult<Boolean> applyToSeller(@NotNull @NotBlank.List(value = { 
			@NotBlank(target="loginaccountId") ,
			@NotBlank(target="shopInputDto")			
			})SellerInfoInputDto sellerInfo) {
		SellerInfoEntity sellerInfoEntity = this.dtoToEntity(sellerInfo);
		WFXResult<Boolean> applyResult;
		try {
			applyResult = sellerInfoService.applyToSellerBackgound(sellerInfoEntity);
		} catch (Exception e) {
			logger.error("申请成为分销商出现异常",e);
			applyResult = new WFXResult<Boolean>();
			applyResult.setResult(false);
			applyResult.setResultCode(3);
			applyResult.setResultMsg("提交失败，系统异常");
		}	
		return applyResult;
	}

	@Override
	public SellerInfoOutputDto getSellerByMemberId(String memberId) {
		SellerInfoEntity entity = sellerInfoService.getSellerByMemberId(memberId);
		return entity == null ? null : entityToDto(entity);
	}
}
