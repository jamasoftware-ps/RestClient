package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.DateFieldValue;

public class DateField extends JamaField {
    @Override
    public DateFieldValue getValue() {
        DateFieldValue dateFieldValue = new DateFieldValue();
        populateFieldValue(dateFieldValue);
        return dateFieldValue;
    }

    public DateField(String type) {
        super(type);
    }

    public DateField() {
        super();
    }

}
