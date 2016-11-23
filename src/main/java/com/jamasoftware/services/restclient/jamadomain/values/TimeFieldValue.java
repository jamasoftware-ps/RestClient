package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
import com.jamasoftware.services.restclient.exception.RestClientException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TimeFieldValue extends JamaFieldValue {
    private String value;

    @Override

    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        // todo: something better
        this.value = value;
    }

    public void setValue(Object value) throws JamaTypeMismatchException {
        checkType(String.class, value);
        this.value = (String)value;
    }

}
