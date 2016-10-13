package com.jamasoftware.services.restclient.jamaclient;

import com.jamasoftware.services.restclient.JamaDomain.JamaDomainObject;
import com.jamasoftware.services.restclient.JamaDomain.JamaInstance;
import com.jamasoftware.services.restclient.httpconnection.Response;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.httpconnection.HttpClient;
import com.jamasoftware.services.restclient.json.Json;

import java.util.ArrayList;
import java.util.List;

public class JamaClient {
    private HttpClient httpClient;
    private Json json;
    private String username;
    private String password;
    private String baseUrl;

    public JamaClient(HttpClient httpClient, Json json, String baseUrl, String username, String password) {
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

    public Page getPage(String url, JamaInstance jamaInstance) throws RestClientException {
        return getPage(url, "", jamaInstance);
    }

    public Page getPage(String url, String startAt, JamaInstance jamaInstance) throws RestClientException {
        Response response = httpClient.get(url + startAt, username, password);
        Page page = json.getPage(response.getResponse(), jamaInstance);
        page.setJamaClient(this);
        page.setUrl(url);
        return page;
    }

    public List<JamaDomainObject> getAll(String url, JamaInstance jamaInstance) throws RestClientException {
        List<JamaDomainObject> results = new ArrayList<>();
        Page page = getPage(url, jamaInstance);
        results.addAll(page.getResults());
        while(page.hasNext()) {
            page = page.getNext(jamaInstance);
            results.addAll(page.getResults());
        }
        return results;
    }
}
