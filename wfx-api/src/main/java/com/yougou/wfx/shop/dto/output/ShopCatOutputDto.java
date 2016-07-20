package com.yougou.wfx.shop.dto.output;

import java.util.HashSet;
import java.util.Set;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * 店铺分类输出DTO
 * @author zhang.hc
 *
 */
public class ShopCatOutputDto extends OutputDto {

	/** 序列化版本号 **/
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String name;
	
	private String sellerId;
	
	private Integer level;
	
	private Integer num;
	
	private Set<ShopCatOutputDto> childs = new HashSet<ShopCatOutputDto>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Set<ShopCatOutputDto> getChilds() {
		return childs;
	}

	public void setChilds(Set<ShopCatOutputDto> childs) {
		this.childs = childs;
	}

	@Override
	public String toString() {
		return "ShopCatOutputDto [id=" + id + ", name=" + name + ", sellerId=" + sellerId + ", level=" + level
				+ ", num=" + num + ", childs=" + childs + "]";
	}
}
