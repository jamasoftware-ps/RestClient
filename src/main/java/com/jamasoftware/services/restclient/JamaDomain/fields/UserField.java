package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.UserFieldValue;

public class UserField extends Field {
    @Override
    public FieldValue getValue() {
        UserFieldValue userFieldValue = new UserFieldValue();
        populateFieldValue(userFieldValue);
        return userFieldValue;
    }
}
