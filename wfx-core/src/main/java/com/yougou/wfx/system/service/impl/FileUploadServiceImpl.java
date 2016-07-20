package com.yougou.wfx.system.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.cms.model.CarouselFigureEntity;
import com.yougou.wfx.cms.model.CommoditySaleCatEntity;
import com.yougou.wfx.cms.service.ICarouselFigureService;
import com.yougou.wfx.cms.service.ICommoditySaleCatService;
import com.yougou.wfx.commodity.enums.BrandImgTypeEnum;
import com.yougou.wfx.commodity.model.BagEntity;
import com.yougou.wfx.commodity.model.CommodityBrandEntity;
import com.yougou.wfx.commodity.service.IBagService;
import com.yougou.wfx.commodity.service.ICommodityBrandService;
import com.yougou.wfx.common.enums.FtpConf;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.framework.ftp.service.FtpService;
import com.yougou.wfx.member.model.MemberProfileEntity;
import com.yougou.wfx.member.service.IMemberProfileService;
import com.yougou.wfx.seller.model.SellerAuthorizeEntity;
import com.yougou.wfx.seller.service.ISellerAuthorizeService;
import com.yougou.wfx.shop.model.ShopEntity;
import com.yougou.wfx.shop.service.IShopService;
import com.yougou.wfx.system.service.FileUploadService;
import com.yougou.wfx.system.service.WFXSystemService;
import com.yougou.wfx.utils.FTPUtil;
import com.yougou.wfx.utils.PicPathUtil;

@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {
	private Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);
	@Resource
	private FtpService ftpService;
	@Resource
	private CachingSessionFactory cachingSessionFactory;
	@Resource
	private ICommoditySaleCatService commoditySaleCatService;
	@Resource
	private IBagService bagService;
	@Resource
	private ICommodityBrandService commodityBrandService;
	@Resource
	private IShopService shopService;
	@Resource
	private IMemberProfileService memberProfileService;
	@Resource
	private ICarouselFigureService carouselFigureService;
	@Resource
	private ISellerAuthorizeService sellerAuthorizeService;
	@Resource 
	private WFXSystemService wfxSystemService;

	@Override
	public WFXResult<String> uploadBagImg(InputStream in, String bagId, String fileName, int fileType) {
		WFXResult<String> result = new WFXResult<String>();
		FtpConf ftpConf = null;
		if(fileType == 1) {//分销包小图
			ftpConf = FtpConf.BAG_SMALL_PIC;
		} else if(fileType == 2) {
			ftpConf = FtpConf.BAG_BIG_PIC;
		} else {
			result.setResultCode(1);
			result.setResultMsg("不合法的图片类型");
			return result;
		}
		
		result = ftpService.uploadFile(in, ftpConf, fileName);
		
		if(StringUtils.isNoneBlank(bagId)) {//更新数据库信息
			//TODO 如果传过来的bagId不为空的话，那么上传完了之后还要跟数据库表进行绑定
		}
		return result;
	}
	


	@Override
	public WFXResult<String> uploadImg(InputStream in, String fileName, FtpConf ftpConf) {
		WFXResult<String> result = new WFXResult<String>();
		if(ftpConf == null) { 
			result.setResultCode(1);
			result.setResultMsg("ftpConf 为空，请传入ftpConf");
			return result;
		}
		
		result = ftpService.uploadFile(in, ftpConf, fileName);
		// 返回图片服务器绝对路径  取消 还是返回相对路径
		if (0 == result.getResultCode() && StringUtils.isNotBlank(result.getResult())){
			result.setResult(PicPathUtil.getImageAbsUrl(result.getResult()));
		}
		return result;
	}

	@Override
	public WFXResult<String> uploadSaleCatImg(InputStream in, String saleCatId,String fileName, String userName,FtpConf ftpConf) {
		WFXResult<String> result = new WFXResult<String>();
		result = ftpService.uploadFile(in, ftpConf, fileName);
		//当上传的结果为上传成功时，将图片路径更新至数据库
		if(result.getResultCode() == 0 && StringUtils.isNoneBlank(saleCatId)) {
			CommoditySaleCatEntity entity = new CommoditySaleCatEntity();
			entity.setId(saleCatId);
			entity.setPicUrl(result.getResult());
			entity.setUpdateTime(new Date());
			entity.setUpdateUser(userName);
			commoditySaleCatService.update(entity);
		}
		return result;
	}
	
	@Override
	public WFXResult<String> uploadArticle(InputStream in,String fileName, int fileType) {
		WFXResult<String> result = new WFXResult<String>();
		FtpConf ftpConf = null;
		if(1==fileType){//文章封面
			ftpConf = FtpConf.ARTICLE_COVER_PIC;
		}else if(2==fileType){//文章编辑框图片
			ftpConf = FtpConf.ARTICLE_EDIT_PIC;
		}else{
			result.setResultCode(1);
			result.setResultMsg("不合法的图片类型");
			return result;
		}
		result = ftpService.uploadFile(in, ftpConf, fileName);
		return result;
	}

	@Override
	public WFXResult<String> uploadCarImg(InputStream in,String fileName,FtpConf ftpConf) {
		WFXResult<String> result = new WFXResult<String>();
		if(ftpConf == null) { 
			result.setResultCode(1);
			result.setResultMsg("ftpConf 为空，请传入ftpConf");
			return result;
		}
		
		result = ftpService.uploadFile(in, ftpConf, fileName);
		return result;
	}
	
	@Override
	public WFXResult<String> uploadBrandImg(InputStream in, String fileName, int fileType) {
		WFXResult<String> result = new WFXResult<String>();
		FtpConf ftpConf = null;
		if(fileType == BrandImgTypeEnum.LOGO_LEAST_URL.getType()) {//品牌微图
			ftpConf = FtpConf.BRAND_LOGO_LEAST_PIC;
		} else if(fileType == BrandImgTypeEnum.LOGO_MIDDLE_URL.getType()) { //品牌中图
			ftpConf = FtpConf.BRAND_LOGO_MIDDLE_PIC;
		} else if(fileType == BrandImgTypeEnum.LOGO_SMALL_URL.getType()) { //品牌小图
			ftpConf = FtpConf.BRAND_LOGO_SMALL_PIC;
		}else if(fileType == BrandImgTypeEnum.MOBILE_PIC.getType()) { //品牌手机图
			ftpConf = FtpConf.BRAND_LOGO_MOBILE_PIC;
		}else if(fileType == BrandImgTypeEnum.LOGO_NAME_URL.getType()) { //品牌大图
			ftpConf = FtpConf.BRAND_LOGO_NAME_PIC;
		}
		
		result = ftpService.uploadFile(in, ftpConf, fileName);
		return result;
	}

	@Override
	public WFXResult<String> clearupInvalidBagImg() {
		// TODO Auto-generated method stub
		WFXResult<String> result = new WFXResult<String>();
		int c_success = 0;
		int c_error = 0;
		
		List<String> list = new ArrayList<String>();//储存无效的ftp的资源列表
		List<String> list1 = new ArrayList<String>();//储存ftp的资源列表
		List<String> list2 = new ArrayList<String>();//储存数据库的资源列表
		Session session = null;
		try {
			session = cachingSessionFactory.getSession();
			List<FTPFile> list3 = FTPUtil.getResourcesByPath(session, FtpConf.BAG_SMALL_PIC.getPath());
			List<FTPFile> list4 = FTPUtil.getResourcesByPath(session, FtpConf.BAG_BIG_PIC.getPath());
			//列出分销包小图的ftp文件
			if(list3!=null){
				for(FTPFile f : list3){
					if(f.isFile()){
						list1.add(FtpConf.BAG_SMALL_PIC.getPath() + "/" + f.getName());
					}
				}
			}
			//列出分销包大图的ftp文件
			if(list4!=null){
				for(FTPFile f : list4){
					if(f.isFile()){
						list1.add(FtpConf.BAG_BIG_PIC.getPath() + "/" + f.getName());
					}
				}
			}
			//列出数据库中分销包小图和大图
			BagEntity bagEntity = new BagEntity();
			long total = bagService.findPageCount(bagEntity);
			int limit = 500;
			long times = (long) Math.ceil(total*1.0/limit);
			for(int i=0; i<times; i++){
				RowBounds rowBounds = new RowBounds(i, limit);
				List<BagEntity> bags = bagService.findPage(bagEntity, rowBounds);
				if(bags!=null){
					for(BagEntity bag : bags){
						list2.add(bag.getBagBigPic());
						list2.add(bag.getBagSmallPic());
					}
				}
			}
			//判断ftp文件是否有效
			for(String s1 : list1){
				boolean flag = true;
				for(String s2 : list2){
					if(s1.equals(s2)){
						flag = false;
						break;
					}
				}
				if(flag){
					list.add(s1);
				}
			}
			//清理无效的ftp文件
			if(list.size()>0){
				for(String s : list){
					String ftpServerPath = s.split("/")[0];
					String fileName = s.split("/")[1];
					WFXResult<String> r = ftpService.deleteFile(fileName, ftpServerPath);
					if(r.getResultCode() == ResultCodeEnum.SUCCESS.getKey()){
						c_success ++ ;
						logger.info("清理ftp文件【"+s+"】成功！");
					}else{
						c_error ++;
						logger.info("清理ftp文件【"+s+"】失败！");
					}
				}
			}
			result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			result.setResult("success");
			result.setResultMsg("本次清理无效分销包ftp文件共"+list.size()+"个,其中成功"+c_success+"个,失败"+c_error+"个。");
			logger.info("本次清理无效分销包ftp文件共"+list.size()+"个,其中成功"+c_success+"个,失败"+c_error+"个。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResult("error");
			result.setResultMsg("清理无效分销包ftp文件程序发生错误!");
			logger.error("清理无效分销包ftp文件程序发生错误!", e);
		} finally{
			if(session!=null){
				session.close();
			}
		}
		return result;
	}


	@Override
	public WFXResult<String> clearupInvalidBrandImg() {
		// TODO Auto-generated method stub
		WFXResult<String> result = new WFXResult<String>();
		int c_success = 0;
		int c_error = 0;
		
		List<String> list = new ArrayList<String>();//储存无效的ftp的资源列表
		List<String> list1 = new ArrayList<String>();//储存ftp的资源列表
		List<String> list2 = new ArrayList<String>();//储存数据库的资源列表
		Session session = null;
		try {
			session = cachingSessionFactory.getSession();
			List<FTPFile> list3 = FTPUtil.getResourcesByPath(session, FtpConf.BRAND_LOGO_LEAST_PIC.getPath());
			//列出品牌微图、品牌中图、品牌手机图、品牌大图、品牌小图的ftp文件
			if(list3!=null){
				for(FTPFile f : list3){
					if(f.isFile()){
						list1.add(FtpConf.BRAND_LOGO_LEAST_PIC.getPath() + "/" + f.getName());
					}
				}
			}
			
			//列出数据库中品牌图
			CommodityBrandEntity entity = new CommodityBrandEntity();
			long total = commodityBrandService.findPageCount(entity);
			int limit = 500;
			long times = (long) Math.ceil(total*1.0/limit);
			for(int i=0; i<times; i++){
				RowBounds rowBounds = new RowBounds(i, limit);
				List<CommodityBrandEntity> entitys = commodityBrandService.findPage(entity, rowBounds);
				if(entitys!=null){
					for(CommodityBrandEntity item : entitys){
						list2.add(item.getLogoLeastUrl());
						list2.add(item.getLogoMiddleUrl());
						list2.add(item.getMobilePic());
						list2.add(item.getLogoNameUrl());
						list2.add(item.getLogoSmallUrl());
					}
				}
			}
			//判断ftp文件是否有效
			for(String s1 : list1){
				boolean flag = true;
				for(String s2 : list2){
					if(s1.equals(s2)){
						flag = false;
						break;
					}
				}
				if(flag){
					list.add(s1);
				}
			}
			//清理无效的ftp文件
			if(list.size()>0){
				for(String s : list){
					String ftpServerPath = s.split("/")[0];
					String fileName = s.split("/")[1];
					WFXResult<String> r = ftpService.deleteFile(fileName, ftpServerPath);
					if(r.getResultCode() == ResultCodeEnum.SUCCESS.getKey()){
						c_success ++ ;
						logger.info("清理ftp文件【"+s+"】成功！");
					}else{
						c_error ++;
						logger.info("清理ftp文件【"+s+"】失败！");
					}
				}
			}
			result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			result.setResult("success");
			result.setResultMsg("本次清理无效品牌ftp文件共"+list.size()+"个,其中成功"+c_success+"个,失败"+c_error+"个。");
			logger.info("本次清理无效品牌ftp文件共"+list.size()+"个,其中成功"+c_success+"个,失败"+c_error+"个。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResult("error");
			result.setResultMsg("清理无效品牌ftp文件程序发生错误!");
			logger.error("清理无效品牌ftp文件程序发生错误!", e);
		} finally{
			if(session!=null){
				session.close();
			}
		}
		return result;
	}


	@Override
	public WFXResult<String> clearupInvalidSaleCatImg() {
		// TODO Auto-generated method stub
		WFXResult<String> result = new WFXResult<String>();
		int c_success = 0;
		int c_error = 0;
		
		List<String> list = new ArrayList<String>();//储存无效的ftp的资源列表
		List<String> list1 = new ArrayList<String>();//储存ftp的资源列表
		List<String> list2 = new ArrayList<String>();//储存数据库的资源列表
		Session session = null;
		try {
			session = cachingSessionFactory.getSession();
			List<FTPFile> list3 = FTPUtil.getResourcesByPath(session, FtpConf.SALECAT_PIC.getPath());
			//列出销售分类图的ftp文件
			if(list3!=null){
				for(FTPFile f : list3){
					if(f.isFile()){
						list1.add(FtpConf.SALECAT_PIC.getPath() + "/" + f.getName());
					}
				}
			}
			//列出数据库中销售分类图
			CommoditySaleCatEntity entity = new CommoditySaleCatEntity();
			long total = commoditySaleCatService.findPageCount(entity);
			int limit = 500;
			long times = (long) Math.ceil(total*1.0/limit);
			for(int i=0; i<times; i++){
				RowBounds rowBounds = new RowBounds(i, limit);
				List<CommoditySaleCatEntity> entitys = commoditySaleCatService.findPage(entity, rowBounds);
				if(entitys!=null){
					for(CommoditySaleCatEntity item : entitys){
						list2.add(item.getPicUrl());
					}
				}
			}
			//判断ftp文件是否有效
			for(String s1 : list1){
				boolean flag = true;
				for(String s2 : list2){
					if(s1.equals(s2)){
						flag = false;
						break;
					}
				}
				if(flag){
					list.add(s1);
				}
			}
			//清理无效的ftp文件
			if(list.size()>0){
				for(String s : list){
					String ftpServerPath = s.split("/")[0];
					String fileName = s.split("/")[1];
					WFXResult<String> r = ftpService.deleteFile(fileName, ftpServerPath);
					if(r.getResultCode() == ResultCodeEnum.SUCCESS.getKey()){
						c_success ++ ;
						logger.info("清理ftp文件【"+s+"】成功！");
					}else{
						c_error ++;
						logger.info("清理ftp文件【"+s+"】失败！");
					}
				}
			}
			result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			result.setResult("success");
			result.setResultMsg("本次清理无效销售分类图ftp文件共"+list.size()+"个,其中成功"+c_success+"个,失败"+c_error+"个。");
			logger.info("本次清理无效销售分类图ftp文件共"+list.size()+"个,其中成功"+c_success+"个,失败"+c_error+"个。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResult("error");
			result.setResultMsg("清理无效销售分类图ftp文件程序发生错误!");
			logger.error("清理无效销售分类图ftp文件程序发生错误!", e);
		} finally{
			if(session!=null){
				session.close();
			}
		}
		return result;
	}

	@Override
	public WFXResult<String> clearupInvalidShopImg() {
		// TODO Auto-generated method stub
		WFXResult<String> result = new WFXResult<String>();
		int c_success = 0;
		int c_error = 0;
		
		List<String> list = new ArrayList<String>();//储存无效的ftp的资源列表
		List<String> list1 = new ArrayList<String>();//储存ftp的资源列表
		List<String> list2 = new ArrayList<String>();//储存数据库的资源列表
		Session session = null;
		try {
			session = cachingSessionFactory.getSession();
			List<FTPFile> list3 = FTPUtil.getResourcesByPath(session, FtpConf.SHOP_LOGO.getPath());
			List<FTPFile> list4 = FTPUtil.getResourcesByPath(session, FtpConf.SHOP_SIGN.getPath());
			List<FTPFile> list5 = FTPUtil.getResourcesByPath(session, FtpConf.SHOP_QR_CODE.getPath());
			//列出店铺LOGO图片的ftp文件
			if(list3!=null){
				for(FTPFile f : list3){
					if(f.isFile()){
						if(!f.getName().equals("default_logo.png")){
							list1.add(FtpConf.SHOP_LOGO.getPath() + "/" + f.getName());
						}
					}
				}
			}
			//列出店招图片的ftp文件
			if(list4!=null){
				for(FTPFile f : list4){
					if(f.isFile()){
						if(!f.getName().equals("default_sign.png")){
							list1.add(FtpConf.SHOP_SIGN.getPath() + "/" + f.getName());
						}
					}
				}
			}
			//列出店铺二维码图片的ftp文件
			if(list5!=null){
				for(FTPFile f : list5){
					if(f.isFile()){
						list1.add(FtpConf.SHOP_QR_CODE.getPath() + "/" + f.getName());
					}
				}
			}
			//列出数据库中店铺相关图
			ShopEntity entity = new ShopEntity();
			long total = shopService.findPageCount(entity);
			int limit = 500;
			long times = (long) Math.ceil(total*1.0/limit);
			for(int i=0; i<times; i++){
				RowBounds rowBounds = new RowBounds(i, limit);
				List<ShopEntity> entitys = shopService.findPage(entity, rowBounds);
				if(entitys!=null){
					for(ShopEntity item : entitys){
						list2.add(item.getLogoUrl());
						list2.add(item.getSignUrl());
						list2.add(item.getQrCodeUrl());
					}
				}
			}
			//判断ftp文件是否有效
			for(String s1 : list1){
				boolean flag = true;
				for(String s2 : list2){
					if(s1.equals(s2)){
						flag = false;
						break;
					}
				}
				if(flag){
					list.add(s1);
				}
			}
			//清理无效的ftp文件
			if(list.size()>0){
				for(String s : list){
					String ftpServerPath = s.split("/")[0];
					String fileName = s.split("/")[1];
					WFXResult<String> r = ftpService.deleteFile(fileName, ftpServerPath);
					if(r.getResultCode() == ResultCodeEnum.SUCCESS.getKey()){
						c_success ++ ;
						logger.info("清理ftp文件【"+s+"】成功！");
					}else{
						c_error ++;
						logger.info("清理ftp文件【"+s+"】失败！");
					}
				}
			}
			result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			result.setResult("success");
			result.setResultMsg("本次清理无效店铺相关图ftp文件共"+list.size()+"个,其中成功"+c_success+"个,失败"+c_error+"个。");
			logger.info("本次清理无效店铺相关图ftp文件共"+list.size()+"个,其中成功"+c_success+"个,失败"+c_error+"个。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResult("error");
			result.setResultMsg("清理无效店铺相关图ftp文件程序发生错误!");
			logger.error("清理无效店铺相关图ftp文件程序发生错误!", e);
		} finally{
			if(session!=null){
				session.close();
			}
		}
		return result;
	}

	@Override
	public WFXResult<String> clearupInvalidUserImg() {
		// TODO Auto-generated method stub
		WFXResult<String> result = new WFXResult<String>();
		int c_success = 0;
		int c_error = 0;
		
		List<String> list = new ArrayList<String>();//储存无效的ftp的资源列表
		List<String> list1 = new ArrayList<String>();//储存ftp的资源列表
		List<String> list2 = new ArrayList<String>();//储存数据库的资源列表
		Session session = null;
		try {
			session = cachingSessionFactory.getSession();
			List<FTPFile> list3 = FTPUtil.getResourcesByPath(session, FtpConf.MEMBER_LOGO.getPath());
			//列出用户头像的ftp文件
			if(list3!=null){
				for(FTPFile f : list3){
					if(f.isFile()){
						if(!f.getName().equals("default_logo.png")){
							list1.add(FtpConf.MEMBER_LOGO.getPath() + "/" + f.getName());
						}
					}
				}
			}
			//列出数据库中用户头像图
			MemberProfileEntity entity = new MemberProfileEntity();
			long total = memberProfileService.findPageCount(entity);
			int limit = 500;
			long times = (long) Math.ceil(total*1.0/limit);
			for(int i=0; i<times; i++){
				RowBounds rowBounds = new RowBounds(i, limit);
				List<MemberProfileEntity> entitys = memberProfileService.findPage(entity, rowBounds);
				if(entitys!=null){
					for(MemberProfileEntity item : entitys){
						list2.add(item.getHeadShowImg());
					}
				}
			}
			//判断ftp文件是否有效
			for(String s1 : list1){
				boolean flag = true;
				for(String s2 : list2){
					if(s1.equals(s2)){
						flag = false;
						break;
					}
				}
				if(flag){
					list.add(s1);
				}
			}
			//清理无效的ftp文件
			if(list.size()>0){
				for(String s : list){
					String ftpServerPath = s.split("/")[0];
					String fileName = s.split("/")[1];
					WFXResult<String> r = ftpService.deleteFile(fileName, ftpServerPath);
					if(r.getResultCode() == ResultCodeEnum.SUCCESS.getKey()){
						c_success ++ ;
						logger.info("清理ftp文件【"+s+"】成功！");
					}else{
						c_error ++;
						logger.info("清理ftp文件【"+s+"】失败！");
					}
				}
			}
			result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			result.setResult("success");
			result.setResultMsg("本次清理无效用户相关图片ftp文件共"+list.size()+"个,其中成功"+c_success+"个,失败"+c_error+"个。");
			logger.info("本次清理无效用户相关图片ftp文件共"+list.size()+"个,其中成功"+c_success+"个,失败"+c_error+"个。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResult("error");
			result.setResultMsg("清理无效用户相关图片ftp文件程序发生错误!");
			logger.error("清理无效用户相关图片ftp文件程序发生错误!", e);
		} finally{
			if(session!=null){
				session.close();
			}
		}
		return result;
	}


	@Override
	public WFXResult<String> clearupInvalidCarouselImg() {
		// TODO Auto-generated method stub
		WFXResult<String> result = new WFXResult<String>();
		int c_success = 0;
		int c_error = 0;
		
		List<String> list = new ArrayList<String>();//储存无效的ftp的资源列表
		List<String> list1 = new ArrayList<String>();//储存ftp的资源列表
		List<String> list2 = new ArrayList<String>();//储存数据库的资源列表
		Session session = null;
		try {
			session = cachingSessionFactory.getSession();
			List<FTPFile> list3 = FTPUtil.getResourcesByPath(session, FtpConf.CAROUSEL_PIC.getPath());
			//列出轮播图的ftp文件
			if(list3!=null){
				for(FTPFile f : list3){
					if(f.isFile()){
						list1.add(FtpConf.CAROUSEL_PIC.getPath() + "/" + f.getName());
					}
				}
			}
			//列出数据库中轮播图
			CarouselFigureEntity entity = new CarouselFigureEntity();
			long total = carouselFigureService.findPageCount(entity);
			int limit = 500;
			long times = (long) Math.ceil(total*1.0/limit);
			for(int i=0; i<times; i++){
				RowBounds rowBounds = new RowBounds(i, limit);
				List<CarouselFigureEntity> entitys = carouselFigureService.findPage(entity, rowBounds);
				if(entitys!=null){
					for(CarouselFigureEntity item : entitys){
						list2.add(item.getPicUrl());
					}
				}
			}
			//判断ftp文件是否有效
			for(String s1 : list1){
				boolean flag = true;
				for(String s2 : list2){
					if(s1.equals(s2)){
						flag = false;
						break;
					}
				}
				if(flag){
					list.add(s1);
				}
			}
			//清理无效的ftp文件
			if(list.size()>0){
				for(String s : list){
					String ftpServerPath = s.split("/")[0];
					String fileName = s.split("/")[1];
					WFXResult<String> r = ftpService.deleteFile(fileName, ftpServerPath);
					if(r.getResultCode() == ResultCodeEnum.SUCCESS.getKey()){
						c_success ++ ;
						logger.info("清理ftp文件【"+s+"】成功！");
					}else{
						c_error ++;
						logger.info("清理ftp文件【"+s+"】失败！");
					}
				}
			}
			result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			result.setResult("success");
			result.setResultMsg("本次清理无效轮播图ftp文件共"+list.size()+"个,其中成功"+c_success+"个,失败"+c_error+"个。");
			logger.info("本次清理无效轮播图ftp文件共"+list.size()+"个,其中成功"+c_success+"个,失败"+c_error+"个。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResult("error");
			result.setResultMsg("清理无效轮播图ftp文件程序发生错误!");
			logger.error("清理无效轮播图ftp文件程序发生错误!", e);
		} finally{
			if(session!=null){
				session.close();
			}
		}
		return result;
	}

	@Override
	public WFXResult<String> clearupInvalidSellerImg() {
		// TODO Auto-generated method stub
		WFXResult<String> result = new WFXResult<String>();
		int c_success = 0;
		int c_error = 0;
		
		List<String> list = new ArrayList<String>();//储存无效的ftp的资源列表
		List<String> list1 = new ArrayList<String>();//储存ftp的资源列表
		List<String> list2 = new ArrayList<String>();//储存数据库的资源列表
		Session session = null;
		try {
			session = cachingSessionFactory.getSession();
			List<FTPFile> list3 = FTPUtil.getResourcesByPath(session, FtpConf.SELLER_IDENTITY.getPath());
			List<FTPFile> list4 = FTPUtil.getResourcesByPath(session, FtpConf.SELLER_AUTHORIZE.getPath());
			//列出分销商身份证图片的ftp文件
			if(list3!=null){
				for(FTPFile f : list3){
					if(f.isFile()){
						list1.add(FtpConf.SELLER_IDENTITY.getPath() + "/" + f.getName());
					}
				}
			}
			//列出分销商委托书图片的ftp文件
			if(list4!=null){
				for(FTPFile f : list4){
					if(f.isFile()){
						list1.add(FtpConf.SELLER_AUTHORIZE.getPath() + "/" + f.getName());
					}
				}
			}
			//列出数据库中分销商相关图片
			SellerAuthorizeEntity entity = new SellerAuthorizeEntity();
			long total = sellerAuthorizeService.findPageCount(entity);
			int limit = 500;
			long times = (long) Math.ceil(total*1.0/limit);
			for(int i=0; i<times; i++){
				RowBounds rowBounds = new RowBounds(i, limit);
				List<SellerAuthorizeEntity> entitys = sellerAuthorizeService.findPage(entity, rowBounds);
				if(entitys!=null){
					for(SellerAuthorizeEntity item : entitys){
						list2.add(item.getIdCardPic());
						list2.add(item.getAuthorizePic());
					}
				}
			}
			//判断ftp文件是否有效
			for(String s1 : list1){
				boolean flag = true;
				for(String s2 : list2){
					if(s1.equals(s2)){
						flag = false;
						break;
					}
				}
				if(flag){
					list.add(s1);
				}
			}
			//清理无效的ftp文件
			if(list.size()>0){
				for(String s : list){
					String ftpServerPath = s.split("/")[0];
					String fileName = s.split("/")[1];
					WFXResult<String> r = ftpService.deleteFile(fileName, ftpServerPath);
					if(r.getResultCode() == ResultCodeEnum.SUCCESS.getKey()){
						c_success ++ ;
						logger.info("清理ftp文件【"+s+"】成功！");
					}else{
						c_error ++;
						logger.info("清理ftp文件【"+s+"】失败！");
					}
				}
			}
			result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			result.setResult("success");
			result.setResultMsg("本次清理无效分销商相关图片ftp文件共"+list.size()+"个,其中成功"+c_success+"个,失败"+c_error+"个。");
			logger.info("本次清理无效分销商相关图片ftp文件共"+list.size()+"个,其中成功"+c_success+"个,失败"+c_error+"个。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResult("error");
			result.setResultMsg("清理无效分销商相关图片ftp文件程序发生错误!");
			logger.error("清理无效分销商相关图片ftp文件程序发生错误!", e);
		} finally{
			if(session!=null){
				session.close();
			}
		}
		return result;
	}
	@Override
	public WFXResult<String> appApkUpload(String fileName, InputStream inputStream) {
		return ftpService.uploadFile(inputStream, FtpConf.APP_APK, fileName);
		
	}



	@Override
	public WFXResult<String> uploadCortexImg(String fileName, InputStream inputStream) {
		WFXResult<String> result = new WFXResult<String>();
		FtpConf ftpConf = FtpConf.CORTEX_PIC;
		result = ftpService.uploadFile(inputStream, ftpConf, fileName);
		return result;
	}

	@Override
	public WFXResult<String> uploadQrCodeImg(String originalFilename,
			InputStream inputStream) {
		WFXResult<String> result = new WFXResult<String>();
		FtpConf ftpConf = FtpConf.SHOP_QR_CODE;
		result = ftpService.uploadFile(inputStream, ftpConf, originalFilename);
		return result;
	}
}