package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.IntegerFieldValue;

public class IntegerField extends JamaField {
    @Override
    public JamaFieldValue getValue() {
        IntegerFieldValue integerFieldValue = new IntegerFieldValue();
        populateFieldValue(integerFieldValue);
        return integerFieldValue;
    }
}
