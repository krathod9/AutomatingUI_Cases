package selenium.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;
    private ConfigLoader(){
        properties=PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }
    public static ConfigLoader getInstance(){
        if(configLoader==null){
            configLoader=new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseURL(){
        String prop=properties.getProperty("baseURL");
        if(prop!=null) return prop;
        else throw  new RuntimeException("Property baseURl is not specified in config.properties");
    }
    public String getUsername(){
        String prop=properties.getProperty("username");
        if(prop!=null) return prop;
        else throw  new RuntimeException("Property username is not specified in config.properties");
    }
    public String getPassword(){
        String prop=properties.getProperty("password");
        if(prop!=null) return prop;
        else throw  new RuntimeException("Property password is not specified in config.properties");
    }
}
