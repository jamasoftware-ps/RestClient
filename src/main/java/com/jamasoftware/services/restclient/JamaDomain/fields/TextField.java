package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.TextFieldValue;

public class TextField extends Field {
    @Override
    public FieldValue getValue() {
        TextFieldValue textFieldValue = new TextFieldValue();
        populateFieldValue(textFieldValue);
        return textFieldValue;
    }
}
