package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.TextBoxFieldValue;

public class TextBoxField extends Field {
    @Override
    public FieldValue getValue() {
        TextBoxFieldValue textBoxFieldValue = new TextBoxFieldValue();
        populateFieldValue(textBoxFieldValue);
        return textBoxFieldValue;
    }
}
