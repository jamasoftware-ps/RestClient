package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
import com.jamasoftware.services.restclient.exception.RestClientException;

public class CalculatedFieldValue extends JamaFieldValue {
    private String value;
    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        this.value = value;
    }
    public void setValue(Object value) throws JamaTypeMismatchException {
        throw new JamaTypeMismatchException("Calculated fields are not editable for field " + getName());
    }

}
