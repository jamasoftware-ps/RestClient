package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;
import com.jamasoftware.services.restclient.jamaclient.JamaPage;

public interface JsonHandler {
    JamaPage getPage(String json, JamaInstance jamaInstance) throws RestClientException;

    JamaDomainObject deserialize(String json, JamaInstance jamaInstance) throws RestClientException;

    String serializeCreated(JamaDomainObject jamaDomainObject) throws RestClientException;

    String serializeEdited(JamaDomainObject jamaDomainObject) throws RestClientException;

    Integer deserializeLocation(String response) throws RestClientException;
}


