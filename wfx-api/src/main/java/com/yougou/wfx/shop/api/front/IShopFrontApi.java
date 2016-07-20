package com.yougou.wfx.shop.api.front;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.shop.dto.input.ShopInputDto;
import com.yougou.wfx.shop.dto.output.ShopCatOutputDto;
import com.yougou.wfx.shop.dto.output.ShopOutputDto;

/**
 * 店铺API接口
 * @author zhang.hc
 *
 */
public interface IShopFrontApi {
	
	/**
	 * 根据店铺id获取店铺信息
	 * @param id 店铺id
	 * @param isSeller 是否是店主自己搜索的店铺信息，如果是则包含一些店主相关订单统计的敏感信息。否则仅包含一般的店铺属性
	 * @return
	 */
	public ShopOutputDto getShopById(String id, boolean isSeller);
	
	/**
	 * 根据分销商的sellerId获取店铺信息
	 * @param sellerId 分销商id
	 * @return
	 */
	public ShopOutputDto getShopBySeller( String sellerId );
	
	/**
	 * 根据店铺id修改店铺公告
	 * @param id
	 * @param notice
	 * @return
	 */
	public WFXResult<Boolean> updateNoticeByShopId(String id, String notice);
	
	/**
	 * 根据店铺id获取店铺销售分类信息
	 * @param shopId
	 * @return
	 */
	public List<ShopCatOutputDto> getShopCatByShopId(String shopId);
	
	
	/**
	 * 校验店铺名称的唯一性
	 * @param shopName 店铺名称
	 * @return true:唯一的 false:已注册
	 * @author zhang.f
	 */
	WFXResult<Boolean> checkShopName(String shopName);	
	

	/**
	 * 检测是否包括敏感词 
	 * @param content 待检测内容
	 * @param separator 检查结果返回的敏感词分隔符，默认英文逗号
	 * @return 返回含有的敏感词，否则返回null
	 * @author zhang.f
	 */
	WFXResult<String> checkSensitiveWord(String separator, String content);
	
	/**
	 * 设置店招信息
	 * @param id 非空
	 * @param in 非空
	 * @param fileName 非空
	 * @return 200:成功
	 */
	public WFXResult<String> updateSign(String id, String fileName, InputStream in);
	
	/**
	 * 设置店铺头像
	 * @param id 非空
	 * @param in 非空
	 * @param fileName 非空
	 * @return 200:成功
	 */
	public WFXResult<String> updateLogo(String id, String fileName, InputStream in);
	
	/**
	 * 修改店铺信息（比如：联系人或者联系电话）
	 * @param shopInputDto 根据dto对象更新数据库，sellerId 不能为空！（不为空的字段都会更新到数据库）
	 * @return
	 */
	public WFXResult<Boolean> updateShop(ShopInputDto shopInputDto);
	
	/**
	 * 店铺访次递增
	 * @param shopId 店铺ID
	 * @return 递增后的访次
	 */
	int addShopVisitCount(String shopId);
	
	/**
	 * 根据店铺ID 获取访次
	 * @param shopId 店铺ID
	 * @return 访次
	 */
	int getShopVisitCountById(String shopId);
	
	/**
	 * 获取店铺默认图片，logo，店招
	 */
	Map<String, String> getShopDefaultImagesUrl();
	/**
	 * 根据分销商的电话（登陆账号）获取店铺信息
	 * @param loginName 登陆账号
	 * @return
	 */
	public ShopOutputDto getShopByPhoneNumber( String loginName );
	/**
	 * 根据分销商的登陆账号ID获取店铺信息
	 * @param memberId 登陆账号Id
	 * @return
	 */
	public ShopOutputDto getShopByMemberId( String memberId );

	/**
	 * 生成优粉的二维码并上传到服务器,保存到Shop表
	 * 
	 */
	WFXResult<String> generateQrCode(String wxCodeUrl,
			ShopOutputDto shopOutputDto);
}
