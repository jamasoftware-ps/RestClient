package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.TextBoxFieldValue;

public class TextBoxField extends JamaField {
    @Override
    public JamaFieldValue getValue() {
        TextBoxFieldValue textBoxFieldValue = new TextBoxFieldValue();
        populateFieldValue(textBoxFieldValue);
        return textBoxFieldValue;
    }
}
