package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.TextFieldValue;

public class TextField extends JamaField {
    @Override
    public JamaFieldValue getValue() {
        TextFieldValue textFieldValue = new TextFieldValue();
        populateFieldValue(textFieldValue);
        return textFieldValue;
    }
}
