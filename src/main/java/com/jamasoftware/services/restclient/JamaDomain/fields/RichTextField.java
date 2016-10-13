package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.RichTextFieldValue;

public class RichTextField extends Field {
    @Override
    public FieldValue getValue() {
        RichTextFieldValue richTextFieldValue = new RichTextFieldValue();
        populateFieldValue(richTextFieldValue);
        return richTextFieldValue;
    }
}
