package com.yougou.wfx.commodity.dto.input;

import com.yougou.wfx.dto.base.InputDto;


public class BagPageInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	// 分销包名称
	private String bagName;
	
	// 状态
	private int status;
	
	// 开始时间
	private String startTime;
	
	// 结束时间
	private String endTime;

	public String getBagName() {
		return bagName;
	}

	public void setBagName(String bagName) {
		this.bagName = bagName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "TagPageModel [bagName=" + bagName + ", status=" + status
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
}