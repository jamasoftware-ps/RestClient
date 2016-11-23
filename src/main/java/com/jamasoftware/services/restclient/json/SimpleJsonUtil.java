package com.jamasoftware.services.restclient.json;

import com.jamasoftware.services.restclient.exception.JsonException;
import com.jamasoftware.services.restclient.util.DateUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Date;

class SimpleJsonUtil {

    // Type checking and casting
    private int longToInt(long value) throws JsonException {
        if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
            throw new JsonException("Attempt to cast long to int would result in lost information.");
        }
        return (int) value;
    }

    private void validateObjectAndKey(JSONObject jsonObject, String key) throws JsonException {
        if (jsonObject == null) {
            throw new JsonException("JSON Object is null");
        }
        if (key == null || key.length() == 0) {
            throw new JsonException("Empty key");
        }
    }

    private boolean testType(Object object, Class clazz) throws JsonException {
        return object == null || clazz.isInstance(object);
    }

    // Require

    private Object require(JSONObject jsonObject, String key) throws JsonException {
        validateObjectAndKey(jsonObject, key);
        Object object = jsonObject.get(key);
        if (object == null) {
            throw new JsonException("No value for key \"" + key + "\" in object: " + jsonObject.toJSONString());
        }
        return object;
    }

    private Object require(JSONObject jsonObject, String key, Class clazz) throws JsonException {
        Object object = require(jsonObject, key);
        if (testType(object, clazz)) {
            return object;
        }
        throw new JsonException("Expected \"" + key + "\" to be type " + clazz.getName() + " in object: " + jsonObject.toJSONString());
    }

    String requireString(JSONObject jsonObject, String key) throws JsonException {
        return (String) require(jsonObject, key, String.class);
    }

    Integer requireInt(JSONObject jsonObject, String key) throws JsonException {
        return longToInt((Long) require(jsonObject, key, Long.class));
    }

    JSONObject requireObject(JSONObject jsonObject, String key) throws JsonException {
        return (JSONObject) require(jsonObject, key, JSONObject.class);
    }

    JSONArray requireArray(JSONObject jsonObject, String key) throws JsonException {
        return (JSONArray) require(jsonObject, key, JSONArray.class);
    }

    Boolean requireBoolean(JSONObject jsonObject, String key) throws JsonException {
        return (Boolean) require(jsonObject, key, Boolean.class);
    }

    // Request

    private Object request(JSONObject jsonObject, String key) throws JsonException {
        validateObjectAndKey(jsonObject, key);
        return jsonObject.get(key);
    }

    private Object request(JSONObject jsonObject, String key, Class clazz) throws JsonException {
        Object object = request(jsonObject, key);
        if (testType(object, clazz)) {
            return object;
        }
        throw new JsonException("Expected \"" + key + "\" to be type " + clazz.getName() + " in object: " + jsonObject.toJSONString());
    }

    Date requestDate(JSONObject jsonObject, String key) throws JsonException {
        String dateString = requestString(jsonObject, key);
        try {
            return DateUtil.parseDate(dateString);
        } catch (java.text.ParseException e) {
            throw new JsonException(e);
        }
    }

    String requestString(JSONObject jsonObject, String key) throws JsonException {
        return (String) request(jsonObject, key, String.class);
    }

    Integer requestInt(JSONObject jsonObject, String key) throws JsonException {
        Object object = request(jsonObject, key, Long.class);
        if(object == null) {
            return null;
        }
        return longToInt((Long) object);
    }

    JSONObject requestObject(JSONObject jsonObject, String key) throws JsonException {
        return (JSONObject) request(jsonObject, key, JSONObject.class);
    }

    // Misc

    String getFieldValue(JSONObject jsonObject, String key, int itemTypeId) throws JsonException {
        Object fieldObject = request(jsonObject, key);
        Object customFieldObject = request(jsonObject, key + "$" + itemTypeId);
        if (fieldObject == null && customFieldObject == null) {
            return null;
        }
        if (fieldObject != null && customFieldObject != null && !fieldObject.equals(customFieldObject)) {
            throw new JsonException("Two different values exist for field " + key);
        }
        if (fieldObject != null) {
            return fieldObject.toString();
        }
        return customFieldObject.toString();
    }

    JSONObject parseObject(String json, JSONParser jsonParser) throws JsonException {
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
            Object data = jsonObject.get("data");
            if (data instanceof JSONObject) {
                return (JSONObject) data;
            }
            return jsonObject;

        } catch (ParseException e) {
            throw new JsonException(e);
        }
    }

    @SuppressWarnings("unchecked")
    void putIfNotNull(JSONObject object, String key, Object value) {
        if(value != null) {
            object.put(key, value);
        }
    }
}
