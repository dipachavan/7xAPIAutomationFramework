package org.automation.com.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader
{
    //this class gives the value of the key provided

    public static String readKey(String key)
    {
        //class from a collection framework which extends Hashtable
        Properties properties= new Properties();
        try
        {
            //read from data.properties file and give the value of the key
            FileInputStream fileInputStream= new FileInputStream("src/test/resources/data.properties");
            properties.load(fileInputStream);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return properties.getProperty(key);
    }
}
