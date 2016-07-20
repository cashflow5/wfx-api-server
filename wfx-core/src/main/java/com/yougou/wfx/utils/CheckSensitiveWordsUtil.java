package com.yougou.wfx.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yougou.tools.common.utils.SpringContextHolder;
import com.yougou.wfx.shop.dao.ShopMapper;
import com.yougou.wfx.system.service.WFXSystemService;

/**
 * 敏感词监测工具类
 * @author zhang.f1
 *
 */
public class CheckSensitiveWordsUtil {
	public static String sensitiveWordsFile = "SensitiveWords.txt";
	public static List<String> sensitiveWords = new ArrayList<String>();
	
	private static Logger logger = LoggerFactory.getLogger(CheckSensitiveWordsUtil.class);

	/**
	 * 初始化敏感词类库
	 * @return
	 */
	public static List<String> insertSensitiveWords() {
		
			ClassLoader classLoader = CheckSensitiveWordsUtil.class.getClassLoader(); 
			//String path = classLoader.getResource(sensitiveWordsFile).getFile();
			InputStream in = classLoader.getResourceAsStream(sensitiveWordsFile);			
			try {
				BufferedReader reader =new BufferedReader(new InputStreamReader(in,"GBK")); //new BufferedReader(new FileReader(path));
				while(reader.ready()) {
					String str = reader.readLine();
					//System.out.println("---"+str);
					if(StringUtils.isNotBlank(str))
						sensitiveWords.add(str);
				}
				ShopMapper shopMapper = SpringContextHolder.getBean(ShopMapper.class);
				shopMapper.insertSensitiveWord(sensitiveWords);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		return sensitiveWords;
	}
	
	/**
	 * 返回包含的敏感词，用separator分隔
	 * @param separator 分隔符，为空默认','分隔
	 * @param content 校验内容
	 * @return
	 */
	public static String checkSensitiveWord(String separator,  String content){
		String result = null;
		try{
			WFXSystemService wfxSystemService = SpringContextHolder.getBean(WFXSystemService.class);
			List<String> allSensitiveWords = wfxSystemService.getAllSensitiveWord();
			StringBuffer sbf = new StringBuffer();
			if(StringUtils.isBlank(separator)){
				separator = ",";
			}
			for(String word : allSensitiveWords){
				if(content.contains(word)){
					sbf.append(word).append(separator);
				}
			}
			
			if(sbf.length()>0){
				result = sbf.substring(0,sbf.length()-1).toString();
				return result;
			}
		}catch(Exception e){
			logger.error("校验敏感词异常：", e);
		}
		return result;
	}
	
	public static void main(String[] args) {
		//System.out.println(result);
		sensitiveWords.size();

	}
}
