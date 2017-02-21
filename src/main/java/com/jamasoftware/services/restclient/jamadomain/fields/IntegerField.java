package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.IntegerFieldValue;

public class IntegerField extends JamaField {
    @Override
    public IntegerFieldValue getValue() {
        IntegerFieldValue integerFieldValue = new IntegerFieldValue();
        populateFieldValue(integerFieldValue);
        return integerFieldValue;
    }

    public IntegerField(String type) {
        super(type);
    }

    public IntegerField() {
        super();
    }

}
