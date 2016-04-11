package com.sonymm.bxwtfk.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class FunctionURLReader {
	protected Logger logger = Logger.getLogger(getClass());
	private static FunctionURLReader ins = new FunctionURLReader();

	private FunctionURLReader() {
	}

	public static FunctionURLReader getIns() {
		return ins;
	}

	public String getProperties(String key) {
		Properties prop =loadProperties();
		
		return prop.getProperty(key);
	}

	public Set<Object> getKeySet(){
		Properties prop = loadProperties();
		
		return prop.keySet();
	}

	/**
	 * 加载功能-URL配置文件
	 * 
	 * @return
	 */
	private Properties loadProperties() {
		Properties prop = new Properties();
		try {
			InputStream is = getClass().getResourceAsStream(
					"/functionurl.properties");
			prop.load(is);
		} catch (FileNotFoundException e) {
			logger.error("functionurl.properties配置文件没有找到");
		} catch (IOException e2) {
			logger.error("读取functionurl.properties文件错误！");
		}
		return prop;
	}
}
