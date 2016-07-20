package com.yougou.wfx.outside.request;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * <p>Title: BaseRequest</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public abstract class BaseRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
