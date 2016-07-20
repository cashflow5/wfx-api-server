package com.yougou.wfx.member.dto.input;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.InputDto;

public class MemberForWXInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	public String openId;
	
	public String nickname;
	
	public int sex;
	
	public String headimgurl;
	
	public String parentSellerId;
	
	public String memberId;
	
	public String h5OpenId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	

	public String getParentSellerId() {
		return parentSellerId;
	}

	public void setParentSellerId(String parentSellerId) {
		this.parentSellerId = parentSellerId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getH5OpenId() {
		return h5OpenId;
	}

	public void setH5OpenId(String h5OpenId) {
		this.h5OpenId = h5OpenId;
	}

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
