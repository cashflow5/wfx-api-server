 /*
 * 版本信息
 
 * 日期 2016-03-29 13:37:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.utils;

import com.yougou.component.logistics.constant.LogisticsCompany;


/**
 * 物流工具类
 * @author wzf
 * @Date 创建时间：2016-03-29 13:37:55
 */
public class LogisticsUtil {
	
	/**
	 * 根据快递公司的编码获取枚举类
	 * @param expressCode
	 * @return
	 */
	public static LogisticsCompany getLogisticsByCode(String expressCode){
		LogisticsCompany logisticsCompany = null;
		if("weitepai".equals(expressCode)){
			logisticsCompany = LogisticsCompany.WEITEPAI;
		}else if("shentong".equals(expressCode)){
			logisticsCompany = LogisticsCompany.SHENTONG;
		}else if("feihu".equals(expressCode)){
			logisticsCompany = LogisticsCompany.FEIHU;
		}else if("haiwaihuanqiu".equals(expressCode)){
			logisticsCompany = LogisticsCompany.HAIWAIHUANQIU;
		}else if("shunfeng".equals(expressCode)){
			logisticsCompany = LogisticsCompany.SHUNFENG;
		}else if("rufengda".equals(expressCode)){
			logisticsCompany = LogisticsCompany.RUFENGDA;
		}else if("quanfengkuaidi".equals(expressCode)){		
			logisticsCompany = LogisticsCompany.QUANFENG;
		}else if("shunjiefengda".equals(expressCode)){
			logisticsCompany = LogisticsCompany.SJFD;
		}else if("shentong".equals(expressCode)){
			logisticsCompany = LogisticsCompany.STO;
		}else if("ems".equals(expressCode)){
			logisticsCompany = LogisticsCompany.EMS;
		}else if("yuantong".equals(expressCode)){
			logisticsCompany = LogisticsCompany.YTO;
		}else if("zhongtong".equals(expressCode)){
			logisticsCompany = LogisticsCompany.ZTO;
		}else if("jd".equals(expressCode)){
			logisticsCompany = LogisticsCompany.JD;
		}else if("feiyuanvipshop".equals(expressCode)){
			logisticsCompany = LogisticsCompany.GDTL;
		}
		return logisticsCompany;
	}
	
}
