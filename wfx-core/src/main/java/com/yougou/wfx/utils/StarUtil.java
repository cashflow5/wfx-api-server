/*
 * 版本信息
 
 * 日期 2016-04-18 17:09:20
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.utils;

import org.apache.commons.lang.StringUtils;


/**
 * 敏感信息加星
 * @author wuyang
 * @Date 创建时间：2016-04-18 17:09:20
 */
public class StarUtil {
	
	public enum StarType {
	    PHONE("phone"),// 手机号
	    EMAIL("email"),// email
	    ADDRESS("address"),// 收获详细地址
	    CARD("card"),// 身份证号
	    BANKNO("bankNo");// 银行卡
	    private final String type;
	    StarType(String type){
	    	this.type = type;
	    }
	}
	
	public static String setStar(StarType starType,String val){
		if (starType != null && StringUtils.isNotBlank(val)) {
	    	if(starType == StarType.PHONE){
	    		if(val.length() == 11){
	    			return hideShowInfo(val, 3, 6);
	    		}
	    	}else if(starType == StarType.EMAIL){
				if(val.length() > 1 && val.indexOf("@") != -1){
					String before = val.substring(0, val.indexOf("@"));
					if(before.length() > 1){
						String backStr = val.substring(val.indexOf("@"), val.length());
	            		int len = before.length();
	            		val = hideShowInfo(before, 2 , len) + backStr;
					}
	    		}
	    	}else if(starType == StarType.ADDRESS){
	    		val = hideShowInfo(val, 0 , val.length() / 2 -1);
	    	}else if(starType == StarType.CARD){
	    		if(val.length() == 18){
	    			val = hideShowInfo(val,5,14);
	    		}else if(val.length() == 15){
	    			val = hideShowInfo(val,5,11);
	    		}
	    	}else if(starType == StarType.BANKNO){
	    		val = hideShowInfo(val,8,val.length()-3);
	    	}
		}
		return val;
	}
	
	private static String hideShowInfo(String showInfo, int start, int end) {
		StringBuilder showStr = new StringBuilder();
		char[] showInfos = showInfo.toCharArray();
		for (int i = 0; i < showInfos.length; i++) {
			if (i>=start && i <= end) {
				showStr.append("*");
			} else {
				showStr.append(showInfos[i]);
			}
		}
		return showStr.toString();
	}
}
