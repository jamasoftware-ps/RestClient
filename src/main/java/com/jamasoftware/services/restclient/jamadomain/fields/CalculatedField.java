package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.CalculatedFieldValue;

public class CalculatedField extends JamaField {
    @Override
    public CalculatedFieldValue getValue() {
        CalculatedFieldValue calculatedFieldValue = new CalculatedFieldValue();
        populateFieldValue(calculatedFieldValue);
        return calculatedFieldValue;
    }

    @Override
    public String getType() {
        return "Calculated Field";
    }
}
