 /*
 * 版本信息
 
 * 日期 2016-04-07 18:29:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.basicset.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.Range;

import org.springframework.stereotype.Service;

import com.yougou.wfx.basicset.api.front.ISystemmgAreaFrontApi;
import com.yougou.wfx.basicset.dto.output.SystemmgAreaOutputDto;
import com.yougou.wfx.basicset.model.SystemmgAreaEntity;
import com.yougou.wfx.basicset.service.ISystemmgAreaService;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;

/**
 * ISystemmgAreaFrontApi实现
 * @author wfx
 * @Date 创建时间：2016-04-07 18:29:33
 */
@Service
public class SystemmgAreaFrontApiImpl implements ISystemmgAreaFrontApi{
	
	@Resource
	ISystemmgAreaService systemmgAreaService;

	@Override
	@LoggerProfile(methodNote="查询地区列表")
	public List<SystemmgAreaOutputDto> queryAreaList(@Range(min=1, max=3)int level, @NotBlank String no) {
		SystemmgAreaEntity systemmgAreaEntity = new SystemmgAreaEntity();
		systemmgAreaEntity.setLevel(String.valueOf(level));
		systemmgAreaEntity.setIsUsable(1);
		if(1 != level){
			systemmgAreaEntity.setNo(no+"-");
		}
		List<SystemmgAreaEntity> lstSystemmgAreas = systemmgAreaService.queryList(systemmgAreaEntity);
		return (List<SystemmgAreaOutputDto>) BeanUtil.convertBeanList(lstSystemmgAreas,SystemmgAreaOutputDto.class);
	}
	
	
}
