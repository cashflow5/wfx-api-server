 /*
 * 版本信息
 
 * 日期 2016-03-24 16:17:44
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.member.dto.input;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.util.DatetimeUtil;
import com.yougou.wfx.dto.base.PageModel;

/**
 * MemberProfileInputDto
 * @author wfx
 * @Date 创建时间：2016-03-24 16:17:44
 */
public class MemberProfileInputDto extends PageModel<MemberProfileInputDto> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	/**
	 * avatarAddress
	 */
	//private String avatarAddress;
	/**
	 * 会员性别,1=男，2=女
	 */
	private Integer memberSex;
	
	/**
	 * 会员头像
	 */
	private String headShowImg;
	
	/**
	 * idCardNum
	 */
	private String idCardNum;
	/**
	 * 会员生日[年-月-日]
	 */
	private Date birthday;
	/**
	 * province
	 */
	private String province;
	/**
	 * city
	 */
	private String city;
	/**
	 * district
	 */
	private String district;
	/**
	 * tbl_member_loginaccount.id
	 */
	private String loginaccountId;
	/**
	 * 是否申请分销商:0=未申请，1=已申请
	 */
	private String applySeller;
	/**
	 * 申请分销商时间
	 */
	private Date applySellerTime;
	/**
	 * 已支付的 订单总数
	 */
	private Integer orderCount;
	/**
	 * 已支付的订单总金额
	 */
	private Double orderAmount;

	public MemberProfileInputDto(){
	}

	public MemberProfileInputDto(
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
	/*public void setAvatarAddress(String value) {
		this.avatarAddress = value;
	}
	
	public String getAvatarAddress() {
		return this.avatarAddress == null ? null : this.avatarAddress.trim();
	}*/
	public void setMemberSex(Integer value) {
		// value = value == null ? 0 : value;
		this.memberSex = value;
	}
	
	public Integer getMemberSex() {
		return this.memberSex ; //== null ? 0 : this.memberSex;
	}
	public void setIdCardNum(String value) {
		this.idCardNum = value;
	}
	
	public String getIdCardNum() {
		return this.idCardNum == null ? null : this.idCardNum.trim();
	}
	public void setBirthday(Date value) {
		this.birthday = value;
	}
	
	public Date getBirthday() {
		return this.birthday;
	}
	
	public String getStringBirthday() {
		if(this.birthday == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.birthday, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setProvince(String value) {
		this.province = value;
	}
	
	public String getProvince() {
		return this.province == null ? null : this.province.trim();
	}
	public void setCity(String value) {
		this.city = value;
	}
	
	public String getCity() {
		return this.city == null ? null : this.city.trim();
	}
	public void setDistrict(String value) {
		this.district = value;
	}
	
	public String getDistrict() {
		return this.district == null ? null : this.district.trim();
	}
	public void setLoginaccountId(String value) {
		this.loginaccountId = value;
	}
	
	public String getLoginaccountId() {
		return this.loginaccountId == null ? null : this.loginaccountId.trim();
	}
	public void setApplySeller(String value) {
		this.applySeller = value;
	}
	
	public String getApplySeller() {
		return this.applySeller == null ? null : this.applySeller.trim();
	}
	public void setApplySellerTime(Date value) {
		this.applySellerTime = value;
	}
	
	public Date getApplySellerTime() {
		return this.applySellerTime;
	}
	
	public String getStringApplySellerTime() {
		if(this.applySellerTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.applySellerTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setOrderCount(Integer value) {
		value = value == null ? 0 : value;
		this.orderCount = value;
	}
	
	public Integer getOrderCount() {
		return this.orderCount == null ? 0 : this.orderCount;
	}
	public void setOrderAmount(Double value) {
		this.orderAmount = value;
	}
	
	public Double getOrderAmount() {
		return this.orderAmount;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getHeadShowImg() {
		return headShowImg;
	}

	public void setHeadShowImg(String headShowImg) {
		this.headShowImg = headShowImg;
	}
}

