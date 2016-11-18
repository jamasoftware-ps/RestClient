package com.jamasoftware.services.restclient;

import com.jamasoftware.services.restclient.httpconnection.ApacheHttpClient;
import com.jamasoftware.services.restclient.httpconnection.HttpClient;
import com.jamasoftware.services.restclient.json.JsonDeserializer;
import com.jamasoftware.services.restclient.json.SimpleJsonDeserializer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class JamaConfig {
    private String baseUrl;
    // TODO add ending forward slash off of domain name
    private String username;
    private String password;
    private JsonDeserializer json;
    private HttpClient httpClient;
    private Integer resourceTimeOut;    //value is seconds

    public JamaConfig() {
        json = new SimpleJsonDeserializer();
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
            baseUrl = properties.getProperty("baseUrl");
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
        this.baseUrl = baseUrl;
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

    public JsonDeserializer getJson() {
        return json;
    }

    public void setJson(JsonDeserializer json) {
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
