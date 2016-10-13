package com.jamasoftware.services.restclient.JamaDomain.values;

import com.jamasoftware.services.restclient.exception.RestClientException;

public class IntegerFieldValue extends FieldValue {
    private Integer value;

    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        if(value == null) {
            this.value = null;
            return;
        }
        this.value = Integer.valueOf(value);
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
