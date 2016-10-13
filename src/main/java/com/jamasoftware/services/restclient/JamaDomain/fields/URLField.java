package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.URLFieldValue;

public class URLField extends Field {
    @Override
    public FieldValue getValue() {
        URLFieldValue urlFieldValue = new URLFieldValue();
        populateFieldValue(urlFieldValue);
        return urlFieldValue;
    }
}
