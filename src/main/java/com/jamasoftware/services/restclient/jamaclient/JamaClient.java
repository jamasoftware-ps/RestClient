package com.jamasoftware.services.restclient.jamaclient;

import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.JamaInstance;
import com.jamasoftware.services.restclient.httpconnection.Response;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.httpconnection.HttpClient;
import com.jamasoftware.services.restclient.json.JsonSerializerDeserializer;

import java.util.ArrayList;
import java.util.List;

public class JamaClient {
    private HttpClient httpClient;
    private JsonSerializerDeserializer json;
    private String username;
    private String password;
    private String baseUrl;

    public JamaClient(HttpClient httpClient, JsonSerializerDeserializer json, String baseUrl, String username, String password) {
        this.httpClient = httpClient;
        this.json = json;
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }

    public JamaDomainObject getResource(String resource, JamaInstance jamaInstance) throws RestClientException {
        Response response = httpClient.get(baseUrl + resource, username, password);
        return json.deserialize(response.getResponse(), jamaInstance);
    }

    public JamaPage getPage(String url, JamaInstance jamaInstance) throws RestClientException {
        return getPage(url, "", jamaInstance);
    }

    public JamaPage getPage(String url, String startAt, JamaInstance jamaInstance) throws RestClientException {
        Response response = httpClient.get(url + startAt, username, password);
        JamaPage page = json.getPage(response.getResponse(), jamaInstance);
        page.setJamaClient(this);
        page.setUrl(url);
        return page;
    }

    public List<JamaDomainObject> getAll(String url, JamaInstance jamaInstance) throws RestClientException {
        List<JamaDomainObject> results = new ArrayList<>();
        JamaPage page = getPage(url, jamaInstance);
        results.addAll(page.getResults());
        while(page.hasNext()) {
            page = page.getNext(jamaInstance);
            results.addAll(page.getResults());
        }
        return results;
    }

    public void ping() throws RestClientException {
        httpClient.get(baseUrl, username, password);
    }
}
