package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
import com.jamasoftware.services.restclient.jamadomain.lazyresources.JamaUser;
import com.jamasoftware.services.restclient.exception.RestClientException;

public class UserFieldValue extends JamaFieldValue {
    private JamaUser value;

    @Override
    public JamaUser getValue() {
        return value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        if(value == null) {
            this.value = null;
            return;
        }
        int userId = Integer.valueOf(value);
        setValueFromPoolOrNew(JamaUser.class, userId);
    }


    public void setValue(Object value) throws JamaTypeMismatchException {
        checkType(JamaUser.class, value);
        this.value = (JamaUser) value;
    }
}
