 /*
 * 版本信息
 
 * 日期 2016-04-21 16:43:38
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.order.dao.ShoppingcartMapper;
import com.yougou.wfx.order.model.ShoppingcartEntity;
import com.yougou.wfx.order.service.IShoppingcartService;

/**
 * IShoppingcartService实现
 * @author wfx
 * @Date 创建时间：2016-04-21 16:43:39
 */
@Service
public class ShoppingcartServiceImpl extends BaseService implements IShoppingcartService {
	
	@Resource
	private ShoppingcartMapper shoppingcartMapper;

	@Override
	public int findPageCount(ShoppingcartEntity shoppingcartEntity){
		return shoppingcartMapper.findPageCount(shoppingcartEntity);
	}

	@Override
	public List<ShoppingcartEntity> findPage(ShoppingcartEntity shoppingcartEntity,RowBounds rowBounds){
		return shoppingcartMapper.findPage(shoppingcartEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(ShoppingcartEntity shoppingcartEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		shoppingcartEntity.setId(strId);
		shoppingcartMapper.insert(shoppingcartEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(ShoppingcartEntity shoppingcartEntity){
		return  shoppingcartMapper.update(shoppingcartEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return shoppingcartMapper.remove(id);
	}
	
	@Override
	public ShoppingcartEntity getById(String id){
		return shoppingcartMapper.getById(id);
	} 
}