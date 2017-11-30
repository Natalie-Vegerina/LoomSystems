package com.loomsystems.integrations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private String serviceNowUrl;// = "https://dev40499.service-now.com";
    private String serviceNowUser;// = "admin";
    private String serviceNowPassword;// = "Developer2017";
    private String hostAddress;// = "Developer2017";
    private String businessRulePattern;

    private static Config instance;

    private Config() {
        fill();
    }

    public static Config getInstance() {
        if (instance == null) {
            synchronized (Config.class) {
                if (instance == null) {
                    instance = new Config();
                }
            }
        }

        return instance;
    }

    public String getServiceNowUrl() {
        return serviceNowUrl;
    }

    public String getServiceNowUser() {
        return serviceNowUser;
    }

    public String getServiceNowPassword() {
        return serviceNowPassword;
    }

    private void fill() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("app.properties"));
            businessRulePattern = props.getProperty("business_rule_pattern");
            serviceNowUrl = props.getProperty("serviceNowUrl");
            serviceNowUser = props.getProperty("serviceNowUser");
            serviceNowPassword = props.getProperty("serviceNowPassword");
            hostAddress = props.getProperty("hostAddress");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public String getBusinessRulePattern() {
        return businessRulePattern;
    }
}
