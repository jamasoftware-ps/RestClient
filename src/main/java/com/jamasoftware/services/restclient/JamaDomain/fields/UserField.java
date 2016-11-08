package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.UserFieldValue;

public class UserField extends JamaField {
    @Override
    public JamaFieldValue getValue() {
        UserFieldValue userFieldValue = new UserFieldValue();
        populateFieldValue(userFieldValue);
        return userFieldValue;
    }
}
