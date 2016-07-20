package com.yougou.wfx.framework.bean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;

/**
 * 对象拷贝工具类
 * 
 * @author wuyang
 *
 */
public class BeanUtil {

	protected final static Logger logger = LoggerFactory.getLogger(BeanUtil.class);
	
	/**
	 * One type of List, converting to other types of List
	 * 
	 * @param srcList
	 * @param destType
	 * @return
	 */
	public static List<?> convertBeanList(List<?> srcList,
			Class<?> destType) {
		List<Object> list = new ArrayList<Object>();
		for (Object obj : srcList) {
			Object destObj = convertBean(obj, destType);
			if (destObj != null) {
				list.add(destObj);
			}
		}
		return list;
	}

	/**
	 * A type of object, cast to another type of object , And copies of the same
	 * attributes inside
	 * 
	 * @param src
	 * @param type
	 * @return
	 */
	public static Object convertBean(Object src, Class<?> type) {
		if(src == null){
			return null;
		}
		Object desObj = null;
		try {
			desObj = type.newInstance();
			copyBean(src, desObj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return desObj;
	}

	/**
	 * Copies of the same name as the property of the bean
	 * 
	 * @param src
	 * @param dest
	 */
	public static void copyBean(Object src, Object dest) {
		try {
			BeanUtils.copyProperties(src, dest);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getPropertyValue(Object src, String property) {
		Object result = null;
		try {
			Class cls = src.getClass();
			property = property.substring(0, 1).toUpperCase()
					+ property.substring(1, property.length());
			Method method = cls.getMethod("get" + property, null);

			if (method != null) {
				result = method.invoke(src, null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
}
