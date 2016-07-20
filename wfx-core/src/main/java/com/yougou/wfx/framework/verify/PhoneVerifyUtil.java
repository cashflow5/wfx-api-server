package com.yougou.wfx.framework.verify;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 手机验证工具类
 *
 */
public class PhoneVerifyUtil {
	
	/**
	 * 验证手机号码格式
	 * @param phone
	 * @return
	 */
	public static boolean validMobile(String phone){
		if(StringUtils.isBlank(phone)){
			return false;
		}
		boolean flag = false;
		if(Pattern.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0-9]|17[0-9])\\d{8}$",phone)){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 删除空格
	 * @param org0
	 * @return
	 */
	public static String delEmptyChar(String arg0){
		Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
		if(pattern.matcher(arg0) != null){
			arg0 = pattern.matcher(arg0).replaceAll("");
		}
		return arg0;
	}

}
