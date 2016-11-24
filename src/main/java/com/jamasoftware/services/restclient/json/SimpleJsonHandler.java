package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.jamadomain.core.JamaDomainObject;
import com.jamasoftware.services.restclient.jamaclient.JamaPage;
import com.jamasoftware.services.restclient.exception.JsonException;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.jamadomain.core.JamaInstance;

public class SimpleJsonHandler implements JsonHandler {
    private SimpleJsonDeserializer deserializer = new SimpleJsonDeserializer();
    private SimpleJsonSerializer serializer = new SimpleJsonSerializer();

    public JamaDomainObject deserialize(String json, JamaInstance jamaInstance) throws JsonException, RestClientException {
        return deserializer.deserialize(json, jamaInstance);
    }

    @Override
    public JamaPage getPage(String json, JamaInstance jamaInstance) throws JsonException, RestClientException {
        return deserializer.getPage(json, jamaInstance);
    }

    @Override
    public String serializeCreated(JamaDomainObject jamaDomainObject) throws RestClientException {
        return serializer.serializeCreated(jamaDomainObject);
    }

    @Override
    public String serializeEdited(JamaDomainObject jamaDomainObject) throws RestClientException {
        return serializer.serializeEdited(jamaDomainObject);
    }

    @Override
    public Integer deserializeLocation(String response) throws RestClientException{
        return deserializer.deserializeLocation(response);
    }
}

