package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.RichTextFieldValue;

public class RichTextField extends JamaField {
    @Override
    public RichTextFieldValue getValue() {
        RichTextFieldValue richTextFieldValue = new RichTextFieldValue();
        populateFieldValue(richTextFieldValue);
        return richTextFieldValue;
    }
}
