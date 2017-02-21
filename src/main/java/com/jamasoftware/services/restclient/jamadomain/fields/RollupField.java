package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.RollupFieldValue;

public class RollupField extends JamaField {
    @Override
    public RollupFieldValue getValue() {
        RollupFieldValue rollupFieldValue = new RollupFieldValue();
        populateFieldValue(rollupFieldValue);
        return rollupFieldValue;
    }

    public RollupField(String type) {
        super(type);
    }

    public RollupField() {
        super();
    }


}
