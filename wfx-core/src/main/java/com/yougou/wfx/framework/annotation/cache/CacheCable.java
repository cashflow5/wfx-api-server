package com.yougou.wfx.framework.annotation.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheCable {
	
	String[] value();

	String key();

	/**
	 *  过期时间单位：s（秒）
	 * @return
	 */
	long expiration() default 0;
	
	/**
	 * 本地过期时间 单位：s（秒）
	 * @return
	 */
	long localExpiration() default 0;
	
}
