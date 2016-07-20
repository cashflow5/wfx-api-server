package com.yougou.wfx.commodity.dto.output;

import com.yougou.wfx.dto.base.OutputDto;
/**
 * 
 * @author zhang.wj
 * @Date 创建时间：2016-03-26 17:40:55
 */
public class SellerCommodityCatRelaOutpuDto  extends OutputDto{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * 主键ID
		 */
		private String id;
		/**
		 * 分类名称
		 */
		private String name;
		/**
		 * 分销商id
		 */
		private String sellerId;
		/**
		 * 分类级别
		 */
		private String level;
		/**
		 * 商品数量
		 */
		private int num;
		/**
		 * 父节点id
		 */
		private String parentId;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSellerId() {
			return sellerId;
		}
		public void setSellerId(String sellerId) {
			this.sellerId = sellerId;
		}
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}
		
	
}
