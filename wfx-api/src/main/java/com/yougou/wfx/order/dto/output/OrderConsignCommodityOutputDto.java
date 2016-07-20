package com.yougou.wfx.order.dto.output;
/**
 * 物流订单商品信息
 * @author zhang.wj
 *
 */
public class OrderConsignCommodityOutputDto implements java.io.Serializable{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -8102575612779644524L;
		/**
		 * 商品名称
		 */
		private String prodName;
		/**
		 * 商品规格(颜色，尺码等）
		 */
		private String prodSpec;
		/**
		 * 购买数量。
		 */
		private Integer num;
		/**
		 * 商家名称
		 */
		private String shopName;
		/**
		 * 图片
		 */
		private String defaultPic;
		
		
		/**
		 * 店铺编码
		 */
		private String shopCode;
		
		public String getShopCode() {
			return shopCode;
		}

		public void setShopCode(String shopCode) {
			this.shopCode = shopCode;
		}
		public String getDefaultPic() {
			return defaultPic;
		}

		public void setDefaultPic(String defaultPic) {
			this.defaultPic = defaultPic;
		}

		public String getShopName() {
			return shopName;
		}

		public void setShopName(String shopName) {
			this.shopName = shopName;
		}

		public String getProdName() {
			return prodName;
		}

		public void setProdName(String prodName) {
			this.prodName = prodName;
		}

		public String getProdSpec() {
			return prodSpec;
		}

		public void setProdSpec(String prodSpec) {
			this.prodSpec = prodSpec;
		}

		public Integer getNum() {
			return num;
		}

		public void setNum(Integer num) {
			this.num = num;
		}

		
	
	
}
