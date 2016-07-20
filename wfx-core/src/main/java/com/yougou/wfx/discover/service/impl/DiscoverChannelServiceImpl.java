 /*
 * 版本信息
 
 * 日期 2016-06-02 13:51:45
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.discover.dao.DiscoverChannelMapper;
import com.yougou.wfx.discover.model.DiscoverChannelEntity;
import com.yougou.wfx.discover.service.IDiscoverChannelService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * IDiscoverChannelService实现
 * @author zhang.wj
 * @Date 创建时间：2016-06-02 13:51:45
 */
@Service
public class DiscoverChannelServiceImpl extends BaseService implements IDiscoverChannelService {
	
	@Resource
	private DiscoverChannelMapper discoverChannelMapper;

	@Override
	public int findPageCount(DiscoverChannelEntity discoverChannelEntity){
		return discoverChannelMapper.findPageCount(discoverChannelEntity);
	}

	@Override
	public List<DiscoverChannelEntity> findPage(DiscoverChannelEntity discoverChannelEntity,RowBounds rowBounds){
		return discoverChannelMapper.findPage(discoverChannelEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(DiscoverChannelEntity discoverChannelEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		discoverChannelEntity.setId(strId);
		discoverChannelMapper.insert(discoverChannelEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(DiscoverChannelEntity discoverChannelEntity){
		return  discoverChannelMapper.update(discoverChannelEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		int removeFlag=0;
		DiscoverChannelEntity discoverChannelEntity=discoverChannelMapper.getById(id);
		removeFlag=discoverChannelMapper.remove(id);
		discoverChannelMapper.updateReduceOrderMark(discoverChannelEntity);
		return removeFlag;
	}
	
	@Override
	public DiscoverChannelEntity getById(String id){
		return discoverChannelMapper.getById(id);
	}

	@Override
	public int getorderMark(String id) {
		
		Integer orderMark=discoverChannelMapper.getorderMark(id);
		if(orderMark==null){
			orderMark=new Integer(0);
		}
		return orderMark; 
	}

	@Override
	public List<DiscoverChannelEntity> queryList(
			DiscoverChannelEntity discoverChannelEntity) {
		return discoverChannelMapper.queryList(discoverChannelEntity);
	}

	@Override
	public List<DiscoverChannelEntity> getChannelList(
			DiscoverChannelEntity discoverChannelEntity) {
		return discoverChannelMapper.getChannelList(discoverChannelEntity);
	}

	@Override
	public int updateOrderMark() {
		
		return discoverChannelMapper.updateOrderMark();
	}

	@Override
	public DiscoverChannelEntity getPrevious(
			DiscoverChannelEntity discoverChannelEntity) {
		return discoverChannelMapper.getPrevious(discoverChannelEntity);
	}

	@Override
	public DiscoverChannelEntity getNext(
			DiscoverChannelEntity discoverChannelEntity) {
		return discoverChannelMapper.getNext(discoverChannelEntity);
	}

	@Override
	public int batchRemove(List<String> list) {
		return discoverChannelMapper.batchRemove(list);
	} 
}