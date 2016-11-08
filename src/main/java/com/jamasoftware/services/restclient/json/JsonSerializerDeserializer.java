package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.jamadomain.JamaDomainObject;
import com.jamasoftware.services.restclient.jamadomain.JamaInstance;
import com.jamasoftware.services.restclient.jamaclient.JamaPage;
import com.jamasoftware.services.restclient.exception.JsonException;

public interface JsonSerializerDeserializer {
    JamaPage getPage(String json, JamaInstance jamaInstance) throws JsonException;

    JamaDomainObject deserialize(String json, JamaInstance jamaInstance) throws JsonException;

    String serialize(JamaDomainObject jamaDomainObject) throws JsonException;
}


