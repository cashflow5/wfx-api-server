package com.yougou.wfx.order.dto.output;

import com.yougou.wfx.dto.base.OutputDto;



public class WfxLogisticsData   extends OutputDto{
    /**
	 * 
	 */
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
        return "LogisticsData [time=" + time + ", context=" + context + "]";
    }
}
