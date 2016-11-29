package com.jamasoftware.services.restclient;

import com.jamasoftware.services.restclient.httpconnection.ApacheHttpClient;
import com.jamasoftware.services.restclient.httpconnection.HttpClient;
import com.jamasoftware.services.restclient.json.JsonHandler;
import com.jamasoftware.services.restclient.json.SimpleJsonHandler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class JamaConfig {
    private String baseUrl;
    // TODO add ending forward slash off of domain name
    private String username;
    private String password;
    private JsonHandler json;
    private HttpClient httpClient;
    private Integer resourceTimeOut;    //value is seconds

    public JamaConfig() {
        json = new SimpleJsonHandler();
        try {
            httpClient = new ApacheHttpClient();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public JamaConfig(boolean loadFromPropertiesFile) {
        this();
        if(!loadFromPropertiesFile) {
            return;
        }
        InputStream input = null;
        try {
            Properties properties = new Properties();
            input = new FileInputStream("jama.properties");
            properties.load(input);
            setBaseUrl(properties.getProperty("baseUrl"));
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            String timeOutString = properties.getProperty("resourceTimeOut");
            resourceTimeOut = Integer.valueOf(timeOutString);
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        if(baseUrl.contains("/rest/")) {
            if(baseUrl.endsWith("/")) {
                this.baseUrl = baseUrl;
                return;
            }
            this.baseUrl = baseUrl + "/";
            return;
        }
        if(baseUrl.endsWith("/")) {
            this.baseUrl = baseUrl + "rest/v1/";
            return;
        }
        this.baseUrl = baseUrl + "/rest/v1/";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JsonHandler getJson() {
        return json;
    }

    public void setJson(JsonHandler json) {
        this.json = json;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Integer getResourceTimeOut() {
        return resourceTimeOut;
    }

    public void setResourceTimeOut(Integer resourceTimeOut) {
        this.resourceTimeOut = resourceTimeOut;
    }
}
