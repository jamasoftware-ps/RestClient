package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
import com.jamasoftware.services.restclient.exception.RestClientException;

public class FlagFieldValue extends JamaFieldValue {
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

    public void setValue(Object value) throws JamaTypeMismatchException {
        checkType(Boolean.class, value);
        this.value = (Boolean) value;
    }
    public void setValue(Boolean value) {
        this.value = value;
    }
}
