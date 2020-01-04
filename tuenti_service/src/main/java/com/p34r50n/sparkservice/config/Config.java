package com.p34r50n.sparkservice.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	public static Properties getConfig() {

		Properties prop = new Properties();
		try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				System.out.println("Sorry, unable to find config.properties");
				return prop;
			}

			//load a properties file from class path, inside static method
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return prop;
	}

}