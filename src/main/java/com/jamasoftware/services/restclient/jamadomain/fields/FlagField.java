package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.FlagFieldValue;

public class FlagField extends JamaField {
    @Override
    public FlagFieldValue getValue() {
        FlagFieldValue flagFieldValue = new FlagFieldValue();
        populateFieldValue(flagFieldValue);
        return flagFieldValue;
    }

    public FlagField(String type) {
        super(type);
    }

    public FlagField() {
        super();
    }

}
