package com.yougou.wfx.framework.utils;

import java.util.UUID;

/**
 * UUID
 */
public class UUIDGenerator {
	
	private UUIDGenerator(){}
	
	public static String get32LowCaseUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
