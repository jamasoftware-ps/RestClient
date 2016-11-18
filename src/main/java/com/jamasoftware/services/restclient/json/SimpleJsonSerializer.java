package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaItem;
import org.json.simple.JSONObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SimpleJsonSerializer implements JsonSerializer {
    public JSONObject serializeItem(JamaItem jamaItem) {
        throw new NotImplementedException();
    }
}
