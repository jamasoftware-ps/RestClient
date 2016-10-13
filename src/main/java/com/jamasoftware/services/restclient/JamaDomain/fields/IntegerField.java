package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.IntegerFieldValue;

public class IntegerField extends Field {
    @Override
    public FieldValue getValue() {
        IntegerFieldValue integerFieldValue = new IntegerFieldValue();
        populateFieldValue(integerFieldValue);
        return integerFieldValue;
    }
}
