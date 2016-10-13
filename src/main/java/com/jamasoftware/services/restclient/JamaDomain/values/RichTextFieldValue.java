package com.jamasoftware.services.restclient.JamaDomain.values;

import com.jamasoftware.services.restclient.JamaDomain.RichText;
import com.jamasoftware.services.restclient.exception.RestClientException;

public class RichTextFieldValue extends FieldValue {
    private RichText value;

    @Override
    public RichText getValue() {
        return value;
    }

    public void setValue(RichText value) {
        this.value = value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        this.value = new RichText();
        this.value.setValue(value);

    }
}
