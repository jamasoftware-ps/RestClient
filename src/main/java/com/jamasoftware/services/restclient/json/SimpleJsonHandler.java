package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.JamaParent;
import com.jamasoftware.services.restclient.jamadomain.*;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.*;
import com.jamasoftware.services.restclient.jamadomain.fields.*;
import com.jamasoftware.services.restclient.jamadomain.values.*;
import com.jamasoftware.services.restclient.jamaclient.JamaPage;
import com.jamasoftware.services.restclient.exception.JsonException;
import com.jamasoftware.services.restclient.exception.RestClientException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SimpleJsonHandler implements JsonHandler {
    private SimpleJsonDeserializer deserializer = new SimpleJsonDeserializer();
    private SimpleJsonSerializer serializer = new SimpleJsonSerializer();

    public JamaDomainObject deserialize(String json, JamaInstance jamaInstance) throws JsonException {
        return deserializer.deserialize(json, jamaInstance);
    }

    @Override
    public JamaPage getPage(String json, JamaInstance jamaInstance) throws JsonException {
        return deserializer.getPage(json, jamaInstance);
    }

    @Override
    public String serialize(JamaDomainObject object) throws JsonException {
        return serializer.serialize(object);
    }
}

