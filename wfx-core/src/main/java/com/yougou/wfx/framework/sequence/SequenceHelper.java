/*
 * 类名 SequenceUtils.java
 *
 * 版本信息 
 *
 * 日期 2013-1-17
 *
 * 版权声明Copyright (C) 2013 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.framework.sequence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yougou.tools.common.utils.SpringContextHolder;
import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;

/**
 * 序列号工具类
 * 
 */
public class SequenceHelper {

	private static final Logger logger = LoggerFactory.getLogger(SequenceHelper.class);

	private SequenceHelper() {
	}

	private static JdbcTemplate getJdbcTemplate() {
		return SpringContextHolder.getBean(JdbcTemplate.class);
	}

	private static String setSeq(final String prefix) throws Exception {
		final String sql = "select func_sequence_nextval(?)";
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		String result = jdbcTemplate.execute(new ConnectionCallback<String>() {
			@Override
			public String doInConnection(Connection con) throws SQLException, DataAccessException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, prefix);
				ResultSet rs = pstmt.executeQuery();
				String result = null;
				if (rs.next()) {
					result = rs.getString(1);
				}
				try {
					rs.close();
					pstmt.close();
				} catch (Exception e) {
					logger.error("关闭jdbcTemplate-stmt,resultset ERROR");
				}
				return result;
			}
		});
		return result;
	}

	/**
	 * 读取交易序列号
	 * 
	 * @param prefix
	 * @return
	 */
	public static String getSeq(final String prefix) throws Exception {
		StringBuffer seqNo = new StringBuffer(20);
		String result = "";
		if (StringUtils.isEmpty(prefix)) {
			throw new Exception("函数参数不能为空!");
		}
		try {
			result = setSeq(prefix);
			seqNo.append(result);
		} catch (Exception e) {
			logger.error("获取序列发生异常!!");
			e.printStackTrace();
		}
		return seqNo.toString();
	}

	/**
	 * 读取交易序列号
	 * 
	 * @param prefix
	 * @param seqLen
	 * @return
	 * @throws Exception
	 */
	public static String getSeq(final String prefix, final int seqLen) throws Exception {
		String result = "";
		StringBuffer seqNo = new StringBuffer(20);
		StringBuffer preZero = new StringBuffer(10);
		if (StringUtils.isEmpty(prefix) || seqLen < 0) {
			throw new Exception("函数参数不正确!prefix=" + prefix + ",seqLen=" + seqLen);
		}
		if (seqLen == 0) {
			result = getSeq(prefix);
			return seqNo.append(result).toString();
		} else if (seqLen > 0) {
			result = setSeq(prefix);
			int relen = result.length();
			int foreachLen = seqLen - relen;
			if (foreachLen > 0) {
				for (int i = 0; i < foreachLen; i++) {
					preZero = preZero.append("0");
				}
				seqNo.append(preZero.append(result));
				return seqNo.toString();
			}
			if (foreachLen < 0) {
				throw new Exception("传入的序列长度" + seqLen + "小于数据库中存在的序列长度" + relen);
			}
		}
		return null;
	}
}
