package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.DateFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;

public class DateField extends JamaField {
    @Override
    public JamaFieldValue getValue() {
        DateFieldValue dateFieldValue = new DateFieldValue();
        populateFieldValue(dateFieldValue);
        return dateFieldValue;
    }
}
