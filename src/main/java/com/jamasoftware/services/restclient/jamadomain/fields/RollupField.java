package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.RollupFieldValue;

public class RollupField extends JamaField {
    @Override
    public RollupFieldValue getValue() {
        RollupFieldValue rollupFieldValue = new RollupFieldValue();
        populateFieldValue(rollupFieldValue);
        return rollupFieldValue;
    }

    @Override
    public String getType() {
        return "Rollup Field";
    }

}
