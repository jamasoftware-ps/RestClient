package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.RestClientException;

public class TextBoxFieldValue extends JamaFieldValue {
    private String value;

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) throws RestClientException {
        this.value = value;
    }
}
