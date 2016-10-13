package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.values.DateFieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;

public class DateField extends Field {
    @Override
    public FieldValue getValue() {
        DateFieldValue dateFieldValue = new DateFieldValue();
        populateFieldValue(dateFieldValue);
        return dateFieldValue;
    }
}
