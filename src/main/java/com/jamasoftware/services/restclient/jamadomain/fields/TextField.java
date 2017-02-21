package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.TextFieldValue;

public class TextField extends JamaField {
    @Override
    public TextFieldValue getValue() {
        TextFieldValue textFieldValue = new TextFieldValue();
        populateFieldValue(textFieldValue);
        return textFieldValue;
    }

    public TextField(String type) {
        super(type);
    }

    public TextField() {
        super();
    }

}
