package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.URLFieldValue;

public class URLField extends JamaField {
    @Override
    public URLFieldValue getValue() {
        URLFieldValue urlFieldValue = new URLFieldValue();
        populateFieldValue(urlFieldValue);
        return urlFieldValue;
    }

    @Override
    public String getType() {
        return "URL Field";
    }
}
