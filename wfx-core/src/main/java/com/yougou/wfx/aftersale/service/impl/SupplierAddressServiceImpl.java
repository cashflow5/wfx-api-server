/*
 * 版本信息

 * 日期 2016-04-26 09:34:42

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.aftersale.service.impl;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.aftersale.dao.SupplierAddressMapper;
import com.yougou.wfx.aftersale.model.SupplierAddressEntity;
import com.yougou.wfx.aftersale.service.ISupplierAddressService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * ISupplierAddressService实现
 * 
 * @author wfx
 * @Date 创建时间：2016-04-26 09:34:43
 */
@Service
public class SupplierAddressServiceImpl extends BaseService implements ISupplierAddressService {

	@Resource
	private SupplierAddressMapper supplierAddressMapper;

	@Override
	public int findPageCount(SupplierAddressEntity supplierAddressEntity) {
		return supplierAddressMapper.findPageCount(supplierAddressEntity);
	}

	@Override
	public List<SupplierAddressEntity> findPage(SupplierAddressEntity supplierAddressEntity, RowBounds rowBounds) {
		return supplierAddressMapper.findPage(supplierAddressEntity, rowBounds);
	}

	@Override
	@Transactional
	public String insert(SupplierAddressEntity supplierAddressEntity) {
		String strId = UUIDGenerator.get32LowCaseUUID();
		String outSideNo = getStringRandom(4);
		SupplierAddressEntity tempEntity = supplierAddressMapper.getByOutSideNo(outSideNo);
		while (tempEntity != null) {
			outSideNo = getStringRandom(4);
			tempEntity = supplierAddressMapper.getByOutSideNo(outSideNo);
		}
		supplierAddressEntity.setOutsideNo(outSideNo);
		supplierAddressEntity.setId(strId);
		supplierAddressMapper.insert(supplierAddressEntity);
		return strId;
	}

	@Override
	@Transactional
	public int update(SupplierAddressEntity supplierAddressEntity) {
		return supplierAddressMapper.update(supplierAddressEntity);
	}

	@Override
	@Transactional
	public int remove(String id) {
		return supplierAddressMapper.remove(id);
	}

	@Override
	public SupplierAddressEntity getById(String id) {
		return supplierAddressMapper.getById(id);
	}

	// 生成随机数字和字母,
	public String getStringRandom(int length) {
		String val = "";
		Random random = new Random();
		// 参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {

			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}
}