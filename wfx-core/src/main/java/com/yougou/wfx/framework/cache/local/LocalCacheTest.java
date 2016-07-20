package com.yougou.wfx.framework.cache.local;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class LocalCacheTest {

	private static LoadingCache<String,Object> loadingCache = null;

	public static LoadingCache<String,Object> getInstance() {
		if (loadingCache == null) {
			loadingCache = CacheBuilder.newBuilder()
					// 设置大小，条目数
					.maximumSize(200)
					// 设置失效时间，创建时间
					.expireAfterWrite(1000, TimeUnit.SECONDS)
					// 设置时效时间，最后一次被访问
					.expireAfterAccess(1000, TimeUnit.SECONDS)
					// 注册统计
					.recordStats()
					// 移除缓存的监听器
					.removalListener(new RemovalListener<String, Object>() {
						public void onRemoval(
								RemovalNotification<String, Object> notification) {
							System.out.println("[LocalCache]有缓存数据被移除了,key="
									+ notification.getKey() + ",data="
									+ notification.getValue());
						}
					})
					// 缓存构建的回调
					.build(new CacheLoader<String, Object>() {// 加载缓存
						@Override
						public String load(String key) throws Exception {
							System.out.println("[LocalCache]没命中[" + key + "]的缓存数据!");
							String data = String.valueOf(new Random()
									.nextInt(20000));
//							System.out.println("[LocalCache]重新构建[" + key+ "]的缓存数据!data=" + data);
							return key + "=====" + data;
						}
					});
		}
		return loadingCache;
	}

	public static void main(String[] args) throws Exception {
		new Thread(new Runnable() {
			@Override
			public void run() {
				LoadingCache<String,Object> cache = LocalCacheTest.getInstance();

				try {
					System.out.println("s1:" + cache.get("wuyang"));
					System.out.println("s1:" + cache.get("wuyang1"));
					System.out.println("s1:" + cache.get("wuyang2"));
					System.out.println("s1:" + cache.get("wuyang3"));
					System.out.println("s1:" + cache.get("wuyang1"));
					System.out.println("s1:" + cache.get("wuyang2"));
					System.out.println("s1:" + cache.get("wuyang6"));
					System.out.println("s1:" + cache.get("wuyang7"));
					printCacheStats();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
		Thread.sleep(2000);
		printCacheStats();
		Thread.sleep(2000);
		LoadingCache<String,Object> cache = LocalCacheTest.getInstance();
		cache.invalidate("s2:" + "wuyang");
		printCacheStats();
		Thread.sleep(2000);
		System.out.println("s3:" + cache.get("wuyang"));
		printCacheStats();
		Thread.sleep(5000);
		System.out.println("s4:" + cache.get("wuyang"));
		printCacheStats();
		Thread.sleep(10000);
		printCacheStats();
	}
	
	private static void printCacheStats(){
		CacheStats stats = LocalCacheTest.getInstance().stats();
		StringBuffer sb = new StringBuffer(200);
		sb.append("[CacheStats]").append("requestCount=").append(stats.requestCount()).append(","); // 返回Cache的lookup方法查找缓存的次数，不论查找的值是否被缓存
		sb.append("hitCount=").append(stats.hitCount()).append(","); //返回Cache的lookup方法命中缓存的次数
		sb.append("hitRate=").append(stats.hitRate()).append(",");//返回缓存请求的命中率，命中次数除以请求次数
		sb.append("missCount=").append(stats.missCount()).append(",");//返回缓存请求的未命中的次数
		sb.append("missRate=").append(stats.missRate()).append(",");//返回缓存请求未命中的比率，未命中次数除以请求次数
		sb.append("loadCount=").append(stats.loadCount()).append(",");//返回缓存调用load方法加载新值的次数
		sb.append("loadSuccessCount=").append(stats.loadSuccessCount()).append(",");//返回缓存加载新值的成功次数
		sb.append("loadExceptionCount=").append(stats.loadExceptionCount()).append(",");//返回缓存加载新值出现异常的次数
		sb.append("loadExceptionRate=").append(stats.loadExceptionRate()).append(",");//返回缓存加载新值出现异常的比率
		sb.append("totalLoadTime=").append(stats.totalLoadTime()).append(",");//返回缓存加载新值所耗费的总时间
		sb.append("averageLoadPenalty=").append(stats.averageLoadPenalty()).append(",");//缓存加载新值的耗费的平均时间，加载的次数除以加载的总时间
		sb.append("evictionCount=").append(stats.evictionCount());//返回缓存中条目被移除的次数
		System.out.println(sb.toString());
	}
}
