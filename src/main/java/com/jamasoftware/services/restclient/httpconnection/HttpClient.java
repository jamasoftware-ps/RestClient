package com.jamasoftware.services.restclient.httpconnection;

import com.jamasoftware.services.restclient.exception.RestClientException;

import java.io.File;

public interface HttpClient {
    Response get(String url, String username, String password) throws RestClientException;
    Response delete(String url, String username, String password) throws RestClientException;
    Response post(String url, String username, String password, String payload) throws RestClientException;
    Response put(String url, String username, String password, String payload) throws RestClientException;
    Response putFile(String url, String username, String password, File file) throws RestClientException;
    FileResponse getFile(String url, String username, String password) throws RestClientException;
}
