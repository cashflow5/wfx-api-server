package com.yougou.wfx.test.base;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class AbstractTestMapper {
	private static SqlSessionFactory sessionFactory;
	protected static SqlSession sqlSession;
	static {
		String resource = "mapper-test.xml";
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
			sqlSession = sessionFactory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
