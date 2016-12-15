package com.jamasoftware.services.restclient.jamaclient;

import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.exception.RestClientException;

import java.util.ArrayList;
import java.util.List;

public class JamaPage {
    private JamaClient jamaClient;
    private String url;
    private int startIndex;
    private int resultCount;
    private int totalResults;
    private int maxResults;

    private List<JamaDomainObject> results = new ArrayList<>();

    public JamaPage(int startIndex, int resultCount, int totalResults, int maxResults) {
        this.startIndex = startIndex;
        this.resultCount = resultCount;
        this.totalResults = totalResults;
        this.maxResults = maxResults;
    }

    public JamaPage(int startIndex, int resultCount, int totalResults) {
        this(startIndex, resultCount, totalResults, 20);
    }

    public boolean hasNext() {
        return startIndex + resultCount <= totalResults;
    }



    public JamaPage getNext(JamaInstance jamaInstance) throws RestClientException {
        int nextPageStart = startIndex + maxResults;
        String delim = !url.contains("?") ? "?" : "&";
        return jamaClient.getPage(url, delim + "startAt=" + nextPageStart, jamaInstance);
    }

    public void addResource(JamaDomainObject jamaDomainObject) {
        if(jamaDomainObject != null) {
            results.add(jamaDomainObject);
        }
    }

    public List<JamaDomainObject> getResults() {
        return results;
    }

    public void setJamaClient(JamaClient jamaClient) {
        this.jamaClient = jamaClient;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
