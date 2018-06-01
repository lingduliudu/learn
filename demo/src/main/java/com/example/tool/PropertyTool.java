package com.example.tool;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;  
  
public class PropertyTool {  
    private static Properties properties;
    private static String activeEnv=createActiveEnv();
    private static String loadEnv=createLoadEnv();
      
    public static String createActiveEnv(){
    	//系统中就从系统中获得,否则取自己环境中的
    	if (System.getProperty("spring.profiles.active") != null) {
			return  System.getProperty("spring.profiles.active");
		}
    	Properties appPro = new Properties();  
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        
        try {  
        	appPro.load(loader.getResourceAsStream("application.properties"));  
        	return appPro.getProperty("active.env");
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        return "";
    }
    public static String createLoadEnv(){
    	Properties appPro = new Properties();  
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	try {  
    		appPro.load(loader.getResourceAsStream("application.properties"));  
    		return appPro.getProperty("load.env");
    	} catch (IOException e) {  
    		e.printStackTrace();  
    	}
    	return "config";
    }
    private static void setProperties(){  
        if (properties==null) {  
            properties = new Properties();
            Properties propertiesTemp = null;
            ClassLoader loader = Thread.currentThread().getContextClassLoader();  
            try {
            	for(String loadenv:loadEnv.split(Pattern.quote(","))){
            		propertiesTemp = new Properties();
            		propertiesTemp.load(loader.getResourceAsStream(loadenv+"-"+activeEnv+".properties"));
            		if(propertiesTemp.isEmpty()){
            			continue;
            		}
            		Set set = propertiesTemp.keySet();
            		for(Object key:set){
            			properties.put(key, propertiesTemp.get(key));
            		}
            	}
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
    public static String getProperty(String key){  
        if (properties==null) {  
        	setProperties();  
        }  
        return properties.getProperty(key);  
    }  
}  