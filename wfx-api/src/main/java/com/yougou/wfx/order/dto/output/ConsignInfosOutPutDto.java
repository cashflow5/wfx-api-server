package com.yougou.wfx.order.dto.output;

import java.util.Date;
import java.util.List;

import com.yougou.wfx.commodity.dto.output.CommodityStyleOutputDto;
import com.yougou.wfx.dto.base.OutputDto;
import com.yougou.wfx.order.dto.output.LogisticsOutputDto.LogisticsData;

public class ConsignInfosOutPutDto extends OutputDto{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 物流公司编码
	 */
	private String expressCode;
	/**
	 * 物流公司名称
	 */
	private String expressName;
	/**
	 * 快递单号
	 */
	private String expressNo;
	/**
	 * 发货时间
	 */
	private Date consignTime;
	/**
	 * 商品信息
	 */
	private List<StyleOutPutDto> styleList;
	/**
	 * 快递追踪数据集
	 */
	private List<WfxLogisticsData> traceData;
	
	public String getExpressCode() {
		return expressCode;
	}
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public Date getConsignTime() {
		return consignTime;
	}
	public void setConsignTime(Date consignTime) {
		this.consignTime = consignTime;
	}
	public List<WfxLogisticsData> getTraceData() {
		return traceData;
	}
	public void setTraceData(List<WfxLogisticsData> traceData) {
		this.traceData = traceData;
	}
	public List<StyleOutPutDto> getStyleList() {
		return styleList;
	}
	public void setStyleList(List<StyleOutPutDto> styleList) {
		this.styleList = styleList;
	}
}
