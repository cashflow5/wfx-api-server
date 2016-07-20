package com.yougou.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * Junit test
 * @author le.sm
 *classpath*:applicationContext-test.xml","classpath*:applicationContext-api-test.xml
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext-test.xml" }) 
public class JUnitServiceBase extends AbstractTransactionalJUnit4SpringContextTests {

		
}
