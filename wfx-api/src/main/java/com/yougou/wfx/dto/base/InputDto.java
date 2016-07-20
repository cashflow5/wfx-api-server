package com.yougou.wfx.dto.base;


/**
 * 输入数据Dto
 * @author wuyang
 *
 */
public class InputDto extends BaseDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 远程调用者IP
	 */
	private String remoteHost;
	
	/**
	 * 远程调用者端口
	 */
	private String remotePort;
	
	private UserContext userContext;

	public String getRemoteHost() {
		try{
//		        RpcContext rpcContext = RpcContext.getContext();
//		        remoteHost = rpcContext.getRemoteHost();
		}catch(Exception ex){}
		return remoteHost;
	}


	public String getRemotePort() {
		try{
//	        RpcContext rpcContext = RpcContext.getContext();
//	        remotePort = rpcContext.getRemotePort();
	}catch(Exception ex){}
		return remotePort;
	}


	public UserContext getUserContext() {
		return userContext;
	}


	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}
	
}
