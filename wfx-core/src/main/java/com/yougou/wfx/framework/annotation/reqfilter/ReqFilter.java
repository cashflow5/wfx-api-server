package com.yougou.wfx.framework.annotation.reqfilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求控制注解
 * @author wuyang
 * @Date 2016年4月26日
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target({ ElementType.METHOD })
@Inherited
public @interface ReqFilter {

	/**
	 * 前缀
	 * <br>
	 * 说明：使用SPEL 表达式
	 * @return
	 */
	String keySpel();
	
	/**
	 * 禁用时间(时间单位：毫秒),默认900000=15分钟
	 * @return
	 */
	int time() default 900000;
	
	/**
	 * 限制频率数,默认4000000
	 * @return
	 */
	int limit() default 4000000;
	
}
