package com.yougou.wfx.enums;




/**
 * 店铺状态枚举
 * @author zhang.f1
 *
 */
public enum ShopStatusEnum {
	OPEN_SHOP(1,"开启"),
	CLOSE_SHOP(2,"关闭");
	
	private Integer status;
	private String statusDesc;
	
	ShopStatusEnum(Integer status,String statusDesc){
		this.setStatus(status);
		this.setStatusDesc(statusDesc);
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
