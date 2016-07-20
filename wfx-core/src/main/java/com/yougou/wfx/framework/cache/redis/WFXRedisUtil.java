package com.yougou.wfx.framework.cache.redis;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.yougou.tools.common.utils.SpringContextHolder;

/**
 * wfx redis标准操作工具
 * @author wuyang
 *
 */
public class WFXRedisUtil {
	
	public enum WFXModuleType {

		COMMON("common"),
		ADDRESS("address"),
		AFTERSERVIVE("mobile"),
		LOGIN("login"),
		REGISTER("register"),
		MONITOR("monitor"),
		ACCOUNT("account"),
		SECURITY("security"),
		SHORTURL("shorturl"),
		PHONECOUPON("phonecoupon"),
		SYSCONFIG("sysConfig"),
		SELLER_STATUS_BY_MEMBER("sellerAndShopStatusByMember"),
		SELLER_STATUS_BY_SHOP("sellerAndShopStatusByShop"),
		SHOPVISIT("shop_visit_count"),//店铺访次数量
		FIRSTPAY("first_pay");//首单支付自动升级优粉的标识
		
		private String name;
		public String getName() {
			return name;
		}
		WFXModuleType(String name){
			this.name = name;
		}
	}
	
	private static RedisTemplate<String, String> redisTemplate =  SpringContextHolder.getBean("redisTemplate");
	private final static Logger logger = Logger.getLogger(WFXRedisUtil.class);
	
	private static ValueOperations<String,String> opsForValue(){
		redisTemplate.setValueSerializer(new JsonRedisSerializable<Object>());
		return redisTemplate.opsForValue();
	}
	
	public static String getKey(WFXModuleType moduleType,String key){
		return "wfx:"+ moduleType.getName() +":" + key;
	}
	
	public static String getString(WFXModuleType moduleType,String key){
		return opsForValue().get(getKey(moduleType,key));
	}
	
	public static int getInt(WFXModuleType moduleType,String key){
		int result = 0;
		String val = opsForValue().get(getKey(moduleType,key));
		if(StringUtils.isNotBlank(val)){
			try{
				result = Integer.valueOf(val);
			}catch(Exception ex){
				logger.error("[MemberRedisUtil]转换数据[String->int]时发生异常,val=" + val, ex);
			}
		}
		return result;
	}
	
	public static void set(WFXModuleType moduleType,String key,String value){
		long expire = redisTemplate.getExpire(getKey(moduleType,key));
		if(expire >-1){
			if(expire == 0){
				expire = 1;
			}
			opsForValue().set(getKey(moduleType,key), value,expire, TimeUnit.SECONDS);
		}else{
			opsForValue().set(getKey(moduleType,key), value);
		}
	}
	
	public static void set(WFXModuleType moduleType,String key,String value,int timeout,TimeUnit unit){
		opsForValue().set(getKey(moduleType,key), value, timeout, unit);
	}
	
	public static void set(WFXModuleType moduleType,String key,int value){
		long expire = redisTemplate.getExpire(getKey(moduleType,key));
		if(expire >-1){
			if(expire == 0){
				expire = 1;
			}
			opsForValue().set(getKey(moduleType,key), String.valueOf(value),expire, TimeUnit.SECONDS);
		}else{
			opsForValue().set(getKey(moduleType,key), String.valueOf(value));
		}
	}
	
	public static void set(WFXModuleType moduleType,String key,int value,int timeout,TimeUnit unit){
		opsForValue().set(getKey(moduleType,key), String.valueOf(value), timeout, unit);
	}
	
	public static void delete(WFXModuleType moduleType,String key){
		redisTemplate.delete(getKey(moduleType,key));
	}
	
	public static void expire(WFXModuleType moduleType,String key,int timeout,TimeUnit unit){
		redisTemplate.expire(getKey(moduleType,key), timeout, unit);
	}
}
