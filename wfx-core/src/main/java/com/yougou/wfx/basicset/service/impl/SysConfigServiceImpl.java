 /*
 * 版本信息
 
 * 日期 2016-04-09 13:01:36
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.basicset.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.basicset.dao.SysConfigMapper;
import com.yougou.wfx.basicset.model.SysConfigEntity;
import com.yougou.wfx.basicset.service.ISysConfigService;
import com.yougou.wfx.commodity.dto.input.SysLogInputDto;
import com.yougou.wfx.enums.BusinessTypeEnum;
import com.yougou.wfx.enums.OperateTypeEnum;
import com.yougou.wfx.framework.annotation.cache.CacheCable;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.cache.redis.WFXRedisUtil;
import com.yougou.wfx.framework.cache.redis.WFXRedisUtil.WFXModuleType;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * ISysConfigService实现
 * @author wfx
 * @Date 创建时间：2016-04-09 13:01:37
 */
@Service
public class SysConfigServiceImpl extends BaseService implements ISysConfigService {
	
	@Resource
	private SysConfigMapper sysConfigMapper;

	@Override
	public int findPageCount(SysConfigEntity sysConfigEntity){
		return sysConfigMapper.findPageCount(sysConfigEntity);
	}

	@Override
	public List<SysConfigEntity> findPage(SysConfigEntity sysConfigEntity,RowBounds rowBounds){
		return sysConfigMapper.findPage(sysConfigEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(SysConfigEntity sysConfigEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		sysConfigEntity.setId(strId);
		sysConfigMapper.insert(sysConfigEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(SysConfigEntity sysConfigEntity){
		WFXRedisUtil.delete(WFXModuleType.SYSCONFIG, sysConfigEntity.getConfigKey());
		return  sysConfigMapper.update(sysConfigEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		SysConfigEntity sysConfigEntity = sysConfigMapper.getById(id);
		WFXRedisUtil.delete(WFXModuleType.SYSCONFIG, sysConfigEntity.getConfigKey());
		return sysConfigMapper.remove(id);
	}
	
	@Override
	public SysConfigEntity getById(String id){
		return sysConfigMapper.getById(id);
	}

	@Override
	public void saveSysLog(SysConfigEntity sysConfigEntity, String operationType) {
		SysLogInputDto sysLogDto = new SysLogInputDto();
		//sysLogDto.setModule(Constant);
		sysLogDto.setBusinessType(BusinessTypeEnum.SYSTEM_CONFIG.getKey());
		sysLogDto.setOperateUser(sysConfigEntity.getUpdateUser());
		sysLogDto.setOperateAccount(sysConfigEntity.getUpdateUser());
		sysLogDto.setOperateType(OperateTypeEnum.DELETE.getKey());
		sysLogDto.setOperateDate(new Date());
		//sysLogDto.setOperatorIp(request.getRemoteAddr());
		
	}

	@Override
	@CacheCable(key="'wfx:sysConfig:'+#key" ,value="value",expiration=10*24*60*60)
	public String getValueBykey(String key) {
		if(StringUtils.isBlank(key)){
			return null;
		}
		String value = null;
		SysConfigEntity entity = new SysConfigEntity();
		entity.setConfigKey(key);
		List<SysConfigEntity> list = sysConfigMapper.queryList(entity);
		if(list != null && list.size() > 0){
			value = list.get(0).getConfigValue();
		}
		return value;
	}
}