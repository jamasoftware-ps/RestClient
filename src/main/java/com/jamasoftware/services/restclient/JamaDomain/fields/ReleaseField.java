package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.ReleaseFieldValue;

public class ReleaseField extends JamaField {
    @Override
    public JamaFieldValue getValue() {
        ReleaseFieldValue releaseFieldValue = new ReleaseFieldValue();
        populateFieldValue(releaseFieldValue);
        return releaseFieldValue;
    }
}
