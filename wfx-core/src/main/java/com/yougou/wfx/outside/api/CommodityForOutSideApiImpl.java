package com.yougou.wfx.outside.api;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.tools.common.utils.StringUtil;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.outside.dao.CommodityForOutSideMapper;
import com.yougou.wfx.outside.domain.Commodity;
import com.yougou.wfx.outside.domain.CommodityDetail;
import com.yougou.wfx.outside.request.CommodityDetailGetRequest;
import com.yougou.wfx.outside.request.CommodityGetRequest;
import com.yougou.wfx.outside.request.SkuQuantityUpdateRequest;
import com.yougou.wfx.outside.response.CommodityDetailGetResponse;
import com.yougou.wfx.outside.response.CommodityGetResponse;
import com.yougou.wfx.outside.response.SkuQuantityUpdateResponse;

/**
 * CommodityForOutSideApiImpl
 * 
 * @author li.lq
 * @Date 2016年4月20日
 */
@Service
public class CommodityForOutSideApiImpl extends BaseService implements ICommodityForOutSideApi {
	@Resource
	private CommodityForOutSideMapper commodityForOutSideMapper;

	@Override
	@LoggerProfile(methodNote = "外部平台查询商品接口 ")
	public CommodityGetResponse queryCommodityList(CommodityGetRequest request) {
		CommodityGetResponse response = new CommodityGetResponse();

		try {
			logger.info("外部平台下载商品输入参数：" + request.toString());
			// 1.入参校验
			if (!(request.getPageNo() > 0 && request.getPageSize() > 0)) {
				logger.error("下载商品:分页条件不符合逻辑！PageNo=" + request.getPageNo() + ",PageSize=" + request.getPageSize());
				response.setResultCode(ResultCodeEnum.WARN.getKey());
				response.setResultMsg("入参有误：分页条件无法获取数据");
				return response;
			}
			// 2. 查询并封装数据
			int total = commodityForOutSideMapper.queryCommodityCount(request);
			List<Commodity> commodityList = commodityForOutSideMapper.queryCommodityList(request, new RowBounds((request.getPageNo() - 1) * request.getPageSize(), request.getPageSize()));
			response.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			response.setResultMsg("执行成功");
			response.setCommodityList(commodityList);
			response.setTotalResults(total);
			return response;

			// 3. 异常处理
		} catch (Exception e) {
			logger.error("外部平台下载商品，程序执行发生错误:", e);
			response.setResultCode(ResultCodeEnum.FAILURE.getKey());
			response.setResultMsg("外部平台下载商品，程序执行发生错误,方法(queryCommodityList):" + e.getMessage());
			return response;
		}

	}

	@Override
	@LoggerProfile(methodNote = "外部平台查询商品详情接口 ")
	public CommodityDetailGetResponse queryCommodityDetailList(CommodityDetailGetRequest request) {
		CommodityDetailGetResponse response = new CommodityDetailGetResponse();

		try {
			logger.info("外部平台下载商品详情输入参数：" + request.toString());
			// 1. 入参校验
			String noArr[] = request.getNoArr();
			if (noArr == null || noArr.length <= 0) {
				logger.error("下载商品:入参有误：未传入商品编号");
				response.setResultCode(ResultCodeEnum.FAILURE.getKey());
				response.setResultMsg(ResultCodeEnum.FAILURE.getDesc() + "入参有误：未传入商品编号");
				return response;
			}

			// 2. 查询并封装数据
			List<CommodityDetail> commodityDetailList = commodityForOutSideMapper.queryCommodityDetailList(request);
			int total = commodityForOutSideMapper.queryCommodityDetailCount(request);
			response.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			response.setResultMsg("执行成功");
			response.setCommodityDetailList(commodityDetailList);
			response.setTotalResults(total);
			return response;

			// 3. 异常处理
		} catch (Exception e) {
			logger.error("外部平台下载商品详情，程序执行发生错误:", e);
			response.setResultCode(ResultCodeEnum.FAILURE.getKey());
			response.setResultMsg("外部平台下载商品详情，程序执行发生错误,方法(queryCommodityDetailList)"+e.getMessage());
			return response;
		}

	}

	@Override
	@Transactional
	@LoggerProfile(methodNote = "SKU库存修改接口 ")
	public SkuQuantityUpdateResponse updateSkuQuantity(SkuQuantityUpdateRequest request) {
		SkuQuantityUpdateResponse response = new SkuQuantityUpdateResponse();
		try {
			logger.info("外部平台SKU库存修改输入参数：" + request.toString());
			// 1. 入参校验
			if (StringUtil.isStrEmpty(request.getOuterSkuId()) && StringUtil.isStrEmpty(request.getCommodityNo())) {
				logger.error("至少指定款色ID或第三方货品条码的其中一个");
				response.setResultCode(ResultCodeEnum.WARN.getKey());
				response.setResultMsg("至少指定款色ID或第三方货品条码的其中一个");
				response.setResult(false);
				return response;
			}
			// 只传款色ID，没传货品条码时，验证是否有重复铺货
			if (StringUtil.isStrEmpty(request.getOuterSkuId()) && !StringUtil.isStrEmpty(request.getCommodityNo())) {

			}

			// 2. 业务处理并封装数据
			Date current = new Date();
			int c = commodityForOutSideMapper.updateSkuQuantity(request, current);
			if (c > 0) {
				response.setResultCode(ResultCodeEnum.SUCCESS.getKey());
				response.setResultMsg("执行成功");
				response.setResult(true);
			} else {
				response.setResultCode(ResultCodeEnum.WARN.getKey());
				response.setResultMsg("修改失败:可能SKU不存在");
				response.setResult(false);
			}
			return response;

			// 3. 异常处理
		} catch (Exception e) {
			logger.error("外部平台SKU库存修改，程序执行发生错误:", e);
			response.setResultCode(ResultCodeEnum.FAILURE.getKey());
			response.setResultMsg("外部平台SKU库存修改，程序执行发生错误,方法(updateSkuQuantity)"+e.getMessage());
			return response;
		}
	}

}
