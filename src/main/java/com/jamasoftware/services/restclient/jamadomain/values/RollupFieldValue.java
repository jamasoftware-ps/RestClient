package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.RestClientException;

public class RollupFieldValue extends JamaFieldValue{
    private Integer value;

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        if(value == null) {
            this.value = null;
            return;
        }
        this.value = Integer.valueOf(value.substring(0, value.indexOf('%')));
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
