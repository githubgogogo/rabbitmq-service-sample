package com.jeremy.sample.utils.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;


/**
 * Created by Jeremy Yang on 11/12/2015.
 */
public class HostNameResolver
{

    private static final Logger logger = LoggerFactory.getLogger(HostNameResolver.class);
    /**
     * @return a file system friendly version of the current host name
     * @throws Exception if unable to generate hostname
     */
    public static String getHostSpecificPropertiesFileLocation() throws Exception
    {
        String envVar = getEnvVar();
        String prop = "classpath:/environments/" + envVar + ".properties";
        logger.info("Environment Properties: " + prop);
        System.out.println("Environment Properties: " + prop);
        return prop;
    }

    private static String getEnvVar() throws UnknownHostException
    {
        String envVar = System.getenv("env");
        if(envVar == null) {
            envVar = InetAddress.getLocalHost().getHostName().toLowerCase().split("[.-]")[0];
        }
        return envVar;
    }

    public static String getHostSpecificProperty(String propertyName) throws Exception{
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("environments/"+getEnvVar()+".properties");
        Properties props = new Properties();
        props.load(is);
        return (String) props.get(propertyName);
    }
}

