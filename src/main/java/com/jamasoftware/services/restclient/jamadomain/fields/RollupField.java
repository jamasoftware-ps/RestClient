package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.JamaFieldValue;
import com.jamasoftware.services.restclient.jamadomain.values.RollupFieldValue;

public class RollupField extends JamaField {
    @Override
    public JamaFieldValue getValue() {
        RollupFieldValue rollupFieldValue = new RollupFieldValue();
        populateFieldValue(rollupFieldValue);
        return rollupFieldValue;
    }
}
