package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
import com.jamasoftware.services.restclient.exception.RestClientException;

public class TestCaseStatusFieldValue extends JamaFieldValue {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) throws RestClientException {
        this.value = value;
    }
    public void setValue(Object value) throws JamaTypeMismatchException {
        throw new JamaTypeMismatchException("Test Case status is not editable for field " + getName());
    }

}
