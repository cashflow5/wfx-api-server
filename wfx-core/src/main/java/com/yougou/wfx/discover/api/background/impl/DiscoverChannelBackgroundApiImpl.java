 /*
 * 版本信息
 
 * 日期 2016-06-02 13:51:45
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.discover.api.background.IDiscoverChannelBackgroundApi;
import com.yougou.wfx.discover.dto.input.DiscoverChannelInputDto;
import com.yougou.wfx.discover.dto.input.DiscoverChannelPageInputDto;
import com.yougou.wfx.discover.dto.output.DiscoverChannelOutputDto;
import com.yougou.wfx.discover.model.DiscoverChannelEntity;
import com.yougou.wfx.discover.service.IDiscoverChannelService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * IDiscoverChannelBackgroundApi实现
 * @author zhang.wj
 * @Date 创建时间：2016-06-02 13:51:45
 */
@Service
public class DiscoverChannelBackgroundApiImpl implements IDiscoverChannelBackgroundApi{
	
	@Resource
	IDiscoverChannelService discoverChannelService;
	
	@Override
	public int removeById(String id) {
		return discoverChannelService.remove(id);
	}

	@Override
	public String insert(DiscoverChannelInputDto discoverChannelDto) {
		return discoverChannelService.insert(this.dtoToEntity(discoverChannelDto));
	}

	@Override
	public PageModel<DiscoverChannelOutputDto> findPage(DiscoverChannelPageInputDto pageInputDto,PageModel<DiscoverChannelOutputDto> pageModel) {
		DiscoverChannelEntity discoverChannelEntity = (DiscoverChannelEntity) BeanUtil.convertBean(pageInputDto,DiscoverChannelEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = discoverChannelService.findPageCount(discoverChannelEntity);
		List<DiscoverChannelEntity> lstDiscoverChannels = discoverChannelService.findPage(discoverChannelEntity, rowBounds);

		return new PageModel<DiscoverChannelOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<DiscoverChannelOutputDto>) BeanUtil.convertBeanList(lstDiscoverChannels,DiscoverChannelOutputDto.class));
	}

	@Override
	public int update(DiscoverChannelInputDto discoverChannelDto) {
		return discoverChannelService.update(this.dtoToEntity(discoverChannelDto));
	}

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
	public int getorderMark(String id) {
		return discoverChannelService.getorderMark(id);
	}

	@Override
	public List<DiscoverChannelOutputDto> getChannelList(DiscoverChannelInputDto InputDto) {
		DiscoverChannelEntity discoverChannelEntity = (DiscoverChannelEntity) BeanUtil.convertBean(InputDto,DiscoverChannelEntity.class);
		List<DiscoverChannelEntity> lstDiscoverChannels = discoverChannelService.getChannelList(discoverChannelEntity);
		return (List<DiscoverChannelOutputDto>) BeanUtil.convertBeanList(lstDiscoverChannels,DiscoverChannelOutputDto.class);
	}

	@Override
	public int updateOrderMark() {
		return discoverChannelService.updateOrderMark();
	}

	@Override
	public DiscoverChannelOutputDto getPrevious(
			DiscoverChannelInputDto InputDto) {
		DiscoverChannelEntity discoverChannelEntity = (DiscoverChannelEntity) BeanUtil.convertBean(InputDto,DiscoverChannelEntity.class);
		DiscoverChannelEntity outputEntity = discoverChannelService.getPrevious(discoverChannelEntity);
		return this.entityToDto(outputEntity);
	}

	@Override
	public DiscoverChannelOutputDto getNext(DiscoverChannelInputDto InputDto) {
		DiscoverChannelEntity discoverChannelEntity = (DiscoverChannelEntity) BeanUtil.convertBean(InputDto,DiscoverChannelEntity.class);
		
		DiscoverChannelEntity outputEntity = discoverChannelService.getNext(discoverChannelEntity);
		return this.entityToDto(outputEntity);
	}

	@Override
	public int batchRemove(List<String> list) {
		
		return discoverChannelService.batchRemove(list);
	}

	@Override
	public int findPageCount(DiscoverChannelInputDto discoverChannelInputDto) {
		DiscoverChannelEntity discoverChannelEntity = (DiscoverChannelEntity) BeanUtil.convertBean(discoverChannelInputDto,DiscoverChannelEntity.class);
		return discoverChannelService.findPageCount(discoverChannelEntity);
	}
}
