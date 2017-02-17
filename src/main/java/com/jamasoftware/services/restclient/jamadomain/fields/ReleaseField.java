package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.ReleaseFieldValue;

public class ReleaseField extends JamaField {
    @Override
    public ReleaseFieldValue getValue() {
        ReleaseFieldValue releaseFieldValue = new ReleaseFieldValue();
        populateFieldValue(releaseFieldValue);
        return releaseFieldValue;
    }

    @Override
    public String getType() {
        return "Release Field";
    }
}
