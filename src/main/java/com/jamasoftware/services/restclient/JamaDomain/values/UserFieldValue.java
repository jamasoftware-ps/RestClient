package com.jamasoftware.services.restclient.jamadomain.values;

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
        this.value = new JamaUser();
        this.value.associate(userId, getJamaInstance());
    }
}
