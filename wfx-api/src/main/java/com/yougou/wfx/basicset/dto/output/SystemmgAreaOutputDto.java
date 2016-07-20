 /*
 * 版本信息
 
 * 日期 2016-04-07 18:29:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.basicset.dto.output;

import java.util.Date;
import com.yougou.wfx.util.DatetimeUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.dto.base.OutputDto;

/**
 * SystemmgAreaOutputDto
 * @author wfx
 * @Date 创建时间：2016-04-07 18:29:33
 */
public class SystemmgAreaOutputDto extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 编号
	 */
	private String no;
	/**
	 * 是否有子节点
	 */
	private String isleaf;
	/**
	 * 子节点编号
	 */
	private String child;
	/**
	 * 级别
	 */
	private String level;
	/**
	 * 排序 
	 */
	private String sort;
	/**
	 * 邮编
	 */
	private String post;
	/**
	 * 区号
	 */
	private String code;
	/**
	 * 时间戳
	 */
	private Long timestamp;
	/**
	 * 是否生效(1 生效 0 不生效)
	 */
	private Integer isUsable;
	/**
	 * 是否货到付款:1-支持;0-不支持
	 */
	private Integer isCashOnDelivy;
	/**
	 * 可配送区
	 */
	private String butTheServiceArea;
	/**
	 * 不可配送区
	 */
	private String notServiceArea;
	/**
	 * 无线平台是否支持货到付款:1-支持;0-不支持
	 */
	private Integer isCashOnDelivyMobile;
	/**
	 * 是否支持电子发票(0:否；1：是)
	 */
	private Integer isSupportInvoice;

	public SystemmgAreaOutputDto(){
	}

	public SystemmgAreaOutputDto(
		String id
	){
		this.id = id;
	}

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id == null ? null : this.id.trim();
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name == null ? null : this.name.trim();
	}
	public void setNo(String value) {
		this.no = value;
	}
	
	public String getNo() {
		return this.no == null ? null : this.no.trim();
	}
	public void setIsleaf(String value) {
		this.isleaf = value;
	}
	
	public String getIsleaf() {
		return this.isleaf == null ? null : this.isleaf.trim();
	}
	public void setChild(String value) {
		this.child = value;
	}
	
	public String getChild() {
		return this.child == null ? null : this.child.trim();
	}
	public void setLevel(String value) {
		this.level = value;
	}
	
	public String getLevel() {
		return this.level == null ? null : this.level.trim();
	}
	public void setSort(String value) {
		this.sort = value;
	}
	
	public String getSort() {
		return this.sort == null ? null : this.sort.trim();
	}
	public void setPost(String value) {
		this.post = value;
	}
	
	public String getPost() {
		return this.post == null ? null : this.post.trim();
	}
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCode() {
		return this.code == null ? null : this.code.trim();
	}
	public void setTimestamp(Long value) {
		this.timestamp = value;
	}
	
	public Long getTimestamp() {
		return this.timestamp;
	}
	public void setIsUsable(Integer value) {
		value = value == null ? 0 : value;
		this.isUsable = value;
	}
	
	public Integer getIsUsable() {
		return this.isUsable == null ? 0 : this.isUsable;
	}
	public void setIsCashOnDelivy(Integer value) {
		value = value == null ? 0 : value;
		this.isCashOnDelivy = value;
	}
	
	public Integer getIsCashOnDelivy() {
		return this.isCashOnDelivy == null ? 0 : this.isCashOnDelivy;
	}
	public void setButTheServiceArea(String value) {
		this.butTheServiceArea = value;
	}
	
	public String getButTheServiceArea() {
		return this.butTheServiceArea == null ? null : this.butTheServiceArea.trim();
	}
	public void setNotServiceArea(String value) {
		this.notServiceArea = value;
	}
	
	public String getNotServiceArea() {
		return this.notServiceArea == null ? null : this.notServiceArea.trim();
	}
	public void setIsCashOnDelivyMobile(Integer value) {
		value = value == null ? 0 : value;
		this.isCashOnDelivyMobile = value;
	}
	
	public Integer getIsCashOnDelivyMobile() {
		return this.isCashOnDelivyMobile == null ? 0 : this.isCashOnDelivyMobile;
	}
	public void setIsSupportInvoice(Integer value) {
		value = value == null ? 0 : value;
		this.isSupportInvoice = value;
	}
	
	public Integer getIsSupportInvoice() {
		return this.isSupportInvoice == null ? 0 : this.isSupportInvoice;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

