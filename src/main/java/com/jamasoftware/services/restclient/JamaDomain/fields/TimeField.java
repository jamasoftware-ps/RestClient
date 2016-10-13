package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.TimeFieldValue;

public class TimeField extends Field {
    @Override
    public TimeFieldValue getValue() {
        TimeFieldValue timeFieldValue = new TimeFieldValue();
        populateFieldValue(timeFieldValue);
        return timeFieldValue;
    }
}
