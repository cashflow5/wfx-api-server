package com.yougou.wfx.framework.cache.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class LocalCache{
	
	private static Cache<String,Object> localCache = null;
	
	private static final Logger logger = LoggerFactory.getLogger(LocalCache.class);
	
	public static Cache<String,Object> getInstance(){
		if(localCache==null){
			localCache = CacheBuilder
					.newBuilder()
					// 设置大小，条目数
					.maximumSize(30000)
//					// 设置失效时间，创建时间
//					.expireAfterWrite(1000, TimeUnit.SECONDS)
//					// 设置时效时间，最后一次被访问
//					.expireAfterAccess(1000, TimeUnit.SECONDS)
					// 设置要统计缓存的命中率
					.recordStats()
					// 移除缓存的移除通知
					.removalListener(new RemovalListener<String, Object>() {
						public void onRemoval(RemovalNotification<String, Object> notification) {
							logger.debug("本地缓存移除,key="+ notification.getKey());
						}
					}).build();
		}
		return localCache;
	}

	/**
	 * 构造方法-可设置大小
	 * @param maximumSize
	 */
	public LocalCache(long maximumSize){
		localCache.cleanUp();
		localCache = CacheBuilder
				.newBuilder()
				// 设置大小，条目数
				.maximumSize(maximumSize)
				// 设置要统计缓存的命中率
				.recordStats()
				// 移除缓存的移除通知
				.removalListener(new RemovalListener<String, Object>() {
					public void onRemoval(RemovalNotification<String, Object> notification) {
						logger.debug("本地缓存移除,key="+ notification.getKey());
					}
				}).build();
	}
}