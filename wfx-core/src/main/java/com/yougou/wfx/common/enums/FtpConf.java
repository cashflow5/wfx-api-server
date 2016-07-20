package com.yougou.wfx.common.enums;

/**
 * FTP相关路径属性
 * 
 * @author zhang.hc
 *
 */
public enum FtpConf {
	/** 用户头像 **/
	MEMBER_LOGO {
		public String getPath() {
			return "MEMBER_LOGO";
		}

		public Integer getMaxSize() {
			return 2 * 1024 * 1024;
		}
	},
	/** 店铺LOGO图片 **/
	SHOP_LOGO {
		public String getPath() {
			return "SHOP_LOGO";
		}

		public Integer getMaxSize() {
			return 2 * 1024 * 1024;
		}
	},
	/** 店招图片 **/
	SHOP_SIGN {
		public String getPath() {
			return "SHOP_SIGN";
		}

		public Integer getMaxSize() {
			return 2 * 1024 * 1024;
		}
	},
	/** 店铺二维码图片 **/
	SHOP_QR_CODE {
		public String getPath() {
			return "SHOP_QR_CODE";
		}

		public Integer getMaxSize() {
			return 2 * 1024 * 1024;
		}
	},
	/** 分销包小图 **/
	BAG_SMALL_PIC {
		public String getPath() {
			return "BAG_SMALL_PIC";
		}

		public Integer getMaxSize() {
			return 2 * 1024 * 1024;
		}
	},
	/** 分销包大图 **/
	BAG_BIG_PIC {
		public String getPath() {
			return "BAG_BIG_PIC";
		}

		public Integer getMaxSize() {
			return 2 * 1024 * 1024;
		}
	},

	/** 轮播图 **/
	CAROUSEL_PIC {
		public String getPath() {
			return "CAROUSEL_PIC";
		}

		public Integer getMaxSize() {
			return 1 * 1024 * 1024;
		}

		public Integer getWidth() {
			return 640;
		}

		public Integer getHeight() {
			return 200;
		}
	},

	/** 销售分类图片 **/
	SALECAT_PIC {
		public String getPath() {
			return "SALE_CAT_PIC";
		}

		public Integer getMaxSize() {
			return 1 * 1024 * 1024;
		}

		public Integer getWidth() {
			return 80;
		}

		public Integer getHeight() {
			return 80;
		}
	},

	/** 发现功能封面 **/
	ARTICLE_COVER_PIC {
		public String getPath() {
			return "ARTICLE_COVER_PIC";
		}

		public Integer getMaxSize() {
			return 1 * 1024 * 800;
		}

		public Integer getWidth() {
			return 290;
		}

		public Integer getHeight() {
			return 224;
		}
	},

	/** 发现功能文章 **/
	ARTICLE_EDIT_PIC {
		public String getPath() {
			return "ARTICLE_EDIT_PIC";
		}

		public Integer getMaxSize() {
			return 1024 * 800;
		}

		public Integer getWidth() {
			return 640;
		}

		public Integer getHeight() {
			return 1200;
		}
	},

	/** 发现功能轮播图 **/
	DISCOVER_CAR_PIC {
		public String getPath() {
			return "DISCOVER_CAR_PIC";
		}

		public Integer getMaxSize() {
			// 发现模块轮播图大小为1.5M
			return 1536 * 1024;
		}

		public Integer getWidth() {
			return 640;
		}

		public Integer getHeight() {
			return 300;
		}
	},

	/** 品牌大图 */
	BRAND_LOGO_NAME_PIC {
		public String getPath() {
			return "BRAND_PIC";
		}

		public Integer getMaxSize() {
			return 2 * 1024 * 1024;
		}

		public Integer getWidth() {
			return 74;
		}

		public Integer getHeight() {
			return 64;
		}
	},

	/** 品牌微图 */
	BRAND_LOGO_LEAST_PIC {
		public String getPath() {
			return "BRAND_PIC";
		}

		public Integer getMaxSize() {
			return 2 * 1024 * 1024;
		}

		public Integer getWidth() {
			return 74;
		}

		public Integer getHeight() {
			return 64;
		}
	},

	/** 品牌小图 */
	BRAND_LOGO_SMALL_PIC {
		public String getPath() {
			return "BRAND_PIC";
		}

		public Integer getMaxSize() {
			return 2 * 1024 * 1024;
		}

		public Integer getWidth() {
			return 110;
		}

		public Integer getHeight() {
			return 50;
		}
	},

	/** 品牌手机图 */
	BRAND_LOGO_MOBILE_PIC {
		public String getPath() {
			return "BRAND_PIC";
		}

		public Integer getMaxSize() {
			return 2 * 1024 * 1024;
		}

		public Integer getWidth() {
			return 140;
		}

		public Integer getHeight() {
			return 120;
		}
	},

	/** 品牌中图 */
	BRAND_LOGO_MIDDLE_PIC {
		public String getPath() {
			return "BRAND_PIC";
		}

		public Integer getMaxSize() {
			return 2 * 1024 * 1024;
		}

		public Integer getWidth() {
			return 85;
		}

		public Integer getHeight() {
			return 40;
		}
	},

	/** 分销商身份证图片 */
	SELLER_IDENTITY {
		public String getPath() {
			return "SELLER_IDENTITY_IMG";
		}

		public Integer getMaxSize() {
			return 10 * 1024 * 1024;
		}
	},
	/** 分销商委托书图片 */
	SELLER_AUTHORIZE {
		public String getPath() {
			return "SELLER_AUTHORIZE_IMG";
		}

		public Integer getMaxSize() {
			return 10 * 1024 * 1024;
		}

		public Integer getWidth() {
			return 1000;
		}

		public Integer getHeight() {
			return 1300;
		}
	},
	/** APP APK **/
	APP_APK {
		public String getPath() {
			return "APP_APK";
		}

		public Integer getMaxSize() {
			return 100 * 1024 * 1024;
		}
	},
	/** 商品皮质 **/
	CORTEX_PIC {
		public String getPath() {
			return "CORTEX_PIC";
		}

		public Integer getMaxSize() {
			return 1024 * 800;
		}

		public Integer getWidth() {
			return 640;
		}

		public Integer getHeight() {
			return 1200;
		}
	};

	public abstract String getPath();

	public abstract Integer getMaxSize();

	public Integer getWidth() {
		return 0;
	}

	public Integer getHeight() {
		return 0;
	}
}
