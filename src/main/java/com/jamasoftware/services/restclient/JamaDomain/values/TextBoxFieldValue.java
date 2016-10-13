package com.jamasoftware.services.restclient.JamaDomain.values;

import com.jamasoftware.services.restclient.exception.RestClientException;

public class TextBoxFieldValue extends FieldValue {
    private String value;

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) throws RestClientException {
        this.value = value;
    }
}
