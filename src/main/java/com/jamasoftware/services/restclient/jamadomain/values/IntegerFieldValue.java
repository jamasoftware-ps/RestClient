package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
import com.jamasoftware.services.restclient.exception.RestClientException;

public class IntegerFieldValue extends JamaFieldValue {
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

    public void setValue(Object value) throws JamaTypeMismatchException {
        checkType(Integer.class, value);
        this.value = (Integer)value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
}
