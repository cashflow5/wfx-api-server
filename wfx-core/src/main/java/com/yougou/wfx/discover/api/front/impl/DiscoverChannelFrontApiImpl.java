 /*
 * 版本信息
 
 * 日期 2016-06-02 13:51:45
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yougou.wfx.discover.api.front.IDiscoverChannelFrontApi;
import com.yougou.wfx.discover.dto.output.DiscoverChannelOutputDto;
import com.yougou.wfx.discover.model.DiscoverChannelEntity;
import com.yougou.wfx.discover.service.IDiscoverChannelService;
import com.yougou.wfx.framework.bean.BeanUtil;

/**
 * IDiscoverChannelFrontApi实现
 * @author zhang.wj
 * @Date 创建时间：2016-06-02 13:51:45
 */
@Service
public class DiscoverChannelFrontApiImpl implements IDiscoverChannelFrontApi{
	
	@Resource
	IDiscoverChannelService discoverChannelService;
	
	@Override
	public DiscoverChannelOutputDto getById(String id) {
		DiscoverChannelEntity discoverChannelEntity = discoverChannelService.getById(id);
		return this.entityToDto(discoverChannelEntity);
	}
	
	private DiscoverChannelEntity dtoToEntity(Object obj){
		return (DiscoverChannelEntity) BeanUtil.convertBean(obj,DiscoverChannelEntity.class);
	}
	
	private DiscoverChannelOutputDto entityToDto(Object obj){
		return (DiscoverChannelOutputDto) BeanUtil.convertBean(obj,DiscoverChannelOutputDto.class);
	}

	@Override
	public List<DiscoverChannelOutputDto> getAllShowChannels() {
		DiscoverChannelEntity discoverChannelEntity = new DiscoverChannelEntity();
		discoverChannelEntity.setStatus( 1 );//显示
		List<DiscoverChannelEntity>  list = discoverChannelService.queryList(discoverChannelEntity);
		if( list !=null && list.size()>0 ){
			return (List<DiscoverChannelOutputDto> ) BeanUtil.convertBeanList(list,DiscoverChannelOutputDto.class);
		}else{
			return null;
		}
	}
}
