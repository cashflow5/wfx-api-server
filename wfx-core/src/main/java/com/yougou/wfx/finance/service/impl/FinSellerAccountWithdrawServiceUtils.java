/**
 * 创建于: 2016年4月6日 下午3:48:37<br>
 * 所属项目:优购时尚商城运营财务组
 * 文件名称:FinSellerAccountWithdrawServiceUtils.java
 * 作者:zhang.jj
 * 版权信息: 版权所有 © 2015-2016 优购
 */
package com.yougou.wfx.finance.service.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;

import org.apache.commons.lang3.StringUtils;

/**
 * 类功能描述：
 * FinSellerAccountWithdrawServiceUtils.java
 * @author zhang.jj
 * @version 0.1
 * @history 2016年4月6日 zhang.jj 创建FinSellerAccountWithdrawServiceUtils.java
 */
public class FinSellerAccountWithdrawServiceUtils {
	
	/**
	 * 获取相应的流水号.定义相应的ipaddress;
	 */
	private static String ipAddress = "";
	
	/**
	 * 生成流水号
	 * @param tradeType
	 * @return
	 * @throws Exception
	 */
	public static String generateSequenceNumber(String tradeType) {
		StringBuffer str = new StringBuffer();
		String ipAdd = getIpAdd();
		String[] ipArr = null;
		String ip = "";
		if(ipAdd!=null&&!"".equals(ipAdd)){
			ipArr = ipAdd.split("\\.");
			ip = ipArr[ipArr.length-1];
		}
		str.append(tradeType).append(ip);
		synchronized(ipAddress){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			str.append(new Date().getTime());
		}
		ipAdd = null;
		ipArr = null;
		ip = null;
		return str.toString();
	}
	
	/**
	 * 获取linux下的ip地址
	 * @return
	 */
	private static String getIpAdd(){
		if(StringUtils.isNotBlank(ipAddress)){
			return ipAddress;
		}
		String sIP = "";
        InetAddress ip = null;
        boolean bFindIP = false;
        Enumeration<NetworkInterface> netInterfaces = null;
        Enumeration<InetAddress> ips = null;
        try {
           
            netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                if (bFindIP) {
                    break;
                }
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                // 遍历所有ip
                ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    ip = (InetAddress) ips.nextElement();
                    if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是lookback地址
                            && ip.getHostAddress().indexOf(":") == -1) {
                            bFindIP = true;
                            break;
                    }
                 }
              }
            if (null != ip) {
                sIP = ip.getHostAddress();
            }
            
        } catch (Exception e) {
            return "";
        } finally{
        	 ip = null;
        	 netInterfaces = null;
             ips = null;
        }
        ipAddress = sIP;
        return sIP;
	}
}
