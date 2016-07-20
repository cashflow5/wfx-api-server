/*
 * 版本信息

 * 日期 2016-03-24 15:23:34

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.api.front.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.BankCompanyEnum;
import com.yougou.wfx.enums.MemberOptTypeEnum;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.enums.SellerAuthorizeStateEnum;
import com.yougou.wfx.enums.SellerStateEnum;
import com.yougou.wfx.enums.ShopStatusEnum;
import com.yougou.wfx.exception.WFXParamsCheckException;
import com.yougou.wfx.finance.api.background.IFinSellerInfoBackgroundApi;
import com.yougou.wfx.finance.api.front.IFinSellerInfoFrontApi;
import com.yougou.wfx.finance.dto.input.FinSellerInfoInputDto;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.member.model.MemberActionLogEntity;
import com.yougou.wfx.member.service.IMemberActionLogService;
import com.yougou.wfx.seller.api.front.ISellerInfoFrontApi;
import com.yougou.wfx.seller.dto.input.SellerAuthorizeInputDto;
import com.yougou.wfx.seller.dto.input.SellerBankInputDto;
import com.yougou.wfx.seller.dto.input.SellerInfoInputDto;
import com.yougou.wfx.seller.dto.output.SellerAuthorizeOutputDto;
import com.yougou.wfx.seller.dto.output.SellerBankOutputDto;
import com.yougou.wfx.seller.dto.output.SellerInfoOutputDto;
import com.yougou.wfx.seller.model.SellerAuthorizeEntity;
import com.yougou.wfx.seller.model.SellerBankEntity;
import com.yougou.wfx.seller.model.SellerInfoEntity;
import com.yougou.wfx.seller.service.ISellerAuthorizeService;
import com.yougou.wfx.seller.service.ISellerBankService;
import com.yougou.wfx.seller.service.ISellerInfoService;
import com.yougou.wfx.shop.service.IShopService;
import com.yougou.wfx.system.service.WFXSystemService;
import com.yougou.wfx.utils.PicPathUtil;

/**
 * ISellerInfoFrontApi实现
 * 
 * @author zhang.f
 * @Date 创建时间：2016-03-24 15:23:34
 */
@Service
public class SellerInfoFrontApiImpl implements ISellerInfoFrontApi {

	@Resource
	WFXSystemService wfxSystemService;
	@Resource
	ISellerInfoService sellerInfoService;
	@Resource
	IShopService shopService;
	@Resource
	IFinSellerInfoBackgroundApi finSellerInfoBackgroundApi;
	@Resource
	ISellerBankService sellerBankService;
	@Resource
	IMemberActionLogService logService;
	@Resource
	ISellerAuthorizeService sellerAuthorizeService;
	@Resource
	IFinSellerInfoFrontApi  finSellerInfoFrontApi;

	private static Logger logger = LoggerFactory.getLogger(SellerInfoFrontApiImpl.class);

	private SellerInfoEntity dtoToEntity(Object obj) {
		return (SellerInfoEntity) BeanUtil.convertBean(obj, SellerInfoEntity.class);
	}

	private SellerInfoOutputDto entityToDto(Object obj) {
		return (SellerInfoOutputDto) BeanUtil.convertBean(obj, SellerInfoOutputDto.class);
	}

	@LoggerProfile(methodNote = "根据分销商ID获取分销商详情接口")
	@Override
	public SellerInfoOutputDto getSellerInfoById(@NotBlank String id) {
		return (SellerInfoOutputDto) BeanUtil.convertBean(sellerInfoService.getSellerInfoById(id), SellerInfoOutputDto.class);
	}

	@Override
	public Map<String, Integer> getTwoItemSum(String sellerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@LoggerProfile(methodNote = "获取所有银行信息接口")
	@Override
	public Map<Integer, String> getAllBankList() {
		Map<Integer, String> result = new HashMap<Integer, String>();
		for (BankCompanyEnum bank : BankCompanyEnum.values()) {
			result.put(bank.getKey(), bank.getDesc());
		}
		return result;
	}

	@Override
	@LoggerProfile(methodNote = "新增分销商的银行卡账户、资质信息")
	public WFXResult<String> insertSellerBank(@NotBlank.List(value = { @NotBlank(target = "sellerId"), @NotBlank(target = "bankAccount"), @NotBlank(target = "idCardNo"), @NotBlank(target = "trueName"), @NotBlank(target = "bankNo"), @NotBlank(target = "bankProvince"), @NotBlank(target = "bankCity"), @NotBlank(target = "bankSubName") }) SellerBankInputDto sellerBankDto, SellerAuthorizeInputDto sellerAuthorizeInputDto) {
		WFXResult<String> rs = new WFXResult<String>();
		List<SellerBankEntity> list = sellerBankService.getSellerBanks(sellerBankDto.getSellerId());
		if (list != null && list.size() > 0) {
			rs.setResultCode(ResultCodeEnum.FAILURE.getKey());
			rs.setResultMsg("该分销商已创建银行卡账户，请勿多次创建！");
			return rs;
		}
		// 1.保存银行卡信息
		Date curDate = new Date();
		sellerBankDto.setCreateTime(curDate);
		sellerBankDto.setUpdateTime(curDate);
		String constent = constructInsertConstent(sellerBankDto);
		SellerBankEntity sellerBankEntity = (SellerBankEntity) BeanUtil.convertBean(sellerBankDto, SellerBankEntity.class);
		String id = sellerBankService.insert(sellerBankEntity);
		StringBuilder logMsg = new StringBuilder();
		
		//保存分销商财务账户信息
		FinSellerInfoInputDto finSellerInfoDto = new FinSellerInfoInputDto();			
		finSellerInfoDto.setLatelyTransactionTime(new Date(System.currentTimeMillis()));	
		finSellerInfoDto.setId(sellerBankEntity.getSellerId());
		finSellerInfoDto.setSellerName(sellerBankEntity.getTrueName());
		finSellerInfoFrontApi.updateSeller(finSellerInfoDto);

		if (StringUtils.isNotEmpty(id)) {
			logMsg.append("分销商银行卡账户第一次绑定 : ").append(constent);
			// 2.保存资质信息（非必输）
			if (sellerAuthorizeInputDto != null && (StringUtils.isNotEmpty(sellerAuthorizeInputDto.getIdCardPicBack()) || StringUtils.isNotEmpty(sellerAuthorizeInputDto.getIdCardPic()))) {

				String aUrl = sellerAuthorizeInputDto.getAuthorizePic();
				if (StringUtils.isNoneEmpty(aUrl)) {
					sellerAuthorizeInputDto.setAuthorizePic(PicPathUtil.getImageRelativeUrl(aUrl));
				}
				String iUrl = sellerAuthorizeInputDto.getIdCardPic();
				if (StringUtils.isNoneEmpty(iUrl)) {
					sellerAuthorizeInputDto.setIdCardPic(PicPathUtil.getImageRelativeUrl(iUrl));
				}
				String iUrlBack = sellerAuthorizeInputDto.getIdCardPicBack();
				if (StringUtils.isNoneEmpty(iUrlBack)) {
					sellerAuthorizeInputDto.setIdCardPicBack(PicPathUtil.getImageRelativeUrl(iUrlBack));
				}
				sellerAuthorizeInputDto.setCreateTime(curDate);
				sellerAuthorizeInputDto.setUpdateTime(curDate);
				sellerAuthorizeInputDto.setStatus(SellerAuthorizeStateEnum.AUTHORIZEING.getStatus());
				SellerAuthorizeEntity sellerAuthorizeEntity = (SellerAuthorizeEntity) BeanUtil.convertBean(sellerAuthorizeInputDto, SellerAuthorizeEntity.class);
				sellerAuthorizeService.insert(sellerAuthorizeEntity);
				logMsg.append(" 分销商资质信息创建. ");
			}

			rs.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			rs.setResult(id);
			rs.setResultMsg(logMsg.append("操作成功！").toString());
			// 3.记录日志
			SellerInfoEntity sellerDto = sellerInfoService.getById(sellerBankDto.getSellerId());
			MemberActionLogEntity memberActionLogEntity = new MemberActionLogEntity();
			if (null != sellerDto) {
				memberActionLogEntity.setLoginaccountId(sellerDto.getLoginaccountId());
				memberActionLogEntity.setLoginName(sellerDto.getLoginName());
			}
			memberActionLogEntity.setOptTime(new Date());
			memberActionLogEntity.setOptType(MemberOptTypeEnum.MODIFY_BANK_CARD.getKey());
			memberActionLogEntity.setRemark(logMsg.toString());
			logService.insert(memberActionLogEntity);
		} else {
			rs.setResultCode(ResultCodeEnum.FAILURE.getKey());
			rs.setResultMsg("新增分销商的银行卡账户、资质信息保存失败！");
		}
		return rs;
	}

	private String constructInsertConstent(SellerBankInputDto vo) {
		StringBuilder logMsg = new StringBuilder();
		vo.setTrueName(vo.getTrueName().trim());
		logMsg.append("真实姓名‘").append(vo.getTrueName()).append("’; ");

		vo.setBankAccount(vo.getBankAccount().trim());
		logMsg.append("银行卡号‘").append(vo.getBankAccount()).append("’; ");

		vo.setBankCity(vo.getBankCity().trim());
		logMsg.append("银行卡所在市‘").append(vo.getBankCity()).append("’; ");

		vo.setBankName(vo.getBankName().trim());
		logMsg.append("开户银行名称‘").append(vo.getBankName()).append("’; ");

		vo.setBankProvince(vo.getBankProvince().trim());
		logMsg.append("银行卡所在省‘").append(vo.getBankProvince()).append("’; ");

		vo.setBankSubName(vo.getBankSubName().trim());
		logMsg.append("支行名称‘").append(vo.getBankSubName()).append("’; ");

		vo.setIdCardNo(vo.getIdCardNo().trim());
		logMsg.append("身份证号码‘").append(vo.getIdCardNo()).append("’; ");

		vo.setBankProvince(vo.getBankProvince().trim());
		logMsg.append("银行卡所在省‘").append(vo.getBankProvince()).append("’ ");

		return logMsg.toString();
	}

	private String constructChangeConstent(SellerBankEntity oldVo, SellerBankInputDto vo) {
		StringBuilder logMsg = new StringBuilder();

		if (StringUtils.isNotEmpty(vo.getTrueName()) && !(vo.getTrueName().trim()).equals(oldVo.getTrueName())) {
			vo.setTrueName(vo.getTrueName().trim());
			logMsg.append("持卡人‘").append(oldVo.getTrueName()).append("’更新为‘").append(vo.getTrueName()).append("’; ");
		} else {
			vo.setTrueName(null);
		}

		if (StringUtils.isNotEmpty(vo.getBankAccount()) && !(vo.getBankAccount().trim()).equals(oldVo.getBankAccount())) {
			vo.setBankAccount(vo.getBankAccount().trim());
			logMsg.append("银行卡号‘").append(oldVo.getBankAccount()).append("’更新为‘").append(vo.getBankAccount()).append("’; ");
		} else {
			vo.setBankAccount(null);
		}

		if (StringUtils.isNotEmpty(vo.getBankCity()) && !(vo.getBankCity().trim()).equals(oldVo.getBankCity())) {
			vo.setBankCity(vo.getBankCity().trim());
			logMsg.append("银行卡所在市‘").append(oldVo.getBankCity()).append("’更新为‘").append(vo.getBankCity()).append("’; ");
		} else {
			vo.setBankCity(null);
		}

		if (StringUtils.isNotEmpty(vo.getBankName()) && !(vo.getBankName().trim()).equals(oldVo.getBankName())) {
			vo.setBankName(vo.getBankName().trim());
			logMsg.append("开户银行名称‘").append(oldVo.getBankName()).append("’更新为‘").append(vo.getBankName()).append("’; ");
		} else {
			vo.setBankName(null);
		}

		// if( StringUtils.isNotEmpty( vo.getBankNo() )
		// && !( vo.getBankNo().trim() ).equals( oldVo.getBankNo() )){
		// logMsg.append("银行编码‘").append(
		// BankCompanyEnum.getDescByKey( Integer.valueOf(oldVo.getBankNo() ))
		// ).append("’更新为‘").append(
		// BankCompanyEnum.getDescByKey( Integer.valueOf(vo.getBankNo() ))
		// ).append("’; ");
		// }else{
		// vo.setBankNo( null );
		// }

		if (StringUtils.isNotEmpty(vo.getBankProvince()) && !(vo.getBankProvince().trim()).equals(oldVo.getBankProvince())) {
			vo.setBankProvince(vo.getBankProvince().trim());
			logMsg.append("银行卡所在省‘").append(oldVo.getBankProvince()).append("’更新为‘").append(vo.getBankProvince()).append("’; ");
		} else {
			vo.setBankProvince(null);
		}

		if (StringUtils.isNotEmpty(vo.getBankSubName()) && !(vo.getBankSubName().trim()).equals(oldVo.getBankSubName())) {
			vo.setBankSubName(vo.getBankSubName().trim());
			logMsg.append("支行名称‘").append(oldVo.getBankSubName()).append("’更新为‘").append(vo.getBankSubName()).append("’; ");
		} else {
			vo.setBankSubName(null);
		}

		if (StringUtils.isNotEmpty(vo.getIdCardNo()) && !(vo.getIdCardNo().trim()).equals(oldVo.getIdCardNo())) {
			vo.setIdCardNo(vo.getIdCardNo().trim());
			logMsg.append("身份证号码‘").append(oldVo.getIdCardNo()).append("’更新为‘").append(vo.getIdCardNo()).append("’; ");
		} else {
			vo.setIdCardNo(null);
		}
		return logMsg.toString();
	}

	@Override
	@LoggerProfile(methodNote = "更新分销商的银行卡账户、资质信息")
	public WFXResult<Boolean> updateSellerBank(@NotBlank.List(value = { @NotBlank(target = "sellerId") }) SellerBankInputDto sellerBankDto, SellerAuthorizeInputDto sellerAuthorizeInputDto) {
		WFXResult<Boolean> rs = new WFXResult<Boolean>();
		Date curDate = new Date();
		StringBuilder logMsg = new StringBuilder();
		String sellerId = sellerBankDto.getSellerId();
		List<SellerBankEntity> list = sellerBankService.getSellerBanks(sellerId);
		if (list == null || list.size() == 0) {
			rs.setResultMsg("更新分销商的银行卡账户、资质信息失败！未查到该分销商的初始绑定银行信息。");
			rs.setResultCode(ResultCodeEnum.FAILURE.getKey());
			return rs;
		}
		SellerBankEntity oldVo = list.get(0);
		String changeConstent = constructChangeConstent(oldVo, sellerBankDto);
		// 1.更新银行卡
		if (StringUtils.isNotEmpty(changeConstent)) {
			sellerBankDto.setUpdateTime(curDate);
			SellerBankEntity sellerBankEntity = (SellerBankEntity) BeanUtil.convertBean(sellerBankDto, SellerBankEntity.class);
			int rowNum = sellerBankService.update(sellerBankEntity);
			if (rowNum > 0) {
				rs.setResult(Boolean.TRUE);
				rs.setResultCode(ResultCodeEnum.SUCCESS.getKey());
				logMsg.append("更新分销商的银行卡信息: ").append(changeConstent);
			}
			if(StringUtils.isNotBlank(sellerBankEntity.getTrueName())){
				//保存分销商财务账户信息
				FinSellerInfoInputDto finSellerInfoDto = new FinSellerInfoInputDto();			
				finSellerInfoDto.setLatelyTransactionTime(new Date(System.currentTimeMillis()));	
				finSellerInfoDto.setId(sellerBankEntity.getSellerId());
				finSellerInfoDto.setSellerName(sellerBankEntity.getTrueName());
				finSellerInfoFrontApi.updateSeller(finSellerInfoDto);
			}
		}
		boolean isUpdate=false;
		// 2.更新资质
		if (StringUtils.isNotEmpty(sellerAuthorizeInputDto.getAuthorizePic()) || StringUtils.isNotEmpty(sellerAuthorizeInputDto.getIdCardPic())) {
			SellerAuthorizeEntity entity = sellerAuthorizeService.getBySellerId(sellerId);
			SellerAuthorizeEntity sellerAuthorizeEntity = (SellerAuthorizeEntity) BeanUtil.convertBean(sellerAuthorizeInputDto, SellerAuthorizeEntity.class);
			sellerAuthorizeEntity.setUpdateTime(curDate);
			sellerAuthorizeEntity.setSellerId(sellerId);
			// 截取相对路径
			String aUrl = sellerAuthorizeEntity.getAuthorizePic();
			if (StringUtils.isNoneEmpty(aUrl)) {
				sellerAuthorizeEntity.setAuthorizePic(PicPathUtil.getImageRelativeUrl(aUrl));
			}
			String iUrl = sellerAuthorizeEntity.getIdCardPic();
			if (StringUtils.isNoneEmpty(iUrl)) {
				if (entity != null && !PicPathUtil.getImageRelativeUrl(iUrl).equals(entity.getIdCardPic())) {
					logMsg.append("身份证正面‘").append(StringUtils.isNotBlank(entity.getIdCardPic())? entity.getIdCardPic() : "").append("’更新为‘").append(iUrl).append("’; ");
					isUpdate=true;
				}
				sellerAuthorizeEntity.setIdCardPic(PicPathUtil.getImageRelativeUrl(iUrl));
			}
			String iUrlback = sellerAuthorizeEntity.getIdCardPicBack();
			if (StringUtils.isNoneEmpty(iUrlback)) {
				if (entity != null && !PicPathUtil.getImageRelativeUrl(iUrlback).equals(entity.getIdCardPicBack())) {
					logMsg.append("身份证反面‘").append(StringUtils.isNotBlank(entity.getIdCardPicBack())? entity.getIdCardPicBack() : "").append("’更新为‘").append(iUrlback).append("’; ");
					isUpdate=true;
				}
				sellerAuthorizeEntity.setIdCardPicBack(PicPathUtil.getImageRelativeUrl(iUrlback));
			}

			if (entity == null) {
				sellerAuthorizeEntity.setCreateTime(curDate);
				sellerAuthorizeEntity.setStatus(SellerAuthorizeStateEnum.AUTHORIZEING.getStatus());
				sellerAuthorizeService.insert(sellerAuthorizeEntity);
				logMsg.append(" 分销商资质信息创建. ");
			} else {

				// 如果状态是审核不通过，再次提交后，状态变为“待审核”
				if (entity.getStatus().intValue() == SellerAuthorizeStateEnum.AUTHORIZE_NOT_PASS.getStatus()) {
					sellerAuthorizeEntity.setStatus(SellerAuthorizeStateEnum.AUTHORIZEING.getStatus());
					isUpdate=true;
				}
				if(isUpdate){
				sellerAuthorizeService.update(sellerAuthorizeEntity);
				logMsg.append(" 分销商资质信息更新. ");
				}
			}

			rs.setResult(Boolean.TRUE);
			rs.setResultCode(ResultCodeEnum.SUCCESS.getKey());
		}
		if(StringUtils.isNotEmpty(logMsg.toString())){
		rs.setResultMsg(logMsg.append("操作成功！").toString());
		// 3.记录日志
		if (rs.getResult() != null && rs.getResult()) {
			SellerInfoEntity sellerDto = sellerInfoService.getById(sellerId);
			MemberActionLogEntity memberActionLogEntity = new MemberActionLogEntity();
			memberActionLogEntity.setLoginaccountId(sellerDto.getLoginaccountId());
			memberActionLogEntity.setLoginName(sellerDto.getLoginName());
			memberActionLogEntity.setOptTime(curDate);
			memberActionLogEntity.setOptType(MemberOptTypeEnum.MODIFY_BANK_CARD.getKey());
			memberActionLogEntity.setRemark(logMsg.toString());
			logService.insert(memberActionLogEntity);
		}}
		return rs;
	}

	@Override
	@LoggerProfile(methodNote = "获取分销商的银行卡账户、资质信息")
	public SellerBankOutputDto getSellerBank(@NotBlank String sellerId) {
		SellerBankOutputDto dto = null;
		List<SellerBankEntity> list = sellerBankService.getSellerBanks(sellerId);
		SellerAuthorizeEntity sellerAuthorizeEntity = sellerAuthorizeService.getBySellerId(sellerId);
		if (list != null && list.size() > 0) {
			dto = (SellerBankOutputDto) BeanUtil.convertBean(list.get(0), SellerBankOutputDto.class);
			if (sellerAuthorizeEntity != null) {
				SellerAuthorizeOutputDto sellerAuthorizeOutputDto = (SellerAuthorizeOutputDto) BeanUtil.convertBean(sellerAuthorizeEntity, SellerAuthorizeOutputDto.class);
				String picUrl = sellerAuthorizeOutputDto.getAuthorizePic();
				if (StringUtils.isNotEmpty(picUrl)) {
					sellerAuthorizeOutputDto.setAuthorizePic(PicPathUtil.getImageAbsUrl(picUrl));// 转为相对路径
				}
				String iUrl = sellerAuthorizeOutputDto.getIdCardPic();
				if (StringUtils.isNotEmpty(iUrl)) {
					sellerAuthorizeOutputDto.setIdCardPic(PicPathUtil.getImageAbsUrl(iUrl));// 转为相对路径
				}
				String iUrlBack = sellerAuthorizeOutputDto.getIdCardPicBack();
				if (StringUtils.isNotEmpty(iUrlBack)) {
					sellerAuthorizeOutputDto.setIdCardPicBack(PicPathUtil.getImageAbsUrl(iUrlBack));// 转为相对路径
				}
				dto.setSellerAuthorizeOutputDto(sellerAuthorizeOutputDto);
				dto.setAuthorizeId(sellerAuthorizeOutputDto.getId());
			}
		}
		return dto;
	}

	@LoggerProfile(methodNote = "根据分销商ID获取下级分销商列表接口")
	@Override
	public PageModel<SellerInfoOutputDto> getSubSellerListById(@NotBlank String sellerId, PageModel pageModel) {
		// TODO Auto-generated method stub
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = sellerInfoService.getSubSellerCount(sellerId);
		List<SellerInfoEntity> lstSellerInfos = null;
		if (totalCount > 0) {
			lstSellerInfos = sellerInfoService.getSubSellerList(sellerId, rowBounds);
			return new PageModel<SellerInfoOutputDto>(pageModel.getPage(), pageModel.getLimit(), totalCount, (List<SellerInfoOutputDto>) BeanUtil.convertBeanList(lstSellerInfos, SellerInfoOutputDto.class));

		}
		return null;
	}

	@LoggerProfile(methodNote = "根据分销商ID获取下级分销商信息(不包括佣金)接口")
	@Override
	public PageModel<SellerInfoOutputDto> getSubSellerListSimple(@NotBlank String sellerId, PageModel pageModel) {
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		int totalCount = sellerInfoService.getSubSellerCount(sellerId);
		List<SellerInfoEntity> lstSellerInfos = null;
		if (totalCount > 0) {
			lstSellerInfos = sellerInfoService.getSubSellerListSimple(sellerId, rowBounds);
			return new PageModel<SellerInfoOutputDto>(pageModel.getPage(), pageModel.getLimit(), totalCount, (List<SellerInfoOutputDto>) BeanUtil.convertBeanList(lstSellerInfos, SellerInfoOutputDto.class));

		}
		return null;
	}

	@LoggerProfile(methodNote = "申请成为分销商接口")
	@Override
	public WFXResult<Boolean> applyToSeller(@NotNull @NotBlank.List(value = { @NotBlank(target = "loginName"), @NotBlank(target = "loginaccountId"), @NotBlank(target = "sellerName"), @NotBlank(target = "shopInputDto"),
	/* @NotBlank(target="shopInputDto.name"), */@NotBlank(target = "shopInputDto.loginName")
	/* @NotBlank(target="shopInputDto.logoUrl") */}) SellerInfoInputDto sellerInfo) throws WFXParamsCheckException {

		SellerInfoEntity sellerInfoEntity = this.dtoToEntity(sellerInfo);
		WFXResult<Boolean> applyResult;
		try {
			applyResult = sellerInfoService.applyToSeller(sellerInfoEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			applyResult = new WFXResult<Boolean>();
			applyResult.setResult(false);
			applyResult.setResultCode(3);
			applyResult.setResultMsg("提交失败，系统异常");
		}
		return applyResult;

	}

	@LoggerProfile(methodNote = "根据会员ID获取分销商状态接口")
	@Override
	public WFXResult<Integer> getSellerStatusByMemberId(String memberId) {
		// TODO Auto-generated method stub
		WFXResult<Integer> result = new WFXResult<Integer>();
		Map<String, Object> map = sellerInfoService.getSellerAndShopStatusByMemberId(memberId);
		if (null != map && map.size() > 0) {
			// 优先判断停止合作状态
			Integer sellerStatus = (Integer) map.get("sellerStatus");
			if (null != sellerStatus && String.valueOf(sellerStatus).equals(SellerStateEnum.CANCEL_COOPERATE.getState())) {
				result.setResult(1); // 停止合作
				return result;
			}
			// 再判断店铺关闭状态
			Integer shopStatus = (Integer) map.get("shopStatus");
			if (null != shopStatus && shopStatus == ShopStatusEnum.CLOSE_SHOP.getStatus()) {
				result.setResult(2); // 店铺关闭
				return result;
			}
		}
		result.setResult(3); // 正常状态
		return result;
	}

	@LoggerProfile(methodNote = "根据店铺ID获取分销商状态接口")
	@Override
	public WFXResult<Integer> getSellerStatusByShopId(String shopId) {
		// TODO Auto-generated method stub
		WFXResult<Integer> result = new WFXResult<Integer>();
		Map<String, Object> map = sellerInfoService.getSellerAndShopStatusByShopId(shopId);
		if (null != map && map.size() > 0) {
			// 优先判断停止合作状态
			Integer sellerStatus = (Integer) map.get("sellerStatus");
			if (null != sellerStatus && String.valueOf(sellerStatus).equals(SellerStateEnum.CANCEL_COOPERATE.getState())) {
				result.setResult(1); // 停止合作
				return result;
			}
			// 再判断店铺关闭状态
			Integer shopStatus = (Integer) map.get("shopStatus");
			if (null != shopStatus && shopStatus == ShopStatusEnum.CLOSE_SHOP.getStatus()) {
				result.setResult(2); // 店铺关闭
				return result;
			}
		}
		result.setResult(3); // 正常状态
		return result;
	}

	@LoggerProfile(methodNote = "根据商家ID获取资质是否审核通过接口")
	@Override
	public WFXResult<Boolean> isSellerAuthorizePass(@NotNull String sellerId) {
		WFXResult<Boolean> result = new WFXResult<Boolean>();
		Integer status = sellerAuthorizeService.getSellerAuthorizeStatus(sellerId);
		if (status != null && status > 0) {
			if (status == SellerAuthorizeStateEnum.AUTHORIZE_PASS.getStatus()) {
				result.setResult(Boolean.TRUE);
			} else {
				result.setResult(Boolean.FALSE);
			}
			result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
		} else {
			result.setResult(Boolean.FALSE);
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResultMsg(" 获取商家资质审核信息异常！");
		}
		return result;
	}

	@LoggerProfile(methodNote = "根据用户ID获取经销商信息（例如状态）接口")
	@Override
	public SellerInfoOutputDto getSellerByMemberId(String memberId) {
		return (SellerInfoOutputDto) BeanUtil.convertBean(sellerInfoService.getSellerByMemberId(memberId), SellerInfoOutputDto.class);
	}

	@LoggerProfile(methodNote = "根据分销商ID获取分销商的一级分销商数目接口")
	@Override
	public Integer getLevelTwoSellerNum(String id) {
		return sellerInfoService.getLevelTwoSellerNum(id);
	}

	@LoggerProfile(methodNote = "根据分销商ID获取分销商的二级分销商数目接口")
	@Override
	public Integer getLevelThreeSellerNum(String id) {
		return sellerInfoService.getLevelThreeSellerNum(id);
	}

	@LoggerProfile(methodNote = "根据分销商ID获取一级分销商列表接口")
	@Override
	public PageModel<SellerInfoOutputDto> getLevelTwoSellerListById(String sellerId, PageModel pageModel) {
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		int totalCount = sellerInfoService.getLevelTwoSellerNum(sellerId);
		List<SellerInfoEntity> lstSellerInfos = null;
		if (totalCount > 0) {
			lstSellerInfos = sellerInfoService.getLevelTwoSellerListById(sellerId, rowBounds);
			return new PageModel<SellerInfoOutputDto>(pageModel.getPage(), pageModel.getLimit(), totalCount, (List<SellerInfoOutputDto>) BeanUtil.convertBeanList(lstSellerInfos, SellerInfoOutputDto.class));

		}
		return null;
	}

	@LoggerProfile(methodNote = "根据分销商ID获取二级分销商列表接口")
	@Override
	public PageModel<SellerInfoOutputDto> getLevelThreeSellerListById(String sellerId, PageModel pageModel) {
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		int totalCount = sellerInfoService.getLevelThreeSellerNum(sellerId);
		List<SellerInfoEntity> lstSellerInfos = null;
		if (totalCount > 0) {
			lstSellerInfos = sellerInfoService.getLevelThreeSellerListById(sellerId, rowBounds);
			return new PageModel<SellerInfoOutputDto>(pageModel.getPage(), pageModel.getLimit(), totalCount, (List<SellerInfoOutputDto>) BeanUtil.convertBeanList(lstSellerInfos, SellerInfoOutputDto.class));

		}
		return null;
	}

	@LoggerProfile(methodNote = "根据分销商ID获取粉丝数目接口")
	@Override
	public Integer getFansCountBySellerId(@NotBlank String sellerId) {
		return sellerInfoService.getFansCountBySellerId(sellerId);
	}

	@Override
	public Integer getSellerAuthorizeStatus(String sellerId) {
		Integer status = sellerAuthorizeService.getSellerAuthorizeStatus(sellerId);
		return status;
	}

}
