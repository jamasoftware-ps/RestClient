package com.jamasoftware.services.restclient.httpconnection;

import com.jamasoftware.services.restclient.exception.RestClientException;

import java.io.File;

public interface HttpClient {
    Response get(String url, String username, String password, String apiKey) throws RestClientException;
    Response delete(String url, String username, String password, String apiKey) throws RestClientException;
    Response post(String url, String username, String password, String apiKey, String payload) throws RestClientException;
    Response put(String url, String username, String password, String apiKey, String payload) throws RestClientException;
    Response putFile(String url, String username, String password, String apiKey, File file) throws RestClientException;
    FileResponse getFile(String url, String username, String password, String apiKey) throws RestClientException;
}
