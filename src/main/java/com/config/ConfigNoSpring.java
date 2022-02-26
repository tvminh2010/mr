package com.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.controller.LineController;

public class ConfigNoSpring {
	private static final Logger logger = Logger.getLogger(ConfigNoSpring.class);

	public Config getconfig() {
    Config config = new Config();
	Properties prop = new Properties();
	InputStream input = null;

	try {

		//input = new FileInputStream("D:\\CV\\LCTECH\\projects\\mr\\src\\main\\resources\\config.properties");
		input = new FileInputStream("config.properties");

		// load a properties file
		prop.load(input);

		// get the property value and print it out
		System.out.println(prop.getProperty("jdbc.driverClassName"));
		System.out.println(prop.getProperty("jdbc.host"));
		System.out.println(prop.getProperty("jdbc.db"));
		System.out.println(prop.getProperty("jdbc.post"));
		System.out.println(prop.getProperty("jdbc.username"));
		System.out.println(prop.getProperty("jdbc.password"));
		config.setJdbcdb(prop.getProperty("jdbc.db"));
		config.setJdbcdriverClassName(prop.getProperty("jdbc.driverClassName"));
		config.setJdbcpassword(prop.getProperty("jdbc.password"));
		config.setJdbcpost(prop.getProperty("jdbc.post"));
		 config.setJdbcurl(prop.getProperty("jdbc.host"));
		 config.setJdbcusername(prop.getProperty("jdbc.username"));
		
       
	} catch (IOException ex) {
		logger.debug(ex);

		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				logger.debug(e);

				e.printStackTrace();
			}
		}
	}
	 return config;
	}
}
