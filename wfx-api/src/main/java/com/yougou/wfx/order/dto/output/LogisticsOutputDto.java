 /*
 * 版本信息
 
 * 日期 2016-03-28 17:16:57
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.dto.output;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * 订单包裹物流数据模型
 * @author wuyang
 * @Date 创建时间：2016-03-28 17:16:57
 */
public class LogisticsOutputDto extends OutputDto{

	private static final long serialVersionUID = 1L;

	/** 数据集合 */
    private List<LogisticsData> data;
    
    /** 物流单是否完结 */
    private boolean isWhether = false;

    /** 订单编号 */
    private String orderNo;

	/** 物流公司名称 */
	private String logisticsName;

	/** 物流单号 */
	private String logisticsOrderNo;

	/** 发货时间 */
	private String shipTime;
	
	/** 物流公司官网 */
	private String logisticsWebsite;
	
	/** 物流公司官方联系号码 */
    private String logisticsTel;
    
    public List<LogisticsData> getData() {
		return data;
	}

	public void setData(List<LogisticsData> data) {
		this.data = data;
	}

	public boolean isWhether() {
		return isWhether;
	}

	public void setWhether(boolean isWhether) {
		this.isWhether = isWhether;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public String getLogisticsOrderNo() {
		return logisticsOrderNo;
	}

	public void setLogisticsOrderNo(String logisticsOrderNo) {
		this.logisticsOrderNo = logisticsOrderNo;
	}

	public String getShipTime() {
		return shipTime;
	}

	public void setShipTime(String shipTime) {
		this.shipTime = shipTime;
	}

	public String getLogisticsWebsite() {
		return logisticsWebsite;
	}

	public void setLogisticsWebsite(String logisticsWebsite) {
		this.logisticsWebsite = logisticsWebsite;
	}

	public String getLogisticsTel() {
		return logisticsTel;
	}

	public void setLogisticsTel(String logisticsTel) {
		this.logisticsTel = logisticsTel;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
     * 物流节点item信息
     */
    public class LogisticsData  extends OutputDto{
    	
		private static final long serialVersionUID = 1L;

		/** 时间 */
        private String time;

        /** 处理情况 */
        private String context;
        
        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }
        
    	@Override
    	public String toString() {
    		return ToStringBuilder.reflectionToString(this);
    	}
    }
}
