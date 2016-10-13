package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.FlagFieldValue;

public class FlagField extends Field {
    @Override
    public FieldValue getValue() {
        FlagFieldValue flagFieldValue = new FlagFieldValue();
        populateFieldValue(flagFieldValue);
        return flagFieldValue;
    }
}
