package com.qbis.common;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * This class is to load the property file and provide utility method to get the property value.
 * 
 * @author Vivek Kumar.
 *
 */
public class ApplicationProperties {

	/**
	 * Variable to store the properties
	 */
	private static final Properties APP_PROPS;
	/**
	 * Instance of Logger.
	 */
	
	private static final Logger logger = Logger.getLogger(ApplicationProperties.class);
	
	/**
	 * Static code block to load the properties.
	 */
	static{		
		APP_PROPS = new Properties();
		try {
			APP_PROPS.load(ApplicationProperties.class.getClassLoader().getResourceAsStream("database.properties"));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	/**
	 * Method to fetch property value.
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropValue(String key){
		
		return APP_PROPS.getProperty(key);
		
	}
	/**
	 * Method to fetch Integer Value of property.
	 * @param key
	 * @return
	 */
	public static Integer getIntValue(String key){
		
		return Integer.parseInt(APP_PROPS.getProperty(key));
		
	}
	/**
	 * Method to fetch Integer Value of property.
	 * @param key
	 * @return
	 */
	public static Boolean getBooleanValue(String key){
		
		return Boolean.parseBoolean(APP_PROPS.getProperty(key));
		
	}
	/**
	 * Main method to test this class.
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		
		System.out.println(getPropValue("test"));
	}
}
