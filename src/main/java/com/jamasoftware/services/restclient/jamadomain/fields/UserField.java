package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.UserFieldValue;

public class UserField extends JamaField {
    @Override
    public UserFieldValue getValue() {
        UserFieldValue userFieldValue = new UserFieldValue();
        populateFieldValue(userFieldValue);
        return userFieldValue;
    }

    public UserField(String type) {
        super(type);
    }

    public UserField() {
        super();
    }

}
