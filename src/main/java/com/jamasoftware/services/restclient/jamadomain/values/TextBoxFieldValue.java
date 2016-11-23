package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
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
    public void setValue(Object value) throws JamaTypeMismatchException {
        checkType(String.class, value);
        this.value = (String)value;
    }
}
