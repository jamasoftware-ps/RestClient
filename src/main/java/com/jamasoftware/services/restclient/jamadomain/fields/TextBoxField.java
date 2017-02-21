package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.TextBoxFieldValue;

public class TextBoxField extends JamaField {
    @Override
    public TextBoxFieldValue getValue() {
        TextBoxFieldValue textBoxFieldValue = new TextBoxFieldValue();
        populateFieldValue(textBoxFieldValue);
        return textBoxFieldValue;
    }

    public TextBoxField(String type) {
        super(type);
    }

    public TextBoxField() {
        super();
    }

}
