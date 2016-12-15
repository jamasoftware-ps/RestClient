package com.jamasoftware.services.restclient.jamaclient;

import com.jamasoftware.services.restclient.httpconnection.FileResponse;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.httpconnection.Response;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.httpconnection.HttpClient;
import com.jamasoftware.services.restclient.jamadomain.core.LazyResource;
import com.jamasoftware.services.restclient.json.JsonHandler;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class JamaClient {
    private HttpClient httpClient;
    private JsonHandler json;
    private String username;
    private String password;
    private String baseUrl;

    public JamaClient(HttpClient httpClient, JsonHandler json, String baseUrl, String username, String password) {
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

//    public JamaPage getPage(String url, JamaInstance jamaInstance) throws RestClientException {
//        return getPage(url, jamaInstance);
//    }

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

    public void putRaw(String url, String payload) throws RestClientException {
        httpClient.put(url, username, password, payload);
    }

    public void put(String resource, LazyResource payload) throws RestClientException {
//        System.out.println(json.serialize(payload));
        putRaw(baseUrl + resource, json.serializeEdited(payload));
    }
    public Response postRaw(String url, String payload) throws RestClientException {
        return httpClient.post(url, username, password, payload);
//        System.out.println(response.getResponse());
    }

    public Integer post(String resource, LazyResource payload) throws RestClientException {
        Response response = postRaw(baseUrl + resource, json.serializeCreated(payload));
        return json.deserializeLocation(response.getResponse());
    }

    public byte[] getItemTypeImage(String url) throws RestClientException{
        String domain = url.substring(0, url.indexOf("/img/"));
        if(!baseUrl.contains(domain)){
            throw new RestClientException("Not a valid Item Type image URL: \"" + url + "\"");
        }
        FileResponse response = httpClient.getFile(url, username, password);
        return response.getFileData();
    }
}
