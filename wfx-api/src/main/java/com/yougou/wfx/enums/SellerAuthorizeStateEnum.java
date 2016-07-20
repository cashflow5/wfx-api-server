package com.yougou.wfx.enums;

/**
 * 分销商授权状态枚举
 * @author zheng.qq
 * 1待审核，2审核通过 3审核不通过
 */
public enum SellerAuthorizeStateEnum {
	AUTHORIZEING(1,"待审核"),
	AUTHORIZE_PASS(2,"审核通过"),
	AUTHORIZE_NOT_PASS(3,"审核不通过");
	
	private Integer status;
	private String statusDesc;
	
	SellerAuthorizeStateEnum(Integer status,String statusDesc){
		this.setStatus(status);
		this.setStatusDesc(statusDesc);
	}
	
	/**
	 * 传入key值，获取对应描述
	 * @param state
	 * @return
	 */
	public static String getDescByKey(Integer keyValue){
		if(null != keyValue){
			for(SellerAuthorizeStateEnum st : SellerAuthorizeStateEnum.values()){
				Integer key = st.getStatus();
				if(key == keyValue ){
					return st.getStatusDesc();
				}
			}
		}
		return null;
	}
	/**
	 * 传入店铺状态key值，获取对应状态描述
	 * @param state
	 * @return
	 */
	public static String getStateDescByState(Integer status){
		
		if(null != status){
			for(ShopStatusEnum st : ShopStatusEnum.values()){
				Integer key = st.getStatus();
				if(key == status){
					return st.getStatusDesc();
				}
			}
		}
		return null;
	}


	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
