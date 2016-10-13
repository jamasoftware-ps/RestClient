package com.jamasoftware.services.restclient.JamaDomain.values;

import com.jamasoftware.services.restclient.exception.RestClientException;

public class FlagFieldValue extends FieldValue {
    private Boolean value;

    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        if(value == null) {
            this.value = null;
            return;
        }
        this.value = Boolean.valueOf(value);
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
