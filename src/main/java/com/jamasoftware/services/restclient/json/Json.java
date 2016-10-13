package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.JamaDomain.JamaDomainObject;
import com.jamasoftware.services.restclient.JamaDomain.JamaInstance;
import com.jamasoftware.services.restclient.jamaclient.Page;
import com.jamasoftware.services.restclient.exception.JsonException;

public interface Json {
    Page getPage(String json, JamaInstance jamaInstance) throws JsonException;

    JamaDomainObject deserialize(String json, JamaInstance jamaInstance) throws JsonException;

    String serialize(JamaDomainObject jamaDomainObject) throws JsonException;
}


