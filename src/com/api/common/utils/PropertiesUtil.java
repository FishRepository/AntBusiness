package com.api.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties p_path = null;//路径配置信息
	static{
		InputStream inputStream_file = PropertiesUtil.class.getResourceAsStream("/properties/config.properties");
		p_path = new Properties();
		try {
			p_path.load(inputStream_file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static String getKeyValue(String key){
		return p_path.getProperty(key);
	}
}