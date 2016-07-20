package com.yougou.wfx.test.base;

import org.apache.commons.lang3.StringUtils;

public class StringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String phone = "18603064816";
		phone = StringUtils.leftPad(phone, 3, "*");
		System.out.println(phone);
	}

}
