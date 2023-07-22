package com.piyovi.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    Properties props;
    final String propertyFilePath = System.getProperty("user.dir") + "/properties/config.properties";
    final String dbPropertyFilePath = System.getProperty("user.dir") + "/properties/db.properties";

    public String getApplicationProperty(String key) throws IOException {
    	if(System.getProperty("testEnv").equalsIgnoreCase("UAT")) {
    		key = "baseURI_UAT";
    	}else if(System.getProperty("testEnv").equalsIgnoreCase("DEV")) {
    		key = "baseURI_DEV";
    	}
        String value = System.getProperty(key);
        if(null == value || value.isEmpty()) {
            readPropertyFile(this.propertyFilePath);
            value = this.props.getProperty(key);
        }
        return value;
    }

    public String getDBProperty(String key) throws IOException {
        readPropertyFile(this.dbPropertyFilePath);
        String value = this.props.getProperty(key);
        return value;
    }

    public String getProperty(String key) {
        String value = this.props.getProperty(key);
        return value;
    }

    public void readPropertyFile(String filePath){
        try {
            this.props = new Properties();
            FileInputStream ip = new FileInputStream(filePath);
            this.props.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
