package com.yougou.wfx.framework.annotation.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

import com.yougou.tools.common.utils.SpringContextHolder;


public class RedisCacheUtil {
	
	private static RedisCacheUtil commentRedisCacheUtil;
	private static RedisTemplate<String, Object> redisTemplate;
	
	private RedisCacheUtil(){};
	
	public static RedisCacheUtil getInstance(){
		if(commentRedisCacheUtil == null){
			commentRedisCacheUtil = new RedisCacheUtil();
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}
		return commentRedisCacheUtil;
	} 
	
	public void put(String key ,Object value ,String []nameSpace ,long expiration){
//		for (String nps : nameSpace) {
//			if(StringUtils.isBlank(nps)){
//				continue;
//			}
			redisTemplate.opsForValue().set(key, value, expiration, TimeUnit.SECONDS);
//		}
	}
	
	public Object get(String key ,String []nameSpace){
		Object object = null;
//		for (String nps : nameSpace) {
//			if(StringUtils.isBlank(nps)){
//				continue;
//			}
			object = redisTemplate.opsForValue().get(key);
//		}
		return object;
	}

}
