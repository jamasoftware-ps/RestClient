package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.JamaInstance;
import com.jamasoftware.services.restclient.jamaclient.JamaPage;
import com.jamasoftware.services.restclient.exception.JsonException;
import com.jamasoftware.services.restclient.jamadomain.LazyResource;

public interface JsonHandler {
    JamaPage getPage(String json, JamaInstance jamaInstance) throws JsonException, RestClientException;

    JamaDomainObject deserialize(String json, JamaInstance jamaInstance) throws JsonException, RestClientException;

    String serialize(JamaDomainObject object) throws JsonException;
}


