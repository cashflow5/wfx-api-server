/**
 * 创建于: 2016年4月5日 下午1:59:13<br>
 * 所属项目:优购时尚商城运营财务组
 * 文件名称:MessageOutputDto.java
 * 作者:zhang.jj
 * 版权信息: 版权所有 © 2015-2016 优购
 */
package com.yougou.wfx.finance.dto.output;

import java.util.HashMap;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * 类功能描述：
 * MessageOutputDto.java
 * @author zhang.jj
 * @version 0.1
 * @history 2016年4月5日 zhang.jj 创建MessageOutputDto.java
 */
public class MessageOutputDto extends OutputDto {

	/**
	 * 唯一标识符.
	 */
	private static final long serialVersionUID = -5473013104095269038L;
	
	/**
	 * 200：单次提现金额不能大于 500 元
	 * 300：月累计提现金额不能超过30000
	 * 400：账户余额不足（提现金额大于账户余额）
	 * 600：超出提现次数（一周限制提现一次）
	 * code:100 表示当前操作成功.
	 * code:500 表示当前操作失败.
	 */
	private String code;
    
	/**
	 * 成功或者失败提示的相关信息.
	 */
	private String message;
	
	
	/**
	 * 对应一些复杂的返回,采用分类信息存储.
	 * 分类信息的存储.
	 */
	private HashMap<String,String> sortMessageMap;


	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * @return the sortMessageMap
	 */
	public HashMap<String, String> getSortMessageMap() {
		return sortMessageMap;
	}


	/**
	 * @param sortMessageMap the sortMessageMap to set
	 */
	public void setSortMessageMap(HashMap<String, String> sortMessageMap) {
		this.sortMessageMap = sortMessageMap;
	}

	@Override
	public String toString() {
		return "MessageOutputDto [code=" + code + ", message=" + message
				+ ", sortMessageMap=" + sortMessageMap + "]";
	}
	
}
