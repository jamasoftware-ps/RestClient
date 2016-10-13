package com.jamasoftware.services.restclient.JamaDomain.fields;

import com.jamasoftware.services.restclient.JamaDomain.values.FieldValue;
import com.jamasoftware.services.restclient.JamaDomain.values.ReleaseFieldValue;

public class ReleaseField extends Field {
    @Override
    public FieldValue getValue() {
        ReleaseFieldValue releaseFieldValue = new ReleaseFieldValue();
        populateFieldValue(releaseFieldValue);
        return releaseFieldValue;
    }
}
