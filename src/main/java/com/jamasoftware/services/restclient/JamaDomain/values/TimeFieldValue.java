package com.jamasoftware.services.restclient.JamaDomain.values;

import com.jamasoftware.services.restclient.exception.RestClientException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TimeFieldValue extends FieldValue {
    @Override
    // todo: something better
    public Object getValue() {
        return getValue();
    }

    @Override
    public void setValue(String value) throws RestClientException {
        throw new NotImplementedException();
    }
}
