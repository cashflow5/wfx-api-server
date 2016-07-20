package com.yougou.wfx.outside.api;

import com.yougou.wfx.outside.request.LogisticsSendRequest;
import com.yougou.wfx.outside.request.SupplierAddressRequest;
import com.yougou.wfx.outside.response.LogisticsSendResponse;
import com.yougou.wfx.outside.response.SupplierAddressResponse;

/**
 * <p>
 * Title: ILogisticsForOutSideApi
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public interface ILogisticsForOutSideApi {

	/**
	 * 发货
	 * 
	 * @param request
	 * @return
	 */
	public LogisticsSendResponse logisticsSend(LogisticsSendRequest request) throws Exception ;

	/**
	 * 查询供货商地址接口
	 * 
	 * @param request
	 * @return
	 */
	public SupplierAddressResponse supplierAddressList(SupplierAddressRequest request) throws Exception ;
}
