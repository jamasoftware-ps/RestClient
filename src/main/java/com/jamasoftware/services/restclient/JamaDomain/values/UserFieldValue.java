package com.jamasoftware.services.restclient.JamaDomain.values;

import com.jamasoftware.services.restclient.JamaDomain.lazyresources.User;
import com.jamasoftware.services.restclient.exception.RestClientException;

public class UserFieldValue extends FieldValue {
    private User value;

    @Override
    public User getValue() {
        return value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        if(value == null) {
            this.value = null;
            return;
        }
        int userId = Integer.valueOf(value);
        this.value = new User();
        this.value.associate(userId, getJamaInstance());
    }
}
