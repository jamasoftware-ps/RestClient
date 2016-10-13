package com.jamasoftware.services.restclient.exception;

public class JsonException extends RestClientException {
    public JsonException(String message) {
        super(message);
    }
    public JsonException(Exception e) {
        super(e);
    }
}
