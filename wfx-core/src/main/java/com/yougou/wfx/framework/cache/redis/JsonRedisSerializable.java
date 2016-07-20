package com.yougou.wfx.framework.cache.redis;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * fastJson序列化处理
 * @author wuyang
 *
 * @param <T>
 */
public class JsonRedisSerializable<T> implements RedisSerializer<T> {

	private final Charset charset;

	public JsonRedisSerializable() {
		this(Charset.forName("UTF8"));
	}

	public JsonRedisSerializable(Charset charset) {
		Assert.notNull(charset);
		this.charset = charset;
	}

	
	@SuppressWarnings("unchecked")
	public T deserialize(byte[] bytes) {
		if(bytes == null){return null;}
		String string = new String(bytes, charset);
		T result = (T) JSON.parse(string);
		if(result instanceof JSONArray){
			List<T> tempList = new ArrayList<T>();
			Iterator<T> iterator = ((List<T>) result).iterator();
			while(iterator.hasNext()){
				T t = iterator.next();
				tempList.add(t);
			}
			result = (T) tempList;
		}
		return (string == null ? null : result);
	}

	public byte[] serialize(T object) {
		if(object == null){return null;}
		String string = JSON.toJSONString(object ,SerializerFeature.WriteClassName);
		return (string == null ? null : string.getBytes(charset));
	}

}
