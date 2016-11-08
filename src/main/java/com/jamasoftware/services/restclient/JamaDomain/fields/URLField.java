package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.URLFieldValue;

public class URLField extends JamaField {
    @Override
    public JamaFieldValue getValue() {
        URLFieldValue urlFieldValue = new URLFieldValue();
        populateFieldValue(urlFieldValue);
        return urlFieldValue;
    }
}
